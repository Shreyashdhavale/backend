package com.NirmanProject.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_postings")
public class JobRequest {

    @Id
    private String jobRequestId; // Randomly generated 6-digit Job Provider ID
    private String jobProviderId;

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

    // Worker assignment
    private String workerAssignedStatus = "Pending";

    @ElementCollection
    @CollectionTable(name = "assigned_workers", joinColumns = {
            @JoinColumn(name = "job_request_id", referencedColumnName = "jobRequestId"),
            @JoinColumn(name = "job_provider_id", referencedColumnName = "jobProviderId")
    })
    @Column(name = "worker_id")
    private List<String> assignedWorkerIds;


    @Column(columnDefinition = "TEXT")
    private String employerIdProofBase64;

    // Generate random 6-digit jobProviderId before persisting
    @PrePersist
    private void generateJobRequestId() {
        Random random = new Random();
        this.jobRequestId = String.format("%06d", random.nextInt(1000000));
    }
}
