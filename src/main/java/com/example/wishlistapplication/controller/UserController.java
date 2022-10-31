package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.model.User;
import com.example.wishlistapplication.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRep) {
        userRepository = userRep;
    }


    @GetMapping("/registerPage")
    public String showRegisterUser(Model model) {
        model.addAttribute("user", new User());
        return "user/registerPage";
    }


    @PostMapping("/createUser")
    public String createUser(User user) {
        userRepository.addUser(user);
        return "redirect:/login";
    }

}

