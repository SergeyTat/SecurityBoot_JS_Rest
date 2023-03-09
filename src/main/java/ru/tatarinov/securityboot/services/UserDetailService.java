package ru.tatarinov.securityboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.repositories.RoleRepsitory;
import ru.tatarinov.securityboot.repositories.UserRepository;

import java.util.List;


@Service
@Transactional
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepsitory roleRepsitory;

    @Autowired
    public UserDetailService(UserRepository userRepository, RoleRepsitory roleRepsitory) {
        this.userRepository = userRepository;
        this.roleRepsitory = roleRepsitory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username);
    }

    public List<User> allUser() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public void addUser(User user) {
        userRepository.save(user);
    }

    public User findUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }


}
