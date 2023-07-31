
import java.util.ArrayList;

class Test {
    static void printNumberList(List<? extends Number> list) {
        for item : list {
            println item
        }
    }

    static public void main(String[] args) {
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(3);
        intList.add(10);

        printNumberList(intList);
    }
}