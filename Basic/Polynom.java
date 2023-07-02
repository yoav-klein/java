
class Polynom {
    public static void main(String[] args) {
        String s = "abcDcbA";
        int len = s.length();
        boolean poly = true;
        for(int i=0, j=len-1; i <= j; ++i, --j) {
            if(s.charAt(i) != s.charAt(j)) {
                poly = false;
                break;
            }
            System.out.println(String.format("i: %d, j: %d", i, j));
        }

        System.out.println(String.format("Is Poly? %s", poly));
    }
}