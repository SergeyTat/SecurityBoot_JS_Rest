package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tatarinov.securityboot.model.Role;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.services.RoleService;
import ru.tatarinov.securityboot.services.UserService;

import java.util.Set;


@Controller
public class AdminUserController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
       }


    @GetMapping(value = "/admin")
    public String printUser(Model model) {
        model.addAttribute("user", userService.allUser());
        return "/admin/admin";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles1", roleService.allRole());

        return "admin/new";
    }

    @PostMapping(value = "/admin/create")
    public String createUser(@ModelAttribute("user") User user) {
        System.out.println(user);

        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUser(id));
        model.addAttribute("roles1", roleService.allRole());
        return "admin/new";
    }

    @DeleteMapping(value = "/admin/{id}/remove")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";

    }

}
