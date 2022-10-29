package com.example.wishlistapplication.model;

public class CategoriData {


    private int listNumber;

    private String categoriDescription;


    public CategoriData(int listNumber, String categoriDescription) {
        this.listNumber = listNumber;
        this.categoriDescription = categoriDescription;
    }

    public CategoriData() {


    }

    public int getListNumber() {
        return listNumber;
    }

    public void setListNumber(int listNumber) {
        this.listNumber = listNumber;
    }

    public String getCategoriDescription() {
        return categoriDescription;
    }

    public void setCategoriDescription(String categoriDescription) {
        this.categoriDescription = categoriDescription;
    }

    @Override
    public String toString() {
        return "CategoriData{" +
                "listNumber=" + listNumber +
                ", categoriDescription='" + categoriDescription + '\'' +
                '}';
    }
}




