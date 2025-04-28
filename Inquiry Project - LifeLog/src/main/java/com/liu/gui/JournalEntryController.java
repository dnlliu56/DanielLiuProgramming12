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

    public void setJournalDAO(JournalDAO dao) {
        this.journalDAO = dao;
    }

    public void setListController(JournalListController controller) {
        this.listController = controller;
    }

    public void setJournalEntry(JournalEntry entry) {
        this.currentEntry = entry;
        titleField.setText(entry.getTitle());
        contentArea.setText(entry.getContent());
    }

    @FXML
    private void handleSave() {
        String title = titleField.getText();
        String content = contentArea.getText();

        if (title.isBlank() || content.isBlank()) {
            return; // Optionally show an alert
        }

        if (currentEntry == null) {
            journalDAO.addEntry(title, content);
        } else {
            journalDAO.updateEntry(currentEntry.getId(), title, content);
        }

        if (listController != null) {
            listController.refreshEntries();
        }

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleExport() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Journal Entry");

        // Suggest a default filename based on title
        String defaultTitle = titleField.getText().isEmpty() ? "untitled" : titleField.getText().replaceAll("\\s+", "_");
        fileChooser.setInitialFileName(defaultTitle + ".txt");

        // Set extension filter
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        // Show save dialog
        File file = fileChooser.showSaveDialog(exportButton.getScene().getWindow());

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Title: " + titleField.getText() + "\n\n");
                writer.write(contentArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
                // Optionally show an error dialog
            }
        }
    }

    @FXML
    private void handleDelete() {
        if (currentEntry == null) {
            return; // Optionally show an alert for no entry selected
        }

        // Show a confirmation dialog before deleting
        Alert alert = new Alert(Alert.AlertType.WARNING, "This action cannot be undone.", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Entry?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            journalDAO.deleteEntry(currentEntry.getId());

            if (listController != null) {
                listController.refreshEntries();
            }

            // Close the window after deletion
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }
}
