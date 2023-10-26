package com.smtp.smtp;

import com.smtp.smtp.util.SendEmailsTLS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmtpApplication {
    private static SendEmailsTLS sendEmailsTLS;

    public static void main(String[] args) {
        SpringApplication.run(SmtpApplication.class, args);

    }

}
