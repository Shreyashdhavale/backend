package com.NirmanProject.backend.service;

import com.NirmanProject.backend.model.ProviderSignUp;
import com.NirmanProject.backend.repositary.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    @Autowired
    public ProviderRepository signUpService;
    public ProviderSignUp registeruser(ProviderSignUp providerSignUp) {
        return signUpService.save(providerSignUp);
    }
}