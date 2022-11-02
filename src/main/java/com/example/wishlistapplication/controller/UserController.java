package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.model.User;
import com.example.wishlistapplication.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/registerPage")
    public String createUser(User user) {
        user.encodePassword();
        userRepository.addUser(user);
        return "redirect:/login";
    }

}

