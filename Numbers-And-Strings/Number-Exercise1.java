
// Create a program that reads an unspecified number of integer arguments from the command line 
// and adds them together. For example, suppose that you enter the following:

// java Adder 1 3 2 10
class Exercise {


    static public void main(String[] args) {
        int sum = 0;
        for(int i = 0; i < args.length; ++i) {
            sum += Integer.valueOf(args[i]);
        }

        System.out.format("Sum: %d%n", sum);
    }
}