package com.NirmanProject.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequestDTO {
    private String location;
    private String contactPerson;
    private String contactNumber;
    private String email;
    private String address;
    private String district;
    private String state;
    private String pincode;

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

    private String employerIdProofBase64;
}
