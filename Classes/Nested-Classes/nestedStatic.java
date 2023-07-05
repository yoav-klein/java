
/**
 * nested static class is just like any top-level class.
 * it's just located inside another class
 */


class Outer {
    int outerField = 0;

    void print() {
        System.out.println(outerField);
    }

    static class Inner {
        int innerField;
        public void changeInnerValue(int val) {
            innerField = val;
        }
    }


    public static void main(String[] args) {

        Outer.Inner i1 = new Outer.Inner(); // you can use this syntax
        Inner i2 = new Inner(); // you can also use this syntax

        System.out.println(i1.innerField);
        i1.changeInnerValue(20);
        System.out.println(i1.innerField);
        
        System.out.println("=========");
        System.out.println(i2.innerField);
        i2.changeInnerValue(15);
        System.out.println(i2.innerField);
    }
}