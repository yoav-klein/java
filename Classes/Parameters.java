/**
 * passing data to methods is done by having method parameters
 * 
 * topics discussed:
 * - parameters shadowing class fields
 * - passing values by value
 * - passing values by reference
 */

class Parameters {
    int x = 1000;
    int y = 0;

    void passByReference(int[] arr) {
        // an array is an object. As such, arr is a reference to the object.
        // 
        for (int i =0; i < arr.length; ++i) {
            arr[i] = i*2;
        }
        // you can change the object to which arr points to, this will not affect
        // the intArr outside the method.
        arr = new int[] {10, 20, 30, 40};
    }
    void passByValue(int i) {
        i = 15;
        System.out.println("i in getByValue " + i);
    }

    void someMethod(int x, int a) {
        // x shadows the x field.
        System.out.println("x: " + x);
        System.out.println("this.x: " + this.x);
        // y can be referenced without "this" of course
        System.out.println("y: " + y);
    }

    public static void main(String[] args) {
        Parameters params = new Parameters();
        params.someMethod(12, 23);

        // pass by value
        int i = 2;
        params.passByValue(i);
        System.out.println("i in main: " + i);

        // pass by reference
        int[] intArr = new int[] {1, 2, 3, 4};
        params.passByReference(intArr);

        for(int x: intArr) {
            System.out.println(x);
        }
    }
}