package com.yelman.cloudserver.api.dto;

public class VetDto {
    private String name;
    private Long userId;
    private Long companyId;
    private String title;
    public VetDto(String name, Long userId, Long companyId, String title) {
        this.name = name;
        this.userId = userId;
        this.companyId = companyId;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
