package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.services.UserDetailService;

@Controller
public class UserController {

    private final UserDetailService userDetailService;

    @Autowired
    public UserController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
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
        System.out.println(user);
        userDetailService.addUser(user);
        return "redirect:/admin";
    }
    @GetMapping(value = "/admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userDetailService.findUser(id));
        return "edit";
    }

    @PatchMapping(value = "/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        userDetailService.addUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping(value = "/admin/{id}/remove")
    public String removeUser(@PathVariable("id") Long id) {
        System.out.println(id);
      userDetailService.removeUser(id);
        return "redirect:/admin";

    }
}
