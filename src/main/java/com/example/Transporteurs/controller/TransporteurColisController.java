package com.example.Transporteurs.controller;

import com.example.Transporteurs.dto.ColisDto;
import com.example.Transporteurs.service.ColisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transporteur/colis")
@RequiredArgsConstructor
public class TransporteurColisController {

    private final ColisService colisService;

    @GetMapping
    public Page<ColisDto> myColis(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        String transporteurId = authentication.getName(); // userId from JWT
        Pageable pageable = PageRequest.of(page, size);

        return colisService.listByTransporteur(transporteurId, pageable);
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<ColisDto> updateStatut(
            @PathVariable String id,
            @RequestBody java.util.Map<String, String> body,
            Authentication authentication) {

        String transporteurId = authentication.getName();
        String statut = body.get("statut");

        return ResponseEntity.ok(
                colisService.updateStatut(id, statut, transporteurId)
        );
    }
}
