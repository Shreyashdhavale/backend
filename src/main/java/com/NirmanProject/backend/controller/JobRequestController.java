package com.NirmanProject.backend.controller;

import com.NirmanProject.backend.dto.AssignmentRequestDTO;
import com.NirmanProject.backend.dto.JobRequestDTO;
import com.NirmanProject.backend.model.JobRequest;
import com.NirmanProject.backend.model.Worker;
import com.NirmanProject.backend.service.JobRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class JobRequestController {

    @Autowired
    private JobRequestService jobRequestService;

    @PostMapping("/jobs")
    public ResponseEntity<?> createJobPosting(@RequestBody JobRequestDTO jobPostingDTO) {
        try {
            JobRequest createdJob = jobRequestService.createJobPosting(jobPostingDTO);
            return ResponseEntity.ok(createdJob);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    // New endpoint: Assign workers to a job
// New endpoint: Assign a single worker using DTO
    @PostMapping("/jobs/assign-worker")
    public ResponseEntity<?> assignSingleWorker(@RequestBody AssignmentRequestDTO requestDTO) {
        try {
            List<String> workerList = List.of(requestDTO.getWorkerId());
            JobRequest updatedJob = jobRequestService.assignWorkers(requestDTO.getJobId(), workerList);
            return ResponseEntity.ok(updatedJob);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }


    // New endpoint: Get eligible workers for a job based on the required skill
    @GetMapping("/jobs/{id}/eligible-workers")
    public ResponseEntity<?> getEligibleWorkers(@PathVariable String id) {
        try {
            List<Worker> eligibleWorkers = jobRequestService.getEligibleWorkers(id);
            return ResponseEntity.ok(eligibleWorkers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobRequest> getJobRequestById(@PathVariable String id) {
        Optional<JobRequest> jobRequest = jobRequestService.getJobRequestById(id);
        return jobRequest.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobRequest>> getJobsByJobProviderId(@RequestParam String jobProviderId) {
        List<JobRequest> jobs = jobRequestService.findByJobProviderId(jobProviderId);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/jobs/all")
    public ResponseEntity<List<JobRequest>> getAllJobs() {
        List<JobRequest> jobs = jobRequestService.findAll();
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/jobs/cancel-worker")
    public ResponseEntity<?> cancelAssignedWorker(@RequestBody AssignmentRequestDTO requestDTO) {
        try {
            JobRequest updatedJob = jobRequestService.removeAssignedWorker(
                    requestDTO.getJobId(),
                    requestDTO.getWorkerId()
            );
            return ResponseEntity.ok(updatedJob);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
    // New endpoint: Delete a job
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable String id) {
        try {
            jobRequestService.deleteJobById(id);
            return ResponseEntity.ok("Job with ID " + id + " has been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

}
