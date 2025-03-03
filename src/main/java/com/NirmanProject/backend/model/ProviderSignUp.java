package com.NirmanProject.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Entity
@Getter
@Setter
@Table(name = "provider_sign_up")
public class ProviderSignUp {

    @Id
    private String jobProviderId; // 6-digit unique ID

    private String name;
    private String email;
    private String password;

    // Generate a random 6-digit jobProviderId before persisting
    @PrePersist
    private void generateJobProviderId() {
        Random random = new Random();
        this.jobProviderId = String.format("%06d", random.nextInt(1000000));
    }
}
