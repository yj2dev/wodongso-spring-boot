package com.wodongso.wodongso.entity;


import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "society_create_status")
public class SocietyCreateStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "to_society_number")
    private Integer toSocietyNumber;

    @Column(name = "from_user_id")
    private String fromUserId;

    private boolean state;

    @Column(name = "reject_reason")
    private String rejectReason;

}
