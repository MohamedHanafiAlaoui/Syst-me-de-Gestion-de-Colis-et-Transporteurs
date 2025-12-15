package com.example.Transporteurs.service;

import com.example.Transporteurs.dto.ColisDto;
import com.example.Transporteurs.mapper.ColisMapper;
import com.example.Transporteurs.model.Colis;
import com.example.Transporteurs.model.User;
import com.example.Transporteurs.repository.ColisRepository;
import com.example.Transporteurs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColisService {

    private final ColisRepository colisRepository;
    private final UserRepository userRepository;
    private final ColisMapper mapper;

    public Page<ColisDto> listAll(Pageable pageable,
                                  Optional<String> type,
                                  Optional<String> statut) {

        Page<Colis> page;

        if (type.isPresent())
            page = colisRepository.findByType(type.get(), pageable);
        else if (statut.isPresent())
            page = colisRepository.findByStatut(statut.get(), pageable);
        else
            page = colisRepository.findAll(pageable);

        return page.map(mapper::toDto);
    }

    public Page<ColisDto> listByTransporteur(String transporteurId, Pageable pageable) {
        return colisRepository.findByAssignedTo(transporteurId, pageable)
                .map(mapper::toDto);
    }

    public ColisDto createColis(ColisDto dto) {
        Colis c = mapper.toEntity(dto);
        c.setCreatedAt(Instant.now().toEpochMilli());
        c.setStatut("EN_ATTENTE");
        return mapper.toDto(colisRepository.save(c));
    }

    public ColisDto updateColis(String id, ColisDto dto) {
        Colis c = colisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colis non trouvé"));

        mapper.updateEntityFromDto(dto, c);
        c.setUpdatedAt(Instant.now().toEpochMilli());

        return mapper.toDto(colisRepository.save(c));
    }

    public void deleteColis(String id) {
        colisRepository.deleteById(id);
    }

    public void assignToTransporteur(String colisId, String transporteurId) {
        Colis c = colisRepository.findById(colisId)
                .orElseThrow(() -> new RuntimeException("Colis non trouvé"));

        User t = userRepository.findById(transporteurId)
                .orElseThrow(() -> new RuntimeException("Transporteur non trouvé"));

        if (t.getSpecialite() == null ||
                !t.getSpecialite().name().equalsIgnoreCase(c.getType())) {
            throw new RuntimeException("Spécialité incompatible");
        }

        c.setAssignedTo(transporteurId);
        c.setStatut("EN_TRANSIT");
        colisRepository.save(c);
    }

    public ColisDto updateStatut(String colisId, String statut, String userId) {
        Colis c = colisRepository.findById(colisId)
                .orElseThrow(() -> new RuntimeException("Colis non trouvé"));

        if (!userId.equals(c.getAssignedTo())) {
            throw new RuntimeException("Accès refusé");
        }

        c.setStatut(statut);
        c.setUpdatedAt(Instant.now().toEpochMilli());

        return mapper.toDto(colisRepository.save(c));
    }
}
