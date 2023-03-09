package com.quizweb.controller;

import com.quizweb.domain.*;
import com.quizweb.service.HomeService;
import com.quizweb.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private HomeService homeService;
    private QuizService quizService;
    @Autowired
    public HomeController(HomeService homeService, QuizService quizService) {
        this.homeService = homeService;
        this.quizService = quizService;
    }

    @GetMapping("/home")
    public String getHome(Model model, @SessionAttribute("user") User user) {
        List<Category> categories = homeService.getAllCategory();
        List<Quiz> quizList = quizService.getQuizzesByUserId(user.getUser_id());
        model.addAttribute("categories", categories);
        model.addAttribute("quizList", quizList);
        return "home";
    }


}
