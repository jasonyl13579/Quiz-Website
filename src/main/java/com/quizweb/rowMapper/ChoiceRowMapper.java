package com.quizweb.rowMapper;

import com.quizweb.domain.Choice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChoiceRowMapper implements RowMapper<Choice> {
    @Override
    public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Choice choice = new Choice();
        choice.setChoice_id(rs.getInt("choice_id"));
        choice.setDescription(rs.getString("description"));
        choice.setCorrect(rs.getBoolean("is_correct"));
        choice.setQuestion_id(rs.getInt("question_id"));
        return choice;
    }
}
