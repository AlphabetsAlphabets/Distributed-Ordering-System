package dcoms.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dcoms.remote.RemoteInterface;

public class ClientInterface {
    private static String rmiString;

    public ClientInterface(String ip, int port) {
        rmiString = "rmi://" + ip + ":" + port + "/";
    }

    public static RemoteInterface getFunction(String name)
            throws MalformedURLException, RemoteException, NotBoundException {
        return (RemoteInterface) Naming.lookup(rmiString + name);
    }
}
