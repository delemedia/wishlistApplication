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



/*  // Den ekstra kode del jeg forholdte mig til
    // Har udkommenteret koden, for at det ikke forstyrre, de andre kode elementer.

public class WishData2 {


    private int wishNumber2;

    private String wishDescription2;


    public WishData2(int wishNumber2, String wishDescription2) {
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
*/
