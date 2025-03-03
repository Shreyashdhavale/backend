package com.NirmanProject.backend.repositary;

import com.NirmanProject.backend.model.JobRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRequestRepository extends JpaRepository<JobRequest, String> {
    List<JobRequest> findByJobProviderId(String jobProviderId);
}
