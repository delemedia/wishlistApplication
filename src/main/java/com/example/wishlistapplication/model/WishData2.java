package com.example.wishlistapplication.model;

public class WishData2 {


    private int wishNumber2;

    private String wishDescription2;



    public WishData2( int wishNumber2, String wishDescription2) {
        this.wishNumber2 = wishNumber2;
        this.wishDescription2 = wishDescription2;
    }


    public WishData2() {

    }


    public int getWishNumber2() {
        return wishNumber2;
    }

    public void setWishNumber2(int wishNumber2) {
        this.wishNumber2 = wishNumber2;
    }

    public String getWishDescription2() {
        return wishDescription2;
    }

    public void setWishDescription2(String wishDescription2) {
        this.wishDescription2 = wishDescription2;
    }



    @Override
    public String toString() {
        return "WishlistData2{" +
                "wishlistNumber2=" + wishNumber2 +
                ", wishDescription2='" + wishDescription2 + '\'' +
                '}';
    }
}