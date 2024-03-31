package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.validator.EmailValidation;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmailSenderService {

    private JavaMailSender javaMailSender;
    private EmailValidation emailValidation;

    public void sendEmailTo(String toEmail,
                            String subject,
                            String body) {

        sendMail("yavuzkarapinar0@gmail.com",
                toEmail,
                subject,
                body);

        LocalDateTime.now();
    }

    public void sendEmailFrom(String fromTo,
                              String subject,
                              String body) {

        sendMail(fromTo,
                "yavuzkarapinar0@gmail.com",
                subject,
                body);

        LocalDateTime.now();
    }

    private void sendMail(String from,
                          String to,
                          String subject,
                          String body) {
        boolean isValidFrom = emailValidation.test(from);
        boolean isValidTo = emailValidation.test(to);

        if(!isValidFrom || !isValidTo) throw new RuntimeException("Wrong Email");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);

    }

}