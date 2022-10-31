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

    public UserController(UserRepository uR) {
        userRepository = uR;
    }

    @GetMapping("/registerPage")
    public String showRegisterUser() {
        return "registerPage";
    }

    @PostMapping("/registerPage")
    public String registerUser(@RequestParam("name") String name, @RequestParam("email") String email) {

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        System.out.println(newUser);
        //save to repository
        userRepository.addUser(newUser);
        return "redirect:/";
    }

    @GetMapping("/loginPage")
    public String showUserLogIn (){
        return "loginPage";
    }

    @PostMapping("/loginPage")
    public String registerUser(@RequestParam("id") int id, @RequestParam("email") String email) {

        User existingUser = new User();
        existingUser.setId(id);
        existingUser.setEmail(email);
        System.out.println(existingUser);
        //login to user's page
        userRepository.findUserById(id);
        return "redirect:/showListPage";
    }
}

