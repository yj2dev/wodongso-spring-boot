package com.wodongso.wodongso.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SocietyRecruitStatusPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "to_society_number_")
    private Society toSocietyNumber;

    @ManyToOne
    @JoinColumn(name = "from_user_id_")
    private User fromUserId;

    @Column(name = "state_")
    private boolean state;

    @Column(name = "reject_reason_")
    private String rejectReason;

}
