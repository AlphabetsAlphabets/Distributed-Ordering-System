package dcoms.remote;

import java.rmi.*;
import java.sql.SQLException;

public interface RemoteInterface extends Remote {
    public boolean registerUser(String username, char[] password, int phone, String email)
            throws RemoteException, SQLException;
}
