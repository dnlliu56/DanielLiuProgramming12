package com.liu.database;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVToDatabase {
    public static void parseCSVAndInsertIntoDB(String csvFilePath) {
        System.out.println("Reading CSV file: " + csvFilePath); // Debugging

        try (FileReader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            System.out.println("CSV file read successfully."); // Debugging

            for (CSVRecord record : csvParser) {
                String username = record.get("USERNAME");
                String password = record.get("PASSWORD");

                System.out.println("Read record: " + username + ", " + password); // Debugging

                try {
                    insertIntoDatabase(username, password);
                } catch (SQLException e) {
                    System.err.println("❌ Failed to insert record: " + record);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Failed to read CSV file: " + csvFilePath);
            e.printStackTrace();
        }
    }


    private static void insertIntoDatabase(String username, String password) throws SQLException {
        // SQL statement for inserting a new record into the PlayerStats table
        String insertSQL = "INSERT INTO USERS (Username, Password) VALUES (?, ?)";

        // Use a try-with-resources statement to automatically close the Connection and PreparedStatement
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // Set the values for the PreparedStatement from the method parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Execute the SQL statement to insert the data into the database
            pstmt.executeUpdate();
        }
    }

}