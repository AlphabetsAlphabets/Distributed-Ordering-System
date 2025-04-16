package dcoms.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dcoms.Errors.LoginException;
import dcoms.Errors.RegisterExecption;
import dcoms.utils.Database;

public class Server extends UnicastRemoteObject implements RemoteInterface {
    public Server() throws RemoteException {
        super();
    }

    @Override
    public boolean registerUser(String username, char[] password, String phone, String email)
            throws RemoteException, SQLException, RegisterExecption {

        Connection connection = Database.getConnection();

        String query = "INSERT INTO users (username, password, phone, email) VALUES (?, ?, ?, ?);";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, new String(password));
        stmt.setString(3, phone);
        stmt.setString(4, email);

        // TODO: Handle a few SQL exceptions and convert to RegisterException
        stmt.executeUpdate();

        return true;
    }

    @Override
    public int getUserRole(String username) throws RemoteException, SQLException {
        Connection connection = Database.getConnection();

        String query = "SELECT admin FROM users WHERE username = ?;";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("admin"); // 1 = admin, 0 = client
        }

        throw new SQLException("User not found when fetching role.");
    }

    @Override
    public boolean loginUser(String username, char[] password) throws RemoteException, SQLException, LoginException {
        Connection connection = Database.getConnection();

        String query = "SELECT COUNT(username) as num_users FROM users WHERE username=? AND password=?;";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, new String(password));

        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt("num_users");
        if (count > 1) {
            throw new LoginException("Multiple user this should not be possible.");
        } else if (count == 0) {
            throw new LoginException("Username or Password is wrong.");
        }

        return true;
    }
}
