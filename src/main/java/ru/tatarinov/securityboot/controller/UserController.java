package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String printCar(Model model) {
        model.addAttribute("user", userDetailService.allUser());
        return "admin";
    }
    @GetMapping(value = "/new")
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
}
