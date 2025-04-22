package dcoms.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import dcoms.remote.RemoteInterface;
import dcoms.utils.Env;
import io.github.cdimascio.dotenv.Dotenv;

public class ClientInterface {
    private static String rmiString;
    private static Registry registry;
    private static Dotenv dotenv = Env.env;

    public ClientInterface() throws RemoteException {
        int port = Integer.parseInt(dotenv.get("RMI_PORT"));
        registry = LocateRegistry.getRegistry(dotenv.get("RMI_IP"), port,
                new RMISSLClientSocketFactory());
        rmiString = "OrderSystem";
    }

    public static RemoteInterface getFunction(String name)
            throws MalformedURLException, RemoteException, NotBoundException {
        return (RemoteInterface) Naming.lookup(rmiString + name);
    }
}