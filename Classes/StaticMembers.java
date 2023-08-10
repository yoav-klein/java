
class StaticMembers {
    static int a = 0;
    int b = 0;

    static void changeStatic(int newVal) {
        a = newVal;
        // b = newVal; // you can't access non-static fields from static methods
    }

    void changeNonStatic(int newVal) { // you can access static members from non-static methods
        a = newVal;
        b = newVal; // you can access non-static members only from non-static methods
        changeStatic(newVal);
    }

    int getA() {
        return a;
    }

    static public void main(String[] args) {
        StaticMembers sm1 = new StaticMembers();
        StaticMembers sm2 = new StaticMembers();

        sm1.changeStatic(12); // you can access static members from instances
        sm2.changeNonStatic(21);
        StaticMembers.changeStatic(10); // you can access static members without instances

        System.out.println("sm1.getA() " + sm1.getA());
        System.out.println("sm2.getA() " + sm2.getA());
        System.out.println("StaticMembers.a: " + StaticMembers.a);
    }
}