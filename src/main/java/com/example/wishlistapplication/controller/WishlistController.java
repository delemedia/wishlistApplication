package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.model.CategoriData;
import com.example.wishlistapplication.model.WishData;
import com.example.wishlistapplication.model.WishData2;
import com.example.wishlistapplication.repository.CategoriRepository;
import com.example.wishlistapplication.repository.WishlistRepository;
import com.example.wishlistapplication.repository.WishlistRepository;
import com.example.wishlistapplication.repository.WishlistRepository2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishlistController {

    WishlistRepository wishlistRepository;

    WishlistRepository2 wishlistRepository2;
    CategoriRepository categoriRepository;

    public WishlistController(WishlistRepository w, WishlistRepository2 w2, CategoriRepository c) {

        wishlistRepository = w;

        wishlistRepository2 = w2;

        categoriRepository = c;
    }



    @GetMapping("/")
    public String showIndex() {

        return "showIndex";
    }


    // showListPage - Controller Content.
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------


    @GetMapping("/showListPage")
    public String showListPage (Model model) {
        model.addAttribute("listAll", categoriRepository.getAll());

        return "showListPage";
    }

    @GetMapping("/createCategori")
    public String showCreateCategori () {

        return "createCategori";
    }


    @PostMapping("/createCategori")
    public String showCreateCategori(@RequestParam("categoriDescription") String categoriDescription) {

        System.out.println(categoriDescription);
        CategoriData newCategoriData = new CategoriData();
        newCategoriData.setCategoriDescription(categoriDescription);
        System.out.println(newCategoriData);

        categoriRepository.addCategoriData(newCategoriData);
        return "redirect:/showListPage";
    }


    @GetMapping("/updateCategoriData/{listNumber}")
    public String showUpdateCategoriData(@PathVariable("listNumber") int deleteListNumber, Model model) {

        model.addAttribute("categories", categoriRepository.findCategoriDataByNumber(deleteListNumber));

        return "updateCategoriData";

    }

    @PostMapping("/updateCategoriData/")
    public String showUpdateCategoriData(@ModelAttribute CategoriData categories) {

        categoriRepository.updateCategoriData(categories);

        return "redirect:/showListPage";

    }

    @GetMapping ("/deleteCategoriData/{listNumber}")
    public String deleteCategoriData(@PathVariable("listNumber") int deleteListNumber) {

        categoriRepository.deleteCategoriDataByNumber(deleteListNumber);

        return "redirect:/showListPage";

    }








    // showWishesPage - Controller Content.
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------


    @GetMapping("/showWishesPage")
        public String showCreateWishes (Model model) {
        model.addAttribute("wishAll", wishlistRepository.getAll());
            return "showWishesPage";

        }


    @GetMapping("/createWish")
    public String showCreateWishes () {

        return "createWish";
    }

    @PostMapping("/createWish")
    public String showCreateWishes (@RequestParam("wishDescription") String wishDescription) {

        System.out.println(wishDescription);
        WishData newWishData = new WishData();
        newWishData.setWishDescription(wishDescription);
        System.out.println(newWishData);

        wishlistRepository.addWishData(newWishData);
        return "redirect:/showWishesPage";
    }


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






    // showWishesPage2 - Controller Content.
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------


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

    }


