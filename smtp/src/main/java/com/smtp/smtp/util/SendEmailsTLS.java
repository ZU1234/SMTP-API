package com.smtp.smtp.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@Configuration
@PropertySource("classpath:application.properties")
public class SendEmailsTLS {

    // application.properties dosyasındaki değerleri enjekte ediyoruz
    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    @Value("${email.smtp.host}")
    private String smtpHost;

    @Value("${email.smtp.port}")
    private String smtpPort;

    @Value("${email.smtp.auth}")
    private String smtpAuth;

    @Value("${email.smtp.starttls.enable}")
    private String smtpStarttlsEnable;

    private boolean sendEmail(String to, String subject, String text) {

        // E-posta göndermek için gereken özellikleri ayarlayan bir Properties nesnesi oluşturuyoruz.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtpHost);
        prop.put("mail.smtp.port", smtpPort);
        prop.put("mail.smtp.auth", smtpAuth);
        prop.put("mail.smtp.starttls.enable", smtpStarttlsEnable);

        // E-posta oturumunu oluşturuyoruz
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // E-posta mesajını oluşturuyoruz
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); //  E-postanın gönderen adresi belirtilir. Bu adres, sizin veya uygulamanızın e-posta adresi olabilir.
            message.setRecipients(
                    Message.RecipientType.TO, //E-posta alıcıları burada belirtilir.
                    InternetAddress.parse(to) // virgülle ayrılmış alıcı e-posta adresleri eklenir. Bu alıcılar, e-posta mesajının hangi adreslere gönderileceğini belirler.
            );
            message.setSubject(subject);//E-postanın konusu belirtilir. Bu kısım, e-postanın ne hakkında olduğunu özetler. Gerçek uygulamada, e-postanın içeriğiyle uyumlu bir konu seçmek önemlidir.
            message.setText(text); //E-postanın içeriği burada belirtilir.

            // E-postayı gönderiyoruz
            Transport.send(message);

            System.out.println("Done");  // Gönderme işlemi başarılı olursa "Done" yazdırılır
            return true;

        } catch (MessagingException e) {
            e.printStackTrace(); // Gönderme işlemi sırasında bir hata oluşursa hata mesajı yazdırılır
            return false;
        }
    }

    public boolean send(String uid, String subject, String message) {
        return sendEmail(uid, subject, message);
    }

}
