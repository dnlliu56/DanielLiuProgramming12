package com.liu.gui;

import com.liu.database.JournalDAO;
import com.liu.database.JournalEntry;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class JournalEntryController {

    @FXML private TextField titleField;
    @FXML private TextArea contentArea;
    @FXML private Button saveButton;
    @FXML private Button exportButton;

    private JournalDAO journalDAO;
    private JournalListController listController;
    private JournalEntry currentEntry;

    // Lets this controller talk to the database
    public void setJournalDAO(JournalDAO dao) {
        this.journalDAO = dao;
    }

    // Connects this controller to the list controller (for refreshing the list)
    public void setListController(JournalListController controller) {
        this.listController = controller;
    }

    // Loads an existing journal entry into the text fields
    public void setJournalEntry(JournalEntry entry) {
        this.currentEntry = entry;
        titleField.setText(entry.getTitle());
        contentArea.setText(entry.getContent());
    }

    // Runs when the "Save" button is clicked — saves or updates an entry
    @FXML
    private void handleSave() {
        String title = titleField.getText();
        String content = contentArea.getText();

        if (title.isBlank() || content.isBlank()) {
            return; // Could show a warning here
        }

        if (currentEntry == null) {
            journalDAO.addEntry(title, content); // Add new entry
        } else {
            journalDAO.updateEntry(currentEntry.getId(), title, content); // Update existing one
        }

        if (listController != null) {
            listController.refreshEntries(); // Refresh the list after saving
        }

        // Close the window after saving
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    // Runs when the "Export" button is clicked — saves the entry as a .txt file
    @FXML
    private void handleExport() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Journal Entry");

        String defaultTitle = titleField.getText().isEmpty() ? "untitled" : titleField.getText().replaceAll("\\s+", "_");
        fileChooser.setInitialFileName(defaultTitle + ".txt");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(exportButton.getScene().getWindow());

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Title: " + titleField.getText() + "\n\n");
                writer.write(contentArea.getText());
            } catch (IOException e) {
                e.printStackTrace(); // Could show an error message
            }
        }
    }

    // Runs when the "Delete" button is clicked — asks for confirmation and deletes the entry
    @FXML
    private void handleDelete() {
        if (currentEntry == null) {
            return; // Could show a message that nothing is selected
        }

        // Ask the user to confirm deletion
        Alert alert = new Alert(Alert.AlertType.WARNING, "This action cannot be undone.", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Entry?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            journalDAO.deleteEntry(currentEntry.getId());

            if (listController != null) {
                listController.refreshEntries(); // Update the list after deletion
            }

            // Close the window after deleting
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }
}
