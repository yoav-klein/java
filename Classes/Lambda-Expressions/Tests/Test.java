
interface DoSomething {
    void doSome();
}

class Test {
    public static void makeDo(DoSomething d) {
        d.doSome();
    }

    public static void main(String[] args) {
        makeDo(() -> { System.out.println("Using a statement in the lambda"); });
        makeDo(() ->  System.out.println("Using an expression in the lambda") );
    }
}