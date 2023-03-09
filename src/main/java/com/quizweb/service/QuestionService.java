package com.quizweb.service;

import com.quizweb.dao.CategoryDao;
import com.quizweb.dao.ChoiceDao;
import com.quizweb.dao.QuestionDao;
import com.quizweb.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {
    private final QuestionDao questionDao;
    private final ChoiceDao choiceDao;
    private final CategoryDao categoryDao;
    @Autowired
    public QuestionService(QuestionDao questionDao, ChoiceDao choiceDao, CategoryDao categoryDao) {

        this.questionDao = questionDao;
        this.choiceDao = choiceDao;
        this.categoryDao = categoryDao;
    }

    /*public Question getQuestion() {
        return questionDao.getQuestion();
    }*/
    public Map<Integer, String> getAllCategoryMap() {
        Map<Integer, String> categoryMap = new HashMap<>();
        for (Category category : categoryDao.getAllCategory())
        {
            categoryMap.put(category.getCategory_id(), category.getName());
        }
        return categoryMap;
    }
    public List<Question> getAllQuestions() {
        List<Question> questions = questionDao.getAllQuestions();
        for (Question question: questions) {
            List<Choice> choices = choiceDao.getChoicesByQuestionId(question.getQuestion_id());
            question.setChoices(choices);

        }
        return questions;
    }
    public Question getQuestionAndChoiceByQuestionId(int question_id) {
        Question question = questionDao.getQuestionByQuestionId(question_id);
        List<Choice> choices = choiceDao.getChoicesByQuestionId(question.getQuestion_id());
        question.setChoices(choices);
        return question;
    }
    public List<QuestionResult> calculateQuestionResult(List<Question> questions) {
        List<QuestionResult> questionResults = new ArrayList<>();
        for (Question question: questions) {
            questionResults.add(new QuestionResult(
                    question, question.getUserChoiceIndex(),
                    question.getCorrectChoiceIndex(), question.isUserCorrect()));

        }
        return questionResults;
    }

    public void createQuestion(Question question) {
        int question_id = questionDao.createQuestion(question.getCategory_id(), question.getDescription());
        for (Choice choice : question.getChoices()) {
            choiceDao.createChoice(question_id, choice.getDescription(), choice.isCorrect());
        }
    }

    public void UpdateQuestions(List<Question> questions) {
        for (Question question : questions) {
            questionDao.updateQuestionDescription(question.getQuestion_id(), question.getDescription());
            for (Choice choice : question.getChoices()) {
                choiceDao.updateChoice(choice.getChoice_id(), choice.getDescription(), choice.isCorrect());
            }
        }
    }
    public boolean toggleActive(int question_id) {
        return questionDao.toggleActive(question_id) == 1;
    }
   /* public String checkAnswer(Choice selectedChoice) {
        Question question = questionDao.getQuestion();
        Choice correctChoice = question.getChoices().get(question.getCorrectChoiceId() - 1);
        return selectedChoice.equals(correctChoice) ? "Correct!" : "Incorrect";
    }

    public Optional<Choice> getChoiceById(Integer selectedChoiceId) {
        return questionDao
                .getQuestion()
                .getChoices()
                .stream()
                .filter(choice -> choice.getId() == selectedChoiceId)
                .findFirst();
    }*/

}
