package com.quizweb.dao;

import com.quizweb.domain.Choice;
import com.quizweb.domain.Question;
import com.quizweb.domain.Quiz;
import com.quizweb.domain.QuizResult;
import com.quizweb.rowMapper.QuestionRowMapper;
import com.quizweb.rowMapper.QuizResultRowMapper;
import com.quizweb.rowMapper.QuizRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuizDao {
    JdbcTemplate jdbcTemplate;
    QuizRowMapper rowMapper;

    QuizResultRowMapper quizResultRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public QuizDao(JdbcTemplate jdbcTemplate, QuizRowMapper rowMapper, QuizResultRowMapper quizResultRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.quizResultRowMapper = quizResultRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    public List<Quiz> getQuizzesByUserId(int user_id) {
        String query = "SELECT * FROM quiz where user_id = ?";
        List<Quiz> quizzes = jdbcTemplate.query(query, rowMapper, user_id);
        return quizzes;
    }
    public Quiz getQuizByQuizId(int quiz_id) {
        String query = "SELECT * FROM quiz where quiz_id = ?";
        List<Quiz> quizzes = jdbcTemplate.query(query, rowMapper, quiz_id);
        return quizzes.size() == 0 ? null : quizzes.get(0);
    }

    public int createNewQuizAndGetId(int user_id, int category_id, String name, String time_start){
        String query = "INSERT INTO Quiz (user_id, category_id, name, time_start) values (?, ?, ?, ?)";
        jdbcTemplate.update(query, user_id, category_id, name, time_start);

        // Retrieve the quiz_id from the database
        String sql = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public int updateQuizEndTime(int quiz_id, String time_end) {
        String query = "UPDATE quiz SET time_end = ? where quiz_id=?";
        return jdbcTemplate.update(query, time_end, quiz_id);
    }

    public List<QuizResult> callQuizResultSummary(Integer category_id, Integer user_id, Boolean sort_by_user, Boolean sort_by_category) {
        String query = "{ call quiz_result_summary(?, ?, ?, ?) }";
        List<QuizResult> quizResults = jdbcTemplate.query(query, quizResultRowMapper, category_id, user_id, sort_by_user, sort_by_category);
        System.out.println(quizResults);
        return quizResults;
    }
}
