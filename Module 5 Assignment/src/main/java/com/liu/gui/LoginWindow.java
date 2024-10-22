package com.liu.gui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

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
            loadWindow("WelcomeWindow.fxml", "Welcome Window", username);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect username and/or password");
            alert.setHeaderText("Invalid Credentials");
            alert.showAndWait();
        }
    }

    private void loadWindow(String location, String title, String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + location));
            Parent parent = loader.load();

            // Get the controller and set the username
            WelcomeWindow welcomeWindowController = loader.getController();
            welcomeWindowController.setWelcomeMessage(username);

            // Create and show the new stage
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
