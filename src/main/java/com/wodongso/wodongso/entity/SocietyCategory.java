package com.wodongso.wodongso.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "society_category")
public class SocietyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from_society_number")
    private Integer fromSocietyNumber;

    private String name;

    @Column(name = "created_At")
    private LocalDateTime createdAt = LocalDateTime.now();


    public void createDefault(Integer number) {
        this.fromSocietyNumber = number;
        this.setName("활동내역");
    }
}
