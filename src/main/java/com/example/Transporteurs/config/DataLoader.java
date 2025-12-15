package com.example.Transporteurs.config;

import com.example.Transporteurs.model.User;
import com.example.Transporteurs.model.enm.Role;
import com.example.Transporteurs.model.enm.Specialite;
import com.example.Transporteurs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setActive(true);
            admin.setEmail("admin@example.com");
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("transp1").isEmpty()) {
            User t = new User();
            t.setUsername("transp1");
            t.setPassword(encoder.encode("transp123"));
            t.setRole(Role.TRANSPORTEUR);
            t.setSpecialite(Specialite.FRAGILE);
            t.setActive(true);
            userRepository.save(t);
        }
    }
}
