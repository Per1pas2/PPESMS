package com.example.applicationintervention;

import com.mysql.cj.xdevapi.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {
    private static String ip = "localhost";
    private static String username = "root" ;
    private static String password = "root";
    private static String db = "homesweethome";

    private static java.sql.Connection dbConnect = null;
    private static java.sql.Statement dbStatement = null;

    public static Object executeQuery(String query){
        try {

            Class.forName("com.mysql.jc.jdbc.Driver").newInstance();
            Login.dbConnect = DriverManager.getConnection("jdbc:mysql:" + ip+"/"+db, username, password);
            dbStatement = dbConnect.createStatement();
            ResultSet rs = dbStatement.executeQuery(query);
            return rs;
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
