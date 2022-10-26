package com.example.wishlistapplication.repository;

import com.example.wishlistapplication.model.WishData;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class WishlistRepository {

public List<WishData> getAll() {

    List<WishData> listOfWish = new LinkedList<>();

    try
    {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                "root",
                "kea2022"
        );
        PreparedStatement psts = conn.prepareStatement("SELECT * FROM wishes");
        ResultSet resultSet = psts.executeQuery();

        while(resultSet.next()) {


            int wishNumber = resultSet.getInt("wishNumber");
            String wishDescription = resultSet.getString("wishDescription");

            listOfWish.add(new WishData(wishNumber, wishDescription));
        }

    }

    catch (SQLException e)
    {
        System.out.println("Cannot connect to database");
        e.printStackTrace();
    }

    return listOfWish;


}

public void addWishData(WishData newWishlistData) {

    try
    {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                "root",
                "kea2022");
        String queryCreate = "INSERT INTO Wishes (wishDescription) VALUES (?)";
        PreparedStatement psts = conn.prepareStatement(queryCreate);

        psts.setString(1, newWishlistData.getWishDescription());

        psts.executeUpdate();

    } catch (SQLException e) {

        System.out.println("Cannot connect to database");
        e.printStackTrace();

    }

}

public WishData findWishDataByNumber(int wishlistNumber) {

    WishData wishDataObject = new WishData();
    wishDataObject.setWishNumber(wishlistNumber);

    try {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                "root",
                "kea2022");
        String queryCreate = "SELECT * FROM wishlistUser WHERE=?";
        PreparedStatement psts = conn.prepareStatement(queryCreate);

        psts.setInt(1, wishlistNumber);

        ResultSet rs = psts.executeQuery();

        rs.next();
        String wishDescription = rs.getString(2);
        wishDataObject.setWishDescription(wishDescription);
        System.out.println(wishDataObject);

    } catch (SQLException e)
    {
        System.out.println("Cannot connect to database");
        e.printStackTrace();
    }

    return wishDataObject;

}

public void updateWishData(WishData wishData) {

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                "root",
                "kea2022");
                String queryCreate = "UPDATE wishData" + "SET wishDescription=? WHERE wishNumber=?";
                PreparedStatement psts = conn.prepareStatement(queryCreate);

                psts.setString(1, wishData.getWishDescription());
                psts.setInt(2, wishData.getWishNumber());

                psts.executeUpdate();


    } catch (SQLException e)
    {
        System.out.println("Cannot connect to database");
        e.printStackTrace();
    }

}

public void deleteWishDataByNumber(int deleteWishNumber) {

    try {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                "root",
                "kea2022");
        String queryCreate = "DELETE FROM wishData WHERE wishNumber=?";
        PreparedStatement psts = conn.prepareStatement(queryCreate);

        psts.setInt(1, deleteWishNumber);

    } catch (SQLException e)
    {
        System.out.println("Cannot connect to database");
        e.printStackTrace();
    }

}


}
