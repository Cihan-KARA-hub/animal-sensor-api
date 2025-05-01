package com.yelman.cloudserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "heart_beat")

public class HeartBeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_respiration_animal"))
    private Animal animal;

    @Column(name = "heart_beat_rate", nullable = false)
    private int heartBeatRate;
    @CreationTimestamp
    @Column(name = "recorded_at")
    private OffsetDateTime recordedAt;

    public HeartBeat(Long id, Animal animal, int heartBeatRate, OffsetDateTime recordedAt) {
        this.id = id;
        this.animal = animal;
        this.heartBeatRate = heartBeatRate;
        this.recordedAt = recordedAt;
    }

    public HeartBeat() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public int getHeartBeatRate() {
        return heartBeatRate;
    }

    public void setHeartBeatRate(int heartBeatRate) {
        this.heartBeatRate = heartBeatRate;
    }

    public OffsetDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(OffsetDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}
