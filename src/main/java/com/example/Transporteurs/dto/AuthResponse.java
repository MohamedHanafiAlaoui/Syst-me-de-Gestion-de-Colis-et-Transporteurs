package com.example.Transporteurs.dto;

import com.example.Transporteurs.model.enm.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Role role;
}
