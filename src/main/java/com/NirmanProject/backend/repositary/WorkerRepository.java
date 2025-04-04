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

<<<<<<< HEAD


=======

    long countByRegisteredByEmail(String email);  // Count workers by registeredByEmail

    //GET WORKERS BY REGISTERED EMAIL
    List<Worker> findByRegisteredByEmail(String email);  // Get workers by registeredByEmail
>>>>>>> 9ecd6d4 (    Added worker login)

    //GET WORKER BY ID
    Optional<Worker> findByWorkerId(String workerId);

}
