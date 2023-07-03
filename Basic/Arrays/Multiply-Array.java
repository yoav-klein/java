
class Test {
    public static void main(String[] args) {
        int[] arr = { 1, 5, 10, 23, 53, 123, 32, 54, 231, 21};

        for(int i = 0; i < arr.length; ++i) {
            arr[i] *=  2;
        }

        for(int num : arr) {
            System.out.println(num);
        }
    }
}