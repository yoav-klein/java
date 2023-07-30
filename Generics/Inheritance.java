
/**
 * Just like you can pass an Integer to a method that accepts a Number,
 * you can also do this with generics.
 * 
 * You can create an instance of Box<Number>, and then call set with Integers
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
        Box<Number> b = new Box<Number>();

        // since Integer is a Number, the following is applicable
        b.set(new Integer(1));
     }
}