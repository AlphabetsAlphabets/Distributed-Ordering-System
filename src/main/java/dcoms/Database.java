package dcoms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static Connection conn;
    private static boolean connected;

    private static void connect() {
        if (connected)
            return;

        String url = "jdbc:postgresql://localhost:5432/mydb";
        String db_user = "star"; // user must have CREATEDB privilege.
        String password = "1234";

        try {
            conn = DriverManager.getConnection(url, db_user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connected = true;
    }

    public static void disconnect() {
        try {
            if (connected) {
                if (conn != null && !conn.isClosed()) {
                    System.out.println("Database connection closed.");
                    connected = false;
                }
            } else {
                System.out.println("Not connected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        connect();
        return conn;
    }

    public static void select() {
        connect();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("username") + ". Password: " + rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
