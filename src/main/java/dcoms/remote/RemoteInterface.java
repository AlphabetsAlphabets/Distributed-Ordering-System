package dcoms.remote;

import java.rmi.*;
import java.sql.SQLException;

import dcoms.Errors.LoginException;
import dcoms.Errors.RegisterExecption;

public interface RemoteInterface extends Remote {
    public boolean registerUser(String username, char[] password, String phone, String email)
            throws RemoteException, SQLException, RegisterExecption;

    public boolean loginUser(String username, char[] password) throws RemoteException, SQLException, LoginException;
    public int getUserRole(String username) throws RemoteException, SQLException;
    
    public boolean ping() throws RemoteException;
}
