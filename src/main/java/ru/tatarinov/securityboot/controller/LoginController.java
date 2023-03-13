package ru.tatarinov.securityboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping(value = "/auth/login")
    public String loginPage() {
        return "/auth/login";
    }
}
