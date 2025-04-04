package com.NirmanProject.backend.service;

import com.NirmanProject.backend.model.NGOSignUp;
import com.NirmanProject.backend.repositary.NGORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class NGOService {

    @Autowired
    private NGORepository ngoRepository;

    public NGOSignUp registerNGO(NGOSignUp ngoSignUp) {
        return ngoRepository.save(ngoSignUp);
    }

    public Optional<NGOSignUp> findByEmail(String email) {
        return ngoRepository.findByEmail(email);
    }
}
