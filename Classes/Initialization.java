/**
 * Initializing static fields can be done using static initialization blocks
 * You can also use private static methods
 */

class Initialization {
    static public int[] arr1;
    static public int[] arr2 = initArr();
    

    static {
        arr1 = new int[10];
        for(int i = 0; i < 10; ++i) {
            arr1[i] = i*3;
        }
    }

    static private int[] initArr() {
        int[] arr = new int[10];
        for(int i = 0; i < 10; ++i) {
            arr[i] = i*3;
        }

        return arr;
    }
    static public void main(String[] args) {
        for(int i = 0; i < 10; ++i) {
            System.out.println(arr1[i]);
        }
        for(int i = 0; i < 10; ++i) {
            System.out.println(arr2[i]);
        }
    }
}