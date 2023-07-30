/**
 * You can declare generic types with multiple type parameters, no problem
 */


interface Pair<K, V> {
    public K getKey();
    public V getValue();
}

class OrderedPair<K, V> implements Pair<K, V> {
    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }
}

class Test {
    public static void main(String[] args) {
        OrderedPair<Integer, String> op1 = new OrderedPair<Integer, String>(1, "Wachad");
        OrderedPair<String, String> op2 = new OrderedPair<>("One", "Wachad"); // you can use the diamond

        System.out.println(op1.getKey());
        System.out.println(op1.getValue());
        System.out.println(op2.getKey());
        System.out.println(op2.getValue());
    }
}