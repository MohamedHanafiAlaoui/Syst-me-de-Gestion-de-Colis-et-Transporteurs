package com.example.Transporteurs.repository;

import com.example.Transporteurs.model.Colis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColisRepository extends MongoRepository<Colis, String> {
    Page<Colis> findByType(String type, Pageable pageable);
    Page<Colis> findByStatut(String statut, Pageable pageable);
    Page<Colis> findByAdresseDestinationContainingIgnoreCase(String adresse, Pageable pageable);
    Page<Colis> findByAssignedTo(String assignedTo, Pageable pageable);
}
