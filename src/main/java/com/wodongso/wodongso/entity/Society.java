package com.wodongso.wodongso.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "society")
public class Society {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    private String name;

    @OneToOne
    @JoinColumn(name = "officer_id")
    private User officerId;

    private String type;

    private String category;

    private String position;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "background_url")
    private String backgroundUrl;

    @Column(name = "simple_desc")
    private String simpleDesc;

    @Column(name = "detail_desc")
    private String detailDesc;

    private boolean enabled;

    @Column(name = "created_at")
    private Date createdAt;

}
