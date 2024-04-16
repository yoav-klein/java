import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {
    public static void main(String[] args) {
        try (
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            SocketChannel socketChannel = SocketChannel.open();
        ){
            
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
            
            ByteBuffer inbuffer = ByteBuffer.allocate(1024);
            Charset charset = Charset.forName("UTF-8"); 
            
            while(true) {
                
                // read message from console
                String message = stdin.readLine();
                // encode message to ByteBuffer
                ByteBuffer buffer = charset.encode(message);
                // write to socket
                socketChannel.write(buffer);
                
                inbuffer.clear();
                // read from socket to ByteBuffer
                int readBytes = socketChannel.read(inbuffer);
                if(-1 == readBytes) {
                    socketChannel.close();
                    return;
                }
                System.out.println("position: " + inbuffer.position());
                // flip buffer before decode
                inbuffer.flip();
                // decode ByteBuffer to String
                String received = charset.decode(inbuffer).toString();
                System.out.println("received: " + received);   


            }
           
            //ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            
            // socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



/* 
import java.io.*;
import java.net.*;


import java.nio.ByteBuffer; 
import java.nio.charset.Charset; 

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

public class Client  {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 8080;

        try(
            SocketChannel sock = new SocketChannel(hostName, portNumber);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream(), "UTF-8"), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String userInput;
            /* Charset charset = Charset.forName("UTF-8"); 
            ByteBuffer byteBuffer = charset.encode(inputString);  */
            /* while((userInput = stdIn.readLine()) != null) {
                //ByteBuffer byteBuffer = charset.encode(inputString); 
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
} */