package com.example.Transporteurs.service;

import com.example.Transporteurs.dto.TransporteurDto;
import com.example.Transporteurs.mapper.TransporteurMapper;
import com.example.Transporteurs.model.User;
import com.example.Transporteurs.model.enm.Role;
import com.example.Transporteurs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TransporteurMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public Page<TransporteurDto> listTransporteurs(Pageable pageable) {
        return userRepository.findByRole(Role.TRANSPORTEUR, pageable)
                .map(mapper::toDto);
    }

    public TransporteurDto createTransporteur(TransporteurDto dto) {
        User u = mapper.toEntity(dto);
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setRole(Role.TRANSPORTEUR);
        u.setActive(true);
        return mapper.toDto(userRepository.save(u));
    }

    public TransporteurDto updateTransporteur(String id, TransporteurDto dto) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transporteur non trouvé"));

        mapper.updateEntityFromDto(dto, u);
        return mapper.toDto(userRepository.save(u));
    }

    public void deleteTransporteur(String id) {
        userRepository.deleteById(id);
    }
}
