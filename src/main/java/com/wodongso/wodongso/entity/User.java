package com.wodongso.wodongso.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@Entity
@Data
public class User {

    @Id
    private String id;

    private String name;

    private String nickname;

    private String password;

    private String role;

    private String contact;

    @Column(name = "profile_url")
    private String profileUrl;

    private String region;

    private String university;

    private String major;

    @Column(name = "class_of")
    private Integer classOf;

    private boolean enabled;

    @Column(name = "created_At")
    private LocalDateTime createdAt = LocalDateTime.now();
}
