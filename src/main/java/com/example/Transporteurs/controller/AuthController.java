package com.example.Transporteurs.controller;

import com.example.Transporteurs.dto.AuthRequest;
import com.example.Transporteurs.dto.AuthResponse;
import com.example.Transporteurs.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody AuthRequest request) {

        return ResponseEntity.ok(authService.authenticate(request));
    }
}
