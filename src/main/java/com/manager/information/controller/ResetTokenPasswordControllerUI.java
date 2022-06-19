package com.manager.information.controller;
import com.manager.information.domain.ResetPassword;
import com.manager.information.domain.ResetTokenPassword;
import com.manager.information.domain.User;
import com.manager.information.repository.UserRepository;
import com.manager.information.service.IResetTockenPasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/reset-Password")
public class ResetTokenPasswordControllerUI {
    private final IResetTockenPasswordService resetTockenPasswordService;
    private final UserRepository userService;
    private final MessageSource messageSource;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResetTokenPasswordControllerUI(IResetTockenPasswordService resetTockenPasswordService, UserRepository userService, MessageSource messageSource) {
        this.resetTockenPasswordService = resetTockenPasswordService;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public String pageView(@RequestParam(name = "token",required = false) String token, Model model){
        ResetTokenPassword resetTokenPassword = resetTockenPasswordService.findByToken(token);
        if(resetTokenPassword == null){
            logger.error("Couldn't find this token " + resetTokenPassword);
            model.addAttribute("error",messageSource.getMessage("Couldn't find password reset token",new Object[]{}, Locale.ENGLISH));


        }else if (resetTokenPassword.getExpirationDate().isBefore(LocalDateTime.now())){
            model.addAttribute("Error",messageSource.getMessage("token has Expired.Please request new password reset",new Object[]{},Locale.ENGLISH));

        }else {
            model.addAttribute("token", resetTokenPassword.getToken());
        }
        return "resetPassword";


    }
    @PostMapping
    public String resetPassword(@Valid @ModelAttribute("password-token") ResetPassword resetPassword, RedirectAttributes redirectAttributes, BindingResult result){
        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute("passwordreset",resetPassword);
            return "redirect:/reset-password?token"+resetPassword.getToken();
        }

        ResetTokenPassword  token = resetTockenPasswordService.findByToken(resetPassword.getToken());
        User userRegistration = token.getUserRegistration();
        userRegistration.setPassword(resetPassword.getPassword());
        userService.save(userRegistration);
        return "redirect:/login";

    }
    @ModelAttribute("/passwordReset")
    public ResetPassword resetPassword(){
        return new ResetPassword();

    }


}
