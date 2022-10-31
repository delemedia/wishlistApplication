package com.example.wishlistapplication.repository;

import com.example.wishlistapplication.model.User;
import com.example.wishlistapplication.utilities.DatabaseServices;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserRepository {

    List<User> listOfAllUsers = new LinkedList<>();
    DatabaseServices databaseServices = new DatabaseServices();


    public List<User> getAllUsers() {
        try {
            PreparedStatement pstsGetAllUsers = databaseServices.dbConnection().prepareStatement("SELECT * FROM Users");
            ResultSet resultSet = pstsGetAllUsers.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("Id");
                String userName = resultSet.getString("Name");
                String userEmail = resultSet.getString("Email");
                String userPassword = resultSet.getString("password");
                listOfAllUsers.add(new User(userId, userName, userEmail, userPassword));
            }
            return listOfAllUsers;
        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return listOfAllUsers;
    }

    public void addUser(User newUser) {
        try {
            String queryAddUser = "INSERT INTO Users (Name, Email, password) VALUES (?,?,?)";
            PreparedStatement pstsAddUser = databaseServices.dbConnection().prepareStatement(queryAddUser);
            pstsAddUser.setString(1, newUser.getName());
            pstsAddUser.setString(2, newUser.getEmail());
            pstsAddUser.setString(3, newUser.getPassword());
            pstsAddUser.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }

    public void updateUser(User user) {

        try {
            String queryUpdateUser = "UPDATE user" + "SET Email=? WHERE Id=?";
            PreparedStatement pstsUpdateUser = databaseServices.dbConnection().prepareStatement(queryUpdateUser);
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
            String queryfindUserById = "SELECT * FROM Users WHERE=?";
            PreparedStatement pstsfindUserById = databaseServices.dbConnection().prepareStatement(queryfindUserById);
            pstsfindUserById.setInt(1, userID);
            ResultSet usersResSet = pstsfindUserById.executeQuery();
            usersResSet.next();
            String userEmail = usersResSet.getString(2);
            userObject.setEmail(userEmail);
            System.out.println(userObject);

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return userObject;
    }

    public void deleteUserById(int userId) {

        try {
            String queryDeleteUser = "DELETE FROM users WHERE Id=?";
            PreparedStatement pstsDeleteUser = databaseServices.dbConnection().prepareStatement(queryDeleteUser);
            pstsDeleteUser.setInt(1, userId);

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }
}
