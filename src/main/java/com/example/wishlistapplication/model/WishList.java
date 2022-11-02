package com.example.wishlistapplication.model;

public class WishList {

    private int wishListID;
    private String wishlistName;

    public WishList(int wishListID, String wishlistName){
        this.wishListID = wishListID;
        this.wishlistName = wishlistName;
    }

    public WishList(){}

    public int getWishListID() {
        return wishListID;
    }

    public void setWishListID(int wishListID) {
        this.wishListID = wishListID;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "wishListID=" + wishListID +
                ", wishlistName='" + wishlistName + '\'' +
                '}';
    }
}
