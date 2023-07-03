
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
        Outer o = new Outer();
        Inner i = new Inner();

        System.out.println(i.innerField);
        i.changeInnerValue(20);
        System.out.println(i.innerField);
        
    }
}