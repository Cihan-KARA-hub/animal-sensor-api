package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.model.Vet;

import java.io.IOException;

public interface AlertsImpl {
     boolean emailManager(Vet email, SensorDto dto, boolean dailyOrHourly, String riskSituation) throws IOException;

}
