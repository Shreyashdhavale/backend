package com.NirmanProject.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderDTO {
    private String name;
    private String email;
    private String type;

    public ProviderDTO(String name, String email) {
        this.name = name;
        this.email = email;
        this.type = "provider";
    }


}