package com.wodongso.wodongso.dto;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class SocietyRecruitWithUser {

    @Id
    private String userId;
    private Integer societyNumber;
    private String name;
    private Integer state;
    private String rejectReason;
    private LocalDateTime createdAt;

    public SocietyRecruitWithUser() {

    }

    public SocietyRecruitWithUser(String userId, Integer societyNumber, String name, Integer state, String rejectReason, LocalDateTime createdAt) {
        this.userId = userId;
        this.societyNumber = societyNumber;
        this.name = name;
        this.state = state;
        this.rejectReason = rejectReason;
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSocietyNumber() {
        return societyNumber;
    }

    public void setSocietyNumber(Integer societyNumber) {
        this.societyNumber = societyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
