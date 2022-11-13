package com.wodongso.wodongso.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "society_recruit_status")
public class SocietyRecruitStatus {

    @EmbeddedId
    SocietyRecruitStatusPK societyRecruitStatusPK;

    @OneToOne
    @JoinColumn(name = "to_society_number")
    private Society toSocietyNumber;

    @OneToOne
    @JoinColumn(name = "from_user_id")
    private User fromUserId;

    private boolean state;

    @Column(name = "reject_reason")
    private String rejectReason;


}
