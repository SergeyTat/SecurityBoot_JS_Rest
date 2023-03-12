package ru.tatarinov.securityboot.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tatarinov.securityboot.model.Role;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.services.RoleService;
import ru.tatarinov.securityboot.services.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class InitUserAdminTable {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitUserAdminTable(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    public void createUsers() {

        Role userRole = new Role(1L,"ROLE_USER");
        Role adminRole = new Role(2L,"ROLE_ADMIN");


        List<Role>  userRoleList = new ArrayList<>();


        roleService.addRole(userRole);
        roleService.addRole(adminRole);


        User userUser = new User("user",  "100","user@mail.ru");
        User userAdmin = new User("admin",  "100", "admin@mail.ru");

        userUser.getRoles().add(userRole);
        userAdmin.getRoles().add(adminRole);

        userService.addUser(userUser);
        userService.addUser(userAdmin);

    }
}
