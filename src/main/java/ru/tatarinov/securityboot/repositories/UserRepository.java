package ru.tatarinov.securityboot.repositories;

import org.springframework.stereotype.Repository;
import ru.tatarinov.securityboot.model.User;

public interface UserRepository {
    public User findByUserName(String username);

}
