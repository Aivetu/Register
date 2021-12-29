package com.example.register.controller;


import com.example.register.apimodel.LoginRequest;
import com.example.register.config.MyException;
import com.example.register.model.CreateRequest;
import com.example.register.model.User;
import com.example.register.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "")
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @PostMapping(value = "")
    public String createUser(@RequestBody CreateRequest request){
        return userService.createUser(request);
    }
   @PostMapping(value = "/search")
    public User getUser(@RequestBody LoginRequest loginRequest) throws MyException {
        return userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());

   }




}
