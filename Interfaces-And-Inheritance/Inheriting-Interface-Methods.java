
/**
 * when inheriting from multiple interfaces that have the same default method
 * the compiler will throw an error
 * 
 * In this case, you must explicitly override the method in your class.
 * 
 */

interface Flyable {
    default void identify() {
        System.out.println("I can fly!");
    }
}

interface Walkable {
    default void identify() {
        System.out.println("I can walk!");
    }
}

class Angel implements Flyable, Walkable {

    public void identify() {
        // you can invoke the superinterface default methods from your class
        Flyable.super.identify();
        Walkable.super.identify();
        
    }
    public static void main(String[] args) {
        Angel a = new Angel();
        a.identify();
    }
}