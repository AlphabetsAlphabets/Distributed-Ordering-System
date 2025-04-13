package dcoms.utils.standalone;

import java.sql.*;

public class CreateDB {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String db_user = "star"; // user must have CREATEDB privilege and schema access.
        String password = "password";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id SERIAL PRIMARY KEY,"
                + "username TEXT UNIQUE NOT NULL, "
                + "password TEXT NOT NULL,"
                + "phone TEXT UNIQUE NOT NULL,"
                + "email TEXT UNIQUE NOT NULL,"
                + "admin INT NOT NULL);";

        try (Connection conn = DriverManager.getConnection(url, db_user, password)) {
            // Create the table
            try (Statement s = conn.createStatement()) {
                s.executeUpdate(createTableSQL); // ✅ Corrected
                System.out.println("Users table created (or already exists).");
            }

            // Insert default users
            String query = "INSERT INTO users (username, password, phone, email, admin) VALUES (?, ?, ?, ?, ?);";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                String[][] users = {
                        { "Jayden", "1234", "0111111111", "jayden@gmail.com", "1" },
                        { "Brayden", "1234", "0123456789", "brayden@gmail.com", "0" },
                        { "Kayden", "1234", "02222222222", "kayden@gmail.com", "0" },
                        { "Okayden", "1234", "03333333333", "okayden@gmail.com", "0" },
                };

                for (String[] user : users) {
                    stmt.setString(1, user[0]);
                    stmt.setString(2, user[1]);
                    stmt.setString(3, user[2]);
                    stmt.setString(4, user[3]);
                    stmt.setInt(5, Integer.parseInt(user[4]));

                    stmt.addBatch();
                }

                stmt.executeBatch();
                System.out.println("Default users inserted.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
}
