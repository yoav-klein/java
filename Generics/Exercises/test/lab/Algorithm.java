
import java.util.*;

public class Algorithm {
    public static <T extends Comparable<T>> T max(List<? extends T> list) {
    //public static <T extends Comparable<T>> T max(List<? extends T> list) {
        T tmp = list.get(0);
        for(int i = 1; i < list.size() - 1; ++i) {
            if(list.get(i).compareTo(tmp) > 0) {
                tmp = list.get(i);
            }
        }
        return tmp;
    }
}