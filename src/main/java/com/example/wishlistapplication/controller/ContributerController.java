package com.example.wishlistapplication.controller;

import com.example.wishlistapplication.config.CustomUserDetails;
import com.example.wishlistapplication.model.Contributer;
import com.example.wishlistapplication.model.User;
import com.example.wishlistapplication.model.Wish;
import com.example.wishlistapplication.model.WishList;
import com.example.wishlistapplication.repository.ContributerRepository;
import com.example.wishlistapplication.repository.WishListRepository;
import com.example.wishlistapplication.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
@Controller
public class ContributerController {

    ContributerRepository contributerRepository;
    WishRepository wishRepository;
    WishListRepository wishListRepository;


    @GetMapping("/wishListSharingPage")
    public String showList(Model model) throws SQLException {
        System.out.println("hello world");
        wishRepository.addWish(new Wish(1,1,"shrimp"));
        System.out.println(wishRepository.getAllWishes());
        model.addAttribute("wishAll", wishRepository.getAllWishes());
        return "contributer/wishListSharingPage";
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
        return "redirect:/contributer/" + dbWishList.getWishListID(); //
    }


}
