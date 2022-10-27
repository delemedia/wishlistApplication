package com.example.wishlistapplication.utilities;

import com.example.wishlistapplication.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDatabaseServices {

    Connection dbConnect;

    public Connection dbConnection() throws SQLException {
        if (dbConnect == null) {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                    "root",
                    "Sy@hy8Qbfpc8ZY8"
            );
        }
        return dbConnect;
    }

    public void dbConnectionError (SQLException dbConnectError){
        System.out.println("Cannot connect to database.");
        dbConnectError.printStackTrace();
    }

    public List<User> preparedStatementGetAllUsers() throws SQLException {
        Connection connection = dbConnection();
        List<User> listOfAllUsers = new LinkedList<>();
        PreparedStatement pstsGetAllUsers = connection.prepareStatement("SELECT * FROM Users");
        ResultSet resultSet = pstsGetAllUsers.executeQuery();
        while(resultSet.next()) {
            int userId = resultSet.getInt("Id");
            String userEmail = resultSet.getString("Email");
            listOfAllUsers.add(new User(userId, userEmail));
        }
        return listOfAllUsers;
    }


}
