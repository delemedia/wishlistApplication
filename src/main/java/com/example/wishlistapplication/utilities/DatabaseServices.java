package com.example.wishlistapplication.utilities;

import org.springframework.beans.factory.annotation.Value;
import java.sql.*;


public class DatabaseServices {

    // Database username & password
    @Value("${spring.datasource.url}")
    private String wishlistdb_url = "jdbc:mysql://localhost:3306/wishlistdb";

    @Value("${spring.datasource.username}")
    private String wishlistdbAdmin_userName = "wishlistdbAdmin";

    @Value("${spring.datasource.password}")
    private String wishlistdbAdmin_password = "wishlistdbAdminPassword";


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
