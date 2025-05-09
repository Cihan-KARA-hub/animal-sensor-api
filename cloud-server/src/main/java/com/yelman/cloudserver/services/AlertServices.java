package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.AlertDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.model.Vet;
import com.yelman.cloudserver.services.impl.AlertsImpl;
import com.yelman.cloudserver.utils.fuzzy.HealthCheck;
import com.yelman.cloudserver.utils.mail.EmailNotificationService;
import org.springframework.stereotype.Service;

@Service
public class AlertServices implements AlertsImpl {

    private final EmailNotificationService emailService;

    public AlertServices(EmailNotificationService emailService) {
        this.emailService = emailService;
    }

    @Override
    public boolean emailManager(Vet vet, SensorDto dto, boolean dailyOrHourly, String riskSituation) {
        try {
            AlertDto c = toEntity(dto);
            Double a;
            a = dailyActiveEmail(c);
            if (a > 80) {
                emailService.sendEmail(vet, dto, false, riskSituation);
            }
            if (a > 55) {
                emailService.sendEmail(vet, dto, false, riskSituation);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
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
