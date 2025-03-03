package com.NirmanProject.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Random;

@Entity
@Table(name = "workers")
@Getter
@Setter
public class Worker {

    @Id
    @Column(name = "worker_id", nullable = false, unique = true)
    private String workerId; // Unique 6-digit worker ID

    // Personal Information
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "contact")
    private String contact;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @Column(name = "email")
    private String email;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "district")
    private String district;

    @Column(name = "state")
    private String state;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "physically_handicapped")
    private String physicallyHandicapped;

    @Column(name = "critical_illness")
    private String criticalIllness; // optional

    // Professional Details
    @Column(name = "skill_set")
    private String skillSet;

    @Column(name = "skill_level")
    private String skillLevel;

    @Column(name = "preferred_work_location")
    private String preferredWorkLocation;

    @Column(name = "availability")
    private String availability;

    @Column(name = "profile_photo", columnDefinition = "TEXT")
    private String profilePhoto;

    @Column(name = "aadhaar_photo", columnDefinition = "TEXT")
    private String aadhaarPhoto;

    @Column(name = "alternate_doc", columnDefinition = "TEXT")
    private String alternateDoc;

    // Consent
    @Column(name = "consent")
    private Boolean consent;

    // Automatically generate a random 6-digit worker ID before persisting
    @PrePersist
    private void generateWorkerId() {
        Random random = new Random();
        this.workerId = String.format("%06d", random.nextInt(1000000));
    }
}
