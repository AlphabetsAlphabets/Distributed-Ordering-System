package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class CreateDB {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:database.db"; // Specify your database file name here

        // SQL script to create a table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT UNIQUE NOT NULL, "
                + "password TEXT NOT NULL);";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {

            // Execute the SQL statement to create the table
            stmt.execute(createTableSQL);

            System.out.println("Database and table created successfully.");

        } catch (SQLException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}