package dcoms;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyRegistry {
    public static void main(String[] args) throws Exception {
        Registry reg = LocateRegistry.createRegistry(1040);
        Server server = new Server();
        reg.bind("test", server);
    }
}
