package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.model.User;
import com.example.wishlistapplication.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository uR) {
        userRepository = uR;
    }

        @GetMapping("/registerPage")
        public String showRegisterUser() {
            return "registerPage";
        }

        @PostMapping("/registerPage")
        public String registerUser(@RequestParam("email") String email) {

            User newUser = new User();
            newUser.setEmail(email);
            System.out.println(newUser);
            //save to repository
            userRepository.addUser(newUser);
            return "redirect:/";
        }
    }

