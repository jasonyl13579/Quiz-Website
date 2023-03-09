package com.quizweb.controller;

import com.quizweb.domain.User;
import com.quizweb.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
//         redirect to /quiz if user is already logged in
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/home";
        }

        return "login";
    }

    // validate that we are always getting a new session after login
    @PostMapping("/login")
    public String postLogin(@RequestParam String username,
                            @RequestParam String password,
                            HttpServletRequest request,
                            Model model) {

        Optional<User> possibleUser = loginService.validateLogin(username, password);

        if(possibleUser.isPresent()) {
            if ( !possibleUser.get().isActive()) {
                model.addAttribute("displayMessage", "User is suspended.");
                return "login";
            }
            HttpSession oldSession = request.getSession(false);
            // invalidate old session if it exists
            if (oldSession != null) oldSession.invalidate();

            // generate new session
            HttpSession newSession = request.getSession(true);

            // store user details in session
            newSession.setAttribute("user", possibleUser.get());
            if (possibleUser.get().isAdmin())
                return "redirect:/admin";
            return "redirect:/home";
        } else { // if user details are invalid
            model.addAttribute("displayMessage", "User does not exist or wrong password.");
            return "login";
        }
    }
    @GetMapping("/register")
    public String getRegister(HttpServletRequest request) {
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String firstname,
                            @RequestParam String lastname,
                            Model model) {
        System.out.println("In register");
        Optional<User> possibleUser = loginService.validateLogin(username, password);

        if(possibleUser.isPresent()) {
            model.addAttribute("errorMessage", "User already exists");
            return "register";
        } else { // if user details are invalid
            int result = loginService.registrationUser(username, firstname, lastname, password);
            System.out.println(result + " row(s) are affected.");
            model.addAttribute("displayMessage", "User register success.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();
        return "redirect:/login";
    }
}
