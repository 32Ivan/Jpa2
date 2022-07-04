package com.example.demo.email.emailcontroller;

import com.example.demo.email.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    EmailServiceImpl emailService;

    @GetMapping("sendEmail")
    public String sendEmail() {
        return emailService.sendEmail();
    }

    @GetMapping("/sendEmailAttachment")
    public String sendEmailWithAttachment() {
        return emailService.sendEmailWithAttachment();
    }

}
