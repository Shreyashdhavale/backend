package com.NirmanProject.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentRequestDTO {
    private String jobId;
    private String workerId;

    // Constructors
    public AssignmentRequestDTO() {}

    public AssignmentRequestDTO(String jobId, String workerId) {
        this.jobId = jobId;
        this.workerId = workerId;
    }

}