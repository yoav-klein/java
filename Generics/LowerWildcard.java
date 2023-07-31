/**
 * Demonstrates the use of lower-bounded wildcards
 * 
 */
import java.util.ArrayList;
import java.util.List;

class Test {
    static void addToList(List<? super Integer> list) {
        for(int i = 0; i < 10; ++i) {
            list.add(i);
        }
    }

    static public void main(String[] args) {
        ArrayList<Object> objList = new ArrayList<>();
        addToList(objList);
    }
}
