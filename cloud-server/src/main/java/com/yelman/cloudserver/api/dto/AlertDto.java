package com.yelman.cloudserver.api.dto;

public class AlertDto {
    double temp;
    int heart;
    int rumination;
    Double humidity;

    public AlertDto(double temp, int heart, int rumination, Double humidity) {
        this.temp = temp;
        this.heart = heart;
        this.rumination = rumination;
        this.humidity = humidity;
    }

    public AlertDto() {

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

    public int getRumination() {
        return rumination;
    }

    public void setRumination(int rumination) {
        this.rumination = rumination;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
