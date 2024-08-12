package my.spring;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class TCP {
    void sendMessage(String message) {
        System.out.println("TCP Sending: " + message);
    }


    // you can create beans in your component classes
    @Bean("myGoodGuyava") // you can provide a name for the bean
    @Qualifier("fruit") // you can provide qualifier metadata, used in autowiring (not demonstrated here)
    Guyava someGuyava() {
        return new Guyava();
    }
}
