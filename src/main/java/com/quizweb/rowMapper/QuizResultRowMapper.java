package com.quizweb.rowMapper;

import com.quizweb.domain.QuizResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizResultRowMapper implements RowMapper<QuizResult> {
    @Override
    public QuizResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizResult quizResult = new QuizResult();
        quizResult.setFullname(rs.getString("fullname"));
        quizResult.setTime_taken(rs.getString("time_taken"));
        quizResult.setCount(rs.getInt("count"));
        quizResult.setScore(rs.getFloat("score"));
        quizResult.setCategory_name(rs.getString("category_name"));
        quizResult.setQuiz_id(rs.getInt("quiz_id"));
        return quizResult;
    }
}
