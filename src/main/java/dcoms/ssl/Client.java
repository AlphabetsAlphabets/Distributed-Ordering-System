package dcoms.ssl;

import javax.net.ssl.*;

import java.io.*;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8443;

        try {
            // Set truststore (optional if using a self-signed certificate)
            System.setProperty("javax.net.ssl.trustStore", "clientkeystore");
            System.setProperty("javax.net.ssl.trustStorePassword", "123456");

            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            try (SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                writer.println("Hello SSL Server!");
                System.out.println("Server response: " + reader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
