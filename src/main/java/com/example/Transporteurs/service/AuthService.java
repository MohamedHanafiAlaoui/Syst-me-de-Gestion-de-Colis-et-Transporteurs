package com.example.Transporteurs.service;

import com.example.Transporteurs.dto.AuthRequest;
import com.example.Transporteurs.dto.AuthResponse;
import com.example.Transporteurs.model.User;
import com.example.Transporteurs.repository.UserRepository;
import com.example.Transporteurs.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!user.isActive())
            throw new RuntimeException("Compte désactivé");

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Mot de passe incorrect");

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole().name()
        );

        return new AuthResponse(token, user.getRole());
    }
}
