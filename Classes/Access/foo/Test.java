
package foo;

import bar.Bar;
import bar.Singleton;

class Test {
    static public void main(String[] args) {
        Foo f = new Foo(); // can instantiate Foo since it's in the same package
        Bar b = new Bar(); // can instantiate Bar since it's a public class
        System.out.println("b.a: " + b.a); // can access since it's public
        // System.out.println(b.b); // can't access since it's package-private

        System.out.println("f.a: " + f.a); // can access since it's public
        System.out.println("f.b: " + f.b); // can access since it's package-private
        // System.out.println(f.c); // can't access since it's private
        
       Singleton s = Singleton.getInstance();
       System.out.println("s.getNumber(): " + s.getNumber());

       assert Singleton.getInstance() == s;
    }
}