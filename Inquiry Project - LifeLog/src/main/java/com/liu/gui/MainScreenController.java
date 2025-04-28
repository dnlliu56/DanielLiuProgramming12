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

    @FXML
    private void goToJournalList() throws IOException {
        // Load the journal list FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/journal_list.fxml"));
        Parent root = loader.load(); // Load the FXML

        // If you want to access the controller later, you can do so like this
        JournalListController controller = loader.getController();

        // Switch to the journal list scene
        Scene scene = new Scene(root);
        Stage stage = (Stage) goToJournalButton.getScene().getWindow(); // Use the button's scene to get the stage
        stage.setScene(scene);
        stage.show();
    }

}
