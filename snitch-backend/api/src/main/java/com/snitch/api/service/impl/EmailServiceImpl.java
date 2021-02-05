package com.snitch.api.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.snitch.api.service.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    private final String subject = "Snitch - admin account new password";

    @Override
    public void sendAdminAccountRecoverEmail(String emailTo, String password) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            String context = createAdminAccountRecoverEmailEmailBody(password);
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(emailTo);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(context, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }

    private String createAdminAccountRecoverEmailEmailBody(String password) {
        Context context = new Context();
        context.setVariable("password", password);
        return templateEngine.process("adminAccountRecover", context);
    }
}
