package my.spring;

import org.springframework.stereotype.Component;

public class TCP {
    void sendMessage(String message) {
        System.out.println("TCP Sending: " + message);
    }
}
