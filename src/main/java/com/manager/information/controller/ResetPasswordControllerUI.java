package com.manager.information.controller;

import com.manager.information.domain.ResetPassword;
import com.manager.information.domain.ResetTokenPassword;
import com.manager.information.domain.User;
import com.manager.information.repository.UserRepository;
import com.manager.information.service.IResetTockenPasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Locale;

@Controller
@RequestMapping("/reset-password")
public class ResetPasswordControllerUI {

    private final UserRepository userService;
    private final IResetTockenPasswordService resetTockenPasswordService;
    private final MessageSource messageSource;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired

    public ResetPasswordControllerUI(UserRepository userService, IResetTockenPasswordService resetTockenPasswordService, MessageSource messageSource) {
        this.userService = userService;
        this.resetTockenPasswordService = resetTockenPasswordService;
        this.messageSource = messageSource;
    }
    @GetMapping
    public String viewPage(@RequestParam(name = "token", required = false) String token,
                           Model model) {
        ResetTokenPassword resetTokenPassword = resetTockenPasswordService.findByToken(token);
        if (resetTokenPassword == null) {
            logger.warn("Could not Find this token" + resetTokenPassword);
            model.addAttribute("error", messageSource.getMessage("Could not find password reset token.", new Object[]{}, Locale.ENGLISH));
        } else if (resetTokenPassword.getExpirationDate().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", messageSource.getMessage("Token has expired, please request a new password reset.", new Object[]{}, Locale.ENGLISH));
        } else {
            model.addAttribute("token", resetTokenPassword.getToken());
        }
        return "/reset-password";
    }

    @PostMapping
    public String resetPassword(@Valid @ModelAttribute("passwordReset") ResetPassword passwordReset,
                                BindingResult result,
                                RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("passwordReset", passwordReset);
            return "redirect:/reset-password?token=" + passwordReset.getToken();
        }
        ResetTokenPassword token = resetTockenPasswordService.findByToken(passwordReset.getToken());
        User user = token.getUserRegistration();
        user.setPassword(passwordReset.getPassword());
        userService.save(user);
        return "redirect:/login";
    }

    @ModelAttribute("passwordReset")
    public ResetPassword passwordReset() {
        return new ResetPassword();
    }

}
