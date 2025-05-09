package dcoms.remote;

import java.rmi.*;
import java.sql.SQLException;
import java.util.ArrayList;

import dcoms.Errors.LoginException;
import dcoms.Errors.RegisterExecption;

public interface RemoteInterface extends Remote {
    public boolean registerUser(String username, char[] password, String phone, String email)
            throws RemoteException, SQLException, RegisterExecption;

    public boolean loginUser(String username, char[] password) throws RemoteException, SQLException, LoginException;

    public int getUserRole(String username) throws RemoteException, SQLException;

    public boolean ping() throws RemoteException;

    public int handleOrder(String foodName, int quantity) throws RemoteException, SQLException;

    public int getQuantity(String foodName) throws RemoteException, SQLException;

    public ArrayList<String[]> getOrders() throws RemoteException, SQLException;
}