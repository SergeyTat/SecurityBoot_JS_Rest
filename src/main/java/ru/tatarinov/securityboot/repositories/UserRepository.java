package ru.tatarinov.securityboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tatarinov.securityboot.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUserName(String username);

}
