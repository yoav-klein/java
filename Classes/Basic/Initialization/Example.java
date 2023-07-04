/**
 * This demonstrates the different methods to initialize class
 * and instance variables
 */

class Example {
    // initializing fields of primitive types can be in the declaration itself
    static final int SIZE = 10;

    // for more complex initializations, instance variables may be initialized in the constructor
    int[] nonStaticArr1 = new int[10];

    // they can also be initialized in initialization blocks
    int[] nonStaticArr2 = new int[10];

    // but class variables cannot, so we use static initialization blocks
    static int[] staticArr = new int[10];

    Example() {
        for(int i = 0; i < 10; ++i) {
            nonStaticArr1[i] = (i + 1) * 2;
        }
    }
    // static initialization block
    static {
        for(int i = 0; i < 10; ++i) {
            staticArr[i] = (i + 1) * 3;
        }
    }

    // [non-static] initialization block
    {
        for(int i = 0; i < 10; ++i) {
            nonStaticArr2[i] = (i + 1) * 4;
        }
    }

    void print() {
        System.out.println("nonStaticArr1");
        for(int i = 0; i < 10; ++i) {
            System.out.print(nonStaticArr1[i] + ", ");  
        }
        System.out.println("");
        System.out.println("nonStaticArr2");
        for(int i = 0; i < 10; ++i) {
            System.out.print(nonStaticArr2[i] + ", ");  
        }
        System.out.println("");
        System.out.println("staticArr");
        for(int i = 0; i < 10; ++i) {
            System.out.print(staticArr[i] + ", ");  
        }
    }

    public static void main(String[] args) {
        Example e = new Example();
        e.print();
    }
}