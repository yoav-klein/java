
package jackson;

public class SendMessageCommand {
    private final CommandType type = CommandType.SEND_MESSAGE_TO_USER;
    private String toUser;
    private String message;

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