package com.wodongso.wodongso.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "user_manager_status")
public class UserManagerStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from_user_id")
    private String fromUserId;

    @Column(name = "proof_image_url")
    private String proofImageUrl;

    private int state;

    @Column(name = "reject_reason")
    private String rejectReason;

    @Column(name = "created_At")
    private LocalDateTime createdAt = LocalDateTime.now();

}
