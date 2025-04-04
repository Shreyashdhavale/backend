package com.NirmanProject.backend.repositary;

import com.NirmanProject.backend.model.NGOSignUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NGORepository extends JpaRepository<NGOSignUp, Integer> {
    boolean existsByEmail(String email);
    Optional<NGOSignUp> findByEmail(String email);
}