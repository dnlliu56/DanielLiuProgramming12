package com.liu.gui;

import com.liu.database.DatabaseManager;

import java.sql.*;

public class Authentication {

    public static boolean authenticateUser(String username, String password) {
        // Construct the SQL query directly
        String query = "SELECT * FROM USERS WHERE username = '" + username + "' AND password = '" + password + "'";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // If a record is found, the user is authenticated
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
