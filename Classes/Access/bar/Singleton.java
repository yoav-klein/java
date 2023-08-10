package bar;

public class Singleton {
    private static Singleton instance;

    private Singleton() {} // private constructor makes it so that the class cannot be instantiated
    // by other classes

    public int getNumber() {
        return 20;
    }

    static public Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}