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

    @Override
    public void sendAdminAccountRecoverEmail(String emailTo, String password) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            String context = createAdminAccountRecoverEmailEmailBody(password);
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(emailTo);
            helper.setFrom(from);
            helper.setSubject("Snitch - admin account new password!");
            helper.setText(context, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }

    @Override
    public void sendVictimEmail(String emailTo) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            String context = templateEngine.process("snitchAddVictim",new Context());
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(emailTo);
            helper.setFrom(from);
            helper.setSubject("Snitch - someone just denounced you!");
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

    @Override
    public void sendResultsEmail(String emailTo, String context) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(emailTo);
            helper.setFrom(from);
            helper.setSubject("Snitch - monthly results!");
            helper.setText(context, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);

    }
}
