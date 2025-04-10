package dcoms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {
    public static boolean register(String username, char[] password, int phone, String email) throws SQLException {
        Connection connection = Database.getConnection();

        String query = "INSERT INTO users (username, password, phone, email) VALUES (?, ?, ?, ?);";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, new String(password));
        stmt.setInt(3, phone);
        stmt.setString(4, email);

        stmt.executeUpdate();

        return true;
    }
}
