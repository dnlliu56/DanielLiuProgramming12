package com.liu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Main class that starts the JavaFX journal app
public class JournalApp extends Application {

    // This method runs when the app starts — it sets up the first screen
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_screen.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("LifeLog");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This is the main method — it launches the whole app
    public static void main(String[] args) {
        launch(args);
    }
}
