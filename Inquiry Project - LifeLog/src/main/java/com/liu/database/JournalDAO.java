package com.liu.database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JournalDAO {
    private final String url = "jdbc:derby:journalDB;create=true";

    public JournalDAO() {
        createTableIfNotExists(); // Make sure the journal table exists when we start
    }

    // This method creates the journal table if it doesn't already exist
    private void createTableIfNotExists() {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE journal (" +
                            "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                            "title VARCHAR(255), " +
                            "content CLOB, " +
                            "lastModified TIMESTAMP)"
            );
        } catch (SQLException e) {
            // If the error means the table already exists, ignore it
            if (!e.getSQLState().equals("X0Y32")) e.printStackTrace();
        }
    }

    // This method adds a new journal entry to the database
    public void addEntry(String title, String content) {
        String sql = "INSERT INTO journal (title, content, lastModified) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now())); // Set current date/time
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This method updates an existing entry using its ID
    public void updateEntry(int id, String title, String content) {
        String sql = "UPDATE journal SET title=?, content=?, lastModified=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(4, id); // Identify which entry to update
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This method gets all journal entries and returns them as a list
    public List<JournalEntry> getAllEntries() {
        List<JournalEntry> entries = new ArrayList<>();
        String sql = "SELECT * FROM journal ORDER BY lastModified DESC"; // Newest first
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                entries.add(new JournalEntry(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("lastModified").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    // This method deletes an entry by its ID
    public void deleteEntry(int id) {
        String sql = "DELETE FROM journal WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This method is unused in your code, but it's for making a custom DB connection
    private Connection getConnection() throws SQLException {
        try {
            // Load the Derby JDBC driver (not needed for embedded DBs usually)
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            String url = "jdbc:derby://localhost:1527/journalAppDB;create=true";
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Database driver not found.", e);
        }
    }
}
