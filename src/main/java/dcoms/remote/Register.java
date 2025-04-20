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

        Registry reg = LocateRegistry.createRegistry(port);
        Server server = new Server();

        reg.bind("registerUser", server);
        reg.bind("loginUser", server);

        System.out.println("Server running.");
    }
}
