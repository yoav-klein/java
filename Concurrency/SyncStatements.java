

/**
 * 
 * demonstrates the use of synchronized statements.
 * 
 * 
 * 
 */

class Counter {
    int count = 0;
    Object lock = new Object();

    void inc() {
        synchronized(lock) {
            ++count;
        }
    }

    void dec() {
        synchronized(lock) {
            --count;
        }
    }

    int getResult() {
        return this.count;
    }
}

class Decrementor implements Runnable {
    Counter counter;
    Decrementor(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for(int i = 0; i < 4000; ++i) {
            counter.dec();
        }
    }
}

class Incrementor implements Runnable {
    Counter counter;
    Incrementor(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for(int i = 0; i < 10000; ++i) {
            counter.inc();
        }
    }
}

class SyncStatements {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread a = new Thread(new Incrementor(counter));
        Thread b = new Thread(new Decrementor(counter));

        a.start();
        b.start();
        
        a.join();
        b.join();

        System.out.println(counter.getResult());
    }
}