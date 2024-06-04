
import java.io.*;
import java.net.*;

class StopThread extends Thread {
    private Socket serverSocket;
    PrintWriter out;
    StopThread(Socket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        out = new PrintWriter(serverSocket.getOutputStream(), true);
    }

    public void run()  {
        System.out.println("Stopping");
        out.println("POISON_PILL");
    }
}

public class Client {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 8080;

        try(
            Socket sock = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String userInput;
            while((userInput = stdIn.readLine()) != null) {
                if(userInput.equals("stop")) {
                    Runtime.getRuntime().addShutdownHook(new StopThread(sock)); 
                    return;
                }
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
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