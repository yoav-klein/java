package my.spring;


import org.springframework.beans.factory.annotation.Autowired;

import my.spring.JsonParser;

public class HTTP {
    @Autowired
    TCP tcp; 

    @Autowired
    Gremlin[] gremlins;

    JsonParser parser;
    Validator validator;

    @Autowired // actually, Spring will provide the JsonParser bean even without the @Autowired
    HTTP(JsonParser parser) {
        this.parser = parser;
    }

    void sendMessage(String message) {
        System.out.println("Number of Gremlins I have: " + gremlins.length);
        System.out.println("Using JsonParser: " + parser.parserCode);
        //tcp[0].sendMessage(message);
        //tcp[1].sendMessage(message);
        tcp.sendMessage(message);
    }

    @Autowired
    void setValidator(Validator validator) { this.validator = validator; }
}
