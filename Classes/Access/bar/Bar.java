
package bar;

public class Bar { // public class, so that it's visible by foo.Foo class
    public int a;
    int b;
    protected int c;
    private int d;

    public Bar() { // must be public in order to be able to insantiate from out of package
        a = 1;
        b = 2;
        c = 3;
        d = 4;
        Zen z = new Zen();
    }

}