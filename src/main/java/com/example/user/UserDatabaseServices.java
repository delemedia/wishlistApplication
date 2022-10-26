package com.example.user;

import java.sql.*;

public class UserDatabaseServices {
    public static Connection dbConnect;


    public void dbConnection(Connection dbConnect) throws SQLException {
        dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/wishlistdb",
                "root",
                "Sy@hy8Qbfpc8ZY8"
        );
    }

    public void dbConnectionError (SQLException dbConnectError){
        System.out.println("Cannot connect to database.");
        dbConnectError.printStackTrace();
    }

    public void preparedStatementGetAllUsers() throws SQLException {
        PreparedStatement pstsGetAllUsers = dbConnect.prepareStatement("SELECT * FROM Users");
        ResultSet resultSet = pstsGetAllUsers.executeQuery();
        while(resultSet.next()) {
            int userId = resultSet.getInt("Id");
            String userEmail = resultSet.getString("Email");
            UserRepository.listOfUsers.add(new User(userId, userEmail));
        }
    }


}
