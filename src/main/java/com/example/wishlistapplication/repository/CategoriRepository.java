package com.example.wishlistapplication.repository;


import com.example.wishlistapplication.model.CategoriData;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CategoriRepository {

    public List<CategoriData> getAll() {

        List<CategoriData> listOfCategories = new LinkedList<>();

        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022"
            );
            PreparedStatement psts = conn.prepareStatement("SELECT * FROM categories");
            ResultSet resultSet = psts.executeQuery();

            while(resultSet.next()) {


                int listNumber = resultSet.getInt("listNumber");
                String categoriDescription = resultSet.getString("categoriDescription");

                listOfCategories.add(new CategoriData(listNumber, categoriDescription));
            }

        }

        catch (SQLException e)
        {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

        return listOfCategories;


    }

    public void addCategoriData(CategoriData newCategoriData) {


        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "INSERT INTO categories (categoriDescription) VALUES (?)";
            PreparedStatement psts = conn.prepareStatement(queryCreate);

            psts.setString(1, newCategoriData.getCategoriDescription());

            psts.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Cannot connect to database");
            e.printStackTrace();

        }

    }

    public CategoriData findCategoriDataByNumber(int listNumber) {

        CategoriData categories = new CategoriData();
        categories.setListNumber(listNumber);

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "SELECT * FROM categories WHERE listNumber=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);

            psts.setInt(1, listNumber);

            ResultSet rs = psts.executeQuery();

            rs.next();
            String categoriDescription = rs.getString(2);
            categories.setCategoriDescription(categoriDescription);
            System.out.println(categories);

        } catch (SQLException e)
        {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

        return categories;

    }

    public void updateCategoriData(CategoriData categories) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "UPDATE categories SET categoriDescription=? WHERE listNumber=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);


            psts.setString(1, categories.getCategoriDescription());
            psts.setInt(2, categories.getListNumber());

            psts.executeUpdate();


        } catch (SQLException e)
        {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

    }

    public void deleteCategoriDataByNumber(int deleteCategoriNumber) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "kea2022");
            String queryCreate = "DELETE FROM categories WHERE listNumber=?";
            PreparedStatement psts = conn.prepareStatement(queryCreate);

            psts.setInt(1, deleteCategoriNumber);

            psts.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

    }

}
