
class Test {
    public static void main(String[] args) {
        String myName = "Yoav";
        String[] names = new String[10];
        names[0] = "Bart";
        names[1] = "Josh";

        names[0] = myName;
        myName = null;
        System.out.println(names[1]);
    }
}