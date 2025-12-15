package com.example.Transporteurs.repository;

import com.example.Transporteurs.model.User;
import com.example.Transporteurs.model.enm.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Page<User> findByRole(Role role, Pageable pageable);

}
