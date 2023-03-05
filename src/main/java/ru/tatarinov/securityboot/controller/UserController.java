package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
