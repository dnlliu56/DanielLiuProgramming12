package com.liu.gui;

import com.liu.database.JournalDAO;
import com.liu.database.JournalEntry;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class JournalListController {

    @FXML private TableView<JournalEntry> tableView;
    @FXML private TableColumn<JournalEntry, String> titleColumn;
    @FXML private TableColumn<JournalEntry, String> modifiedColumn;

    private final JournalDAO journalDAO = new JournalDAO();

    // This method runs when the screen loads — sets up the table and loads entries
    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        modifiedColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getLastModified()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
        );

        loadEntries();

        // Lets user double-click a row to edit the entry
        tableView.setRowFactory(tv -> {
            TableRow<JournalEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    openEntryEditor(row.getItem());
                }
            });
            return row;
        });
    }

    // Loads all journal entries from the database and displays them in the table
    private void loadEntries() {
        ObservableList<JournalEntry> entries = FXCollections.observableArrayList(journalDAO.getAllEntries());
        tableView.setItems(entries);
    }

    // Opens a new window for creating a brand new journal entry
    @FXML
    private void openNewEntryWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/journal_entry.fxml"));
            Parent root = loader.load();

            JournalEntryController controller = loader.getController();
            controller.setJournalDAO(journalDAO);
            controller.setListController(this); // So the list can refresh later

            Stage stage = new Stage();
            stage.setTitle("New Journal Entry");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Opens the selected journal entry in a new window for editing
    private void openEntryEditor(JournalEntry entry) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/journal_entry.fxml"));
            Parent root = loader.load();

            JournalEntryController controller = loader.getController();
            controller.setJournalDAO(journalDAO);
            controller.setListController(this);
            controller.setJournalEntry(entry); // Load the existing entry into the form

            Stage stage = new Stage();
            stage.setTitle("Edit Journal Entry");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadEntries(); // Refresh the list after editing
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Refreshes the table of entries (e.g., after saving or deleting)
    public void refreshEntries() {
        loadEntries();
    }
}
