package com.wodongso.wodongso.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(generator = "uuids")
    @GenericGenerator(name = "uuid2", strategy = "uuid")
    private UUID _id;

    private String id;

    private String name;

    private String nickname;

    private String password;

    private Integer type;

    private String contact;

    private String profileUrl;

    private String region;

    private String university;

    private String major;

    private Integer classOf;

    private boolean enabled;

    private Date createdAt;

    @ManyToMany()
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
}
