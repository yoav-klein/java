

class Strings {
    static public void main(String[] args) {
        int a = 10;

        // creating strings out of characters
        char[] helloWorld = { 'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};
        String strHelloWorld = new String(helloWorld);
        System.out.println(strHelloWorld);

        // you can also get an array of chars out of a String object
        String s1 = "This is a new string";
        char[] chars = new char[s1.length()];
        s1.getChars(0, s1.length(), chars, 0);
         
        // format string
        String s2 = String.format("The number is: %d\n", a);
        System.out.println(s2);

        // concatenating strings
        String hello = "hello ";
        String world = "world";

        System.out.println(hello.concat(world));

    }
}