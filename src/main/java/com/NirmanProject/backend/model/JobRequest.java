package com.NirmanProject.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_postings")
public class JobRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Employer Details
    private String location;
    private String contactPerson;
    private String contactNumber;
    private String email;
    private String address;
    private String district;
    private String state;
    private String pincode;

    // Job Details
    private String jobTitle;
    private String jobDescription;
    private String skillRequired;
    private String skillLevel;
    private int numOfWorkers;
    private String workLocation;
    private String startDate;
    private String endDate;
    private double wagePerDay;
    private String workingHours;
    private String jobType;

    // Verification
    @Lob
    @Column(columnDefinition = "TEXT")
    private String employerIdProofBase64;
}
