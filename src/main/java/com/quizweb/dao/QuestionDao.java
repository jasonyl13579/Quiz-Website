package com.quizweb.dao;

import com.quizweb.domain.Choice;
import com.quizweb.domain.Question;
import com.quizweb.domain.User;
import com.quizweb.rowMapper.QuestionRowMapper;
import com.quizweb.rowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDao {
    JdbcTemplate jdbcTemplate;
    QuestionRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final Question question;
    private static final List<Choice> choices;

    static {
        choices = new ArrayList<>();
        choices.add(new Choice(1, 1, "42", false));
        choices.add(new Choice(2, 1, "correct answer", true));
        choices.add(new Choice(3, 1, "yes", true));
        question = new Question(
                1,
                1,
                "What is the correct answer?",
                true,
                choices,
                1);
    }
    @Autowired
    public QuestionDao(JdbcTemplate jdbcTemplate, QuestionRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    public List<Question> getAllQuestions() {
        String query = "SELECT * FROM question";
        List<Question> questions = jdbcTemplate.query(query, rowMapper);
        return questions;
    }
    public List<Question> getQuestionsByCategoryIdAndActive(int category_id, boolean is_active) {
        String query = "SELECT * FROM question where category_id = ? And is_active = ?";
        List<Question> questions = jdbcTemplate.query(query, rowMapper, category_id, is_active);
        return questions;
    }

    public Question getQuestionByQuestionId(int question_id) {
        String query = "SELECT * FROM question where question_id = ?";
        List<Question> questions = jdbcTemplate.query(query, rowMapper, question_id);
        return questions.size() == 0 ? null : questions.get(0);
    }
    public int createQuestion(int category_id, String description) {
        String query = "INSERT INTO question (category_id, description) values (?, ?)";
        jdbcTemplate.update(query, category_id, description);

        String sql = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public int updateQuestionDescription(int question_id, String description) {
        String query = "UPDATE question SET description = ? where question_id = ?";
        return jdbcTemplate.update(query, description, question_id);
    }
    public int toggleActive(int question_id) {
        String query = "UPDATE question SET is_active = NOT is_active WHERE question_id = ?";
        return jdbcTemplate.update(query, question_id);
    }
}
