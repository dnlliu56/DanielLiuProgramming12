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

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        modifiedColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getLastModified()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
        );

        loadEntries();

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

    private void loadEntries() {
        ObservableList<JournalEntry> entries = FXCollections.observableArrayList(journalDAO.getAllEntries());
        tableView.setItems(entries);
    }

    @FXML
    private void openNewEntryWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/journal_entry.fxml"));
            Parent root = loader.load();

            JournalEntryController controller = loader.getController();
            controller.setJournalDAO(journalDAO);
            controller.setListController(this); // for refreshing after save

            Stage stage = new Stage();
            stage.setTitle("New Journal Entry");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openEntryEditor(JournalEntry entry) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/journal_entry.fxml"));
            Parent root = loader.load();

            JournalEntryController controller = loader.getController();
            controller.setJournalDAO(journalDAO);   // Add this
            controller.setListController(this);     // Add this
            controller.setJournalEntry(entry);      // Already there

            Stage stage = new Stage();
            stage.setTitle("Edit Journal Entry");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadEntries();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshEntries() {
        loadEntries();
    }

}
