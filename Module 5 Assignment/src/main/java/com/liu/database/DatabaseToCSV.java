package com.liu.database;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseToCSV {
    public static void exportDBToCSV(String csvFilePath) {
        // SQL query to select all records from the PlayerStats table
        String selectSQL = "SELECT * FROM Users";

        // Use a try-with-resources statement to automatically close resources after use
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL);
             FileWriter writer = new FileWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(rs.getMetaData()))) {

            // Iterate through the result set and write each record to the CSV file
            while (rs.next()) {
                csvPrinter.printRecord(
                        rs.getString("Username"),
                        rs.getString("Password")
                );
            }
        } catch (SQLException | IOException e) {
            // Handle any SQL or I/O exceptions that occur
            e.printStackTrace();
        }
    }

    public static void exportDBObjectToCSV(String csvFilePath) {
        // SQL query to select all records from the PlayerStats table
        String selectSQL = "SELECT * FROM Users";

        // Use a try-with-resources statement to automatically close resources after use
        try (Connection conn = DatabaseManager.getConnection();  // Obtain a connection to the database
             Statement stmt = conn.createStatement();  // Create a statement object to execute the SQL query
             ResultSet rs = stmt.executeQuery(selectSQL);  // Execute the SQL query and store the results in a ResultSet
             FileWriter writer = new FileWriter(csvFilePath);  // Create a FileWriter to write to the specified CSV file
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(rs.getMetaData()))) {  // Initialize CSVPrinter with the FileWriter and set headers using the ResultSet metadata

            // Iterate through the ResultSet and write each record to the CSV file
            while (rs.next()) {
                csvPrinter.printRecord(
                        rs.getString("Username"),
                        rs.getString("Password")
                );
            }
        } catch (SQLException | IOException e) {
            // Handle any SQL or I/O exceptions that occur
            e.printStackTrace();
        }
    }

}