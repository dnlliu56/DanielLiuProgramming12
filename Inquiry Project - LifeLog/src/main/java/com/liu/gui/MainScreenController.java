package com.liu.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private Button goToJournalButton;

    // This method runs when the "Go to Journal" button is clicked.
    // It switches the screen to the journal list view.
    @FXML
    private void goToJournalList() throws IOException {
        // Load the journal list FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/journal_list.fxml"));
        Parent root = loader.load(); // Load the design/layout

        // Get the controller for the journal list screen (optional here, but useful if you need to talk to it)
        JournalListController controller = loader.getController();

        // Set up the new scene and switch to it using the same window
        Scene scene = new Scene(root);
        Stage stage = (Stage) goToJournalButton.getScene().getWindow(); // Get the current window
        stage.setScene(scene); // Set the new screen
        stage.show(); // Show it
    }

}
