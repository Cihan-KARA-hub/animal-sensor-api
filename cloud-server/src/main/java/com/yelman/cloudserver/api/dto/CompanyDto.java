package com.yelman.cloudserver.api.dto;

public class CompanyDto {
    private String name;
    private Long userId;

    public CompanyDto(String name, Long userId) {
        this.name = name;
        this.userId = userId;
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
}
