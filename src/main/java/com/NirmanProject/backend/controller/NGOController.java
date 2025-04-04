package com.NirmanProject.backend.controller;

import com.NirmanProject.backend.model.NGOSignUp;
import com.NirmanProject.backend.service.NGOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ngo")
@CrossOrigin(origins = "http://localhost:5173")  // Adjust if needed
public class NGOController {

    @Autowired
    private NGOService ngoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> createNGO(@RequestBody NGOSignUp ngoSignUp) {
        if (ngoService.findByEmail(ngoSignUp.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(
                    Map.of("success", false, "message", "Email already exists!")
            );
        }

        ngoSignUp.setPassword(passwordEncoder.encode(ngoSignUp.getPassword()));
        NGOSignUp savedNGO = ngoService.registerNGO(ngoSignUp);

        // Create response that matches expected structure in frontend
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Registration successful!");
        response.put("name", savedNGO.getName());
        response.put("email", savedNGO.getEmail());
        response.put("id", savedNGO.getId());
        response.put("type", "ngo");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginNGO(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        System.out.println("Login attempt: " + email);

        Optional<NGOSignUp> ngoOptional = ngoService.findByEmail(email);
        if (ngoOptional.isEmpty()) {
            System.out.println("NGO not found with email: " + email);
            return ResponseEntity.badRequest().body(
                    Map.of("success", false, "message", "Email not found!")
            );
        }

        NGOSignUp ngo = ngoOptional.get();
        System.out.println("NGO found: " + ngo.getName() + ", validating password");

        if (!passwordEncoder.matches(password, ngo.getPassword())) {
            System.out.println("Invalid password for: " + email);
            return ResponseEntity.badRequest().body(
                    Map.of("success", false, "message", "Invalid password!")
            );
        }

        System.out.println("Login successful for: " + email);

        // Create response that matches expected structure in frontend
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Login successful!");
        response.put("name", ngo.getName());
        response.put("email", ngo.getEmail());
        response.put("id", ngo.getId());
        response.put("type", "ngo");

        return ResponseEntity.ok(response);
    }
}