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
                + "password TEXT NOT NULL,"
                + "phone INT UNIQUE NOT NULL,"
                + "email TEXT UNIQUE NOT NULL);";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            // Execute the SQL statement to create the table
            stmt.execute(createTableSQL);

            stmt.executeUpdate(
                    "INSERT INTO users (username, password, phone, email) VALUES ('Jayden', '1234', 0111111111, 'jayden@gmail.com')");
            stmt.executeUpdate(
                    "INSERT INTO users (username, password, phone, email) VALUES ('Brayden', '1234', 0123456789, 'brayden@gmail.com')");
            stmt.executeUpdate(
                    "INSERT INTO users (username, password, phone, email) VALUES ('Kayden', '1234', 02222222222, 'kayden@gmail.com')");
            stmt.executeUpdate(
                    "INSERT INTO users (username, password, phone, email) VALUES ('Okayden', '1234', 03333333333, 'okayden@gmail.com')");

            System.out.println("Database and table created successfully.");
        } catch (SQLException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

    }
}