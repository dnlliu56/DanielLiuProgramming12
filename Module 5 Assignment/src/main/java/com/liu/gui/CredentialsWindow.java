package com.liu.gui;

import com.liu.database.DatabaseManager;
import com.liu.database.DatabaseToCSV;
import com.liu.gui.Authentication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CredentialsWindow {
    @FXML
    private TextField currentValidUsernameField;

    @FXML
    private TextField currentValidPasswordField;

    @FXML
    private TextField newUserUsernameField;

    @FXML
    private TextField newUserPasswordField;

    @FXML
    public void addUser() {
        // Retrieve text from the TextFields
        String currentValidUsername = currentValidUsernameField.getText();
        String currentValidPassword = currentValidPasswordField.getText();
        String newUserUsername = newUserUsernameField.getText();
        String newUserPassword = newUserPasswordField.getText();

        System.out.println("Current Username: " + currentValidUsername); // Debugging
        System.out.println("Current Password: " + currentValidPassword); // Debugging
        System.out.println("New Username: " + newUserUsername); // Debugging
        System.out.println("New Password: " + newUserPassword); // Debugging

        // Authenticate current user
        if (Authentication.authenticateUser(currentValidUsername, currentValidPassword)) {
            // Insert new user into the database
            if (insertNewUser(newUserUsername, newUserPassword)) {
                DatabaseToCSV.exportDBToCSV("user credentials.csv");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("User successfully added!");
                alert.setHeaderText("Success");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error adding new user.");
                alert.setHeaderText("Database Error");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect username and/or password");
            alert.setHeaderText("Invalid Credentials");
            alert.showAndWait();
        }
    }

    private boolean insertNewUser(String username, String password) {
        String insertSQL = "INSERT INTO USERS (username, password) VALUES (?, ?)";
        System.out.println("Inserting user: " + username); // Debugging

        try (Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println("✅ Inserted user: " + username); // Debugging
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("❌ Failed to insert user: " + username);
            System.err.println("Error: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState()); // Debugging
            e.printStackTrace();
            return false;
        }
    }
}