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


    @GetMapping("/")
    public String showIndex() {
        return "showIndex";
    }


    @GetMapping("/showWishListsPage")
    public String showWishListsPage(Model model) {
        model.addAttribute("listAll", wishListRepository.getAllWishLists()); //attribute name??
        return "showWishListsPage";
    }


    @GetMapping("/createWishList")
    public String showCreateWishList() {
        return "createWishList";
    }


    @PostMapping("/createWishList")
    public String showCreateWishList(@RequestParam("wishlistName") String wishlistName) {

        System.out.println(wishlistName);
        WishList newWishList = new WishList();
        newWishList.setWishlistName(wishlistName);
        System.out.println(newWishList);

        wishListRepository.addWishlist(newWishList);
        return "redirect:/showListPage";
    }


    @GetMapping("/updateWishList/{wishListID}")
    public String showUpdateWishList(@PathVariable("wishListID") int deleteWishListID, Model model) {
        model.addAttribute("categories", wishListRepository.findWishlistByID(deleteWishListID));
        return "updateWishList";

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
