package com.NirmanProject.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkerDTO {
    // Personal Information
    private Long Id;
    private String fullName;
    private Integer age;
    private String dateOfBirth; // expected in ISO format (yyyy-MM-dd)
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

    // Verification (Files)
    private String profilePhoto;
    private String aadhaarPhoto;
    private String alternateDoc;

    // Consent
    private Boolean consent;

    // Getters and Setters

}
