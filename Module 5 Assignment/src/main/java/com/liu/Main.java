package com.liu;

import com.liu.database.CSVToDatabase;
import com.liu.database.DatabaseManager;
import com.liu.database.DatabaseToCSV;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        DatabaseManager.createTable();
        CSVToDatabase.parseCSVAndInsertIntoDB("user credentials.csv");

        DatabaseManager.printAllUsers();
        DatabaseManager.printAllTables();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginWindow.fxml"));
        stage.setTitle("Title");
        stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
