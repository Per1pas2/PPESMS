package com.example.applicationintervention;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequetesSQL {

    public int returnIDVendeur(String username, String password){
        ResultSet result = (ResultSet)Login.executeQuery("SELECT * from vendeur");
        try {
            return result.getInt(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;

    }
}
