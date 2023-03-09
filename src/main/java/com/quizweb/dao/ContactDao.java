package com.quizweb.dao;

import com.quizweb.domain.Contact;
import com.quizweb.rowMapper.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public class ContactDao {

    JdbcTemplate jdbcTemplate;
    ContactRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ContactDao(JdbcTemplate jdbcTemplate, ContactRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Contact> getAllContacts() {
        String query = "SELECT * FROM contact";
        List<Contact>  contact = jdbcTemplate.query(query, rowMapper);
        return contact;
    }

    public int createNewCategory(String subject, String message, String email, String timestamp){
        String query = "INSERT INTO contact (subject, message, email, timestamp) values (?, ?, ?, ?)";
        return jdbcTemplate.update(query, subject, message, email, timestamp);
    }


}
