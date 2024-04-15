
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListExample {
    public static void main(String[] args) {
        // a simple example of ArrayList

        List<Person> roster = new ArrayList<>();
        roster.add(new Person("Shimon", 22));
        roster.add(new Person("Yehuda", 30));
        roster.add(new Person("Sara", 50));
        roster.add(new Person("Ilya", 40));

        // positional access
        System.out.println(roster.get(2).getName());

        roster.remove(2);
        roster.add(2, new Person("John", 2));

        // iteration
        ListIterator<Person> iter = roster.listIterator();
        while(iter.hasNext()) {
            Person curr = iter.next();
            System.out.println(curr.getName());
        }

        // list iterator can go backwards
        while(iter.hasPrevious()) {
            Person curr = iter.previous();
            System.out.println(curr.getName());
        }
    }
}
