package com.manager.information.service;

import com.manager.information.domain.Email;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
@Service
@Transactional
public class EmailServiceImp implements IEmailService{
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    public EmailServiceImp(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine) {
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    public void sendEmail(Email email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariables(email.getModel());
            String html = springTemplateEngine.process("email/email-template",context);
            helper.setTo(email.getTo());
            helper.setFrom(email.getFrom());
            helper.setSubject(email.getSubject());
            helper.setText(html,true);
            javaMailSender.send(mimeMessage);

        }catch (MessagingException e){
            e.printStackTrace();

        }

    }
}
