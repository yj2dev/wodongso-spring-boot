package com.wodongso.wodongso.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SocietyCreateStatusPK implements Serializable {

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
