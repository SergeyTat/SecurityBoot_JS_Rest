package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tatarinov.securityboot.model.Role;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.repositories.RoleRepsitory;
import ru.tatarinov.securityboot.services.RoleService;
import ru.tatarinov.securityboot.services.UserDetailService;

import java.security.Principal;

@Controller
public class UserController {

    private final UserDetailService userDetailService;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserDetailService userDetailService, RoleRepsitory roleRepsitory, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.roleService = roleService;

        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping(value = "/admin")
    public String printUser(Model model) {
        model.addAttribute("user", userDetailService.allUser());
        return "admin";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping(value = "create")
    public String createUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userDetailService.findUser(id));
        model.addAttribute("role", roleService.allRole());
        return "edit";
    }

    @PatchMapping(value = "/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        userDetailService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/admin/{id}/remove")
    public String removeUser(@PathVariable("id") Long id) {
        userDetailService.removeUser(id);
        return "redirect:/admin";

    }

    @PostMapping(value = "/editRoleUser")
    public String editRoleUser(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {

        User user = userDetailService.findUser(userId);
        Role role = roleService.findRole(roleId);
        user.getRoles().add(role);
        userDetailService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/deleteRoleUser")
    public String deleteRoleUser(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {

        User user = userDetailService.findUser(userId);
        Role role = roleService.findRole(roleId);
        user.getRoles().removeIf(i -> (i.getId() == roleId));
        userDetailService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/user")
    public String userProfil(Model model, Principal principal) {
        model.addAttribute("user", (User) userDetailService.loadUserByUsername(principal.getName()));

        return "userProfil";
    }
}
