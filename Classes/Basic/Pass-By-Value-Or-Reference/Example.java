
/**
 * This example demonstrates passing objects to a method. When you pass an object to a method (not
 * a primitive type), it is passed by reference, not by value, so any modifications done in the 
 * method is visible outside of it.
 */

class MyClass {
    int val = 0;

    static void changeValue(MyClass mc) {
        mc.val = 50;

        MyClass mc1 = new MyClass();
        mc = mc1; // this doesn't affect the calling context, since we only change the reference, not the underlying object
    }

    void print() {
        System.out.println(val);
    }

    static void changeArr(int[] arr) {
        arr[0] = 12;
    }

    static public void main(String[] args) {
        MyClass mc = new MyClass();
        System.out.println(mc.val);
        changeValue(mc);
        System.out.println(mc.val);

        int[] arr = {0, 0, 0, 0, 0};
        changeArr(arr);
        System.out.println(arr[0]);

    }
}