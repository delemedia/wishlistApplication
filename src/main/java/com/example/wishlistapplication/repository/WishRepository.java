package com.example.wishlistapplication.repository;

import com.example.wishlistapplication.model.Wish;
import com.example.wishlistapplication.utilities.DatabaseServices;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import javax.xml.crypto.dsig.spec.RSAPSSParameterSpec;

@Repository
public class WishRepository {

    List<Wish> listOfAllWishes = new LinkedList<>();
    DatabaseServices databaseServices = new DatabaseServices();

    public List<Wish> getAllWishes() {

        try {
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement("SELECT * FROM wishes");
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                int wishNumber = resultSet.getInt("wishNumber");
                int wishlistID = resultSet.getInt("WishListID");
                String wishDescription = resultSet.getString("wishDescription");


                listOfAllWishes.add(new Wish(wishNumber, wishlistID, wishDescription));
            }

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return listOfAllWishes;
    }

    public List<Wish> findWhere(String column, Object value) {
        List<Wish> collection = new LinkedList<>();
        try {
            String sql = String.format("SELECT * FROM wishes WHERE %s = ?", column);
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(sql);
            psts.setObject(1, value);
            
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                int wishNumber = resultSet.getInt("wishNumber");
                int wishListID = resultSet.getInt("WishListID");
                String wishDescription = resultSet.getString("wishDescription"); 
                               
                collection.add(new Wish(wishNumber, wishListID, wishDescription));
            }
        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return collection;
    }


    public void addWish(Wish wish) throws SQLException {
        String queryCreate = "INSERT INTO wishes (wishDescription, WishListID) VALUES (?, ?)";
        PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
        psts.setString(1, wish.getWishDescription());
        psts.setInt(2, wish.getWishListID());
        psts.executeUpdate();
    }


    public Wish findWishByNumber(int inputWishNumber) {
        Wish wish = null;

        try {
            String queryCreate = "SELECT * FROM wishes WHERE wishNumber=?";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setInt(1, inputWishNumber);
            ResultSet rs = psts.executeQuery();
            rs.next();
            int wishNumber = rs.getInt("wishNumber");
            int wishListID = rs.getInt("WishListID");
            String wishDescription = rs.getString("wishDescription"); 

            wish = new Wish(wishNumber, wishListID, wishDescription);
        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return wish;
    }

    public void updateWish(Wish wishes) throws SQLException {
        String queryCreate = "UPDATE wishes SET wishDescription=? WHERE wishNumber=?";
        PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
        psts.setString(1, wishes.getWishDescription());
        psts.setInt(2, wishes.getWishNumber());
        psts.executeUpdate();
    }

    public void deleteWishById(int deleteWishId) {

        try {
            String queryCreate = "DELETE FROM wishes WHERE wishNumber=?";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setInt(1, deleteWishId);
            psts.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }

}
