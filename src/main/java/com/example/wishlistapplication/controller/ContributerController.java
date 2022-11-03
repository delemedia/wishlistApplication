package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.config.CustomUserDetails;
import com.example.wishlistapplication.model.Contributer;
import com.example.wishlistapplication.model.User;
import com.example.wishlistapplication.model.Wish;
import com.example.wishlistapplication.model.WishList;
import com.example.wishlistapplication.repository.ContributerRepository;
import com.example.wishlistapplication.repository.WishRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

public class ContributerController {

    ContributerRepository contributerRepository;
    WishRepository wishRepository;


    @GetMapping("/wishListSharingPage")
    public String showList(Model model) {
        return "contributer/wishListSharingPage";
    }


    // ADD CONTRIBUTER
    @GetMapping("/addContributer")
    public String addContributer(Model model) {
        model.addAttribute("contributerName", new Contributer());
        return "contributer/addContributer";
    }

    @PostMapping("/addContributer")
    public String addContributerName(Contributer contributer , Wish wish, RedirectAttributes redirectAttributes){

        contributer.setWisheswishID(wish.getWishListID());
        contributerRepository.addContributer(contributer);
        return "redirect:/wishListSharingPage";

    }


}
