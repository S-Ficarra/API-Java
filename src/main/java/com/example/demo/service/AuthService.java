package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String email, String password) {
        // Recherche l'utilisateur par son email
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return false; // Utilisateur non trouvé
        }

        // Vérifie si le mot de passe correspond à celui dans la base de données
        return user.get().getPassword().equals(password);  // Comparaison simple
    }
}
