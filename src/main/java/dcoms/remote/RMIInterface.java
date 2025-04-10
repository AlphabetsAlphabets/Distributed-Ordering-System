package dcoms.remote;

import java.rmi.*;

public interface RMIInterface extends Remote {
    public void test() throws RemoteException;
}
