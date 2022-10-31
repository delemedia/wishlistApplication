package com.example.wishlistapplication.repository;

import com.example.wishlistapplication.model.User;
import com.example.wishlistapplication.utilities.DatabaseServices;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserRepository {

    List<User> listOfUsers = new LinkedList<>();
    DatabaseServices databaseServices = new DatabaseServices();
    Connection dbConnect;

    public List<User> getAllUsers() {
        try {
            databaseServices.dbConnection();
            databaseServices.preparedStatementGetAllUsers();
        }
        catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return listOfUsers;
    }

    public void addUser(User newUser) {
        try
        {
            databaseServices.dbConnection();
            String queryAddUser = "INSERT INTO Users (Email) VALUES (?)";
            PreparedStatement pstsAddUser = dbConnect.prepareStatement(queryAddUser);
            pstsAddUser.setString(Integer.parseInt("Id"), newUser.getEmail()); // Check!! parameterindex!!
            pstsAddUser.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }

    public void updateUser(User user) {

        try {
            databaseServices.dbConnection();
            String queryUpdateUser = "UPDATE user" + "SET Email=? WHERE Id=?";
            PreparedStatement pstsUpdateUser = dbConnect.prepareStatement(queryUpdateUser);
            pstsUpdateUser.setString(Integer.parseInt("Email"), user.getEmail()); // Check!! parameterindex!!
            pstsUpdateUser.setInt(Integer.parseInt("Id"), user.getId()); // Check!! parameterindex!!
            pstsUpdateUser.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }

    }

    public User findUserById(int userID) {

        User userObject = new User();
        userObject.setId(userID);

        try {
            databaseServices.dbConnection();
            String queryfindUserById = "SELECT * FROM Users WHERE=?";
            PreparedStatement pstsfindUserById = dbConnect.prepareStatement(queryfindUserById);
            pstsfindUserById.setInt(1, userID);
            ResultSet usersResSet = pstsfindUserById.executeQuery();

            usersResSet.next();
            String userEmail = usersResSet.getString(2);
            userObject.setEmail(userEmail);
            System.out.println(userObject);

        }
        catch (SQLException dbConnectError) {
        databaseServices.dbConnectionError(dbConnectError);
    }
        return userObject;
    }

    public void deleteUserById(int userId) {

        try {
            databaseServices.dbConnection();
            String queryDeleteUser = "DELETE FROM users WHERE Id=?";
            PreparedStatement pstsDeleteUser = dbConnect.prepareStatement(queryDeleteUser);
            pstsDeleteUser.setInt(1, userId);

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }
}
