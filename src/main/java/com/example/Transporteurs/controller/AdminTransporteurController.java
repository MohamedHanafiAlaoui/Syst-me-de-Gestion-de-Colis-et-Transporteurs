package com.example.Transporteurs.controller;

import com.example.Transporteurs.dto.TransporteurDto;
import com.example.Transporteurs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/transporteurs")
@RequiredArgsConstructor
public class AdminTransporteurController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<TransporteurDto> create(@RequestBody TransporteurDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createTransporteur(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransporteurDto> update(
            @PathVariable String id,
            @RequestBody TransporteurDto dto) {

        return ResponseEntity.ok(userService.updateTransporteur(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.deleteTransporteur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<TransporteurDto> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return userService.listTransporteurs(PageRequest.of(page, size));
    }
}
