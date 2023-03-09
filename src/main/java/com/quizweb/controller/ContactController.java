package com.quizweb.controller;

import com.quizweb.domain.User;
import com.quizweb.service.ContactService;
import com.quizweb.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @GetMapping("/contact")
    public String getContact(Model model) {
        return "contact";
    }

    @PostMapping("/contact")
    public String postContactUs(@RequestParam String subject,
                               @RequestParam String message,
                               @RequestParam String email,
                               @RequestParam String timestamp,
                               Model model) {
        contactService.createNewContactMessage(subject, message, email, timestamp);
        model.addAttribute("displayMessage", "We received your message.");
        return "contact";
    }

}
