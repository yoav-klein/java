/**
 * topics covered:
 * - creating arrays
 * - accessing array elements
 * - multi-dimensional arrays
 * - length of arrays
 * - copying arrays
 */


class Arrays {
    static public void main(String[] args) {
        // array of ints
        // one way to create an arrry is with the new operator:
        int[] arr = new int[10];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 4;
        arr[5] = 5;
        arr[6] = 6;
        arr[7] = 7;
        arr[8] = 8;
        arr[9] = 9;

        // declaring variables that hold reference to an array
        // array type is denoted as such: type[]
        int[] arr2;
        arr2 = arr;

        // accessing array elements is done using []
        System.out.println(arr[0]);

        // you can also create an array as such:
        int[] anotherArray = {1, 2, 3, 4};

        // multi-dimensional arrays are arrays which holds arrays:\
        int[][] multi = new int[2][3];

        String[][] names = {
            {"Mr.", "Mrs.", "Ms."},
            {"Smith", "Jones"}
        }

        System.out.println(names[0][0] + " " + names[1][0]);
        System.out.println(names[0][1] + " " + names[1][1]);

        // the length of the array can be retrieved using the length property
        System.out.println(arr.length);

        int[] newArr = new int[10];
        System.arraycopy(arr, newArr, 0, 9);

    }
}