package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.AlertDto;
import com.yelman.cloudserver.api.dto.EmailSendDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.model.Vet;
import com.yelman.cloudserver.services.impl.AlertsImpl;
import com.yelman.cloudserver.utils.fuzzy.HealthCheck;
import com.yelman.cloudserver.utils.mail.mails.EmailServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AlertServices implements AlertsImpl {

    private final EmailServiceImpl emailService;

    public AlertServices(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @Override
    public boolean emailManager(Vet vet, SensorDto dto, boolean dailyOrHourly, String riskSituation) {
        AlertDto c = toEntity(dto);
        Double a;
        a = dailyActiveEmail(c);
        EmailSendDto emailSendDto = new EmailSendDto();
        emailSendDto.setRecipient(vet.getUser().getEmail());
        emailSendDto.setSubject(" %" + riskSituation + "ihtimalle ilgili hayvan riskte !!");
        emailSendDto.setMsgBody("Hayvan id'si :" + dto.getAnimalId().toString() + "\n" +
                "Kalp Atış hızı : " + dto.getHeartBeat() + "\n" +
                "Vücut Sıcaklıgı : " + dto.getTemperature() + "\n"
        );


        if (a > 80) {
            emailService.sendSimpleMail(emailSendDto);
        }
        if (a > 65) {
            emailService.sendSimpleMail(emailSendDto);
        }
        return true;
    }


    private Double dailyActiveEmail(AlertDto dto) {
        return HealthCheck.fuzzyLogicGeneral(dto);
    }

    private AlertDto toEntity(SensorDto dto) {
        AlertDto c = new AlertDto(dto.getTemperature(), dto.getHeartBeat(), dto.getChewingActivity(), dto.getHumidity());
        c.setHeart(dto.getHeartBeat());
        c.setHumidity(dto.getHumidity());
        c.setTemp(dto.getTemperature());
        c.setRumination(dto.getChewingActivity());
        return c;
    }


}
