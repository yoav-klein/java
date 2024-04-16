
import java.io.*;
import java.net.*;

public class Client  {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 8080;

        try(
            Socket sock = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String serverLine;
            String userLine;
            while(true) {
                serverLine = in.readLine();
                System.out.println("server: " + serverLine);
                userLine = stdIn.readLine();
                out.println(userLine);
            }

        } catch(UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch(IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}