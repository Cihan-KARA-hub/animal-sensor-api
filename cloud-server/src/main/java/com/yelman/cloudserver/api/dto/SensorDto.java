package com.yelman.cloudserver.api.dto;

public class SensorDto {
    Long animalId;
    Integer chewingActivity;
    Integer heartBeat;
    Double temperature;
    Double humidity;

    public SensorDto(Long animalId, Integer chewingActivity, Integer heartBeat, Double temperatureHumidity, Double humidity) {
        this.animalId = animalId;
        this.chewingActivity = chewingActivity;
        this.heartBeat = heartBeat;
        this.temperature = temperatureHumidity;
        this.humidity = humidity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperatureHumidity) {
        this.temperature = temperatureHumidity;
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
