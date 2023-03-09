package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.services.RoleServiceImpl;
import ru.tatarinov.securityboot.services.UserDetailService;

import java.security.Principal;

@Controller
public class UserController {
    private final UserDetailsService userDetailService;

    @Autowired
    public UserController(RoleServiceImpl roleService, UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping(value = "/user")
    public String userProfil(Model model, Principal principal) {
        model.addAttribute("user", (User) userDetailService.loadUserByUsername(principal.getName()));

        return "userProfil";
    }
}
