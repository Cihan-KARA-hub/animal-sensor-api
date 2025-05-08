package com.yelman.cloudserver.utils.mail;

import com.yelman.cloudserver.api.dto.EmailSendDto;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailUsername;
    public void sendSimpleMail(EmailSendDto details) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailUsername);
            message.setTo(details.getRecipient());
            message.setSubject(details.getSubject());
            message.setText(details.getMsgBody());

            mailSender.send(message);
            ResponseEntity.ok("Email sent");
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    public void sendHtmlMail(EmailSendDto details, byte[] pdfBytes) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailUsername);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            helper.addAttachment("weekly_report.pdf", new ByteArrayDataSource(pdfBytes, "application/pdf"));
            helper.setText(details.getMsgBody(), true);

            mailSender.send(message);
            ResponseEntity.ok("HTML Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().body("Error while sending HTML email.");
        }
    }
}
