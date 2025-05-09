package com.yelman.cloudserver.api.dto;

import jakarta.annotation.Nullable;

public class AlertDto {
    double temp;
    int heart;
    double rumination;
    @Nullable()
    Double humidity;

    public AlertDto(double temp, int heart, double rumination, Double humidity) {
        this.temp = temp;
        this.heart = heart;
        this.rumination = rumination;
        this.humidity = humidity;
    }


    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public double getRumination() {
        return rumination;
    }

    public void setRumination(double rumination) {
        this.rumination = rumination;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
