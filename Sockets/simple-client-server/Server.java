import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        int portNumber = 8080;

        try(
            ServerSocket sock = new ServerSocket(portNumber);
            Socket clientSock = sock.accept();

            PrintWriter out = new PrintWriter(clientSock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        ) {
            String serverLine;
            String userLine;
            while(true) {
                userLine = stdIn.readLine();
                out.println(userLine);
                serverLine = in.readLine();
                System.out.println("client: " + serverLine);
            }

            /* while((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            } */
        } catch(IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber);
            System.out.println(e.getMessage());
        }
    }
}