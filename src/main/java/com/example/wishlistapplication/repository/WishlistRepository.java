package com.example.wishlistapplication.repository;

import com.example.wishlistapplication.model.WishList;
import com.example.wishlistapplication.utilities.DatabaseServices;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class WishListRepository {

    List<WishList> listOfAllWishlists = new LinkedList<>();
    DatabaseServices databaseServices = new DatabaseServices();
    Connection dbConnect;

    public List<WishList> getAllWishLists() {

        try {
            databaseServices.dbConnection();
            PreparedStatement psts = dbConnect.prepareStatement("SELECT * FROM Wishlists");
            ResultSet resultSet = psts.executeQuery();

            while(resultSet.next()) {
                int wishListID = resultSet.getInt("wishListID");
                String wishlistName = resultSet.getString("wishlistName");
                listOfAllWishlists.add(new WishList(wishListID, wishlistName));
            }
        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return listOfAllWishlists;
    }


    public void addWishlist(WishList newWishList) {

        try {
            databaseServices.dbConnection();
            String queryCreate = "INSERT INTO Wishlists (wishlistName) VALUES (?)";
            PreparedStatement psts = dbConnect.prepareStatement(queryCreate);
            psts.setString(1, newWishList.getWishlistName());
            psts.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }


    public WishList findWishlistByID(int inputWishlistID) {

        WishList wishLists = new WishList();
        wishLists.setWishListID(inputWishlistID);

        try {
            databaseServices.dbConnection();
            String queryCreate = "SELECT * FROM WishLists WHERE wishListID=?";
            PreparedStatement psts = dbConnect.prepareStatement(queryCreate);
            psts.setInt(1, inputWishlistID);
            ResultSet rs = psts.executeQuery();

            rs.next();
            String inputWishlistName = rs.getString(2); //Naming??
            wishLists.setWishlistName(inputWishlistName);
            System.out.println(wishLists);

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return wishLists;
    }


    public void updateWishList(WishList wishList) {
        try {
            databaseServices.dbConnection();
            String queryCreate = "UPDATE WishLists SET wishlistName=? WHERE wishListID=?";
            PreparedStatement psts = dbConnect.prepareStatement(queryCreate);

            psts.setString(1, wishList.getWishlistName());
            psts.setInt(2, wishList.getWishListID());
            psts.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }


    public void deleteWishList(int inputWishListID) {
        try {
            databaseServices.dbConnection();
            String queryCreate = "DELETE FROM WishLists WHERE wishListID=?";
            PreparedStatement psts = dbConnect.prepareStatement(queryCreate);
            psts.setInt(1, inputWishListID);
            psts.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }
}
