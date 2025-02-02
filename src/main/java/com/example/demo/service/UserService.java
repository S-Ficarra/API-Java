package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    public User createUser(User user) {

        Optional<User> alreadyExisting = userRepository.findByEmail(user.getEmail());
        if (alreadyExisting.isPresent()) {
            throw new IllegalArgumentException("Email already taken");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        Optional<User> alreadyExisting = userRepository.findByEmail(user.getEmail());
        if (alreadyExisting.isPresent()) {
            throw new IllegalArgumentException("Email already taken");
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Optional<User> alreadyExisting = userRepository.findById(id);
        if (alreadyExisting.isPresent()) {
            throw new IllegalArgumentException("User do not exist");
        }
        userRepository.deleteById(id);
    }
















}
