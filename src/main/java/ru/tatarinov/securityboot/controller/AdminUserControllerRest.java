package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.services.RoleService;
import ru.tatarinov.securityboot.services.UserService;

import java.util.List;
import java.util.Set;

@RestController
public class AdminUserControllerRest {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminUserControllerRest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/test")
    public List<User> Test() {
        return userService.allUser();
    }

}
