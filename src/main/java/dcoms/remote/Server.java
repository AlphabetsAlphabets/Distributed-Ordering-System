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

        String query = "SELECT COUNT(username) FROM users WHERE username=? AND password=?;";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, new String(password));

        ResultSet rs = stmt.executeQuery();
        System.out.println("Attempting login for user: " + username);
        
        if (rs.next()) {
            int count = rs.getInt(1); // Use column index instead of name
            System.out.println("Found " + count + " matching users");
            
            if (count > 1) {
                System.out.println("Multiple users found with same credentials!");
                return false;
            } else if (count == 1) {
                System.out.println("Login successful");
                return true;
            } else {
                System.out.println("No matching users found");
                return false;
            }
        }
        
        System.out.println("No results returned from query");
        return false;
    }
}
