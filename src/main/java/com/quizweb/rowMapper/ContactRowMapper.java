package com.quizweb.rowMapper;

import com.quizweb.domain.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact c = new Contact();
        c.setContact_id(rs.getInt("contact_id"));
        c.setEmail(rs.getString("email"));
        c.setMessage(rs.getString("message"));
        c.setSubject(rs.getString("subject"));
        c.setTimestamp(rs.getString("timestamp"));
        return c;
    }
}
