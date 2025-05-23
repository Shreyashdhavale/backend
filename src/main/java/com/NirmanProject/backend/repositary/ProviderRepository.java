package com.NirmanProject.backend.repositary;

import com.NirmanProject.backend.model.ProviderSignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderSignUp, String> {

    boolean existsByEmail(String email);

    Optional<ProviderSignUp> findByEmail(String email);

    Optional<ProviderSignUp> findByJobProviderId(String jobProviderId);
}