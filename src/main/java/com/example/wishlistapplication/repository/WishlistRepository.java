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

    public List<WishList> getAllWishLists() {

        try {
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement("SELECT * FROM WishLists");
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
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
            String queryCreate = "INSERT INTO WishLists (wishlistName) VALUES (?)";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setString(1, newWishList.getWishlistName());
            psts.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }


    public WishList findWishlistByID(int inputWishlistID) {

        WishList wishListObject = new WishList();
        wishListObject.setWishListID(inputWishlistID);

        try {
            String queryCreate = "SELECT * FROM WishLists WHERE wishListID=?";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setInt(1, inputWishlistID);
            ResultSet rs = psts.executeQuery();
            rs.next();
            String inputWishlistName = rs.getString(2); //Naming??
            wishListObject.setWishlistName(inputWishlistName);
            System.out.println(wishListObject);

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return wishListObject;
    }


    public void updateWishList(WishList wishList) {
        try {
            String queryCreate = "UPDATE WishLists SET wishlistName=? WHERE wishListID=?";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setString(1, wishList.getWishlistName());
            psts.setInt(2, wishList.getWishListID());
            psts.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }


    public void deleteWishList(int inputWishListID) {
        try {
            String queryCreate = "DELETE FROM WishLists WHERE wishListID=?";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setInt(1, inputWishListID);
            psts.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }
}
