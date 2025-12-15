package com.example.Transporteurs.model;

import com.example.Transporteurs.model.enm.Role;
import com.example.Transporteurs.model.enm.Specialite;
import com.example.Transporteurs.model.enm.TransporteurStatut;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private Role role;
    private boolean active = true;

    private TransporteurStatut statut;
    private Specialite specialite;

    private String email;
    private String phone;
}
