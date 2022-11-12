package com.wodongso.wodongso.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "society_create_status")
public class SocietyCreateStatus {

    @EmbeddedId
    SocietyCreateStatusPK societyCreateStatusPK;

    @OneToMany
    @JoinColumn(name = "to_society_number")
    private List<Society> toSocietyNumber;

    @OneToMany
    @JoinColumn(name = "from_user_id")
    private List<User> fromUserId;

    private boolean state;

    @Column(name = "to_society_number")
    private String rejectReason;

}
