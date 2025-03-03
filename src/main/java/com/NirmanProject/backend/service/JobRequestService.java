package com.NirmanProject.backend.service;

import com.NirmanProject.backend.dto.JobRequestDTO;
import com.NirmanProject.backend.model.JobRequest;
import com.NirmanProject.backend.repositary.JobRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class JobRequestService {

    @Autowired
    private JobRequestRepository jobRequestRepository;

    public JobRequest createJobPosting(JobRequestDTO dto) {
        JobRequest jobPosting = new JobRequest();

        // Mapping DTO to Entity
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

        jobPosting.setEmployerIdProofBase64(dto.getEmployerIdProofBase64());

        return jobRequestRepository.save(jobPosting);
    }

    public Optional<JobRequest> getJobRequestById(Long id) {
        return jobRequestRepository.findById(id);
    }


    public List<JobRequest> findAll() {
        return jobRequestRepository.findAll();
    }
}
