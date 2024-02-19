
import java.util.*;
import java.io.Serializable;



public class Test {
    public static <T> T pick(T a, T b) {
        System.out.println(b.getClass());
        System.out.println(a.getClass());
        return b;
    }

    public static <T extends Number> void inspect(T a, T b) {
        System.out.println(a.getClass());
    }

    public static void foo() {
        //Box<String> b = new Box<>();

        String s = "Hello";
        Integer a = 4;

        Serializable ser = pick(s, new ArrayList<String>());
        
        System.out.println(ser.getClass());
        System.out.println("-----------------");

        inspect(new Double(3), new Double(1.2));
    }

    public static <T> void toot(T a, T b) {
        T tmp = new String();
    
    }
    public static void main(String[] args) {
       /* List<Integer> li = new ArrayList<Integer>();

        if(li instanceof ArrayList<Integer>) {
            System.out.println("yes");
        }*/

        Box<Integer> b = new Box<>();

        Integer i;
        System.out.println(i.getClass());
        // System.out.println(b instanceof Box<Integer>);

    }
}