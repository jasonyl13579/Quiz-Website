package com.quizweb.dao;

import com.quizweb.domain.QuizQuestion;
import com.quizweb.rowMapper.QuizQuestionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizQuestionDao {
    JdbcTemplate jdbcTemplate;
    QuizQuestionRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public QuizQuestionDao(JdbcTemplate jdbcTemplate, QuizQuestionRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    public List<QuizQuestion> getQuestionsByQuizId(int quiz_id) {
        String query = "SELECT * FROM quizQuestion where quiz_id = ?" ;
        List<QuizQuestion> quizQuestions = jdbcTemplate.query(query, rowMapper, quiz_id);
        return quizQuestions;
    }
    public int createNewQuizQuestion(int quiz_id, int question_id) {
        String query = "INSERT INTO quizQuestion (quiz_id, question_id) values (?, ?)";
        return jdbcTemplate.update(query, quiz_id, question_id);
    }

    public int updateQuestionUserChoice(int quiz_id, int choice_id) {
        String query = "SELECT a.question_id from question as a \n" +
                "INNER JOIN choice c on a.question_id=c.question_id\n" +
                "where choice_id = ?";
        int question_id = jdbcTemplate.queryForObject(query, Integer.class, choice_id);
        query = "UPDATE quizquestion SET user_choice_id = ? where quiz_id=? and question_id = ?";
        return jdbcTemplate.update(query, choice_id, quiz_id, question_id);
    }

}
