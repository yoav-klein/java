
class Test {
    public static void main(String[] args) {
        int num = 17;

        int half = num / 2;
        boolean isPrime = true;
        for(int divider = 2; divider <= half; ++divider) {
            if(num % divider == 0) {
                isPrime = false;
                System.out.println(num + " divides by " + divider);
                break;
            }
        }

        if(isPrime) {
            System.out.println(num + " is Prime !!");
        }
    }
}