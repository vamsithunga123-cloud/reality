package com.maytri.reality.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carousal {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    private Long id;
    @Column(length=2050)
    private String imageUrl;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Carousal(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
    public Carousal() {
    }
    
    

}
