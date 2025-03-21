package com.yelman.cloudserver.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;


@Entity
@Table(name = "chewing_activity")

public class ChewingActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_chewing_animal"))
    private Animal animal;
    @Column(name = "chew_count", nullable = false)
    private int chewCount;
    @CreationTimestamp
    @Column(name = "recorded_at")
    private OffsetDateTime recordedAt;

    public ChewingActivity(Long id, Animal animal, int chewCount, OffsetDateTime recordedAt) {
        this.id = id;
        this.animal = animal;
        this.chewCount = chewCount;
        this.recordedAt = recordedAt;
    }

    public ChewingActivity() {

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

    public int getChewCount() {
        return chewCount;
    }

    public void setChewCount(int chewCount) {
        this.chewCount = chewCount;
    }

    public OffsetDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(OffsetDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}
