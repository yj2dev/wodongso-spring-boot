package com.wodongso.wodongso.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "society_content")
public class SocietyContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "to_category_id")
    private Integer toCategoryId;

    @Column(name = "from_society_number")
    private Integer fromSocietyNumber;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "writer_id")
    private String writerId;

    private String title;

    private String content;

    @Column(name = "created_At")
    private LocalDateTime createdAt = LocalDateTime.now();

}
