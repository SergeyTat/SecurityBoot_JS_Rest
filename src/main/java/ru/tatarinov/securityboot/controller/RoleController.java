package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tatarinov.securityboot.model.Role;
import ru.tatarinov.securityboot.services.RoleService;
import ru.tatarinov.securityboot.services.UserService;

@Controller
public class RoleController {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping(value = "/admin/roles")
    public String printPoles(Model model) {
        model.addAttribute("role", roleService.allRole());
        return "/role/roles";
    }

    @GetMapping(value = "/admin/role/new")
    public String newRole(Model model) {
        model.addAttribute("role", new Role());
        return "role/newRole";
    }

    @PostMapping(value = "admin/createRole")
    public String createRole(@ModelAttribute("roles") Role role) {

        roleService.addRole(role);
        return "redirect:/admin/roles";
    }

    @GetMapping(value = "/admin/role/{id}/edit")
    public String editRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("role", roleService.findRole(id));

        return "role/newRole";
    }

    @DeleteMapping(value = "/admin/role/{id}/remove")
    public String removeRole(@PathVariable("id") Long id) {

        roleService.removeRole(id);
        return "redirect:/admin/roles";

    }

}
