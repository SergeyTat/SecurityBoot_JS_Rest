package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tatarinov.securityboot.model.Role;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.services.RoleService;
import ru.tatarinov.securityboot.services.UserService;


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
        return "admin";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping(value = "create")
    public String createUser(@ModelAttribute("user") User user) {

        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUser(id));
        model.addAttribute("role", roleService.allRole());
        return "edit";
    }

    @PatchMapping(value = "/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/admin/{id}/remove")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";

    }

    @PostMapping(value = "/editRoleUser")
    public String editRoleUser(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {

        User user = userService.findUser(userId);
        Role role = roleService.findRole(roleId);
        user.getRoles().add(role);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/deleteRoleUser")
    public String deleteRoleUser(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {

        User user = userService.findUser(userId);
        Role role = roleService.findRole(roleId);
        user.getRoles().removeIf(i -> (i.getId() == roleId));
        userService.addUser(user);
        return "redirect:/admin";
    }


    @GetMapping(value = "/admin/roles")
    public String printPoles(Model model) {
        model.addAttribute("role", roleService.allRole());
        return "roles";
    }

    @GetMapping(value = "/admin/role/new")
    public String newRole(Model model) {
        model.addAttribute("role", new Role());
        return "newRole";
    }

    @PostMapping(value = "createRole")
    public String createRole(@ModelAttribute("roles") Role role) {

        roleService.addRole(role);
        return "redirect:/admin/roles";
    }

    @GetMapping(value = "/admin/role/{id}/edit")
    public String editRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("role1", roleService.findRole(id));

        return "editRole";
    }

    @PostMapping(value = "/edit2")
    public String updateRole(@ModelAttribute("role1") Role role1) {
        roleService.addRole(role1);
        return "redirect:/admin/roles";
    }

    @DeleteMapping(value = "/admin/role/{id}/remove")
    public String removeRole(@PathVariable("id") Long id) {

        roleService.removeRole(id);
        return "redirect:/admin/roles";

    }
}
