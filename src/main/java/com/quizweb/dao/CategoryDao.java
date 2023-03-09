package com.quizweb.dao;

import com.quizweb.domain.Category;
import com.quizweb.rowMapper.CategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public class CategoryDao {

    JdbcTemplate jdbcTemplate;
    CategoryRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CategoryDao(JdbcTemplate jdbcTemplate, CategoryRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public List<Category> getAllCategory() {
        String query = "SELECT * FROM category";

        List<Category> categories = jdbcTemplate.query(query, rowMapper);
        return categories;
    }
    public Category getCategoryById(int id) {
        String query = "SELECT * FROM category where category_id = ?";
        List<Category> categories =  jdbcTemplate.query(query, rowMapper, id);
        return categories.size() == 0 ? null : categories.get(0);
    }

    public int createNewUser(String username, String firstname, String lastname, String password){
        String query = "INSERT INTO user (username, firstname, lastname, password) values (?, ?, ?, ?)";
        return jdbcTemplate.update(query, username, firstname, lastname, password);
    }

}
