package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.model.WishList;
import com.example.wishlistapplication.repository.WishListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishlistController {

    WishListRepository wishListRepository;

    public WishlistController(WishListRepository wishListRep) {
        wishListRepository = wishListRep;
    }

    @GetMapping("/showWishListsPage")
    public String showWishListsPage(Model model) {
        model.addAttribute("wishListAll", wishListRepository.getAllWishLists());
        return "wishlist/showWishListsPage";
    }


    @GetMapping("/createWishList")
    public String showCreateWishList(Model model) {
        model.addAttribute("wishList", new WishList());
        return "wishlist/createWishList";
    }


    @PostMapping("/createWishList")
    public String createWishList(WishList wishList){
        System.out.println(wishList.getWishlistName());
        wishListRepository.addWishlist(wishList);
        return "redirect:/showWishListsPage";
    }


    @GetMapping("/updateWishList/{wishListID}")
    public String showUpdateWishList(@PathVariable("wishListID") int deleteWishListID, Model model) {
        model.addAttribute("categories", wishListRepository.findWishlistByID(deleteWishListID));
        return "wishlist/updateWishList";

    }


    @PostMapping("/updateWishList/")
    public String showUpdateWishList(@ModelAttribute WishList wishLists) {
        wishListRepository.updateWishList(wishLists);
        return "redirect:/showWishListsPage";
    }


    @GetMapping("/deleteWishList/{wishListID}")
    public String deleteWishList(@PathVariable("wishListID") int deleteWishListID) {
        wishListRepository.deleteWishList(deleteWishListID);
        return "redirect:/showWishListsPage";

    }
}
