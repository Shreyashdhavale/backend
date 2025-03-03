package com.NirmanProject.backend.repositary;

import com.NirmanProject.backend.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    List<Worker> findByFullNameContainingIgnoreCase(String name);
}