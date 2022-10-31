package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.model.Wish;
import com.example.wishlistapplication.model.WishList;
import com.example.wishlistapplication.repository.WishListRepository;
import com.example.wishlistapplication.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishlistController {

    WishRepository wishRepository;
    WishListRepository wishListRepository;

    public WishlistController(WishRepository w, WishListRepository c) {
        wishRepository = w;
        wishListRepository = c;
    }


    @GetMapping("/")
    public String showIndex() {
        return "showIndex";
    }


    // showWishListsPage - Controller Content.
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------


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


    // showWishesPage - Controller Content.
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------


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


    // Den ekstra kode del jeg forholdte mig til
    // Har udkommenteret koden, for at det ikke forstyrre, de andre kode elementer.

    // showWishesPage2 - Controller Content.
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------

/*
    @GetMapping("/showWishesPage2")
    public String showCreateWishes2 (Model model) {
        model.addAttribute("wishAll2", wishlistRepository2.getAll2());
        return "showWishesPage2";

    }


    @GetMapping("/createWish2")
    public String showCreateWishes2 () {

        return "createWish2";
    }

    @PostMapping("/createWish2")
    public String showCreateWishes2 (@RequestParam("wishDescription2") String wishDescription2) {

        System.out.println(wishDescription2);
        WishData2 newWishData2 = new WishData2();
        newWishData2.setWishDescription2(wishDescription2);
        System.out.println(newWishData2);

        wishlistRepository2.addWishData2(newWishData2);
        return "redirect:/showWishesPage2";
    }


    @GetMapping("/updateWishData2/{wishNumber2}")
    public String showUpdateWishData2(@PathVariable("wishNumber2") int deleteWishNumber2, Model model) {

        model.addAttribute("wishes2", wishlistRepository2.findWishDataByNumber2(deleteWishNumber2));

        return "updateWishData2";

    }

    @PostMapping("/updateWishData2/")
    public String showUpdateWishData2(@ModelAttribute WishData2 wishes2) {

        wishlistRepository2.updateWishData2(wishes2);

        return "redirect:/showWishesPage2";

    }

    @GetMapping ("/deleteWishData2/{wishNumber2}")
    public String deleteWishData2(@PathVariable("wishNumber2") int deleteWishNumber2) {

        wishlistRepository2.deleteWishDataByNumber2(deleteWishNumber2);

        return "redirect:/showWishesPage2";

    }


*/

