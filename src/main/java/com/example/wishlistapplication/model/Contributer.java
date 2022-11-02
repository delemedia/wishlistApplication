package com.example.wishlistapplication.model;

public class Contributer {

    private String contributerName;
    //private int wisheswishID;

    public Contributer(){}

    public Contributer(String contributerName){
        this.contributerName = contributerName;
    }

    public String getContributerName() {
        return contributerName;
    }

    public void setContributerName(String contributerName) {
        this.contributerName = contributerName;
    }

    @Override
    public String toString() {
        return "Contributer{" +
                "contributerName='" + contributerName + '\'' +
                '}';
    }
}
