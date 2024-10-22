package com.liu.database;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:derby:myDB;create=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTable() {
        String createTableSQL = "CREATE TABLE Users ("
                + "Username VARCHAR(100),"
                + "Password VARCHAR(100)"
                + ")";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Check if the table already exists
            ResultSet rs = conn.getMetaData().getTables(null, null, "USERS", null);
            if (!rs.next()) {
                stmt.execute(createTableSQL);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}