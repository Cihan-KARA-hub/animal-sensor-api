package com.yelman.cloudserver.api.dto;

public class SensorDto {
    Long animalId;
    Integer chewingActivity;
    Integer heartBeat;
    Double temperatureHumidity;
    Double humidity;

    public SensorDto(Long animalId, Integer chewingActivity, Integer heartBeat, Double temperatureHumidity, Double humidity) {
        this.animalId = animalId;
        this.chewingActivity = chewingActivity;
        this.heartBeat = heartBeat;
        this.temperatureHumidity = temperatureHumidity;
        this.humidity = humidity;
    }

    public Double getTemperatureHumidity() {
        return temperatureHumidity;
    }

    public void setTemperatureHumidity(Double temperatureHumidity) {
        this.temperatureHumidity = temperatureHumidity;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Integer getChewingActivity() {
        return chewingActivity;
    }

    public void setChewingActivity(Integer chewingActivity) {
        this.chewingActivity = chewingActivity;
    }

    public Integer getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(Integer heartBeat) {
        this.heartBeat = heartBeat;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }


}
