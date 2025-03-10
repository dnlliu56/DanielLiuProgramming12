package com.liu.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoadWindow {

    // Modified loadWindow to accept an optional username parameter
    public static void loadWindow(String location, String title, String... username) {
        try {
            FXMLLoader loader = new FXMLLoader(LoadWindow.class.getResource("/fxml/" + location));
            Parent parent = loader.load();

            // If a username is provided, set it in the WelcomeWindow controller
            if (username.length > 0) {
                WelcomeWindow welcomeWindowController = loader.getController();
                welcomeWindowController.setWelcomeMessage(username[0]);
            }

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
