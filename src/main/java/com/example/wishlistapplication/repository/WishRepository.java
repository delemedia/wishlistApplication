package com.example.wishlistapplication.repository;

import com.example.wishlistapplication.model.Wish;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class WishRepository {

public List<Wish> getAll() {

    List<Wish> listOfWish = new LinkedList<>();

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

            listOfWish.add(new Wish(wishNumber, wishDescription));
        }

    }

    catch (SQLException e)
    {
        System.out.println("Cannot connect to database");
        e.printStackTrace();
    }

    return listOfWish;


}

public void addWishData(Wish newWishlistData) {

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

public Wish findWishDataByNumber(int wishNumber) {

    Wish wishes = new Wish();
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

public void updateWishData(Wish wishes) {

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


/*   // Den ekstra kode del jeg forholdte mig til
     // Har udkommenteret koden, for at det ikke forstyrre, de andre kode elementer.


@Repository
public class WishlistRepository2 {

    public List<WishData2> getAll2() {

        List<WishData2> listOfWish2 = new LinkedList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022"
            );
            PreparedStatement psts = conn.prepareStatement("SELECT * FROM wishes2");
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {


                int wishNumber2 = resultSet.getInt("wishNumber2");
                String wishDescription2 = resultSet.getString("wishDescription2");

                listOfWish2.add(new WishData2(wishNumber2, wishDescription2));
            }

        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

        return listOfWish2;


    }

    public void addWishData2(WishData2 newWishlistData2) {

        try {
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

        } catch (SQLException e) {
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


        } catch (SQLException e) {
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

        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

    }

}
*/

