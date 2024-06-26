/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


public class App {

    public static void main(String[] args) throws IOException {
        
        ObjectMapper mapper = new ObjectMapper();

        SendMessageCommand messageCommand = new SendMessageCommand("Yaniv", "How are you Yaniv?");
        String jsonString = mapper.writeValueAsString(messageCommand);

        JsonNode actualObj = mapper.readTree(jsonString);

        JsonNode jsonNode1 = actualObj.get("type");
        String textValue = jsonNode1.textValue();
        
        CommandType type = CommandType.valueOf(textValue);

        switch(type) {
            case SEND_MESSAGE_TO_USER:
                SendMessageCommand command = mapper.readValue(jsonString, SendMessageCommand.class);
                System.out.println(command.getToUser() + " " + command.getMessage());
                break;
        }
        
    }
}
