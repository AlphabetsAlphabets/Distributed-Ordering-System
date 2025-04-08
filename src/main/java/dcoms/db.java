package dcoms;

import java.sql.Connection;
import java.sql.DriverManager;

public class db {
    public static void main(String[] args) {
        try (Connection connection = DriverManager
                .getConnection("jdbc:sqlite:test.db")) {
            System.out.println("All good");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
