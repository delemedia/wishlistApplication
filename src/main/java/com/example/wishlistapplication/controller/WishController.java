package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.config.CustomUserDetails;
import com.example.wishlistapplication.model.Wish;
import com.example.wishlistapplication.model.WishList;
import com.example.wishlistapplication.repository.WishListRepository;
import com.example.wishlistapplication.repository.WishRepository;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WishController {

    WishRepository wishRepository;
    WishListRepository wishListRepository;

    public WishController(WishRepository wishRep, WishListRepository wishListRepository) {
        wishRepository = wishRep;
        this.wishListRepository = wishListRepository;
    }

    // SHOW WISHES
    //@GetMapping("/showWishes")
    @GetMapping("/showWishes")
    public String showWishes(Model model) {
        model.addAttribute("wishAll", wishRepository.getAllWishes());
        return "wish/showWishesPage";
        //return "wish/showWishesPage";
    }

    // CREATE
    @GetMapping("/createWish")
    public String instantiate(Model model) {
        model.addAttribute("wish", new Wish());
        return "wish/createWish";
    }

    @PostMapping("/createWish")
    public String create(Wish wish, RedirectAttributes redirectAttributes){
        
        CustomUserDetails authUser = CustomUserDetails.GetAuthenticatedUser();
        WishList dbWishList = wishListRepository.findWishlistByID(wish.getWishListID());
        if (dbWishList.getUserID() != authUser.getId()) {
            redirectAttributes.addAttribute("error", "OPERATION NOT ALLOWED!");
            return "redirect:/createWish";
        }
        
        try {
            wishRepository.addWish(wish);
            return "redirect:/wishlists/" + dbWishList.getWishListID();
        } catch (SQLException e) {
            redirectAttributes.addAttribute("wishlist_id", dbWishList.getWishListID());
            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/createWish";
        }
    }

    // EDIT
    @GetMapping("/updateWishData/{wishNumber}")
    public String edit(@PathVariable("wishNumber") int wishNumber, Model model) {
        model.addAttribute("wish", wishRepository.findWishByNumber(wishNumber));
        return "wish/updateWishData";
    }

    // UPDATE
    @PatchMapping("/updateWishData/")
    public String update(Wish wish, RedirectAttributes redirectAttributes) {

        CustomUserDetails authUser = CustomUserDetails.GetAuthenticatedUser();
        Wish dbWish = wishRepository.findWishByNumber(wish.getWishNumber());
        WishList dbWishList = wishListRepository.findWishlistByID(dbWish.getWishListID());
        if (dbWishList.getUserID() != authUser.getId()) {
            redirectAttributes.addAttribute("error", "OPERATION NOT ALLOWED!");
            return "redirect:/showWishes";
        }

        try {
            wishRepository.updateWish(wish);
            return "redirect:/wishlists/" + wish.getWishListID();
        } catch (SQLException e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/updateWishData/" + wish.getWishNumber();
        }
    }

    // DELETE
    @GetMapping("/deleteWishData/{wishNumber}/{wishlistID}")
    public String delete(@PathVariable("wishNumber") int wishNumber, @PathVariable("wishlistID") int wishlistID, RedirectAttributes redirectAttributes) {

        CustomUserDetails authUser = CustomUserDetails.GetAuthenticatedUser();
        Wish dbWish = wishRepository.findWishByNumber(wishNumber);
        WishList dbWishList = wishListRepository.findWishlistByID(dbWish.getWishListID());
        if (dbWishList.getUserID() != authUser.getId()) {
            redirectAttributes.addAttribute("error", "OPERATION NOT ALLOWED!");
            return "redirect:/showWishes";
        }

        wishRepository.deleteWishById(wishNumber);

        return "redirect:/wishlists/" + wishlistID;
    }
}
