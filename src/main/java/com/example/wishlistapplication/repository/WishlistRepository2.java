package com.example.wishlistapplication.repository;

import com.example.wishlistapplication.model.WishData2;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class WishlistRepository2 {

    public List<WishData2> getAll2() {

        List<WishData2> listOfWish2 = new LinkedList<>();

        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022"
            );
            PreparedStatement psts = conn.prepareStatement("SELECT * FROM wishes2");
            ResultSet resultSet = psts.executeQuery();

            while(resultSet.next()) {


                int wishNumber2 = resultSet.getInt("wishNumber2");
                String wishDescription2 = resultSet.getString("wishDescription2");

                listOfWish2.add(new WishData2(wishNumber2, wishDescription2));
            }

        }

        catch (SQLException e)
        {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

        return listOfWish2;


    }

    public void addWishData2(WishData2 newWishlistData2) {

        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "INSERT INTO wishes2 (wishDescription2) VALUES (?)";
            PreparedStatement psts = conn.prepareStatement(queryCreate);

            psts.setString(1, newWishlistData2.getWishDescription2());

            psts.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Cannot connect to database");
            e.printStackTrace();

        }

    }

    public WishData2 findWishDataByNumber2(int wishNumber2) {

        WishData2 wishes2 = new WishData2();
        wishes2.setWishNumber2(wishNumber2);

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "SELECT * FROM wishes2 WHERE wishNumber2=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);

            psts.setInt(1, wishNumber2);

            ResultSet rs = psts.executeQuery();

            rs.next();
            String wishDescription2 = rs.getString(2);
            wishes2.setWishDescription2(wishDescription2);
            System.out.println(wishes2);

        } catch (SQLException e)
        {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

        return wishes2;

    }

    public void updateWishData2(WishData2 wishes2) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "UPDATE wishes2 SET wishDescription2=? WHERE wishNumber2=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);


            psts.setString(1, wishes2.getWishDescription2());
            psts.setInt(2, wishes2.getWishNumber2());

            psts.executeUpdate();


        } catch (SQLException e)
        {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

    }

    public void deleteWishDataByNumber2(int deleteWishNumber2) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "DELETE FROM wishes2 WHERE wishNumber2=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);

            psts.setInt(1, deleteWishNumber2);

            psts.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

    }

}
