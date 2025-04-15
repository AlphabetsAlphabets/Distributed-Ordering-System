package dcoms.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dcoms.remote.RemoteInterface;
import dcoms.utils.Env;
import io.github.cdimascio.dotenv.Dotenv;

public class ClientInterface {
    private static String rmiString;

    private static Dotenv dotenv = Env.env;

    public ClientInterface() {
        rmiString = "rmi://" + dotenv.get("RMI_IP") + ":" + dotenv.get("RMI_PORT") + "/";

    }

    public static RemoteInterface getFunction(String name)
            throws MalformedURLException, RemoteException, NotBoundException {
        if (rmiString == null) {
            throw new IllegalStateException("RMI connection string not initialized. Make sure Env.loadEnv() was called.");
        }
        return (RemoteInterface) Naming.lookup(rmiString + name);
    }
}
