package com.quizweb.service;

import com.quizweb.dao.CategoryDao;
import com.quizweb.dao.ContactDao;
import com.quizweb.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactDao contactDao;
    @Autowired
    public ContactService(CategoryDao categoryDao, ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public void createNewContactMessage(String subject, String message, String email, String timestamp) {
        contactDao.createNewCategory(subject, message, email, timestamp);
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public Contact getContactById(int contact_id) {
        return getAllContacts().stream()
                .filter(contact -> contact.getContact_id() == contact_id)
                .findAny()
                .orElse(null);
    }
}
