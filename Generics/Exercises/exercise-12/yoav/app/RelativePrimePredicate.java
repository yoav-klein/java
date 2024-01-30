
package yoav.app;

import java.util.*;

interface UnaryPredicate<T> {
    boolean test(T element);
} 

public class RelativePrimePredicate implements UnaryPredicate<Integer> {
    private Collection<Integer> c;

    RelativePrimePredicate(Collection<Integer> c) {
        this.c = c;
    }

    public boolean test(Integer i) {
        for(Integer x : c) {
            if(Algorithm.gcd(i, x) != 1) {
                return false;
            }
        }
        return true;
    }
}