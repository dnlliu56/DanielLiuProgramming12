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
        // Use a try-with-resources statement to automatically close FileReader and CSVParser
        try (FileReader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            // Iterate over each record in the CSV file
            for (CSVRecord record : csvParser) {
                // Extract and parse the data from each column in the record
                String username = record.get("Username");
                String password = record.get("Password");

                // Insert the extracted data into the database
                insertIntoDatabase(username, password);
            }
        } catch (IOException | SQLException e) {
            // Handle any I/O or SQL exceptions that occur
            e.printStackTrace();
        }
    }


    private static void insertIntoDatabase(String username, String password) throws SQLException {
        // SQL statement for inserting a new record into the PlayerStats table
        String insertSQL = "INSERT INTO Users (Username, Password) VALUES (?, ?)";

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