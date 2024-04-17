

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Client {
    private static ByteBuffer inbuffer = ByteBuffer.allocate(1024);
    private static final Charset charset = Charset.forName("UTF-8"); 


    private static String readMessage(SocketChannel sock) throws IOException {
        
        // receive response from server 
        inbuffer.clear();
        // read from socket to ByteBuffer
        int readBytes = sock.read(inbuffer);
        if(-1 == readBytes) {
            System.out.println("closing");
            sock.close();
            return null;
        }

        // flip buffer before decode
        inbuffer.flip();
        // decode ByteBuffer to String
        String message = charset.decode(inbuffer).toString();
        return message;
    }

    private static void sendMessage(SocketChannel sock, String message) throws IOException {
        ByteBuffer outbuffer;
        outbuffer = charset.encode(message);
        sock.write(outbuffer);
    }

    private static void pingPong(SocketChannel socket) {
        
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        try {
            String userInput;
            while((userInput = stdIn.readLine()) != null) {
                sendMessage(socket, userInput);
                System.out.println("echo: " + readMessage(socket));
                if(userInput.equals("close")) {
                    socket.close();
                    return;
                }
                
                
            }

        }  catch(IOException e) {
            System.exit(1);
        }
    }

    private static SocketChannel initTCPConnection() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

        return socketChannel;
    }

    public static void main(String[] args) throws Exception {
        try {
            SocketChannel socket = initTCPConnection();
            pingPong(socket);
        } catch(IOException e) {
            System.err.println("Error");
            System.err.println(e);
        } 
    }
}

