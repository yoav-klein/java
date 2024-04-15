/**
 * Aggregate operations allow you to perform an operation
 * on a large number of items in your collection
 * 
 * Aggregate operations are done using streams, by using the stream() method
 * 
 * 
 */


import java.util.*;

public class Aggregate {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(20);
        l.add(11);
        l.add(5);
        l.add(8);
        l.add(22);

        // this is a pipeline - a sequence of aggregate operations
        l.stream().filter(i -> i > 10).forEach(i -> System.out.println(i));

        // another pipeline
        Integer sumOfOdd = l.stream().filter(i -> i % 2 == 1).mapToInt(Integer::intValue).sum();
        System.out.println(sumOfOdd);

        System.out.println("/* *************************** */");

        List<Person> roster = new ArrayList<>();
        roster.add(new Person("Shimon", 22));
        roster.add(new Person("Yehuda", 30));
        roster.add(new Person("Sara", 50));
        roster.add(new Person("Ilya", 40));
        roster.add(new Person("Benny", 23));
        roster.add(new Person("Avi", 70));
        roster.add(new Person("Yulia", 27));
        roster.add(new Person("Moti", 22));

        System.out.println("Print all names in roster");
        roster.stream().forEach(p -> System.out.println(p.getName()));

        System.out.println("Print all persons in age above 30");
        roster.stream().filter(p -> p.getAge() > 30).forEach(p -> System.out.println(p.getName()));

        System.out.println("Average age of roster");
        double average = roster.stream().mapToInt(Person::getAge).average().getAsDouble();
        System.out.println(average);

    }
}