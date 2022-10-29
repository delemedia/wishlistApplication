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
        String queryCreate = "INSERT INTO wishes (wishDescription) VALUES (?)";
        PreparedStatement psts = conn.prepareStatement(queryCreate);

        psts.setString(1, newWishlistData.getWishDescription());

        psts.executeUpdate();

    } catch (SQLException e) {

        System.out.println("Cannot connect to database");
        e.printStackTrace();

    }

}

public WishData findWishDataByNumber(int wishNumber) {

    WishData wishes = new WishData();
    wishes.setWishNumber(wishNumber);

    try {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                "root",
                "kea2022");
        String queryCreate = "SELECT * FROM wishes WHERE wishNumber=?";
        PreparedStatement psts = conn.prepareStatement(queryCreate);

        psts.setInt(1, wishNumber);

        ResultSet rs = psts.executeQuery();

        rs.next();
        String wishDescription = rs.getString(2);
        wishes.setWishDescription(wishDescription);
        System.out.println(wishes);

    } catch (SQLException e)
    {
        System.out.println("Cannot connect to database");
        e.printStackTrace();
    }

    return wishes;

}

public void updateWishData(WishData wishes) {

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                "root",
                "kea2022");
                String queryCreate = "UPDATE wishes SET wishDescription=? WHERE wishNumber=?";
                PreparedStatement psts = conn.prepareStatement(queryCreate);


                psts.setString(1, wishes.getWishDescription());
                psts.setInt(2, wishes.getWishNumber());

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
        String queryCreate = "DELETE FROM wishes WHERE wishNumber=?";
        PreparedStatement psts = conn.prepareStatement(queryCreate);

        psts.setInt(1, deleteWishNumber);

        psts.executeUpdate();

    } catch (SQLException e)
    {
        System.out.println("Cannot connect to database");
        e.printStackTrace();
    }

}



}
