package com.example.wishlistapplication.model;

public class Contributer {

    private String contributerName;
    private int WisheswishID;

    public Contributer (){}

    public Contributer(String contributerName, int WisheswishID){
        this.contributerName = contributerName;
        this.WisheswishID = WisheswishID;
    }

    public String getContributerName() {
        return contributerName;
    }

    public void setContributerName(String contributerName) {
        this.contributerName = contributerName;
    }

    public int getWisheswishID() {
        return WisheswishID;
    }

    public void setWisheswishID(int wisheswishID) {
        WisheswishID = wisheswishID;
    }

    @Override
    public String toString() {
        return "Contributer{" +
                "contributerName='" + contributerName + '\'' +
                '}';
    }
}
