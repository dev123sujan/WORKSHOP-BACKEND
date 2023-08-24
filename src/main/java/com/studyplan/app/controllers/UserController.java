package com.studyplan.app.controllers;

import com.studyplan.app.entities.User;
import com.studyplan.app.payload.SignInDto;
import com.studyplan.app.repositories.UserRepository;
import com.studyplan.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    //http://localhost:8080/api/users/auth/signUp
    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody User user){
        if(userRepository.existsByEmail(user.getEmail())){
            return new ResponseEntity<>("User already exists with this email", HttpStatus.BAD_REQUEST);
        }
        User savedUser = userService.saveOneUser(user);
        return new ResponseEntity(savedUser, HttpStatus.OK);
    }


    //http://localhost:8080/api/users/{userId}/user
    @GetMapping("/{userId}/user")
    public ResponseEntity<?> getSingleUser(@PathVariable(value = "userId") Long userId){
        User oneUser = userService.getOneUser(userId);
        if(oneUser == null){
            return new ResponseEntity<>("User not found with this Id: "+userId, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(oneUser);
    }

//    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<User>>  allUsers(){
        List<User> allUsers = userService.getAllUser();
        return new ResponseEntity(allUsers, HttpStatus.OK);
    }

    @PutMapping("/{userId}/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable(value = "userId")Long userId){
        User updatedUser = userService.updateOneUSer(user, userId);
        if(updatedUser == null){
            return new ResponseEntity<>("User not found with this Id: "+userId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/deleteUser")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId")Long userId){
        Boolean deleted = userService.deleteOneUser(userId);
        if(!deleted){
            return new ResponseEntity<>("User not found with this Id: "+userId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User deleted Successfully", HttpStatus.OK);
    }



    //http://localhost:8080/api/users/signIn
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto){
        System.out.println(signInDto);
        if(!userRepository.existsByEmail(signInDto.getEmail())){
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        User userObj = userRepository.findByEmail(signInDto.getEmail());
        System.out.println(userObj);
        if(!signInDto.getPassword().equals(userObj.getPassword())){
            return new ResponseEntity<>("You entered wrong password", HttpStatus.BAD_REQUEST);
        }
        System.out.println("200 ok");
        return new ResponseEntity<>(userObj, HttpStatus.OK );
    }
}
