package com.quizweb.service;

import com.quizweb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final UserService userService;

    @Autowired
    public LoginService(UserService userService) {this.userService = userService; }

    public Optional<User> validateLogin(String username, String password) {
        return userService.validateLogin(username, password);
    }

    public int registrationUser(String username, String firstname, String lastname, String password){
        return userService.createNewUser(username, firstname, lastname, password);
    }

}
