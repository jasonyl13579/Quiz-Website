package com.quizweb.controller;

import com.quizweb.domain.Question;
import com.quizweb.domain.QuestionResult;
import com.quizweb.domain.Quiz;
import com.quizweb.domain.User;
import com.quizweb.service.QuestionService;
import com.quizweb.service.QuizService;
import com.quizweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizController {

    private QuestionService questionService;
    private QuizService quizService;
    private UserService userService;
    @Autowired
    public QuizController(QuestionService questionService, QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.userService = userService;
    }

    @GetMapping("/quiz")
    public String getQuiz(Model model, @SessionAttribute("user") User user) {
        Integer quiz_id = user.getOpen_quiz_id();
        if (quiz_id == null) {
            return "redirect:/home";
        }
        List<Question> questions = quizService.getQuestionsByQuizId(quiz_id);
        Quiz quiz = quizService.getQuizByQuizId(quiz_id);
        model.addAttribute("questions", questions);
        model.addAttribute("quiz", quiz);
        return "quiz";
    }
    @GetMapping("/quiz/{quiz_id}")
    public String getQuizById(@PathVariable int quiz_id, Model model, @SessionAttribute("user") User user) {
        Integer user_quiz_id = user.getOpen_quiz_id();
        if (user_quiz_id==null || user_quiz_id != quiz_id) {
            return "redirect:/home";
        }
        return "redirect:/quiz";
    }
    @GetMapping("/quiz/new-quiz/{category_id}")
    public String getQuizByCategoryId(@PathVariable int category_id, @SessionAttribute("user") User user, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        //Question question = questionService.getQuestion();
        //model.addAttribute("question", question);
        List<Question> questions;
        Integer quiz_id = user.getOpen_quiz_id();
        System.out.println(quiz_id);
        if (quiz_id == null) {
            questions = quizService.generateQuestionsByCategoryId(category_id);
            quiz_id = quizService.generateNewQuizByCategoryId(questions, category_id, user.getUser_id());
            System.out.println(quiz_id);
            quizService.updateQuizBeforeSubmit(quiz_id, user.getUser_id());
            user.setOpen_quiz_id(quiz_id);
            session.setAttribute("user", user);
        }
        System.out.println(user);
        // if user quiz exists (not handle yet)

        /*String categoryName = quizService.getCategoryNameById(category_id);
        session.setAttribute("quiz", quizService.getQuizByQuizId(quiz_id));
        redirectAttributes.addFlashAttribute("questions", questions);
        redirectAttributes.addFlashAttribute("category", categoryName);*/
        return "redirect:/quiz/" + quiz_id;
    }
    @PostMapping("/quiz/{quiz_id}")
    public String submitQuiz(
            @PathVariable int quiz_id,
            @RequestParam(name = "q1_selectedChoiceId", required = false) Integer q1_selectedChoiceId,
            @RequestParam(name = "q2_selectedChoiceId", required = false) Integer q2_selectedChoiceId,
            @RequestParam(name = "q3_selectedChoiceId", required = false) Integer q3_selectedChoiceId,
            @RequestParam(name = "q4_selectedChoiceId", required = false) Integer q4_selectedChoiceId,
            @RequestParam(name = "q5_selectedChoiceId", required = false) Integer q5_selectedChoiceId,
            @SessionAttribute("user") User user,
            HttpSession session
    ) {
        // selectedChoiceId is assumed to be non-null
        List<Integer> selectedChoiceId = new ArrayList<Integer>(){{
            add(q1_selectedChoiceId);
            add(q2_selectedChoiceId);
            add(q3_selectedChoiceId);
            add(q4_selectedChoiceId);
            add(q5_selectedChoiceId);
        }};
        quizService.updateQuizAfterSubmit(quiz_id, LocalDateTime.now().toString(),
                selectedChoiceId, user.getUser_id());
        session.setAttribute("selectedChoiceId", selectedChoiceId);
        user.setOpen_quiz_id(null);
        session.setAttribute("user", user);
        return "redirect:/quiz/result/" + quiz_id;
    }

    @GetMapping("/quiz/result/{quiz_id}")
    public String getQuizResult(@PathVariable int quiz_id, Model model) {
        List<Question> questions = quizService.getQuestionsByQuizId(quiz_id);
        List<QuestionResult> questionResults = questionService.calculateQuestionResult(questions);
        Quiz quiz = quizService.getQuizByQuizId(quiz_id);
        model.addAttribute("quizUserName", userService.getUserById(quiz.getUser_id()).getUsername());
        model.addAttribute("quizScore", quizService.getQuizScore(questions));
        model.addAttribute("quizName", quiz.getName());
        model.addAttribute("quizQuestions", questionResults);
        //System.out.println(questionResults);
        /*Integer selectedChoiceId = (Integer) session.getAttribute("selectedChoiceId");

        Optional<Choice> selectedChoice = questionService.getChoiceById(selectedChoiceId);

        if (selectedChoice.isPresent()) {
            String result = questionService.checkAnswer(selectedChoice.get());
            model.addAttribute("selectedChoiceDescription", selectedChoice.get().getDescription());
            model.addAttribute("result", result);
        } else {
            model.addAttribute("result", "Invalid choice");
        }*/
        return "quiz-result";
    }
}
