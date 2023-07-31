/**
 * Upper-bounded wildcards
 * 
 */
import java.util.ArrayList;
import java.util.List;

class Test {
    static void printNumberList(List<? extends Number> list) {
        for (Number item : list) {
            System.out.println(item);
        }
    }

    static public void main(String[] args) {
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(3);
        intList.add(10);

        printNumberList(intList);

        ArrayList<Double> doubleList = new ArrayList<>();
        doubleList.add(1.23);
        doubleList.add(2.33);
        doubleList.add(1.66);

        printNumberList(doubleList);

        ArrayList<String> strList = new ArrayList<>();
        strList.add("Yossi");
        strList.add("Yoni");
        strList.add("Eli");
        //printNumberList(strList); // will not compile
    }
}