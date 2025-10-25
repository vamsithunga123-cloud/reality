package com.maytri.reality.model;

import jakarta.persistence.*;

@Entity
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;

    @Column(length = 1000)
    private String message;

    private String interest; // For project interest
    private String type;     // PROJECT, CONTACT, MODAL

    public Enquiry() {}

    public Enquiry(String name, String phone, String email, String message, String interest, String type) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.message = message;
        this.interest = interest;
        this.type = type;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getInterest() { return interest; }
    public void setInterest(String interest) { this.interest = interest; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
