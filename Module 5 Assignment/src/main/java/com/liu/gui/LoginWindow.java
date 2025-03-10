package com.liu.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginWindow {

    @FXML
    private TextField usernameField; // Links to the TextField in the FXML file

    @FXML
    private TextField passwordField; // Links to the TextField in the FXML file

    @FXML
    public void login() {
        // Retrieve text from the TextFields
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (Authentication.authenticateUser(username, password)) {
            LoadWindow.loadWindow("WelcomeWindow.fxml", "Welcome Window", username);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect username and/or password");
            alert.setHeaderText("Invalid Credentials");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleExit() {
        System.exit(1); // Exit with status 1
    }
}
