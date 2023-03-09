package com.quizweb.rowMapper;

import com.quizweb.domain.Question;
import com.quizweb.domain.QuizQuestion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizQuestionRowMapper implements RowMapper<QuizQuestion> {
    @Override
    public QuizQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizQuestion question = new QuizQuestion();
        question.setQq_id(rs.getInt("qq_id"));
        question.setQuiz_id(rs.getInt("quiz_id"));
        question.setQuestion_id(rs.getInt("question_id"));
        question.setUser_choice_id(rs.getInt("user_choice_id"));
        return question;
    }
}
