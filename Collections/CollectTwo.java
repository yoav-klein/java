import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 */

public class CollectTwo {
    
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Yoav", 30));
        people.add(new Person("Dikla", 60));

        List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());

        System.out.println(list.get(0));

    }
}
