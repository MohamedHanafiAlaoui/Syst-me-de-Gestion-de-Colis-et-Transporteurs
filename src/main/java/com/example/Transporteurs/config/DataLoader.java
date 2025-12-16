package com.example.Transporteurs.config;

import com.example.Transporteurs.model.Colis;
import com.example.Transporteurs.model.User;
import com.example.Transporteurs.model.enm.Role;
import com.example.Transporteurs.model.enm.Specialite;
import com.example.Transporteurs.repository.ColisRepository;
import com.example.Transporteurs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ColisRepository colisRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setActive(true);
            admin.setEmail("admin@example.com");
            userRepository.save(admin);
        }

        if (userRepository.findAllByRole(Role.TRANSPORTEUR).isEmpty()) {
            createTransporteur("transp1", Specialite.FRAGILE);
            createTransporteur("transp2", Specialite.FRIGO);
            createTransporteur("transp3", Specialite.FRAGILE);
            createTransporteur("transp4", Specialite.FRIGO);
            createTransporteur("transp5", Specialite.STANDARD);
        }

        if (colisRepository.count() == 0) {
            createColis("Ordinateur portable", 2.5, "Casablanca", Specialite.FRAGILE);
            createColis("Télévision 55 pouces", 12.0, "Marrakech", Specialite.FRAGILE);
            createColis("Vaisselle en verre", 4.0, "Fès", Specialite.FRAGILE);
            createColis("Livre rare", 1.2, "Rabat", Specialite.FRAGILE);
            createColis("Produits électroniques", 3.5, "Tangier", Specialite.FRAGILE);

            createColis("Glace / congélateur", 6.0, "Rabat", Specialite.FRIGO);
            createColis("Viande congelée", 8.5, "Agadir", Specialite.FRIGO);
            createColis("Poisson surgelé", 5.5, "Casablanca", Specialite.FRIGO);
            createColis("Lait congelé", 4.2, "Meknès", Specialite.FRIGO);
            createColis("Desserts surgelés", 3.8, "Fès", Specialite.FRIGO);

            createColis("Vêtements", 2.0, "Rabat", Specialite.STANDARD);
            createColis("Chaussures", 3.0, "Casablanca", Specialite.STANDARD);
            createColis("Sac à dos", 1.5, "Marrakech", Specialite.STANDARD);
            createColis("Accessoires de mode", 1.0, "Tangier", Specialite.STANDARD);
            createColis("Livres scolaires", 4.0, "Fès", Specialite.STANDARD);

            createColis("Jouets enfants", 2.2, "Agadir", Specialite.STANDARD);
            createColis("Produits cosmétiques", 1.8, "Marrakech", Specialite.STANDARD);
            createColis("Articles ménagers", 5.5, "Casablanca", Specialite.STANDARD);
            createColis("Papeterie", 3.0, "Rabat", Specialite.STANDARD);
            createColis("Matériel informatique", 6.0, "Fès", Specialite.STANDARD);
        }

        System.out.println("Fake data loaded ✅");
    }

    private void createTransporteur(String username, Specialite specialite) {
        User t = new User();
        t.setUsername(username);
        t.setPassword(encoder.encode("transp123"));
        t.setRole(Role.TRANSPORTEUR);
        t.setSpecialite(specialite);
        t.setActive(true);
        userRepository.save(t);
    }

    private void createColis(String description, double poids, String destination, Specialite specialite) {
        Colis c = new Colis();
        c.setDescription(description);
        c.setPoids(poids);
        c.setAdresseDestination(destination);
        c.setType(specialite.name());
        c.setStatut("EN_ATTENTE");
        c.setCreatedAt(Instant.now().toEpochMilli());
        colisRepository.save(c);
    }
}
