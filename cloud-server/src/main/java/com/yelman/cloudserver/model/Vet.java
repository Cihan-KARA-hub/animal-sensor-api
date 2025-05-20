package com.yelman.cloudserver.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;


@Entity
@Table(name = "vet")
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_vet", nullable = false, foreignKey = @ForeignKey(name = "fk_users"))
    private Users user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_company", nullable = false, foreignKey = @ForeignKey(name = "fk_company"))
    private Company responsibleCompany;
    @Column(name = "title", length = 255)
    private String title;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public Vet(String name, Users user, Company responsibleCompany, String title) {

        this.name = name;
        this.user = user;
        this.responsibleCompany = responsibleCompany;
        this.title = title;

    }

    public Vet() {

    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Company getResponsibleCompany() {
        return responsibleCompany;
    }

    public void setResponsibleCompany(Company responsibleCompany) {
        this.responsibleCompany = responsibleCompany;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}


