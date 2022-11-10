package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.config.CustomUserDetails;
import com.example.wishlistapplication.model.Contributer;
import com.example.wishlistapplication.model.User;
import com.example.wishlistapplication.model.Wish;
import com.example.wishlistapplication.model.WishList;
import com.example.wishlistapplication.repository.ContributerRepository;
import com.example.wishlistapplication.repository.WishListRepository;
import com.example.wishlistapplication.repository.WishRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

public class ContributerController {

    ContributerRepository contributerRepository;
    WishRepository wishRepository;
    WishListRepository wishListRepository;

    @GetMapping("/shareList")
    public String shareList(Model model)  {

        return "contributer/wishListSharingPage";
    }

    @GetMapping("/wishlists/{id}")
    public String show(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        CustomUserDetails authUser = CustomUserDetails.GetAuthenticatedUser();
        WishList dbWishList = wishListRepository.findWishlistByID(id);
        if (dbWishList.getUserID() != authUser.getId()) {
            redirectAttributes.addAttribute("error", "OPERATION NOT ALLOWED!");
            return "redirect:/";
        }

        model.addAttribute("wishList", dbWishList);
        model.addAttribute("wishes", wishRepository.findWhere("WishListID", dbWishList.getWishListID()));
        return "wish/showWishlistWishes";
    }


    // ADD CONTRIBUTER
    @GetMapping("/addContributer")
    public String addContributer(Model model) {
        model.addAttribute("contributerName", new Contributer());
        return "contributer/addContributer";
    }

    @PostMapping("/addContributer")
    public String addContributerName(Contributer contributer , int WisheswishID, RedirectAttributes redirectAttributes){
        CustomUserDetails authUser = CustomUserDetails.GetAuthenticatedUser();
        Contributer dbContributer = contributerRepository.findContributerByWisheswishID(WisheswishID);
        Wish dbWish = wishRepository.findWishByNumber(dbContributer.getWisheswishID());
        WishList dbWishList = wishListRepository.findWishlistByID(dbWish.getWishListID());
        if (dbWishList.getWishListID() != authUser.getId()) {
            redirectAttributes.addAttribute("error", "OPERATION NOT ALLOWED!");
            return "redirect:/addContributer";
        }
        contributerRepository.addContributer(contributer);
        return "redirect:/contributer/" + dbWishList.getWishListID(); //??
    }



}
