package my.spring;

public class Server {
    private HTTPServer http;

    public Server(HTTPServer http) { 
        this.http = http; 
        System.out.println("Server constructor");
    }
    
    void sendMessage() {
        http.sendMessage();
    }

}