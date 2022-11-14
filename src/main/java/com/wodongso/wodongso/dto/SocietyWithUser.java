package com.wodongso.wodongso.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SocietyWithUser {

    @Id
    private Integer number;
    private String university;
    private String userName;
    private String societyName;
    private String category;
    private String simpleDesc;
    private String detailDesc;
    private Integer enabled;

    public SocietyWithUser() {
    }

    public SocietyWithUser(Integer number, String university, String userName, String societyName, String category, String simpleDesc, String detailDesc, Integer enabled) {
        this.number = number;
        this.university = university;
        this.userName = userName;
        this.societyName = societyName;
        this.category = category;
        this.simpleDesc = simpleDesc;
        this.detailDesc = detailDesc;
        this.enabled = enabled;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSimpleDesc() {
        return simpleDesc;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }


}
