package com.example.wishlistapplication.controller;

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
    public String showCreateWishes(Model model) {
        model.addAttribute("wishAll", wishRepository.getAllWishes());
        return "showWishesPage";
    }


    @GetMapping("/createWish")
    public String showCreateWishes() {

        return "createWish";
    }


    @PostMapping("/createWish")
    public String showCreateWishes(@RequestParam("wishDescription") String wishDescription) {

        System.out.println(wishDescription);
        Wish newWish = new Wish();
        newWish.setWishDescription(wishDescription);
        System.out.println(newWish);
        wishRepository.addWish(newWish);
        return "redirect:/showWishesPage";
    }


    @GetMapping("/updateWishData/{wishNumber}")
    public String showUpdateWishData(@PathVariable("wishNumber") int deleteWishNumber, Model model) {
        model.addAttribute("wishes", wishRepository.findWishByNumber(deleteWishNumber));
        return "updateWishData";
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
