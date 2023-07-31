/**
 * Use an unbound wildcard when you want
 * 1. A method that works on objects of type Object
 * 2. A method that uses the methods of the generic class, like List.size
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Test {
    static void printListSize(List<?> list) {
        System.out.println(list.size());
    }

    static public void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        printListSize(list);
    }
}