package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.services.RoleService;
import ru.tatarinov.securityboot.services.RoleServiceImpl;
import ru.tatarinov.securityboot.services.UserDetailsServiceImpl;
import ru.tatarinov.securityboot.services.UserService;

import java.security.Principal;

@Controller
public class UserController {
    private final UserDetailsService userDetailService;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(RoleServiceImpl roleService, UserDetailsServiceImpl userDetailService, UserService userService, RoleService roleService1) {
        this.userDetailService = userDetailService;
        this.userService = userService;
        this.roleService = roleService1;
    }

    @GetMapping(value = "/user")
    public String userProfil(Model model, Principal principal) {

        model.addAttribute("user", userService.allUser());
        model.addAttribute("roles", roleService.allRole());
        model.addAttribute("user2", new User());

        User user3 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();;
        model.addAttribute("user3",  user3);


        return "userProfil";
    }
}
