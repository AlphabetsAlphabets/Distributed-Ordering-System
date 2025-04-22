package dcoms.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dcoms.remote.RMISSLClientSocketFactory;
import dcoms.remote.RemoteInterface;
import dcoms.utils.Env;
import io.github.cdimascio.dotenv.Dotenv;

public class ClientInterface {
    private static String rmiString;
    private static Registry registry;
    private static Dotenv dotenv = Env.env;

    public ClientInterface() throws RemoteException {
        System.setProperty("javax.net.ssl.trustStore", "clienttruststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", Env.env.get("TRUSTSTORE_PASSWORD"));

        int port = Integer.parseInt(dotenv.get("RMI_PORT"));
        registry = LocateRegistry.getRegistry(dotenv.get("RMI_IP"), port,
                new RMISSLClientSocketFactory());
        rmiString = "OrderSystem";
    }

    public static RemoteInterface getFunction()
            throws MalformedURLException, RemoteException, NotBoundException {
        return (RemoteInterface) registry.lookup(rmiString);
    }
}