package ru.tatarinov.securityboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.repositories.UserRepositoryDataJpa;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepositoryDataJpa userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepositoryDataJpa userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> allUser() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    @Override
    @Transactional(readOnly = true)
    public User findUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }


}
