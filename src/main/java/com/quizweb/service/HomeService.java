package com.quizweb.service;

import com.quizweb.dao.CategoryDao;
import com.quizweb.dao.ContactDao;
import com.quizweb.domain.Category;
import com.quizweb.domain.Choice;
import com.quizweb.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HomeService {
    private final CategoryDao categoryDao;
    private final ContactDao contactDao;
    @Autowired
    public HomeService(CategoryDao categoryDao, ContactDao contactDao) {
        this.categoryDao = categoryDao;
        this.contactDao = contactDao;
    }
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }
    public List<String> getAllCategoryName() {
        return categoryDao.getAllCategory().stream()
                .map(c->c.getName())
                .collect(Collectors.toList());
    }

}
