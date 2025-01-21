

/**
 * 
 * demonstrates the use of ThreadLocal. 
 * 
 * ThreadLocal variable is a variable that is local to the thead :)
 * each thread gets is own copy of this variable. In our case, if it wasn't a ThreadLocal, 
 * the two threads would increment and decrement the same value, ending up in an unexpected value.
 * 
 * since it's a ThreadLocal, each thread gets its own copy, thus one thread will print 10000 and the other -4000
 * 
 * ThreadLocal is good for global variables that needs to be shared across methods and classes in the same thread. Like UserID
 * 
 */

import java.lang.ThreadLocal;

class Counter {
    static ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
        @Override protected Integer initialValue() {
            return 0;
        }
    };

    static void inc() {
        count.set(count.get() + 1);
    }

    static void dec() {
        count.set(count.get() - 1);
    }

    static int getResult() {
        return count.get();
    }
}

class Decrementor implements Runnable {
    
    public void run() {
        for(int i = 0; i < 4000; ++i) {
            Counter.dec();
        }
        System.out.println(Counter.getResult());
    }
}

class Incrementor implements Runnable {

    public void run() {
        for(int i = 0; i < 10000; ++i) {
            Counter.inc();
        }
        System.out.println(Counter.getResult());

    }
}

class UsingThreadLocal {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new Incrementor());
        Thread b = new Thread(new Decrementor());

        a.start();
        b.start();
        
        a.join();
        b.join();

        System.out.println(Counter.getResult());
    }
}