package com.example.servicio_de_autenticacion.controller;


import com.example.servicio_de_autenticacion.entity.User;
import com.example.servicio_de_autenticacion.model.UserModel;
import com.example.servicio_de_autenticacion.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public User register(@RequestBody UserModel userModel){

        if(userModel.getPassword() == null || userModel.getUsername() == null) {
            throw new IllegalArgumentException("The field is null");
        }else {
            User newUser = new User();
            newUser.setUsername(userModel.getUsername());
            newUser.setEmail(userModel.getEmail());
            newUser.setPassword(passwordEncoder.encode(userModel.getPassword()));
            return userRepository.save(newUser);
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "you're seeing the dashboard content";
    }
}
