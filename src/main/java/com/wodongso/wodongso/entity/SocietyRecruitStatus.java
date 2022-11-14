package com.wodongso.wodongso.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "society_recruit_status")
public class SocietyRecruitStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @OneToOne
//    @JoinColumn(name = "to_society_number")
//    private Society toSocietyNumber;

//    @OneToOne
//    @JoinColumn(name = "from_user_id")
//    private User fromUserId;

    private Integer toSocietyNumber;

    private String fromUserId;


    private Integer state;

    @Column(name = "reject_reason")
    private String rejectReason;

    @Column(name = "created_At")
    private LocalDateTime createdAt = LocalDateTime.now();
}
