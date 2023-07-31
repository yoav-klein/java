/**
 * Demonstrates the use of generic interface
 * 
 */

import java.util.ArrayList;

interface Container<T> {
    void put(T item);
}

class Bus<T> {
    ArrayList<T> passengers = new ArrayList<T>();

    void put(T item) {
        passengers.add(item);
    }

}

class Test {
    public static void main(String[] args) {
        Bus<Integer> bus = new Bus<Integer>();

        bus.put(new Integer(1));
    }
}