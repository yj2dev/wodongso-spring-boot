package com.wodongso.wodongso.dto;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class SocietyRecruitWithUserOfficer {

    @Id
    private Integer uId;
    private Integer societyNumber;
    private String userId;
    private String userName;
    private String userUniversity;
    private String userProfileUrl;
    private String userMajor;
    private String userContact;
    private Integer state;
    private String rejectReason;
    private LocalDateTime createdAt;

    public SocietyRecruitWithUserOfficer() {

    }

    public SocietyRecruitWithUserOfficer(Integer uId, Integer societyNumber, String userId, String userName, String userUniversity, String userProfileUrl, String userMajor, String userContact, Integer state, String rejectReason, LocalDateTime createdAt) {
        this.uId = uId;
        this.societyNumber = societyNumber;
        this.userId = userId;
        this.userName = userName;
        this.userUniversity = userUniversity;
        this.userProfileUrl = userProfileUrl;
        this.userMajor = userMajor;
        this.userContact = userContact;
        this.state = state;
        this.rejectReason = rejectReason;
        this.createdAt = createdAt;
    }


    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getSocietyNumber() {
        return societyNumber;
    }

    public void setSocietyNumber(Integer societyNumber) {
        this.societyNumber = societyNumber;
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

    public String getUserUniversity() {
        return userUniversity;
    }

    public void setUserUniversity(String userUniversity) {
        this.userUniversity = userUniversity;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
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
