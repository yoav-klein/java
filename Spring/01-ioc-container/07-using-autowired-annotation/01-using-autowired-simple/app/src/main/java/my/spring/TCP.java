package my.spring;

public class TCP {
    int serial;

    TCP(int serial) {
        this.serial = serial;
    }

    void sendMessage(String message) {
        System.out.println("TCP Serial: " + serial + ", Sending: " + message);
    }
}
