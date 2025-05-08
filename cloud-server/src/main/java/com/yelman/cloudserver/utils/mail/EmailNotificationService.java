package com.yelman.cloudserver.utils.mail;

import com.yelman.cloudserver.api.dto.EmailSendDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.model.Vet;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class EmailNotificationService {
    private   EmailService emailService;
    public void sendEmail(Vet vet, SensorDto dto, boolean dailyOrHourly, String riskSituation) throws IOException {
        if (vet == null || vet.getUser() == null || vet.getResponsibleCompany() == null ||
                vet.getResponsibleCompany().getUser() == null) {
            throw new IllegalArgumentException("Vet veya Company bilgileri eksik!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss");
        String formattedDate = sdf.format(new Date());

        Map<String, String> emailData = new HashMap<>();
        emailData.put("farmName", vet.getResponsibleCompany().getName());
        emailData.put("temperature", String.valueOf(dto.getTemperature()));
        emailData.put("heartRate", String.valueOf(dto.getHeartBeat()));
        emailData.put("dataType", dailyOrHourly ? "Saatlik verilerdir !!" : "Günlük verilerilerdir !!");
        emailData.put("chewingActivity", String.valueOf(dto.getChewingActivity()));
        emailData.put("humidity", String.valueOf(dto.getHumidity()));
        emailData.put("date", formattedDate);


        String formattedEmail = EmailTemplateUtil.getFormattedEmail(emailData);
        sendEmailToUser(vet.getUser().getEmail(), dto.getAnimalId(), riskSituation, formattedEmail);
        sendEmailToUser(vet.getResponsibleCompany().getUser().getEmail(), dto.getAnimalId(), riskSituation, formattedEmail);
    }

    private void sendEmailToUser(String recipient, Long animalId, String riskSituation, String emailContent) {
        if (recipient == null || recipient.isEmpty()) {
            return;
        }

        EmailSendDto emailDto = new EmailSendDto();
        emailDto.setRecipient(recipient);
        emailDto.setSubject(animalId + " - Hayvan Sağlık Durumu (" + riskSituation + ")");
        emailDto.setMsgBody(emailContent);

        emailService.sendSimpleMail(emailDto);
    }

    private void sendEmailToUserAtch(String recipient,byte[] pdf) {
        if (recipient == null || recipient.isEmpty()) {
            return;
        }
        EmailSendDto emailDto = new EmailSendDto();
        emailDto.setRecipient(recipient);
        emailDto.setSubject("Haftalık Saglık raporunuz ektedir .");
        emailService.sendHtmlMail(emailDto,pdf);
    }
    public void sendEmailAtc(byte[] pdf,String email ){
        sendEmailToUserAtch(email,pdf);
    }
}
