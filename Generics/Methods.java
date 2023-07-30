/**
 * A generic method is similar to a generic type, only that the type parameter 
 * is limited to the scope of the method.
 * 
 * Static and non-static methods are allowed.
 */

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }
}

class Utils {
    static public <K, V> boolean compare(Pair<K, V> one, Pair<K, V> two) {
        return one.getKey().equals(two.getKey()) && 
            one.getValue().equals(two.getValue());
    }
}

class Methods {
    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1, "One");
        Pair<Integer, String> p2 = new Pair<>(1, "One");

        System.out.format("compare(p1, p2): %b", Utils.compare(p1, p2));

        /** 
         * this will not work 
         * Pair<Integer, Integer> p3 = new Pair<>(1, 3);
        Pair<Integer, String> p4 = new Pair<>(4, "Four");

        Utils.compare(p3, p4);
         * 
         * */
        
    }
}
