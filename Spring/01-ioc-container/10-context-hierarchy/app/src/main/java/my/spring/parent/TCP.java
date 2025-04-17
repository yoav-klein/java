package my.spring.parent;

import org.springframework.stereotype.Component;

public class TCP {
    public void sendMessage(String message) {
        System.out.println("TCP Sending: " + message);
    }
}
