package dcoms.remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dcoms.utils.Env;

import io.github.cdimascio.dotenv.Dotenv;

public class Register {
        public static void main(String[] args) throws Exception {
                Env.loadEnv();
                Dotenv dotenv = Env.env;
                int port = Integer.parseInt(dotenv.get("RMI_PORT"));

                System.setProperty("javax.net.ssl.keyStore", "serverkeystore.jks");
                System.setProperty("javax.net.ssl.keyStorePassword", dotenv.get("SERVER_KEYSTORE_PASSWORD"));

                Registry reg = LocateRegistry.createRegistry(port, new RMISSLClientSocketFactory(),
                                new RMISSLServerSocketFactory());
                Server server = new Server();

                reg.rebind("OrderSystem", server);
                System.out.println("Server running.");
        }
}
