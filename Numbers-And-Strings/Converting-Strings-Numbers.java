
class Converts {
    public static void main(String[] args) {
        
        // converting numbers to strings
        int i = 10;

        String s1 = Integer.toString(i);
        System.out.println(s1);

        // concatenate with empty string
        String s2 = "" + i;
        System.out.println(s2);

        // use the valueOf method
        String s3 = String.valueOf(i);
        System.out.println(s3);


        
    }
}