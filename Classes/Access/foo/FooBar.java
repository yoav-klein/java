
package foo;

import bar.Bar;

class FooBar extends Bar {
    int getA() { // can access a since it's public
        return a;
    }

    /*int getB() { // can't access b since it's package-private
        return b;
    }*/

    int getC() { // can access c since it's protected
        return c;
    }

    /*int getD() { // can't access d since it's private
        return d;
    }*/
}