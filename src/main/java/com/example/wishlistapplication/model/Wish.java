package com.example.wishlistapplication.model;

public class Wish {

    private int wishNumber;
    private int wishListID;
    private String wishDescription;


    public Wish (){}

    public Wish (String wishDescription) {
        this.wishDescription = wishDescription;
    }

    public Wish(int wishNumber, int wishListID, String wishDescription) {
        this.wishNumber = wishNumber;
        this.wishListID = wishListID;
        this.wishDescription = wishDescription;
    }

    public int getWishListID() {
        return wishListID;
    }

    public void setWishListID(int wishListID) {
        this.wishListID = wishListID;
    }

    public int getWishNumber() {
        return wishNumber;
    }

    public void setWishNumber(int wishNumber) {
        this.wishNumber = wishNumber;
    }

    public String getWishDescription() {
        return wishDescription;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }

    @Override
    public String toString() {
        return "WishlistData{" +
                "wishlistNumber=" + wishNumber +
                ", wishDescription='" + wishDescription + '\'' +
                '}';
    }
}

