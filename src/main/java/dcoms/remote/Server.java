package dcoms.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dcoms.Database;

public class Server extends UnicastRemoteObject implements RemoteInterface {
    public Server() throws RemoteException {
        super();
    }

    @Override
    public boolean registerUser(String username, char[] password, String phone, String email)
            throws RemoteException, SQLException {

        Connection connection = Database.getConnection();

        String query = "INSERT INTO users (username, password, phone, email) VALUES (?, ?, ?, ?);";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, new String(password));
        stmt.setString(3, phone);
        stmt.setString(4, email);

        stmt.executeUpdate();

        return true;
    }

    @Override
    public boolean loginUser(String username, char[] password) throws RemoteException, SQLException {
        Connection connection = Database.getConnection();

        String query = "SELECT COUNT(username) as num_users FROM users WHERE username=? AND password=?;";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, new String(password));

        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt("num_users");
        if (count > 1) {
            // TODO: Pass this through the error class
            return false;
        } else if (count == 0) {
            return false;
        }

        return true;
    }
}
