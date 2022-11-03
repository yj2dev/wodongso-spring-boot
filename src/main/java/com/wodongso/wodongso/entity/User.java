package com.wodongso.wodongso.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

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
}
