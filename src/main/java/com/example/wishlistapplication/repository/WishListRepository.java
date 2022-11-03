package com.example.wishlistapplication.repository;

import com.example.wishlistapplication.model.WishList;
import com.example.wishlistapplication.utilities.DatabaseServices;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class WishListRepository {

    DatabaseServices databaseServices = new DatabaseServices();

    public List<WishList> getAllWishLists() {
        List<WishList> listOfAllWishlists = new LinkedList<>();
        try {
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement("SELECT * FROM WishLists");
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                int wishListID = resultSet.getInt("wishListID");
                int UserID = resultSet.getInt("UserID");
                String wishlistName = resultSet.getString("wishlistName");
                listOfAllWishlists.add(new WishList(wishListID, UserID, wishlistName));
            }
        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return listOfAllWishlists;
    }

    public List<WishList> findWhere(String column, Object value) {
        List<WishList> listOfAllWishlists = new LinkedList<>();
        try {
            String sql = String.format("SELECT * FROM WishLists WHERE %s = ?", column);
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(sql);
            psts.setObject(1, value);
            System.out.println(psts);
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                int wishListID = resultSet.getInt("wishListID");
                int UserID = resultSet.getInt("UserID");
                String wishlistName = resultSet.getString("wishlistName");
                listOfAllWishlists.add(new WishList(wishListID, UserID, wishlistName));
            }
        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return listOfAllWishlists;
    }

    public void addWishlist(WishList newWishList) throws SQLException {
        String queryCreate = "INSERT INTO WishLists (wishlistName, UserID) VALUES (?, ?)";
        PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
        psts.setString(1, newWishList.getWishlistName());
        psts.setInt(2, newWishList.getUserID());
        psts.executeUpdate();
    }


    public WishList findWishlistByID(int inputWishlistID) {
        WishList wishList = null;

        try {
            String queryCreate = "SELECT * FROM WishLists WHERE wishListID=?";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setInt(1, inputWishlistID);
            ResultSet rs = psts.executeQuery();
            rs.next();
            wishList = new WishList(
                    rs.getInt("wishListID"),
                    rs.getInt("UserID"),
                    rs.getString("wishlistName")
            );
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return wishList;
    }


    public void updateWishList(WishList wishList) throws SQLException {
        String queryCreate = "UPDATE WishLists SET wishlistName=? WHERE wishListID=?";
        PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
        psts.setString(1, wishList.getWishlistName());
        psts.setInt(2, wishList.getWishListID());
        psts.executeUpdate();
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

