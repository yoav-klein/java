
package yoav.app;

import java.util.*;

public class Test {
    public static <T extends Object & Comparable<? super T>> 
        T max(List<? extends T> list, int start, int end) {
            T max = list.get(start);
            for(int i = start + 1; i < end; ++i) {
                T tmp = list.get(i);
                if(tmp.compareTo(max) > 0) {
                    max = tmp;
                }
            }

            return max;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(4);
        list.add(20);
        list.add(13);
        list.add(6);
        list.add(50);
        list.add(11);
        list.add(1);
        list.add(132);
        list.add(69);
        list.add(91);

        System.out.println(max(list, 0, 5));
    }
}