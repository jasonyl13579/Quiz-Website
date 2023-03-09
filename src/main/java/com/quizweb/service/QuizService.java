package com.quizweb.service;

import com.quizweb.dao.*;
import com.quizweb.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuizService {
    private final QuizDao quizDao;
    private final QuizQuestionDao quizQuestionDao;
    private final QuestionDao questionDao;
    private final ChoiceDao choiceDao;
    private final CategoryDao categoryDao;
    private final UserDao userDao;
    @Autowired
    public QuizService(QuizDao quizDao, QuizQuestionDao quizQuestionDao, QuestionDao questionDao,
                       ChoiceDao choiceDao, CategoryDao categoryDao, UserDao userDao) {
        this.quizDao = quizDao;
        this.quizQuestionDao = quizQuestionDao;
        this.choiceDao = choiceDao;
        this.questionDao = questionDao;
        this.categoryDao = categoryDao;
        this.userDao = userDao;
    }
    public int generateNewQuizByCategoryId(List<Question> questions, int category_id, int user_id) {

        int quiz_id = quizDao.createNewQuizAndGetId(user_id,
                category_id,
                this.getCategoryNameById(category_id),
                LocalDateTime.now().toString()
        );
        this.createNewQuizQuestion(quiz_id, questions);
        return quiz_id;
    }

    public List<Question> generateQuestionsByCategoryId(int category_id) {
        List<Question> questions = questionDao.getQuestionsByCategoryIdAndActive(category_id, true);
        Collections.shuffle(questions);
        questions = questions.subList(0, 5);
        for (Question question : questions) {
            List<Choice> choices = choiceDao.getChoicesByQuestionId(question.getQuestion_id());
            question.setChoices(choices);
        }
        return questions;
    }
    public String getCategoryNameById(int category_id) {
        return categoryDao.getCategoryById(category_id).getName();
    }
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }
    public List<Question> getQuestionsByQuizId(int quiz_id) {
        List<QuizQuestion> quizQuestions = quizQuestionDao.getQuestionsByQuizId(quiz_id);
        List<Question> questions = new ArrayList<>();
        for (QuizQuestion quizQuestion: quizQuestions){
            Question question = questionDao.getQuestionByQuestionId(quizQuestion.getQuestion_id());
            List<Choice> choices = choiceDao.getChoicesByQuestionId(question.getQuestion_id());
            //System.out.println(choices);
            question.setChoices(choices);
            question.setUser_choice_id(quizQuestion.getUser_choice_id());
            questions.add(question);
        }
        return questions;
    }
    public float getQuizScore(List<Question> questions) {
        float score = 0;
        for (Question question : questions) {
            if (question.isUserCorrect()) {
                score += 1;
            }
        }
        return score * 100 / questions.size();
    }
    public List<Quiz> getQuizzesByUserId(int user_id) {
        return quizDao.getQuizzesByUserId(user_id);
    }
    public int getUserIdByQuizId(int quiz_id) {
        return quizDao.getQuizByQuizId(quiz_id).getUser_id();
    }
    public Quiz getQuizByQuizId(int quiz_id) {
        return quizDao.getQuizByQuizId(quiz_id);
    }

    public List<QuizResult> getQuizResultSummary(Integer category_id, Integer user_id, Boolean sort_by_user, Boolean sort_by_category) {
        return quizDao.callQuizResultSummary(category_id, user_id, sort_by_user, sort_by_category);
    }

    public void updateQuizAfterSubmit(Integer quiz_id, String time_end, List<Integer> selectedChoiceId, int user_id) {
        this.updateQuizEndTime(quiz_id, time_end);
        this.updateQuestionChoice(quiz_id, selectedChoiceId);
        userDao.updateUserOpenQuiz(null, user_id);
    }
    public void updateQuizBeforeSubmit(Integer quiz_id, int user_id){
        userDao.updateUserOpenQuiz(quiz_id, user_id);
    }
    private void updateQuestionChoice(int quiz_id, List<Integer> selectedChoiceId) {
        for (Integer choice_id : selectedChoiceId) {
            if (choice_id == null) {
                continue;
            }
            quizQuestionDao.updateQuestionUserChoice(quiz_id, choice_id);
        }
    }

    private void updateQuizEndTime(int quiz_id, String time_end) {
        quizDao.updateQuizEndTime(quiz_id, time_end);
    }
    private void createNewQuizQuestion(int quiz_id, List<Question> questions) {
        for (Question question : questions) {
            quizQuestionDao.createNewQuizQuestion(quiz_id, question.getQuestion_id());
        }
    }



}
