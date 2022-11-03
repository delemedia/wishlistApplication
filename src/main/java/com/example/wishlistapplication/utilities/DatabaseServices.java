package com.example.wishlistapplication.utilities;

import com.example.wishlistapplication.model.User;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DatabaseServices {

    // Database username & password proxy values
    @Value("${spring.datasource.url}")
    private String wishlistdb_url = "jdbc:mysql://localhost:3306/wishlistdb";

    @Value("${spring.datasource.username}")
    private String wishlistdbAdmin_userName = "root";

    @Value("${spring.datasource.password}")
    private String wishlistdbAdmin_password = "kea2022";


    //DB Connection
    Connection dbConnect;
    public Connection dbConnection() throws SQLException {
        if (dbConnect == null) {
            dbConnect = DriverManager.getConnection(wishlistdb_url, wishlistdbAdmin_userName, wishlistdbAdmin_password);
        }
        return dbConnect;
    }

    //DB Connection Error
    public void dbConnectionError (SQLException dbConnectError){
        System.out.println("Cannot connect to database.");
        dbConnectError.printStackTrace();
    }


}
