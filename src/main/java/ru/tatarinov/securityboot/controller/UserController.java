package ru.tatarinov.securityboot.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    @GetMapping(value = "/user")
    public String userProfil() {
        return "userProfil";
    }
}
