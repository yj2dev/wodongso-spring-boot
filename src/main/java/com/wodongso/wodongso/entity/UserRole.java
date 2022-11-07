package com.wodongso.wodongso.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserRole {

    @Id
    String userId;

    @Id
    int roleId;

}
