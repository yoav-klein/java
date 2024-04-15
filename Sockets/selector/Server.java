
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

public class Server {
    private static final int PORT = 8080;
    private static ByteBuffer buffer = ByteBuffer.allocate(1024);


    private static void start() throws IOException {
        // first, we create a selectable channel
        ServerSocketChannel serverSocket = ServerSocketChannel.open(); 
        serverSocket.bind(new InetSocketAddress(PORT));
        serverSocket.configureBlocking(false); // must be non-blocking to allow selecting

        // now, we create a Select instance
        Selector selector = Selector.open();

        // register the channel with the selector. our interest is "accept", as 
        // this socket will accept connections
        serverSocket.register(selector, SelectionKey.OP_ACCEPT); 

        // now we block on "select()" until a channel is ready for communication
        while(true) {
            selector.select();
            // we get the list of SelectionKeys that are ready for communication
            Set<SelectionKey> selectedKeys = selector.selectedKeys(); // returns a Set<SelectionKey>
            Iterator<SelectionKey> iter = selectedKeys.iterator(); // get the iterator of the set

            while(iter.hasNext()) {
                SelectionKey curr = iter.next();
                iter.remove(); // remove from the set

                if(curr.isAcceptable()) { // a new connection arrived, register it with the selector
                    System.out.println("INFO: Received new client");
                    registerClient(selector, serverSocket);
                }

                if(curr.isReadable()) { // one of the client sockets want to talk
                    answerWithEcho((SocketChannel) curr.channel());
                }
            }


        }


    }

    private static void registerClient(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept(); // accept the connection from client
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ); // register, this time with OP_READ because this socket is for reading, not receiving connections
    }

    private static void answerWithEcho(SocketChannel client) throws IOException {
        buffer.clear();
        int readBytes = client.read(buffer);
        if(-1 == readBytes ||  new String(buffer.array()).trim().equals("POISON_PILL")) {
            client.close();
            return;
        }
        buffer.flip();
        client.write(buffer);
    }

    public static void main(String[] args) {
        try {
            start();
        } catch(IOException e) {
            System.err.println(e);
        }
    }
}