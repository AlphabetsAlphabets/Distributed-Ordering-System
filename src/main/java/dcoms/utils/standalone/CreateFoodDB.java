package dcoms.utils.standalone;

import java.sql.Statement;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateFoodDB {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String url = "jdbc:postgresql://" + dotenv.get("DB_IP") + ":" + dotenv.get("DB_PORT") + "/"
                + dotenv.get("DB_NAME");
        String db_user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        String createTableSQL = "CREATE TABLE IF NOT EXISTS food ("
                + "food_id SERIAL PRIMARY KEY, "
                + "food_name TEXT UNIQUE NOT NULL, "
                + "price DECIMAL(10, 2) NOT NULL, "
                + "quantity INTEGER NOT NULL DEFAULT 0);";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, db_user, password);
            Statement s = conn.createStatement();
            s.execute(createTableSQL);
            System.out.println("Food table created successfully.");


            String createOrdersTableSQL = "CREATE TABLE IF NOT EXISTS orders (" +
                "username TEXT NOT NULL, " +
                "order_id SERIAL PRIMARY KEY, " +
                "food_id INTEGER NOT NULL REFERENCES food(food_id), " +
                "food_name TEXT NOT NULL, " +
                "quantity INTEGER NOT NULL, " +
                "price DECIMAL(10, 2) NOT NULL, " + // NEW
                "total_price DECIMAL(10, 2) NOT NULL, " + // NEW
                "order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ");";

            Statement orderStmt = conn.createStatement();
            orderStmt.execute(createOrdersTableSQL);
            System.out.println("Orders table created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating food table: " + e.getMessage());
        }

        try {
            // Sample food data to insert
            Object[][] foods = {
                    { "Nasi Lemak", 8.50, 20 },
                    { "Ramly Burger", 7.50, 15 },
                    { "Maggi Goreng", 6.50, 30 },
                    { "Roti Canai", 3.50, 25 }
            };

            // Check for each food item if it already exists before inserting
            for (Object[] food : foods) {
                String foodName = (String) food[0];
                Double price = (Double) food[1];
                Integer quantity = (Integer) food[2];

                // Check if the food item already exists
                String checkQuery = "SELECT COUNT(*) FROM food WHERE food_name = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                checkStmt.setString(1, foodName);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                // Only insert if the food doesn't exist
                if (count == 0) {
                    String insertQuery = "INSERT INTO food (food_name, price, quantity) VALUES (?, ?, ?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                    insertStmt.setString(1, foodName);
                    insertStmt.setDouble(2, price);
                    insertStmt.setInt(3, quantity);
                    insertStmt.executeUpdate();
                    System.out.println("Inserted: " + foodName + " - $" + price + " - Qty: " + quantity);
                } else {
                    System.out.println("Skipped duplicate: " + foodName);
                }
            }

            System.out.println("Food data setup completed.");
        } catch (SQLException e) {
            System.err.println("Error inserting food data: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}
