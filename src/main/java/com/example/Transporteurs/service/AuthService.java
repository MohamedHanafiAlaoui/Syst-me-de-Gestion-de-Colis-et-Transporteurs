package com.example.Transporteurs.service;


import com.example.Transporteurs.dto.AuthRequest;
import com.example.Transporteurs.dto.AuthResponse;
import com.example.Transporteurs.model.User;
import com.example.Transporteurs.repository.UserRepository;
import com.example.Transporteurs.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthService authService(AuthRequest request)
    {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        if (!user.isActive())
        {
            throw new RuntimeException("Compte désactivé");
        }

        if (!passwordEncode.matches(request.getPassword(),user.getPassword()))
        {
            throw new RuntimeException("Password incorrecte");
        }

        String token=JwtUtil.generateToken(user);
        return new AuthResponse(token,user.getRole());

    }



}
