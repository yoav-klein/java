
package foo;

import bar.Bar;
// import bar.Zen; // can't import bar.Zen, since it's package private

class Foo {
    public int a;
    int b;
    private int c;

    Foo() {
        a = 1;
        b = 2;
        c = 3;
    }
}