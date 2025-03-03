package com.NirmanProject.backend.controller;

import com.NirmanProject.backend.dto.JobRequestDTO;
import com.NirmanProject.backend.model.JobRequest;
import com.NirmanProject.backend.service.JobRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:5173")  // Allow frontend access
public class JobRequestController {

    @Autowired
    private JobRequestService jobRequestService;

    @PostMapping
    public ResponseEntity<?> createJobPosting(@RequestBody JobRequestDTO jobPostingDTO) {
        try {
            JobRequest createdJob = jobRequestService.createJobPosting(jobPostingDTO);
            return ResponseEntity.ok(createdJob);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<JobRequest> getJobRequestById(@PathVariable Long id) {
        Optional<JobRequest> jobRequest = jobRequestService.getJobRequestById(id);
        return jobRequest
                .map(jr -> ResponseEntity.ok(jr))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<JobRequest>> getAllJobs() {
        List<JobRequest> jobs = jobRequestService.findAll();
        return ResponseEntity.ok(jobs);
    }
}
