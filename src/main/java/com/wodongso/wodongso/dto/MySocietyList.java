package com.wodongso.wodongso.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MySocietyList {


    @Id
    private String userId;
    private String userName;
    private String societyName;
    private String societyProfileUrl;

    public MySocietyList() {
    }

    public MySocietyList(String userId, String userName, String societyName, String societyProfileUrl) {
        this.userId = userId;
        this.userName = userName;
        this.societyName = societyName;
        this.societyProfileUrl = societyProfileUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getSocietyProfileUrl() {
        return societyProfileUrl;
    }

    public void setSocietyProfileUrl(String societyProfileUrl) {
        this.societyProfileUrl = societyProfileUrl;
    }
}
