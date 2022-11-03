package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.config.CustomUserDetails;
import com.example.wishlistapplication.model.WishList;
import com.example.wishlistapplication.repository.WishListRepository;
import com.example.wishlistapplication.repository.WishRepository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WishlistController {

    private WishListRepository wishListRepository;
    private WishRepository wishRepository;

    public WishlistController(WishListRepository wishListRep, WishRepository wishRepository) {
        wishListRepository = wishListRep;
        this.wishRepository = wishRepository;
    }

    // INDEX
    @GetMapping("/showWishListsPage")
    public String showWishListsPage(Model model) {

        CustomUserDetails authUser = CustomUserDetails.GetAuthenticatedUser();
        model.addAttribute("wishListAll", wishListRepository.findWhere("UserID", authUser.getId()));
        return "wishlist/index";
    }

    // SHOW
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
        return "wishlist/show";
    }

    // NEW
    @GetMapping("/createWishList")
    public String showCreateWishList(Model model) {
        model.addAttribute("wishList", new WishList());
        return "wishlist/createWishList";
    }

    // CREATE
    @PostMapping("/createWishList")
    public String createWishList(WishList wishList, RedirectAttributes redirectAttributes){

        CustomUserDetails authUser = CustomUserDetails.GetAuthenticatedUser();
        wishList.setUserID(authUser.getId());
        
        try {
            wishListRepository.addWishlist(wishList);
            return "redirect:/showWishListsPage";
        } catch (SQLException e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/createWishList";
        }
    }

    // EDIT
    @GetMapping("/updateWishList/{wishListID}")
    public String showUpdateWishList(@PathVariable("wishListID") int wishListID, Model model) {
        model.addAttribute("wishList", wishListRepository.findWishlistByID(wishListID));
        return "wishlist/updateWishList";
    }

    // UPDATE
    @PatchMapping("/updateWishList/")
    public String showUpdateWishList(@ModelAttribute WishList wishLists, RedirectAttributes redirectAttributes) {

        CustomUserDetails authUser = CustomUserDetails.GetAuthenticatedUser();
        WishList dbWishList = wishListRepository.findWishlistByID(wishLists.getWishListID());
        if (dbWishList.getUserID() != authUser.getId()) {
            redirectAttributes.addAttribute("error", "OPERATION NOT ALLOWED!");
            return "redirect:/showWishListsPage";
        }
        
        try {
            wishListRepository.updateWishList(wishLists);
            return "redirect:/showWishListsPage";
        } catch (SQLException e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/updateWishList/" + wishLists.getUserID();
        }
    }

    // DELETE
    @GetMapping("/deleteWishList/{wishListID}")
    public String deleteWishList(@PathVariable("wishListID") int deleteWishListID, RedirectAttributes redirectAttributes) {

        CustomUserDetails authUser = CustomUserDetails.GetAuthenticatedUser();
        WishList dbWishList = wishListRepository.findWishlistByID(deleteWishListID);
        if (dbWishList.getUserID() != authUser.getId()) {
            redirectAttributes.addAttribute("error", "OPERATION NOT ALLOWED!");
            return "redirect:/showWishListsPage";
        }

        wishListRepository.deleteWishList(deleteWishListID);
        return "redirect:/showWishListsPage";
    }
}
