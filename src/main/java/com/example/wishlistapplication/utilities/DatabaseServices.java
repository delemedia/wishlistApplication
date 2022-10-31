package com.example.wishlistapplication.utilities;

import com.example.wishlistapplication.model.User;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DatabaseServices {

    Connection dbConnect;

    // Database username & password proxy values
    @Value("${spring.datasource.url}")
    private String wishlistdb_url;

    @Value("${spring.datasource.username}")
    private String wishlistdbAdmin_userName;

    @Value("${spring.datasource.password}")
    private String wishlistdbAdmin_password;

    //DB Connection Method
    public Connection dbConnection() throws SQLException {
        if (dbConnect == null) {
            dbConnect = DriverManager.getConnection(wishlistdb_url, wishlistdbAdmin_userName, wishlistdbAdmin_password);
        }
        return dbConnect;
    }

    //DB Connection Error Method
    public void dbConnectionError (SQLException dbConnectError){
        System.out.println("Cannot connect to database.");
        dbConnectError.printStackTrace();
    }


// UserRepository preparedStatement (Exercise)
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
