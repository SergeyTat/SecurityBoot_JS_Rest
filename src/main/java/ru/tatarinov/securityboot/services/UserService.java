package ru.tatarinov.securityboot.services;

import ru.tatarinov.securityboot.model.User;

import java.util.List;

public interface UserService {
    public List<User> allUser();
    public void addUser(User user);
    public User findUser(Long id);
    public void removeUser(Long id);
}
