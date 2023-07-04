
/**
 * A nested non-static class objects are associated with the object of the outter class.
 * They can access private fields of the outter class.
 * 
 * You can instantiate multiple inner objects from the same outter objects.
 * 
 */

class Outer {
    private int outerField = 0;

    void print() {
        System.out.println(outerField);
    }


    class Inner {
        int innerField;
        // static int staticInnerField; -> compiler error - you can't have
        // static members in non-static nested classes.

        public void changeOutterValue(int val) {
            outerField = val;
        }
    }

    private class PrivateInner {
        int innerField;
        public void changeOutterValue(int val) {
            outerField = val;
        }
    }


    public static void main(String[] args) {
        Outer o = new Outer();

        Outer.Inner i1 = o.new Inner();
        Outer.Inner i2 = o.new Inner();

        o.print();
        i1.changeOutterValue(10);
        o.print();
        i2.changeOutterValue(20);
        o.print();

        Outer.PrivateInner pi = o.new PrivateInner(); // note that you can instantiate
        // this here because it's the enclosing class. In "External.java" you can't
        
    }
}