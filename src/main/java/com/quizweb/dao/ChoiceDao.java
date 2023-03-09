package com.quizweb.dao;

import com.quizweb.domain.Choice;
import com.quizweb.rowMapper.ChoiceRowMapper;
import com.quizweb.rowMapper.QuestionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChoiceDao {
    JdbcTemplate jdbcTemplate;
    ChoiceRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public ChoiceDao(JdbcTemplate jdbcTemplate, ChoiceRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    public List<Choice> getChoicesByQuestionId(int question_id) {
        String query = "SELECT * FROM choice where question_id = ?";
        List<Choice> choices = jdbcTemplate.query(query, rowMapper, question_id);
        //System.out.println(choices);
        return choices;
    }

    public int createChoice(int question_id, String description, boolean is_correct) {
        String query = "INSERT INTO choice (question_id, description, is_correct) values (?, ?, ?)";
        return jdbcTemplate.update(query, question_id, description, is_correct);
    }

    public int updateChoice(int choice_id, String description, boolean is_correct) {
        String query = "UPDATE choice SET description = ?, is_correct = ? where choice_id = ?";
        return jdbcTemplate.update(query, description, is_correct, choice_id);
    }
}
