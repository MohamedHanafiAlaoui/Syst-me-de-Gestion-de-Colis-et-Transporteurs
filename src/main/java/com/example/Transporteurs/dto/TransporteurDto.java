package com.example.Transporteurs.dto;

import com.example.Transporteurs.model.enm.Specialite;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class TransporteurDto {
    private String id;
    @NotBlank
    private String username;
    private String password;
    private boolean active;
    private Specialite specialite;
    private String email;
    private String phone;
}
