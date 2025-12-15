package com.example.Transporteurs.controller;

import com.example.Transporteurs.dto.ColisDto;
import com.example.Transporteurs.service.ColisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin/colis")
@RequiredArgsConstructor
public class AdminColisController {

    private final ColisService colisService;

    @GetMapping
    public Page<ColisDto> listAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String statut) {

        Pageable pageable = PageRequest.of(page, size);
        return colisService.listAll(
                pageable,
                Optional.ofNullable(type),
                Optional.ofNullable(statut)
        );
    }

    @PostMapping
    public ResponseEntity<ColisDto> create(@RequestBody ColisDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(colisService.createColis(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColisDto> update(
            @PathVariable String id,
            @RequestBody ColisDto dto) {

        return ResponseEntity.ok(colisService.updateColis(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        colisService.deleteColis(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/assign/{transporteurId}")
    public ResponseEntity<Void> assign(
            @PathVariable String id,
            @PathVariable String transporteurId) {

        colisService.assignToTransporteur(id, transporteurId);
        return ResponseEntity.ok().build();
    }
}
