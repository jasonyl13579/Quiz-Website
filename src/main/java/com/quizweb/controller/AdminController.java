package com.quizweb.controller;

import com.quizweb.domain.*;
import com.quizweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequestMapping("/admin")
@Controller
public class AdminController {

    private UserService userService;
    private QuizService quizService;
    private QuestionService questionService;
    private ContactService contactService;
    private final int page_size = 5;
    @Autowired
    public AdminController(UserService userService, QuizService quizService, QuestionService questionService, ContactService contactService) {
        this.userService = userService;
        this.quizService = quizService;
        this.questionService = questionService;
        this.contactService = contactService;
    }

    @GetMapping()
    public String getAdmin() {
        return "admin/home";
    }
    @GetMapping("user-management")
    public String getUserManagement(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "admin/user-management";
    }

    @PostMapping("user-management/{user_id}/toggle-status/")
    public String submitToggleUserStatus(@PathVariable int user_id) {
        userService.toggleActive(user_id);
        return "redirect:/admin/user-management";
    }
    @GetMapping("quiz-result-management")
    public String getQuizResultManagement(@RequestParam(name = "page", required = false) Integer page,
                                          Model model) {
        List<QuizResult> quizResults = quizService.getQuizResultSummary(null,null, null, null);

        if (page == null)
            page = 1;
        int total = quizResults.size();
        int totalPages = (int) Math.ceil((double) total / page_size);

        page = Math.min(totalPages, page);
        int startIndex = (page - 1) * page_size;
        quizResults = quizResults.subList(startIndex, Math.min(startIndex + page_size, total));

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("quizResults", quizResults);
        model.addAttribute("categories", quizService.getAllCategory());
        model.addAttribute("users", userService.getAllUsers());
        return "admin/quiz-result-management";
    }

    @PostMapping("quiz-result-management")
    public String postQuizResultManagement(Model model,
                                           @RequestParam(name = "page", required = false) Integer page,
                                           @RequestParam(name = "category_id", required = false) Integer category_id,
                                           @RequestParam(name = "user_id", required = false) Integer user_id,
                                           @RequestParam(name = "sort_by_user", required = false) Boolean sort_by_user,
                                           @RequestParam(name = "sort_by_category", required = false) Boolean sort_by_category) {
        model.addAttribute("quizResults", quizService.getQuizResultSummary(category_id, user_id, sort_by_user, sort_by_category));
        model.addAttribute("categories", quizService.getAllCategory());
        model.addAttribute("users", userService.getAllUsers());
        return "admin/quiz-result-management";
    }
    @GetMapping("question-management")
    public String getQuestionManagement(@RequestParam(name = "page", required = false) Integer page,
                                        Model model) {
        if (page == null)
            page = 1;

        List<Question> questions = questionService.getAllQuestions();
        Map<Integer, String> categoryMap = questionService.getAllCategoryMap();

        int total = questions.size();
        int totalPages = (int) Math.ceil((double) total / page_size);

        page = Math.min(totalPages, page);
        int startIndex = (page - 1) * page_size;
        questions = questions.subList(startIndex, Math.min(startIndex + page_size, total));

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("questions", questions);
        model.addAttribute("categoryMap", categoryMap);
        return "admin/question-management";
    }


    @PostMapping("question-management/{question_id}/toggle-status")
    public String submitToggleQuestionStatus(@RequestParam(name = "page", required = false) Integer page,
                                             @PathVariable int question_id) {
        questionService.toggleActive(question_id);
        return "redirect:/admin/question-management?page=" + page;
    }

    @GetMapping("question-management/{question_id}/edit")
    public String editQuestion(@RequestParam(name = "page", required = false) Integer page,
                               @PathVariable int question_id, Model model, HttpServletRequest request) {
        Question question = questionService.getQuestionAndChoiceByQuestionId(question_id);
        model.addAttribute("question", question);
        model.addAttribute("currentPage", page);
        return "/admin/question-edit";
    }
    @PostMapping("question-management/finish-edit")
    public String submitEditQuestion(@RequestParam(name = "page", required = false) Integer page,
                                     @ModelAttribute("question") Question question, Model model) {
        questionService.UpdateQuestions(new ArrayList<>(Collections.singletonList(question)));
        System.out.println(question);
        return "redirect:/admin/question-management?page=" + page;
    }

    @GetMapping("question-management/question/add")
    public String addQuestion(Model model) {
        model.addAttribute("categories", quizService.getAllCategory());
        return "/admin/question-add";
    }
    @PostMapping("question-management/finish-add")
    public String submitAddQuestion(@ModelAttribute("question") Question question, Model model) {
        System.out.println(question);
        questionService.createQuestion(question);
        return "redirect:/admin/question-management";
    }

    @GetMapping("contact-us-management")
    public String getContactManagement(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());
        return "/admin/contact-us-management";
    }

    @GetMapping("contact/view/{contact_id}")
    public String getContactManagement(@PathVariable int contact_id, Model model) {
        model.addAttribute("contact", contactService.getContactById(contact_id));
        return "/admin/contact-us-view";
    }




}
