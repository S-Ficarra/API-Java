package com.example.demo.controller;
import com.example.demo.model.User;
import com.example.demo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class SecurityController {

    private final SecurityService securityService;


    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = new SecurityService();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestParam String email, @RequestParam String password) {
        try {
            User user = securityService.login(email, password);

            user.setPassword(null);
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

