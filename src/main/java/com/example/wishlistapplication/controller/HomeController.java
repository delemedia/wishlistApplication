package com.example.wishlistapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {



    @GetMapping("/")
    public String showIndex() {
        return "home/showIndex";
    }
}
