package com.wodongso.wodongso.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class UserRole {

    @Id
    String userId;

    @Id
    int roleId;

}
