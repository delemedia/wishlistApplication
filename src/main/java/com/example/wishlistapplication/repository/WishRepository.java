package com.example.wishlistapplication.repository;

import com.example.wishlistapplication.model.Wish;
import com.example.wishlistapplication.utilities.DatabaseServices;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
                String wishDescription = resultSet.getString("wishDescription");

                listOfAllWishes.add(new Wish(wishNumber, wishDescription));
            }

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return listOfAllWishes;
    }


    public void addWish(Wish newWish) {

        try {
            String queryCreate = "INSERT INTO wishes (wishDescription) VALUES (?)";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setString(1, newWish.getWishDescription());
            psts.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }


    public Wish findWishByNumber(int inputWishNumber) {
        Wish wishObject = new Wish();
        wishObject.setWishNumber(inputWishNumber);

        try {
            String queryCreate = "SELECT * FROM wishes WHERE wishNumber=?";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setInt(1, inputWishNumber);
            ResultSet rs = psts.executeQuery();
            rs.next();
            String wishDescription = rs.getString(2);
            wishObject.setWishDescription(wishDescription);
            System.out.println(wishObject);

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return wishObject;
    }

    public void updateWish(Wish wishes) {

        try {
            String queryCreate = "UPDATE wishes SET wishDescription=? WHERE wishNumber=?";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setString(1, wishes.getWishDescription());
            psts.setInt(2, wishes.getWishNumber());
            psts.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }

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
