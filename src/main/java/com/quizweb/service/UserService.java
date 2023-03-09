package com.quizweb.service;

import com.quizweb.dao.UserDao;
import com.quizweb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) { this.userDao = userDao; }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(int id) {
        return userDao.getAllUsers().stream()
                .filter(a -> a.getUser_id() == id)
                .findFirst()
                .orElse(new User(-1, "invalid", "invalid","invalid","invalid",true,true,-1));
    }

    public Optional<User> validateLogin(String username, String password) {
        return userDao.getAllUsers().stream()
                .filter(a -> a.getUsername().equals(username)
                        && a.getPassword().equals(password))
                .findAny();
    }

    public int createNewUser(String username, String firstname, String lastname, String password) {
        return userDao.createNewUser(username, firstname, lastname, password);
    }

    public boolean toggleActive(int user_id) {
        return userDao.toggleActive(user_id) == 1;
    }




}
