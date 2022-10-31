package com.example.wishlistapplication.utilities;

import com.example.wishlistapplication.model.User;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDatabaseServices {

    Connection dbConnect;

    @Value("${spring.datasource.url}")
    private String wishlishdb_url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    public Connection dbConnection() throws SQLException {
        if (dbConnect == null) {
            //dbConnect = DriverManager.getConnection("wishlishdb_url, userName, password");
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
            String userName = resultSet.getString("Name");
            String userEmail = resultSet.getString("Email");
            listOfAllUsers.add(new User(userId, userName, userEmail));
        }
        return listOfAllUsers;
    }


}
