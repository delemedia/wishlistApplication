package com.example.wishlistapplication.repository;

import com.example.wishlistapplication.model.Contributer;
import com.example.wishlistapplication.utilities.DatabaseServices;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ContributerRepository {

    DatabaseServices databaseServices = new DatabaseServices();

    public List<Contributer> getAllContributers() {
        List<Contributer> listOfAllContributers = new LinkedList<>();
        try {
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement("SELECT * FROM Contributer");
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                String contributerName = resultSet.getString("contributerName");
                int wishId = resultSet.getInt("WisheswishID");
                listOfAllContributers.add(new Contributer(contributerName,wishId));
            }

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return listOfAllContributers;
    }


    public void addContributer(Contributer newContributer) {

        try {
            String queryCreate = "INSERT INTO Contributer (contributerName) VALUES (?)";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setString(1, newContributer.getContributerName());
            psts.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }


    public Contributer findContributerByWisheswishID(int inputWisheswishID) {

        try {
            String queryCreate = "SELECT * FROM Contributer WHERE WisheswishID=?";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setInt(1, inputWisheswishID);
            ResultSet rs = psts.executeQuery();
            rs.next();
            String contributerName = rs.getString("contributerName");
            return new Contributer(contributerName, inputWisheswishID);

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return null;
    }


    public void updateContributer(Contributer contributer) {

        try {
            String queryCreate = "UPDATE Contributer SET contributerName=? WHERE wishNumber=?";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setString(1, contributer.getContributerName());
            psts.executeUpdate();

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
    }
    public boolean checkIfContributerFree(boolean inputWisheswishID){

        try {
            String queryCreate = "SELECT * FROM Contributer WHERE contributerName=? IS NULL";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setBoolean(1, inputWisheswishID);
            psts.executeQuery();

        } catch (SQLException dbConnectErorr) {
            databaseServices.dbConnectionError(dbConnectErorr);
        }
        //lave method der returnere boolean alt efter om en wish har en contributer.
        return true;
    }
}

