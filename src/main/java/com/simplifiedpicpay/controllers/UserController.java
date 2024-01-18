package com.simplifiedpicpay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplifiedpicpay.domain.user.User;
import com.simplifiedpicpay.dtos.UserDto;
import com.simplifiedpicpay.services.Interfaces.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto dto){
        User user = userService.creatUser(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> listUsers = this.userService.getAll();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }
}
