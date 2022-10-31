package com.example.wishlistapplication.repository;


import com.example.wishlistapplication.model.WishList;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class WishListRepository {

    public List<WishList> getAllWishLists() {

        List<WishList> listOfAllWishlists = new LinkedList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            PreparedStatement psts = conn.prepareStatement("SELECT * FROM Wishlists");
            ResultSet resultSet = psts.executeQuery();

            while(resultSet.next()) {
                int wishListID = resultSet.getInt("wishListID");
                String wishlistName = resultSet.getString("wishlistName");
                listOfAllWishlists.add(new WishList(wishListID, wishlistName));
            }
        }
        catch (SQLException e)
        {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }
        return listOfAllWishlists;
    }


    public void addWishlist(WishList newWishList) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "INSERT INTO Wishlists (wishlistName) VALUES (?)";
            PreparedStatement psts = conn.prepareStatement(queryCreate);

            psts.setString(1, newWishList.getWishlistName());

            psts.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Cannot connect to database");
            e.printStackTrace();

        }

    }

    public WishList findWishlistByID(int inputWishlistID) {

        WishList wishLists = new WishList();
        wishLists.setWishListID(inputWishlistID);

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "SELECT * FROM WishLists WHERE wishListID=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);

            psts.setInt(1, inputWishlistID);

            ResultSet rs = psts.executeQuery();

            rs.next();
            String inputWishlistName = rs.getString(2); //Naming??
            wishLists.setWishlistName(inputWishlistName);
            System.out.println(wishLists);

        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

        return wishLists;

    }

    public void updateWishList(WishList wishList) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "UPDATE WishLists SET wishlistName=? WHERE wishListID=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);


            psts.setString(1, wishList.getWishlistName());
            psts.setInt(2, wishList.getWishListID());

            psts.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

    }

    public void deleteWishList(int inputWishListID) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "DELETE FROM WishLists WHERE wishListID=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);

            psts.setInt(1, inputWishListID);

            psts.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

    }

}
