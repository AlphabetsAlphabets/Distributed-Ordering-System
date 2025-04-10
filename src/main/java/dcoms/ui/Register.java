package dcoms.ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dcoms.Database;

public class Register {
    public static boolean register(String username, char[] password) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "SELECT username FROM users WHERE username = ?;";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        if (rs.getRow() != 0) {
            // TODO: Proper error message.
            return false;
        }

        query = "INSERT INTO users (username, password) VALUES (?, ?);";
        stmt = connection.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, new String(password));

        return true;
    }
}
