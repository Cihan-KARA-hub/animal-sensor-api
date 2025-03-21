package com.yelman.cloudserver.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MedicalHistoryDto {
    private String treatment;

    private String veterinarian;
    private Long animalId;
    private String diseaseName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date diagnosisDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recoveryDate;


    public MedicalHistoryDto(Long animalId, String diseaseName, Date diagnosisDate, Date recoveryDate, String treatment, String veterinarian) {
        this.animalId = animalId;
        this.diseaseName = diseaseName;
        this.diagnosisDate = diagnosisDate;
        this.recoveryDate = recoveryDate;
        this.treatment = treatment;
        this.veterinarian = veterinarian;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
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
}
