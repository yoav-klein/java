
import java.util.*;
import java.util.function.*;


class Averager implements IntConsumer
{
    private int total = 0;
    private int count = 0;
        
    public double average() {
        return count > 0 ? ((double) total)/count : 0;
    }
        
    public void accept(int i) { 
        total += i; 
        count++; 
        System.out.println("accept called"); 
    }
    
    public void combine(Averager other) {
        System.out.println("Combine called");
        total += other.total;
        count += other.count;
    }
}

public class CollectExample {
    public static void main(String[] args) {
        List<Person> roster = new ArrayList<>();
        roster.add(new Person("Shimon", 22));
        roster.add(new Person("Yehuda", 30));
        roster.add(new Person("Sara", 50));
        roster.add(new Person("Ilya", 40));
        roster.add(new Person("Benny", 23));
        roster.add(new Person("Avi", 70));
        roster.add(new Person("Yulia", 27));
        roster.add(new Person("Moti", 22));
        
        Averager averageCollect = roster.stream()
            /* .filter(p -> p.getGender() == Person.Sex.MALE) */
            .map(Person::getAge)
            .collect(Averager::new, Averager::accept, Averager::combine);
                        
        System.out.println("Average age of male members: " +
            averageCollect.average());
    }
}