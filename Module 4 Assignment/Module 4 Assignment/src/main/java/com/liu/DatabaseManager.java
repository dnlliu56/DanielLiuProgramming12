package com.liu;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:derby:myDB;create=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTable() {
        String createTableSQL = "CREATE TABLE PlayerStats ("
                + "ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "Name VARCHAR(100),"
                + "Team VARCHAR(100),"
                + "GamesPlayed INT,"
                + "AtBats INT,"
                + "Runs INT,"
                + "Hits INT,"
                + "Doubles INT,"
                + "Triples INT,"
                + "HomeRuns INT,"
                + "RBIs INT"
                + ")";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Check if the table already exists
            ResultSet rs = conn.getMetaData().getTables(null, null, "PLAYERSTATS", null);
            if (!rs.next()) {
                stmt.execute(createTableSQL);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}