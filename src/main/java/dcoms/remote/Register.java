package dcoms.remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Register {
    public static void main(String[] args) throws Exception {
        Registry reg = LocateRegistry.createRegistry(1040);
        Server server = new Server();

        reg.bind("registerUser", server);
        reg.bind("loginUser", server);

        System.out.println("Server running.");
    }
}
