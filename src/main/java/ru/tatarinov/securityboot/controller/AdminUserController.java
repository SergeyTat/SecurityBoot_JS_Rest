package ru.tatarinov.securityboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class AdminUserController {

    @GetMapping(value = "/admin")
    public String printUser() {
        return "admin/admin2";
    }


}
