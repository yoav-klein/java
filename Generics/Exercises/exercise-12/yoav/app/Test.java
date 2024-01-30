
package yoav.app;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(6, 5, 4, 67);
        List<Integer> c = Arrays.asList(3);

        RelativePrimePredicate p = new RelativePrimePredicate(c);
        
        int res = Algorithm.findFirst(list, 0, 4, p);

        System.out.println(res);
        
    }
}