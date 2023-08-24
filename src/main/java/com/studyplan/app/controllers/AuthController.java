package com.studyplan.app.controllers;

import com.studyplan.app.entities.User;
import com.studyplan.app.payload.SignInDto;
import com.studyplan.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto){
        if(!userRepo.existsByEmail(signInDto.getEmail())){
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        User userObj = userRepo.findByEmail(signInDto.getEmail());
        System.out.println(userObj);
        if(!signInDto.getPassword().equals(userObj.getPassword())){
            return new ResponseEntity<>("You entered wrong password", HttpStatus.BAD_REQUEST);
        }
        System.out.println("200 ok");
        return new ResponseEntity<>(userObj, HttpStatus.OK );
    }
}
