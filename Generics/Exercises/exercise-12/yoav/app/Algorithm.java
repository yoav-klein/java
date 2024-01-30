
package yoav.app;

import java.util.List;

public final class Algorithm {
    public static <T>
        int findFirst(List<T> list, int begin, int end, UnaryPredicate<T> p) {
            for (int i = begin; i < end; ++i) {
                if(p.test(list.get(i))) {
                    return i;
                }
            }
            return -1;
        }

    public static int gcd(int x, int y) {
        for(int r; (r = x % y) != 0; x = y, y = r) { }
        return y;
    }
    
}