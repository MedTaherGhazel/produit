package com.iset.produit.service;

import com.iset.produit.DAO.RoleRepository;
import com.iset.produit.DAO.UserRepository;
import com.iset.produit.controllers.UserController;
import com.iset.produit.entities.Role;
import com.iset.produit.entities.User;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private  final PasswordEncoder bCryptPasswordEncoder;
    private UserController userController;

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }


    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder bCryPasswordEncoder, UserController userController) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryPasswordEncoder;
        this.userController = userController;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        Objects.requireNonNull(username);
        User user = userRepository.findUserWithName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
    public User saveUser(String username, String password, String confirmedPassword) {
        User appUser = new User();
        if (userRepository.findUserWithName(username).isPresent() == true)
            throw new RuntimeException("User already exists");
        if (!password.equals(confirmedPassword))
            throw new RuntimeException("Please confirm your password");
        appUser.setUsername(username);
        Set<Role> roles = new HashSet<Role>();
        Role r = new Role("ROLE_USER");
        roleRepository.save(r);
        roles.add(r);
        appUser.setRoles(roles);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(appUser);
        return appUser;
    }


}