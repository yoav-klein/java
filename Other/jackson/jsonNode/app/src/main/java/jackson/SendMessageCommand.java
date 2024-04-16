
package jackson;

public class SendMessageCommand {
    private CommandType type = CommandType.SEND_MESSAGE_TO_USER;
    private String toUser;
    private String message;
    
    public SendMessageCommand() {} // In order for jackson to build an instance of SendMessageCommand, you must have a 
    // no-argument constructor


    public SendMessageCommand(String toUser, String message) {
        this.toUser = toUser;
        this.message = message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    void setToUser(String toUser) {
        this.toUser = toUser;
    }

    void setType(CommandType type) {
        this.type = type;
    }

    public String getToUser() {
        return this.toUser;
    }

    public String getMessage() {
        return this.message;
    }

    public CommandType getType() {
        return this.type;
    }

}