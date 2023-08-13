
class Numbers {

    static void printMyInteger(Integer i) {
        System.out.println(i);
    }

    static void printMyPrimitive(int i) {
        System.out.println(i);
    }

    static public void main(String[] args) {
        System.out.format("Hello %s%n", 3);

        printMyInteger(5); // you can pass a primitive where an Integer is expected
        printMyPrimitive(new Integer(10)); // and the other way around
    }
}