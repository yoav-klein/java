
class Test {
    public static void main(String[] arr) {
        int[] arr1 = new int = {1, 3, 5, 7, 9};
        int[] arr2 = new int = {2, 4, 6, 8, 10};

        for(int i = 0; i < 5; ++i) {
            int tmp = arr1[i];
            arr1[i] = arr2[i];
            arr2[i] = tmp;
        }
    }
}