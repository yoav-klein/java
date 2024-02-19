/**
 * Raw type is a generic type without specifying type argument.
 * Box is the raw type of Box<T>.
 * When using raw types, you get a pre-generic behavior - A Box gives you Objects.
 * 
 */


class Box<T> {
    T obj;

    void set(T obj) {
        this.obj = obj; 
    }
    T get() {
        return this.obj;
    }

    static public void main(String[] args) {
        
        Box<Integer> intBox = new Box<Integer>(); // parameterized type
        Box rawBox = intBox; // assigning a parameterized type to a variable of raw type is fine

        Box anotherRaw = new Box(); // raw type
        Box<Integer> anotherIntBox = anotherRaw; // assigning a raw type to a parameterized type gives you a warning.
        
        // also, use a raw type to invoke generic methods defined in the generic type
        // issues a warning
        Box<String> stringBox = new Box<String>();
        Box rawBox2 = stringBox;
        rawBox2.set(8);

        System.out.println(rawBox2.get());
    }
}