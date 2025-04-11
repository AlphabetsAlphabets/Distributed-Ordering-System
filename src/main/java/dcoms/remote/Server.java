package dcoms.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dcoms.Database;

public class Server extends UnicastRemoteObject implements RemoteInterface {
    public Server() throws RemoteException {
        super();
    }

    @Override
    public boolean registerUser(String username, char[] password, int phone, String email)
            throws RemoteException, SQLException {

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
