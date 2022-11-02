package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.model.User;
import com.example.wishlistapplication.model.Wish;
import com.example.wishlistapplication.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishController {

    WishRepository wishRepository;

    public WishController(WishRepository wishRep) {
        wishRepository = wishRep;
    }


    @GetMapping("/showWishesPage")
    public String showWishesPage(Model model) {
        model.addAttribute("wishAll", wishRepository.getAllWishes());
        return "wish/showWishesPage";
    }


    @GetMapping("/createWish")
    public String showCreateWish(Model model) {
        model.addAttribute("wish", new Wish());
        return "wish/createWish";
    }

    @PostMapping("/createWish")
    public String CreateWish(Wish wish){
        wishRepository.addWish(wish);
        return "redirect:/showWishesPage";
    }


    @GetMapping("/updateWishData/{wishNumber}")
    public String showUpdateWishData(@PathVariable("wishNumber") int deleteWishNumber, Model model) {
        model.addAttribute("wishes", wishRepository.findWishByNumber(deleteWishNumber));
        return "wish/updateWishData";
    }


    @PostMapping("/updateWishData/")
    public String showUpdateWishData(@ModelAttribute Wish wishes) {
        wishRepository.updateWish(wishes);
        return "redirect:/showWishesPage";
    }


    @GetMapping("/deleteWishData/{wishNumber}")
    public String deleteWishData(@PathVariable("wishNumber") int deleteWishNumber) {
        wishRepository.deleteWishById(deleteWishNumber);
        return "redirect:/showWishesPage";
    }
}
