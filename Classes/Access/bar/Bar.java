
package bar;

//import foo.Foo;

public class Bar {
    public int a;
    int b;

    public Bar() { // must be public in order to be able to insantiate from out of package
        a = 1;
        b = 2;
    }
}