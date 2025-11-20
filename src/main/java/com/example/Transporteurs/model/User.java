package com.example.Transporteurs.model;

import com.example.Transporteurs.model.enm.Role;
import com.example.Transporteurs.model.enm.Specialite;
import com.example.Transporteurs.model.enm.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter

@Document(collection = "users")
@Data

public class User {
    @org.springframework.data.annotation.Id
    private String id;
    private String username;
    private String Password;
    private Role role;
    private boolean active = false;



    //TRANSPORTEUR

    private Status status;
    private Specialite specialite;
}
