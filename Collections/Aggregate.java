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
    }
}