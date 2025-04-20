package dcoms.ssl;

import javax.net.ssl.*;

import java.io.*;

import java.security.KeyStore;

public class Server {
    public static void main(String[] args) {
        int port = 8443; // Secure port

        try {
            // Load the keystore
            KeyStore keyStore = KeyStore.getInstance("JKS");
            try (FileInputStream fis = new FileInputStream("clientkeystore")) {
                keyStore.load(fis, "123456".toCharArray());
            }

            // Initialize KeyManagerFactory
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(keyStore, "123456".toCharArray());

            // Initialize SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, null);

            // Create SSL ServerSocket

            SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(port);

            System.out.println("SSL Server started on port " + port);

            while (true) {
                try (SSLSocket socket = (SSLSocket) serverSocket.accept();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                    System.out.println("Client connected.");

                    String received = reader.readLine();
                    System.out.println("Received: " + received);

                    writer.println("Hello from SSL Server");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
