import java.net.*;
import java.io.*;
import java.util.*;


public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int portNumber = 8080;

        try(
            ServerSocket sock = new ServerSocket(portNumber);
            Socket clientSock = sock.accept();

            ObjectOutputStream out = new ObjectOutputStream(clientSock.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSock.getInputStream());
        ) {
            Object obj;
            while((obj = in.readObject()) != null) {
                System.out.println("Received object");
                List<Integer> list = (List<Integer>)obj;
                list.stream().forEach(i -> System.out.println(i));
                
                
            }
        } catch(IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber);
            System.out.println(e.getMessage());
        }
    }
}