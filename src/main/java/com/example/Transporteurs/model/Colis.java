package com.example.Transporteurs.model;

import com.example.Transporteurs.model.enm.Specialite;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "colis")
public class Colis {
    @Id
    private String id;
    private String description;
    private Double poids;
    private String adresseDestination;
    private String statut;
    private String assignedTo;
    private String type;

    private String instructionsManutention;

    private Integer temperatureMin;
    private Integer temperatureMax;

    private Long createdAt;
    private Long updatedAt;
}
