package com.wodongso.wodongso.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Society {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

	private String name;

    private String type;

    private String category;

    private String position;

    private String profileUrl;

    private String backgroundUrl;

    private String simpleDesc;

    private String detailDesc;

    private Date createdAt;

}
