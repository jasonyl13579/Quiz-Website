package com.quizweb.dao;

import com.quizweb.domain.User;
import com.quizweb.rowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public class UserDao {

    JdbcTemplate jdbcTemplate;
    UserRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM user";

        List<User> users = jdbcTemplate.query(query, rowMapper);
        System.out.println(users);
        return users;
    }

    public int createNewUser(String username, String firstname, String lastname, String password){
        String query = "INSERT INTO user (username, firstname, lastname, password) values (?, ?, ?, ?)";
        return jdbcTemplate.update(query, username, firstname, lastname, password);
    }

    public int updateUserOpenQuiz(Integer quiz_id, int user_id) {
        String query = "UPDATE user SET open_quiz_id = ? where user_id= ?";
        return jdbcTemplate.update(query, quiz_id, user_id);
    }

    public int toggleActive(int user_id) {
        String query = "UPDATE user SET is_active = NOT is_active WHERE user_id = ?";
        return jdbcTemplate.update(query, user_id);
    }
}
