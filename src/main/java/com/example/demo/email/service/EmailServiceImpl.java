package com.example.demo.email.service;

import com.example.demo.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${my.list.of.email}")
    private List<String> myList;

    public String sendEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("think3pad3@gmail.com");
            message.setTo(myList.toArray(new String[0]));
            message.setSubject("Test Subject");
            message.setText("Test Body");

            javaMailSender.send(message);

            return "Mail send successfully";
        } catch (Exception e) {
            return "Mail sent failed";
        }
    }

    public String sendEmailError() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("think3pad3@gmail.com");
            message.setTo(myList.toArray(new String[0]));
            message.setSubject("Test Error");
            message.setText("Mistakes have been made");

            javaMailSender.send(message);

            return "Error mail send successfully";
        } catch (Exception e) {
            return "Error mail sent failed";
        }
    }

    public String sendEmailWithAttachment() {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setFrom("think3pad3@gmail.com");
            messageHelper.setTo("think3pad3@gmail.com");
            messageHelper.setSubject("Test Subject");
            messageHelper.setText("Test Body");

            File file = new File("/home/hive/Desktop/Halo.text");

            messageHelper.addAttachment(file.getName(), file);

            javaMailSender.send(message);

            return "Mail sent successfully";

        } catch (Exception e) {
            return "Attachment mail sent failed";
        }
    }
}
