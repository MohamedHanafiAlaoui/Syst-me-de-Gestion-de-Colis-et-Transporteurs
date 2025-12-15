package com.example.Transporteurs.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class ColisDto {
    private String id;
    @NotBlank
    private String type;
    @NotNull
    private Double poids;
    @NotBlank
    private String adresseDestination;
    private String statut;
    private String assignedTo;


    private String instructionsManutention;


    private Integer temperatureMin;
    private Integer temperatureMax;
}
