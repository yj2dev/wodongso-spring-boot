package com.wodongso.wodongso.dto;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class SocietyCreateWithUser {

    @Id
    private String userId;
    private Integer societyNumber;
    private String societyName;
    private String simpleDesc;
    private Integer state;
    private String rejectReason;
    private LocalDateTime createdAt;

    public SocietyCreateWithUser() {

    }

    public SocietyCreateWithUser(String userId, Integer societyNumber, String societyName, String simpleDesc, Integer state, String rejectReason, LocalDateTime createdAt) {
        this.userId = userId;
        this.societyNumber = societyNumber;
        this.societyName = societyName;
        this.simpleDesc = simpleDesc;
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

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getSimpleDesc() {
        return simpleDesc;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
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
