package com.wodongso.wodongso.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "society_content_image")
public class SocietyContentImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from_society_content_id")
    private Integer fromSocietyContentId;

    private String path;
    
    @Column(name = "created_At")
    private LocalDateTime createdAt = LocalDateTime.now();

}
