package com.studyplan.app.controllers;

import com.studyplan.app.entities.User;
import com.studyplan.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserRepository userRepo;


    //http://localhost:8080/api/users/auth/signUp
    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody User user){
        if(userRepo.existsByEmail(user.getEmail())){
            return new ResponseEntity<>("User already exists with this email", HttpStatus.BAD_REQUEST);
        }

//        User savedUser = userService.saveOneUser(user);
        User savedUser = userRepo.save(user);
        return new ResponseEntity(savedUser, HttpStatus.OK);
    }

    //http://localhost:8080/api/users/signIn


    //http://localhost:8080/api/users/{userId}/user
    @GetMapping("/{userId}/user")
    public ResponseEntity<?> getSingleUser(@PathVariable(value = "userId") Long userId){
        Optional<User> userById = userRepo.findById(userId);
        User user = userById.get();
        if(user == null){
            return new ResponseEntity<>("User not found with this Id: "+userId, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    //    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<User>>  allUsers(){
        List<User> users = userRepo.findAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @PutMapping("/{userId}/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable(value = "userId")Long userId){
        Optional<User> userById = userRepo.findById(userId);
        if(!userById.isPresent()){
            return null;
        }else{
            User userObj = userRepo.findById(userId).get();
            userObj.setFullName(user.getFullName());
            userObj.setEmail(user.getEmail());
            User updatedUser = userRepo.save(userObj);
            return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);

        }
    }

    @DeleteMapping("/{userId}/deleteUser")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId")Long userId){
        Optional<User> userById = userRepo.findById(userId);
        if(userById.isPresent()){
            User user = userById.get();
            userRepo.deleteById(userId);
        }
        return new ResponseEntity<>("User deleted Successfully", HttpStatus.OK);
    }
}
