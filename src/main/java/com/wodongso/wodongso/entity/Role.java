package com.wodongso.wodongso.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
