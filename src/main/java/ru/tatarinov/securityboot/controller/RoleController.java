package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tatarinov.securityboot.model.Role;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.services.RoleService;

@Controller
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin/roles")
    public String printPoles(Model model) {
        model.addAttribute("role", roleService.allRole());
        return "roles";
    }

    @GetMapping(value = "/admin/role/new")
    public String newUser(Model model) {
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
