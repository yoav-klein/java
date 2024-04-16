
import java.io.*;
import java.net.*;
import java.util.*;

public class Client  {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 8080;

        try(
            Socket sock = new Socket(hostName, portNumber);
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String userInput;
            while((userInput = stdIn.readLine()) != null) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(3);
                list.add(50);
                list.add(12);

                out.writeObject(list);
                
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