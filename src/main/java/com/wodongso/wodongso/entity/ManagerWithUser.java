package com.wodongso.wodongso.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ManagerWithUser {

    @Id
    private String id;
    private String name;
    private String contact;
    private String university;
    private String major;
    private Integer classOf;
    private String proofImageUrl;
    private LocalDateTime createdAt;
    private boolean state;
    private String rejectReason;

    public ManagerWithUser() {
    }

    public ManagerWithUser(String id, String name, String contact, String university, String major, Integer class_of, String proof_image_url, LocalDateTime created_at, boolean state, String reject_reason) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.university = university;
        this.major = major;
        this.classOf = class_of;
        this.proofImageUrl = proof_image_url;
        this.createdAt = created_at;
        this.state = state;
        this.rejectReason = reject_reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getClassOf() {
        return classOf;
    }

    public void setClassOf(Integer classOf) {
        this.classOf = classOf;
    }

    public String getProofImageUrl() {
        return proofImageUrl;
    }

    public void setProofImageUrl(String proofImageUrl) {
        this.proofImageUrl = proofImageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
