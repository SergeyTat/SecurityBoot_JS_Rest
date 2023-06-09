package ru.tatarinov.securityboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.services.RoleService;
import ru.tatarinov.securityboot.services.UserService;

import java.util.List;


@RestController
public class AdminUserControllerRest {
    private final UserService userService;



    @Autowired
    public AdminUserControllerRest(UserService userService) {
        this.userService = userService;

    }

    @GetMapping(value = "/admin/users")
    public ResponseEntity<List<User>> Users() {
        List<User> users = userService.allUser();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @PostMapping(value = "/admin/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/admin/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping(value = "/admin/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findUser(id));
    }

    @DeleteMapping(value = "/admin/{id}")
    public ResponseEntity<User> removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/usertab")
    public ResponseEntity<User> getAuthorisedUser() {
        return ResponseEntity.ok((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    }

}
