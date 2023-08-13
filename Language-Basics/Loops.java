
// Count the number of Y's in a string
class Loops {

    public static void forLoop() {
        String myString = "My name is Yoav, and my mom used to call me Yovyov";
        int length = myString.length();
        int count = 0;

        for(int i = 0; i < length; ++i) {
            if(myString.toLowerCase().charAt(i) == 'y') {
                ++count;
            }
        }

        System.out.println("Number of Ys: " + count);
        
        // enhanced for
        int[] arr = { 1, 3, 53, 32, 1, 6, 10};
        for(int i: arr) { 
            System.out.println(i);
        }
    }

    public static void whileLoop() {
        int arr[] = {1, 4, 53, 21, 6, 43, 2, 12};
        int num = arr[0];
        int index = 0;
        while(num != 6) {
            num = arr[index++];
        }
    }

    public static void main(String[] args) {
        
        
    }
}