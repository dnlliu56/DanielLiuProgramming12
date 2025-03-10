package com.liu.database;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:derby:myDB;create=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTable() {
        dropTable("USERS"); // Drops any prior existing table to refresh data
        String createTableSQL = "CREATE TABLE USERS (" +
                "username VARCHAR(50) PRIMARY KEY, " +
                "password VARCHAR(50))";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("✅ USERS table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) { // Derby code for "table already exists"
                System.out.println("⚠️ USERS table already exists.");
            } else {
                e.printStackTrace();
                System.out.println("❌ Failed to create USERS table.");
            }
        }
    }

    public static void dropTable(String tableName) {
        String dropTableSQL = "DROP TABLE \"" + tableName + "\"";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(dropTableSQL);
            System.out.println("✅ " + tableName + " table dropped.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("42Y55")) { // Derby code for "table does not exist"
                System.out.println("⚠️ " + tableName + " table does not exist.");
            } else {
                System.err.println("❌ Failed to drop " + tableName + " table: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Test method while debugging
    public static void printAllUsers() {
        String selectSQL = "SELECT * FROM USERS"; // Use "Users" or "USERS" as appropriate

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            System.out.println("Users in the database:");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println("Username: " + username + ", Password: " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Test method while debugging
    public static void printAllTables() {
        try (Connection conn = getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "%", new String[]{"TABLE"});

            System.out.println("Tables in the database:");
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                System.out.println(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}