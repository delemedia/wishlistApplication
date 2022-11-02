package com.example.wishlistapplication.model;

public class WishList {

    private int wishListID;
    private int UserID;
    private String wishlistName;

    public WishList(int wishListID, int UserID, String wishlistName){
        this.wishListID = wishListID;
        this.UserID = UserID;
        this.wishlistName = wishlistName;
    }

    public WishList(){}

    public int getWishListID() {
        return wishListID;
    }

    public void setWishListID(int wishListID) {
        this.wishListID = wishListID;
    }

    public void setUserID(int id) {
        UserID = id;
    }

    public int getUserID() {
        return UserID;
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
