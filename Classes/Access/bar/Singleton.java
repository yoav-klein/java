package bar;

public class Singleton {
    private static Singleton instance;

    private Singleton() {}

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