package dcoms.utils.standalone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FoodDB {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mydb";
    private static final String USER = "star";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try {
            // Register PostgreSQL driver
            Class.forName("org.postgresql.Driver");
            
            // Open a connection
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // Create food table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS food " +
                    "(food_id SERIAL PRIMARY KEY, " +  // PostgreSQL serial type
                    " food_name VARCHAR(100), " + 
                    " price DECIMAL(10,2))";

            Statement stmt = conn.createStatement();
            stmt.execute(createTableSQL);
            System.out.println("Created food table in database...");

            // Insert sample data
            String insertDataSQL = "INSERT INTO food (food_name, price) VALUES " +
                    "('Nasi Lemak', 8.50)," +
                    "('Maggi Goreng', 6.50)," +
                    "('Roti Canai', 2.50)," +
                    "('Ramly Burger', 7.50)";
            
            stmt.executeUpdate(insertDataSQL);
            System.out.println("Inserted sample data into food table...");

            // Clean up
            stmt.close();
            conn.close();
            System.out.println("Database setup completed successfully!");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
