package com.liu;

public class Main {
    public static void main(String[] args) {
        // Initialize database and create table
        DatabaseManager.createTable();

        // Path to CSV files to insert
        String csvFilePath = "PlayerData.csv";
        String csvFilePath2 = "PlayerData2.csv";

        // Parse CSV files and insert data into database
        CSVToDatabase.parseCSVAndInsertIntoDB(csvFilePath);
        CSVToDatabase.parseCSVAndInsertIntoDB(csvFilePath2);

        // Export data from database to CSV
        String outputCsvFilePath = "PlayerDatabase.csv";
        DatabaseToCSV.exportDBToCSV(outputCsvFilePath);
    }
}