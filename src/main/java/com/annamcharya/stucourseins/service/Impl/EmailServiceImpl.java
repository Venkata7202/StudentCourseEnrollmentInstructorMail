package com.annamcharya.stucourseins.service.Impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl  {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEnrollmentConfirmationEmail(String recipientEmail, String recipientType) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipientEmail);
        helper.setSubject("Enrollment Confirmation");

        if ("student".equalsIgnoreCase(recipientType)) {
            helper.setText("Dear Student, Your enrollment has been successfully confirmed.");
        } else if ("instructor".equalsIgnoreCase(recipientType)) {
            helper.setText("Dear Instructor, A student has enrolled in your course.");
        }

        javaMailSender.send(message);
    }
}
