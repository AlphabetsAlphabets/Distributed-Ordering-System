package dcoms.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements RMIInterface {
    public Server() throws RemoteException {
        super();
    }

    @Override
    public void test() throws RemoteException {
        System.out.println("Hello");
    }
}
