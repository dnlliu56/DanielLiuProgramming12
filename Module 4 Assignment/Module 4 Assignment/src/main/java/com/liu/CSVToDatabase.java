package com.liu;

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
                String name = record.get("Name");
                String team = record.get("Team");
                int gamesPlayed = Integer.parseInt(record.get("GamesPlayed"));
                int atBats = Integer.parseInt(record.get("AtBats"));
                int runs = Integer.parseInt(record.get("Runs"));
                int hits = Integer.parseInt(record.get("Hits"));
                int doubles = Integer.parseInt(record.get("Doubles"));
                int triples = Integer.parseInt(record.get("Triples"));
                int homeRuns = Integer.parseInt(record.get("HomeRuns"));
                int rbis = Integer.parseInt(record.get("RBIs"));

                // Insert the extracted data into the database
                insertIntoDatabase(name, team, gamesPlayed, atBats, runs, hits, doubles, triples, homeRuns, rbis);
            }
        } catch (IOException | SQLException e) {
            // Handle any I/O or SQL exceptions that occur
            e.printStackTrace();
        }
    }


    private static void insertIntoDatabase(String name, String team, int gamesPlayed, int atBats, int runs, int hits, int doubles, int triples, int homeRuns, int rbis) throws SQLException {
        // SQL statement for inserting a new record into the PlayerStats table
        String insertSQL = "INSERT INTO PlayerStats (Name, Team, GamesPlayed, AtBats, Runs, Hits, Doubles, Triples, HomeRuns, RBIs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Use a try-with-resources statement to automatically close the Connection and PreparedStatement
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // Set the values for the PreparedStatement from the method parameters
            pstmt.setString(1, name);
            pstmt.setString(2, team);
            pstmt.setInt(3, gamesPlayed);
            pstmt.setInt(4, atBats);
            pstmt.setInt(5, runs);
            pstmt.setInt(6, hits);
            pstmt.setInt(7, doubles);
            pstmt.setInt(8, triples);
            pstmt.setInt(9, homeRuns);
            pstmt.setInt(10, rbis);

            // Execute the SQL statement to insert the data into the database
            pstmt.executeUpdate();
        }
    }

}