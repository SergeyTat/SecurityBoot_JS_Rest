package ru.tatarinov.securityboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tatarinov.securityboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user JOIN FETCH user.roles  WHERE user.userName=:username")
    public User findByUserName(String username);

}
