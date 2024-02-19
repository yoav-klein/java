
import java.lang.Runnable;
import java.lang.RuntimeException;


public class Test {

    static void exec(Runnable r) {
        r.run();
        throw new RuntimeException("HHHHH");
    }

    static public void main(String[] args) {
        System.out.println("Start");

        try {
            exec(() -> System.out.println("From exec"));
        } catch(Exception e) {
            System.out.println("Exception!!");
        }
    }
}