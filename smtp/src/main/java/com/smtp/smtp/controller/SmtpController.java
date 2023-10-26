package com.smtp.smtp.controller;

import com.smtp.smtp.util.SendEmailsTLS;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmtpController {
    private SendEmailsTLS sendEmailsTLS;

    public SmtpController(SendEmailsTLS sendEmailsTLS) {
        this.sendEmailsTLS = sendEmailsTLS;
    }

    @GetMapping(value = "/")
    public void send() {
        String to = "your@gmail.com";
        String subject = "Testing Gmail TLS";
        String messge = "Dear Mail Crawler,"
                + "\n\n Please do not spam my email!";
        sendEmailsTLS.send(to, subject, messge);
    }


}
