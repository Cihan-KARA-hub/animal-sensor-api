package com.yelman.cloudserver.api.dto;

import com.yelman.cloudserver.model.ChewingActivity;
import com.yelman.cloudserver.model.HeartBeat;
import com.yelman.cloudserver.model.TemperatureHumidity;
import org.springframework.data.domain.Page;

public class AnimalHealthDto {
    private Page<HeartBeat> heartBeats;
    private Page<TemperatureHumidity> temperatureHumidities;
    private Page<ChewingActivity> chewingActivities;

    public Page<HeartBeat> getHeartBeats() {
        return heartBeats;
    }

    public void setHeartBeats(Page<HeartBeat> heartBeats) {
        this.heartBeats = heartBeats;
    }

    public Page<TemperatureHumidity> getTemperatureHumidities() {
        return temperatureHumidities;
    }

    public void setTemperatureHumidities(Page<TemperatureHumidity> temperatureHumidities) {
        this.temperatureHumidities = temperatureHumidities;
    }

    public Page<ChewingActivity> getChewingActivities() {
        return chewingActivities;
    }
    ;

    public void setChewingActivities(Page<ChewingActivity> chewingActivities) {
        this.chewingActivities = chewingActivities;
    }
}
