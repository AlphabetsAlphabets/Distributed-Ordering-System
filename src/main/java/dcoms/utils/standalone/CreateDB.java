package dcoms.utils.standalone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class CreateDB {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:database.db"; // Specify your database file name
        // here

        // SQL script to create a table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT UNIQUE NOT NULL, "
                + "password TEXT NOT NULL);";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            // Execute the SQL statement to create the table
            stmt.execute(createTableSQL);

            stmt.executeUpdate("INSERT INTO users (username, password) VALUES ('Jayden', '1234')");
            stmt.executeUpdate("INSERT INTO users (username, password) VALUES ('Brayden', '1234')");
            stmt.executeUpdate("INSERT INTO users (username, password) VALUES ('Kayden', '1234')");
            stmt.executeUpdate("INSERT INTO users (username, password) VALUES ('Okayden', '1234')");

            System.out.println("Database and table created successfully.");
        } catch (SQLException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

    }
}