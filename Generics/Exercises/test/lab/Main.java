
import java.util.*;

public class Main {
    public static <T extends Comparable<T>> void inspect(List<? extends T> l, T unit) {
        //T tmp = l.get(0);
    }

    public static void main(String[] args) {
        Apple a1 = new Apple(15);
        Apple a2 = new Apple(12);

        List<Apple> apples = new ArrayList<>();
        apples.add(a1);
        apples.add(a2);
        //System.out.println(Algorithm.max(apples));

        Student john = new Student();
        Student dana = new Student();
        List<Student> students = new ArrayList<Student>();
        students.add(john);
        students.add(dana);

        System.out.println(Algorithm.max(students));

        inspect(students, new Person());


    }
}
