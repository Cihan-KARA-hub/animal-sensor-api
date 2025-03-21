package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.model.TemperatureHumidity;

import java.util.List;

public interface TemperatureHumidityImpl {

    //
    TemperatureHumidity addTemperatureHumidity(TemperatureHumidity temp);

    List<TemperatureHumidity> getTemperatureHumidity();

    TemperatureHumidity getTemperatureHumidity(Long id);

    TemperatureHumidity updateTemperatureHumidity();

    TemperatureHumidity deleteTemperatureHumidity();
}
