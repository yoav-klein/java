
// Count the number of Y's in a string
class ForLoop {
    public static void main(String[] args) {
        String myString = "My name is Yoav, and my mom used to call me Yovyov";
        int length = myString.length();
        int count = 0;

        for(int i = 0; i < length; ++i) {
            if(myString.toLowerCase().charAt(i) == 'y') {
                ++count;
            }
        }

        System.out.println("Number of Ys: " + count);
    }
}