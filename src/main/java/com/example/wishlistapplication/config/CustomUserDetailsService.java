package com.example.wishlistapplication.config;

import com.example.wishlistapplication.utilities.DatabaseServices;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DatabaseServices databaseServices = new DatabaseServices();

        try {
            String sql = "SELECT * FROM Users WHERE Email=?";
            PreparedStatement pstsfindUserById = databaseServices.dbConnection().prepareStatement(sql);
            pstsfindUserById.setString(1, username);
            ResultSet usersResSet = pstsfindUserById.executeQuery();
            usersResSet.next();
            int id = usersResSet.getInt("Id");
            String email = usersResSet.getString("Email");
            String name = usersResSet.getString("Name");
            String password = usersResSet.getString("password");
            return new CustomUserDetails(id, name, email, password);

        } catch (SQLException dbConnectError) {
            dbConnectError.printStackTrace();
        }
        throw new UsernameNotFoundException("User not found.");
    }
}
