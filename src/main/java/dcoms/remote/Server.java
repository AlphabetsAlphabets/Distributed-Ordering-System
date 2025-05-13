package dcoms.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

        String query = "INSERT INTO users (username, password, phone, email, admin) VALUES (?, ?, ?, ?, 0);";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setString(1, username);
        stmt.setString(2, new String(password));
        stmt.setString(3, phone);
        stmt.setString(4, email);

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

    @Override
    public boolean ping() throws RemoteException {
        return true;
    }

    @Override
    public int handleOrder(String foodName, int quantity) throws RemoteException, SQLException {
        int updated = checkQuantity(foodName, quantity);
        if (updated <= 0) {
            return updated;
        }

        return addOrder(foodName, quantity);
    }

    private int addOrder(String foodName, int quantity) throws SQLException {
        Connection conn = Database.getConnection();
        dcoms.utils.UserSession currentSession = dcoms.utils.Session.loadSession();
        if (currentSession == null || currentSession.username == null) {
            return 0;
        }

        String username = currentSession.username;

        String getInfoQuery = "SELECT food_id, price FROM food WHERE food_name = ?";
        PreparedStatement getInfoStmt = conn.prepareStatement(getInfoQuery);
        getInfoStmt.setString(1, foodName);
        ResultSet infoResult = getInfoStmt.executeQuery();

        int foodId = -1;
        double price = 0.0;
        if (!infoResult.next()) {
            return 0;
        }

        foodId = infoResult.getInt("food_id");
        price = infoResult.getDouble("price");

        String insertOrderQuery = "INSERT INTO orders (username, food_id, food_name, quantity, price, total_price) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement insertOrderStmt = conn.prepareStatement(insertOrderQuery);

        insertOrderStmt.setString(1, username);
        insertOrderStmt.setInt(2, foodId);
        insertOrderStmt.setString(3, foodName);
        insertOrderStmt.setInt(4, quantity);
        insertOrderStmt.setDouble(5, price);
        insertOrderStmt.setDouble(6, price * quantity);
        insertOrderStmt.executeUpdate();

        return 1;
    }

    private int checkQuantity(String foodName, int quantity) throws RemoteException, SQLException {
        Connection conn = Database.getConnection();

        // Atomic update: Only subtract quantity IF current stock is enough
        String updateQuery = "UPDATE food SET quantity = quantity - ? WHERE food_name = ? AND quantity >= ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setInt(1, quantity);
        updateStmt.setString(2, foodName);
        updateStmt.setInt(3, quantity);

        return updateStmt.executeUpdate();
    }

    @Override
    public int getQuantity(String foodName) throws SQLException {
        Connection conn = Database.getConnection();

        // If no row was updated, stock was changed or insufficient
        // Get the latest available stock to show a message
        String recheckQuery = "SELECT quantity FROM food WHERE food_name = ?";
        PreparedStatement recheckStmt = conn.prepareStatement(recheckQuery);
        recheckStmt.setString(1, foodName);
        ResultSet rs = recheckStmt.executeQuery();

        if (rs.next()) {
            int currentStock = rs.getInt("quantity");
            return currentStock;
        }

        return -1;
    }

    @Override
    public ArrayList<String[]> getOrders() throws RemoteException, SQLException {
        Connection conn = dcoms.utils.Database.getConnection();

        String query = "SELECT username, order_id, food_id, food_name, quantity, price, total_price, order_time FROM orders ORDER BY order_time DESC";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        ArrayList<String[]> rows = new ArrayList<>();

        while (rs.next()) {
            String username = rs.getString("username");
            int orderId = rs.getInt("order_id");
            int foodId = rs.getInt("food_id");
            String foodName = rs.getString("food_name");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            double totalPrice = rs.getDouble("total_price");
            String orderTime = rs.getTimestamp("order_time").toString(); // if you want to use timestamp later

            String[] row = { username, Integer.toString(orderId), Integer.toString(foodId), foodName,
                    Integer.toString(quantity),
                    String.format("RM %.2f", price),
                    String.format("RM %.2f", totalPrice), orderTime
            };

            rows.add(row);
        }

        rs.close();
        stmt.close();

        return rows;
    }
}