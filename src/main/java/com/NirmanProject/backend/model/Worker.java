package com.NirmanProject.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "workers")
@Getter
@Setter
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Personal Information
    private String fullName;
    private Integer age;
    private LocalDate dateOfBirth;
    private String gender;
    private String contact;
    private String emergencyContact;
    private String email;
    private String streetAddress;
    private String district;
    private String state;
    private String pincode;
    private String maritalStatus;
    private String physicallyHandicapped;

    private String criticalIllness; // optional

    // Professional Details
    private String skillSet;
    private String skillLevel;
    private String preferredWorkLocation;
    private String availability;

    @Column(columnDefinition = "TEXT")
    private String profilePhoto;

    @Column(columnDefinition = "TEXT")
    private String aadhaarPhoto;

    @Column(columnDefinition = "TEXT")
    private String alternateDoc;
    // Consent
    private Boolean consent;

}
