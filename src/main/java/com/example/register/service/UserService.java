package com.example.register.service;

import com.example.register.config.MyException;
import com.example.register.model.CreateRequest;
import com.example.register.model.User;
import com.example.register.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepositories userRepositories;
    @Autowired
    EmailValidator emailValidator;



    public List<User> getUsers(){

        return userRepositories.findAll();
    }

    public String createUser(CreateRequest request){
       boolean isValidEmail = emailValidator.
               test(request.getEmail());
       if(!isValidEmail) {
           throw new IllegalStateException("Email not valid");
       }

       return UserService.
    }
    public  String signUpUser(User user){
        return "";
    }

    public User loginUser(String username,String password) throws MyException {
        Optional<User> optional = userRepositories.findByUsername(username);
        if (optional.isPresent()){
            if(password.equals(optional.get().getPassword())){
                return optional.get();}
                else { throw new MyException("Incorrect Password");
            }}
            else {throw new MyException();}
        }
        }




