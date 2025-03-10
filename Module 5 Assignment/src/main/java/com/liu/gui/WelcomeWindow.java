package com.liu.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class WelcomeWindow {

    @FXML
    private Text welcomeTextField; // Link to the Text object in FXML

    @FXML
    public void setWelcomeMessage(String username) {
        if (welcomeTextField != null) {
            welcomeTextField.setText("Welcome " + username + "!");
        } else {
            System.err.println("welcomeTextField is not initialized.");
        }
    }

    @FXML
    public void login() {
        LoadWindow.loadWindow("welcomeWindow.fxml", "Welcome Window");
    }

    @FXML
    public void newUser() {
        LoadWindow.loadWindow("credentialsWindow.fxml", "Credentials Window");
    }
}
