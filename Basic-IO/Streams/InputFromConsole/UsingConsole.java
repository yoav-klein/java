import java.io.Console;
import java.io.IOException;

public class UsingConsole {
    
    public static void main (String args[]) throws IOException {

        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        while(true) {
            String line = c.readLine("Say something: ");
            System.out.println(line);
        }
    }
    
}