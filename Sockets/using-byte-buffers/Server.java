

// selector related
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;

// sockets related
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

// network and buffer
import java.nio.ByteBuffer;
import java.net.InetSocketAddress;

import java.util.Set;
import java.util.Iterator;

import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.CharBuffer;





public class Server {
    private static final int PORT = 8080;
    private static ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void showBuffer(ByteBuffer b) {
        System.out.println("capacity: " + b.capacity());
        System.out.println("limit: " + b.limit());
        System.out.println("position: " + b.position());
        
    }

    public static void main(String[] args) {

        try(
            ServerSocketChannel serverSocket = ServerSocketChannel.open(); 
        ) {
        
            Charset charset = Charset.forName("UTF-8");
            // serverSocket.configureBlocking(false); // must be non-blocking to allow selecting
            serverSocket.bind(new InetSocketAddress(PORT));
            SocketChannel client = serverSocket.accept(); // accept the connection from client
            // client.configureBlocking(false);

            while(true) {
                buffer.clear();

                System.out.println("Start of loop");
                showBuffer(buffer);
                // read from socket to ByteBuffer
                int readBytes = client.read(buffer);
                System.out.println("After read from client");
                showBuffer(buffer);
                
                if(-1 == readBytes) {
                    client.close();
                    return;
                }

                // flip buffer before decode
                buffer.flip();
                System.out.println("After flip");
                showBuffer(buffer);
                
                // decode ByteBuffer to String
                String received = charset.decode(buffer).toString();
                System.out.println("client says: " + received);   

                // encode String to ByteBuffer
                ByteBuffer outBuffer = charset.encode(received);

                // write to socket
                client.write(outBuffer);
            }

        } catch(IOException e) {
            System.err.println(e);
        }
    }
}