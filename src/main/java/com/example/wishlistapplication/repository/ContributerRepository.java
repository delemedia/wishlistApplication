package com.example.wishlistapplication.repository;

import com.example.wishlistapplication.model.Contributer;
import com.example.wishlistapplication.model.Wish;
import com.example.wishlistapplication.utilities.DatabaseServices;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ContributerRepository {

    List<Contributer> listOfAllContributers = new LinkedList<>();
    DatabaseServices databaseServices = new DatabaseServices();

    public List<Contributer> getAllContributers() {

        try {
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement("SELECT * FROM Contributer");
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                String contributerName = resultSet.getString("contributerName");
                listOfAllContributers.add(new Contributer(contributerName));
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


    public Contributer findContributerByWisheswishID(String inputWisheswishID) {
        Contributer contributerObject = new Contributer();
        contributerObject.setContributerName(inputWisheswishID);

        try {
            String queryCreate = "SELECT * FROM Conttributer WHERE conttributerName=?";
            PreparedStatement psts = databaseServices.dbConnection().prepareStatement(queryCreate);
            psts.setInt(1, Integer.parseInt(inputWisheswishID)); //Foreign Key Problems
            ResultSet rs = psts.executeQuery();
            rs.next();
            String inputContributerName = rs.getString(2);
            contributerObject.setContributerName(inputContributerName);
            System.out.println(contributerObject);

        } catch (SQLException dbConnectError) {
            databaseServices.dbConnectionError(dbConnectError);
        }
        return contributerObject;
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
}

