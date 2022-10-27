package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.model.WishData;
import com.example.wishlistapplication.repository.WishlistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishlistController {

    WishlistRepository wishlistRepository;

    public WishlistController(WishlistRepository w) {

        wishlistRepository = w;
    }

    @GetMapping("/")
    public String showIndex() {


        return "showIndex";

    }

    @GetMapping("/showListPage")
    public String showListPage () {

        return "showListPage";
    }

    @GetMapping("/showWishesPage")
        public String showCreateWishlist (Model model) {
        model.addAttribute("wishAll", wishlistRepository.getAll());
            return "showWishesPage";

        }

    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------


    @GetMapping("/createWish")
    public String showCreateWish () {

        return "createWish";
    }

    @PostMapping("/createWish")
    public String showCreateWish (@RequestParam("wishDescription") String wishDescription) {

        System.out.println(wishDescription);
        WishData newWishData = new WishData();
        newWishData.setWishDescription(wishDescription);
        System.out.println(newWishData);

        wishlistRepository.addWishData(newWishData);
        return "redirect:/showWishesPage";
    }


    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
   // ------------------------------------------------------------------------

    @GetMapping("/updateWishData/{wishNumber}")
    public String showUpdateWishData(@PathVariable("wishNumber") int deleteWishNumber, Model model) {

        model.addAttribute("wishes", wishlistRepository.findWishDataByNumber(deleteWishNumber));

        return "updateWishData";

    }

    @PostMapping("/updateWishData/")
    public String showUpdateWishData(@ModelAttribute WishData wishes) {

        wishlistRepository.updateWishData(wishes);

        return "redirect:/showWishesPage";

    }

    @GetMapping ("/deleteWishData/{wishNumber}")
    public String deleteWishData(@PathVariable("wishNumber") int deleteWishNumber) {

        wishlistRepository.deleteWishDataByNumber(deleteWishNumber);

        return "redirect:/showWishesPage";

    }


    }


