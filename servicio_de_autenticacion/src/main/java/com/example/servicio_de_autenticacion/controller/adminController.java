package com.example.servicio_de_autenticacion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class adminController {

    @GetMapping("/home")
    public String adminHome(){
        return "Admin Home";
    }

    @GetMapping("/dashboard")
    public String adminDashboard(){
        return "Admin Dashboard";
    }


}
