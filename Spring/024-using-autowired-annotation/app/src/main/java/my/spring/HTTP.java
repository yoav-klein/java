package my.spring;


import org.springframework.beans.factory.annotation.Autowired;

import my.spring.JsonParser;

public class HTTP {
    @Autowired
    TCP[] tcp;
    JsonParser parser;
    Validator validator;

    @Autowired
    HTTP(JsonParser parser) {
        this.parser = parser;
    }

    void sendMessage(String message) {
        System.out.println("Using JsonParser: " + parser.parserCode);
        tcp[0].sendMessage(message);
        tcp[1].sendMessage(message);
    }

    @Autowired
    void setValidator(Validator validator) { this.validator = validator; }
}
