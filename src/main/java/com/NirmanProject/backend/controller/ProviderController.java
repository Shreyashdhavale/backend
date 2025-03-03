package com.NirmanProject.backend.controller;

import com.NirmanProject.backend.model.ProviderSignUp;
import com.NirmanProject.backend.repositary.ProviderRepository;
import com.NirmanProject.backend.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ProviderController {

    @Autowired
    private ProviderService signUpService;

    @Autowired
    private PasswordEncoder passwordEncoder;  // ✅ Use PasswordEncoder interface

    @Autowired
    private ProviderRepository signUpRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> createProvider(@RequestBody ProviderSignUp providerSignUp) {
        if (signUpRepository.existsByEmail(providerSignUp.getEmail())) {
            return ResponseEntity.badRequest().body(
                    Map.of("success", false, "message", "Email already exists!")
            );
        }

        // Encrypt the password before saving
        providerSignUp.setPassword(passwordEncoder.encode(providerSignUp.getPassword()));

        // Save the provider using the service layer
        ProviderSignUp savedProvider = signUpService.registeruser(providerSignUp);

        return ResponseEntity.ok(
                Map.of("success", true, "message", "Registration successful!", "provider", savedProvider)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginProvider(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Optional<ProviderSignUp> providerOptional = signUpRepository.findByEmail(email);
        if (providerOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    Map.of("success", false, "message", "Email not found!")
            );
        }

        ProviderSignUp provider = providerOptional.get();
        if (!passwordEncoder.matches(password, provider.getPassword())) {
            return ResponseEntity.badRequest().body(
                    Map.of("success", false, "message", "Invalid password!")
            );
        }

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message", "Login successful!",
                        "name", provider.getName(),
                        "email", provider.getEmail()
                )
        );
    }
}
