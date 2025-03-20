package com.NirmanProject.backend.service;

import com.NirmanProject.backend.dto.JobRequestDTO;
import com.NirmanProject.backend.model.JobRequest;
import com.NirmanProject.backend.model.Worker;
import com.NirmanProject.backend.repositary.JobRequestRepository;
import com.NirmanProject.backend.repositary.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobRequestService {

    @Autowired
    private JobRequestRepository jobRequestRepository;

    @Autowired
    private WorkerRepository workerRepository;

    public JobRequest createJobPosting(JobRequestDTO dto) {
        JobRequest jobPosting = new JobRequest();

        jobPosting.setJobProviderId(dto.getJobProviderId());
        jobPosting.setLocation(dto.getLocation());
        jobPosting.setContactPerson(dto.getContactPerson());
        jobPosting.setContactNumber(dto.getContactNumber());
        jobPosting.setEmail(dto.getEmail());
        jobPosting.setAddress(dto.getAddress());
        jobPosting.setDistrict(dto.getDistrict());
        jobPosting.setState(dto.getState());
        jobPosting.setPincode(dto.getPincode());

        jobPosting.setJobTitle(dto.getJobTitle());
        jobPosting.setJobDescription(dto.getJobDescription());
        jobPosting.setSkillRequired(dto.getSkillRequired());
        jobPosting.setSkillLevel(dto.getSkillLevel());
        jobPosting.setNumOfWorkers(dto.getNumOfWorkers());
        jobPosting.setWorkLocation(dto.getWorkLocation());
        jobPosting.setStartDate(dto.getStartDate());
        jobPosting.setEndDate(dto.getEndDate());
        jobPosting.setWagePerDay(dto.getWagePerDay());
        jobPosting.setWorkingHours(dto.getWorkingHours());
        jobPosting.setJobType(dto.getJobType());
        jobPosting.setWorkerAssignedStatus(dto.getWorkerAssignedStatus());
        jobPosting.setEmployerIdProofBase64(dto.getEmployerIdProofBase64());

        // Optionally, initialize the list if not provided
        if (jobPosting.getAssignedWorkerIds() == null) {
            jobPosting.setAssignedWorkerIds(new ArrayList<>());
        }

        return jobRequestRepository.save(jobPosting);
    }

    public JobRequest assignWorkers(String jobId, List<String> workerIds) {
        Optional<JobRequest> jobRequestOptional = jobRequestRepository.findById(jobId);
        if (jobRequestOptional.isEmpty()) {
            throw new RuntimeException("Job not found with ID: " + jobId);
        }

        JobRequest jobRequest = jobRequestOptional.get();

        // Ensure the assignedWorkerIds list is initialized
        if (jobRequest.getAssignedWorkerIds() == null) {
            jobRequest.setAssignedWorkerIds(new ArrayList<>());
        }

        // Add each new worker if not already assigned
        for (String workerId : workerIds) {
            if (!jobRequest.getAssignedWorkerIds().contains(workerId)) {
                jobRequest.getAssignedWorkerIds().add(workerId);
            }
        }

        // Only update the status to "Assigned" if the required number of workers have been assigned
        if (jobRequest.getAssignedWorkerIds().size() >= jobRequest.getNumOfWorkers()) {
            jobRequest.setWorkerAssignedStatus("Assigned");
        }

        return jobRequestRepository.save(jobRequest);
    }

    public Optional<JobRequest> getJobRequestById(String id) {
        return jobRequestRepository.findById(id);
    }

    public List<JobRequest> findAll() {
        return jobRequestRepository.findAll();
    }

    public List<JobRequest> findByJobProviderId(String jobProviderId) {
        return jobRequestRepository.findByJobProviderId(jobProviderId);
    }

    // New method: get eligible workers based on job's required skill
    public List<Worker> getEligibleWorkers(String jobId) {
        Optional<JobRequest> jobRequestOptional = jobRequestRepository.findById(jobId);
        if (jobRequestOptional.isEmpty()) {
            throw new RuntimeException("Job not found with ID: " + jobId);
        }
        JobRequest jobRequest = jobRequestOptional.get();
        String requiredSkill = jobRequest.getSkillRequired();
        // This will return workers whose skillSet contains the required skill (ignoring case) and are available
        return workerRepository.findBySkillSetIgnoreCaseContainingAndAvailabilityTrue(requiredSkill);
    }
}