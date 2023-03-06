package ru.tatarinov.securityboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tatarinov.securityboot.model.User;
import ru.tatarinov.securityboot.repositories.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username);
    }
    public List<User> allUser(){
       return userRepository.findAll();
    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public User findUser(Long id){
        return userRepository.findById(id).isEmpty()?null:userRepository.findById(id).get();
    }
    public void removeUser(Long id){
        userRepository.deleteById(id);
    }
}
