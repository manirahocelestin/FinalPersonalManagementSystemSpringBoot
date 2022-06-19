package com.manager.information.controller;

import com.manager.information.domain.Email;
import com.manager.information.domain.ForgetPassword;
import com.manager.information.domain.ResetTokenPassword;
import com.manager.information.domain.User;
import com.manager.information.repository.UserRepository;
import com.manager.information.service.IEmailService;
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
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/forget-password")

public class ForgetPasswordControllerUI {

    private final UserRepository userService;
    private final IResetTockenPasswordService resetTockenPasswordService;
    private final IEmailService emailService;
    private final MessageSource messageSource;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ForgetPasswordControllerUI(UserRepository userService, IResetTockenPasswordService resetTockenPasswordService, IEmailService emailService, MessageSource messageSource) {
        this.userService = userService;
        this.resetTockenPasswordService = resetTockenPasswordService;
        this.emailService = emailService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public String viewPage(){
        return "forgot-password";
    }

    @PostMapping

    public String processPasswordForgot(@Valid @ModelAttribute("passwordForgot") ForgetPassword forgetPassword ,
                                        BindingResult result, Model model, RedirectAttributes attributes,
                                        HttpServletRequest request){
        if (result.hasErrors()){
            logger.error("An error has occured while fetching the user "+result.hasErrors());
            return "forgot-password";
        }
        User userRegistration = userService.findByEmail(forgetPassword.getEmail());
        if (userRegistration == null){
            logger.warn("you couldn't find user with this email " +userRegistration);
            model.addAttribute("emailError",messageSource.getMessage
                    ("We couldn't find any account for that email address",new Object[]{}, Locale.ENGLISH));
            return "forgot-password";
        }
        // process to send email with this link to reset a password to this email address

        ResetTokenPassword token = new ResetTokenPassword();
        token.setUserRegistration(userRegistration);
        token.setToken(UUID.randomUUID().toString());
        token.setExpirationDate(LocalDateTime.now().minusMinutes(5));
        token = resetTockenPasswordService.saveToken(token);


        if (token == null){
            logger.warn("Token couldn't find with this user "+ token);
            model.addAttribute("tokenError",messageSource.getMessage(
                    "Any Error occured while creating the token for reseting your password please try  ",new Object[]{},
                    Locale.ENGLISH));
            return "/forgot-password";

        }

        Email email = new Email();
        email.setFrom("celestinmani@gamil.com");
        email.setTo(userRegistration.getEmail());
        email.setSubject("question password");
        Map<String,Object> mailModel = new HashMap<>();
        mailModel.put("token",token);
        mailModel.put("userRegistration",userRegistration);
        String url = request.getScheme() + "://"  + request.getServerPort();
        mailModel.put("resetUrl",url + "/reset-password?token= " + token.getToken());
        email.setModel(mailModel);

        // send email using email service if email send sucessfully redirect with flash attribute
        emailService.sendEmail(email);
        attributes.addFlashAttribute("success",messageSource.getMessage
                ("Any email has been sent to your email address with a link to reset with your password",
                        new Object[]{}, Locale.ENGLISH));
        return "redirect:/forget-password";

    }
    @ModelAttribute("forget-password")
    public ForgetPassword forgetPassword(){
        return new ForgetPassword();
    }

}
