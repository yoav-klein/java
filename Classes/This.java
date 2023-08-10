/**
 * demonstrates the use of the this keyword
 * - this in methods - to explicitly refer to fields. This is useful to handle shadowing
 * - this in constructors - you can use this in constructors to invoke other constructors
 */
class This {
    int x;

    void shadows(int x) {
        System.out.println("this.x: " + this.x);
        System.out.println("x: " + x);
    }

    This() {
        this(10);               
    }

    This(int x) {
        this.x = x;
    }

    public static void main(String[] args) {
        This t = new This();
        t.x = 20;

        t.shadows();
    }
}