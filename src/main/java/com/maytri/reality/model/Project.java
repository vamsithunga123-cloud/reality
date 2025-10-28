package com.maytri.reality.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    @Column(name = "image_url", length = 2000)
    private String imageUrl;
    private String location;
}
