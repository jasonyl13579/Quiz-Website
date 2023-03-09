package com.quizweb.rowMapper;

import com.quizweb.domain.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category c = new Category();
        c.setCategory_id(rs.getInt("category_id"));
        c.setName(rs.getString("name"));
        return c;
    }
}
