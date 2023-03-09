package ru.tatarinov.securityboot.repositories;

import org.springframework.stereotype.Repository;
import ru.tatarinov.securityboot.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findByUserName(String username) {
        User user1 = (User) entityManager
                .createQuery("SELECT user FROM User user JOIN FETCH user.roles  WHERE user.userName=:username")
                .setParameter("username", username)
                .getSingleResult();
        return user1;
    }

    ;
}
