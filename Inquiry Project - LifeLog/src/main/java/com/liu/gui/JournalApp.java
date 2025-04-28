package com.liu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JournalApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the main screen FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_screen.fxml"));
            Parent root = loader.load();

            // Set the scene and the stage
            primaryStage.setTitle("LifeLog");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
