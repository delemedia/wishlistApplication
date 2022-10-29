package com.example.wishlistapplication.model;

public class WishData {


    private int wishNumber;

    private String wishDescription;



    public WishData( int wishNumber, String wishDescription) {
        this.wishNumber = wishNumber;
        this.wishDescription = wishDescription;
    }


    public WishData() {

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
