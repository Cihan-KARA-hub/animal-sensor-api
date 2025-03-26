package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.AlertDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.model.Vet;
import com.yelman.cloudserver.services.impl.AlertsImpl;
import com.yelman.cloudserver.utils.fuzzy.DailyHealthCheck;
import com.yelman.cloudserver.utils.fuzzy.HourlyHealthCheck;
import com.yelman.cloudserver.utils.mail.EmailNotificationService;


import java.io.IOException;


public class AlertServices implements AlertsImpl {
        EmailNotificationService emailNotificationService;

    @Override
    public boolean emailManager(Vet vet, SensorDto dto, boolean dailyOrHourly)  {
        AlertDto c = toEntity(dto);
        String riskSituation;
        //Hourly true
        Double a;
        if (dailyOrHourly) {
            a = hourlyActiveEmail(c);

        } else {
            a = dailyActiveEmail(c);
        }
        if (a > 80) {
            riskSituation = " Kritik";
            try {
                emailNotificationService.sendEmail(vet, dto, dailyOrHourly, riskSituation);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ;
        if (a > 65) {
            riskSituation = "Riskli olabilir ";
            try {
                emailNotificationService.sendEmail(vet, dto, dailyOrHourly, riskSituation);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    private Double hourlyActiveEmail(AlertDto dto) {
        return HourlyHealthCheck.hourlyHealthCheck(dto);
    }

    private Double dailyActiveEmail(AlertDto dto) {
        return DailyHealthCheck.fuzzyLogicGeneral(dto);
    }

    private AlertDto toEntity(SensorDto dto) {
        AlertDto c = new AlertDto();
        c.setHeart(dto.getHeartBeat());
        c.setHumidity(dto.getHumidity());
        c.setTemp(dto.getTemperatureHumidity());
        c.setRumination(dto.getChewingActivity());
        return c;
    }


}
