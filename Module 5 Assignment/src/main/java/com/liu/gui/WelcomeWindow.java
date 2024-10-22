package com.liu.gui;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class WelcomeWindow {

    @FXML
    private Text welcomeTextField; // Link to the Text object in FXML

    @FXML
    public void initialize() {
        // Initialization logic if needed, but data should be set via setWelcomeMessage()
    }

    @FXML
    public void setWelcomeMessage(String username) {
        if (welcomeTextField != null) {
            welcomeTextField.setText("Welcome " + username + "!");
        } else {
            System.err.println("welcomeTextField is not initialized.");
        }
    }
}
