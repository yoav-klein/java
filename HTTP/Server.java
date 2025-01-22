

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.OutputStream;

public class Server {


    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);

        server.createContext("/", new Handler());

        server.start();

    }

    private static class Handler implements HttpHandler {

        public Handler() {
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                System.out.println("Do work");
                String response = "Hello from server";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } finally {}

        }

    }
}