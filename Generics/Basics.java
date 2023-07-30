/**
 * 
 * A simple generic class
 */

class NonGenericBox {
    Object obj;

    void set(Object obj) {
        this.obj = obj;
    }

    Object get() {
        return obj;
    }
}

class Box<T> {
    T obj;

    void set(T obj) {
        this.obj = obj; 
    }
    T get() {
        return this.obj;
    }

    
}


class Generics {
    static public void main(String[] args) {
        /**
         * Why do we need generics? Consider the following piece of code
         * 
         * We use the non-generic Box, and put an Integer in it
         */
        NonGenericBox b = new NonGenericBox();
        Integer i = new Integer(2);
        b.set(i);
        
        /**
         * Now some other part of the code expects this to 
         * hold a String object
         * 
         * This will not fail on compile time, only at runtime
         */
        String s = (String)b.get();


        /**
         * But using the generic Box, this will not be possible,
         * because it will fail on compile time
         */
        Box<Integer> b1 = new Box<Integer>();
        
        Integer i1 = 20;
        b1.set(i1);

        // String s1 = (String)b1.get(); -> this will fail at compile time

        /**
         * The diamond - you can use the "diamond" <> to instaniate a generic type, as long 
         * as the compiler can determine the type arguments from the context
         */
        Box<Integer> b2 = new Box<>(); // the compiler can determine that it needs to instantiate a Box<Integer>()
    }
}