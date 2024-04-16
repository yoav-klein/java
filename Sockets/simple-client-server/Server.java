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
                userLine = stdIn.readLine(); // read from user
                // out.println(userLine); // write to socket
                out.print("sometextwithoutlineend");
                serverLine = in.readLine(); // read from socket
                System.out.println("client: " + serverLine); // write to user
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