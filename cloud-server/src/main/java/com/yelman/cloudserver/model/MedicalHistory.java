package com.yelman.cloudserver.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "medical_history")

public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_medical_animal"))
    private Animal animal;

    @Column(name = "disease_name", nullable = false, length = 100)
    private String diseaseName;

    @Column(name = "diagnosis_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date diagnosisDate;

    @Column(name = "recovery_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recoveryDate;

    @Column(name = "treatment", columnDefinition = "TEXT")
    private String treatment;

    @Column(name = "veterinarian", length = 100)
    private String veterinarian;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public MedicalHistory(Long id, Animal animal, String diseaseName, Date diagnosisDate, Date recoveryDate, String treatment, String veterinarian, OffsetDateTime createdAt) {
        this.id = id;
        this.animal = animal;
        this.diseaseName = diseaseName;
        this.diagnosisDate = diagnosisDate;
        this.recoveryDate = recoveryDate;
        this.treatment = treatment;
        this.veterinarian = veterinarian;
        this.createdAt = createdAt;
    }

    public MedicalHistory() {

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

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public Date getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(Date recoveryDate) {
        this.recoveryDate = recoveryDate;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
