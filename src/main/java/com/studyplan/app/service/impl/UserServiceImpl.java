package com.studyplan.app.service.impl;

import com.studyplan.app.entities.User;
import com.studyplan.app.repositories.UserRepository;
import com.studyplan.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

//    @Autowired
//    private RoleRepository roleRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public User saveOneUser(User user) {
//        String userOrAdmin = "ROLE_"+user.getUserRole().toUpperCase();
//        Role roles = roleRepository.findByName(userOrAdmin).get();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        Set<Role> setOfRoles = Collections.singleton(roles);
//        user.setUserRole(userOrAdmin);
        User savedUser = userRepo.save(user);
        return savedUser;
    }

    @Override
    public User getOneUser(Long userId) {
        Optional<User> userById = userRepo.findById(userId);
        User user = userById.get();
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @Override
    public Boolean deleteOneUser(Long userId) {
        Optional<User> userById = userRepo.findById(userId);
        if(userById.isPresent()){
            User user = userById.get();
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public User updateOneUSer(User user, Long userId) {
        Optional<User> userById = userRepo.findById(userId);
        if(!userById.isPresent()){
            return null;
        }else{
            User userObj = userRepo.findById(userId).get();
            userObj.setFullName(user.getFullName());
            userObj.setEmail(user.getEmail());
            User updatedUser = userRepo.save(userObj);
            return updatedUser;
        }
    }
}
