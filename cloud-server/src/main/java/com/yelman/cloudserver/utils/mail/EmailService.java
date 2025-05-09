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
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailUsername;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleMail(EmailSendDto details)  {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-16");

            helper.setFrom(emailUsername);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            helper.setText(details.getMsgBody(), true);

            mailSender.send(message);
            System.out.println("✅ HTML Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error while sending HTML email.");
        }
    }
    public void sendHtmlMail(EmailSendDto details, byte[] pdfBytes) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailUsername);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());

            // ✅ Null kontrolü: Eğer body null gelirse boş string yapar
            String safeBody = details.getMsgBody() != null ? details.getMsgBody() : "";
            helper.setText(safeBody, true);

            // ✅ PDF eklenecekse null değilse ekle
            if (pdfBytes != null) {
                helper.addAttachment("weekly_report.pdf", new ByteArrayDataSource(pdfBytes, "application/pdf"));
            }

            mailSender.send(message);
            System.out.println("✅ HTML Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error while sending HTML email.");
        }
    }

}
