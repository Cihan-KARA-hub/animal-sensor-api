package com.yelman.cloudserver.utils.mail;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class EmailScheduler {
    private JdbcTemplate jdbcTemplate;
    private EmailService emailService;

    @Scheduled(fixedRate = 30000)
    private void checkDatabaseAndSendEmail() {
        String sql = "SELECT email, message FROM notifications WHERE status = 'PENDING'";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : list) {
            String email = (String) row.get("email");
            String message = (String) row.get("message");

            // E-posta gönder
            emailService.sendEmail(email, "Bildirim", message);

            // Gönderilen mesajı güncelle (örneğin, status = 'SENT' yap)
            jdbcTemplate.update("UPDATE notifications SET status = 'SENT' WHERE email = ?", email);
        }
    }
}
