package com.NirmanProject.backend.repositary;

import com.NirmanProject.backend.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, String> {

    List<Worker> findByFullNameContainingIgnoreCase(String name);

    List<Worker> findBySkillSetIgnoreCaseContainingAndAvailabilityTrue(String requiredSkill);

    long count();  // Counts total workers



    long countByEmail(String email);;  // Count workers by registeredByEmail

    //GET WORKERS BY REGISTERED EMAIL
    List<Worker> findByEmail(String email);  // Get workers by registeredByEmail


    //GET WORKER BY ID
    Optional<Worker> findByWorkerId(String workerId);

}
