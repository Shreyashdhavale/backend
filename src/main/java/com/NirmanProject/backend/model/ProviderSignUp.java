package com.NirmanProject.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProviderSignUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name ;
    String email ;
    String password ;
}
