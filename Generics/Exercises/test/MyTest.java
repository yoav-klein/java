public class MyTest {

    public void demo() {
        inspect(new FirstSon("a"), new SecondSon("b"));
    }

    public <T> void inspect(T a, T b) {
        System.out.println(a + "_" + b);
    }


    interface Parent {
    }

    static class FirstSon implements Parent {
        private String name;

        public FirstSon(String name) {
            this.name = name;
        }
    }

    static class SecondSon implements Parent {
        private String name;

        public SecondSon(String name) {
            this.name = name;
        }
    }

    static public void main(String[] args) {
        MyTest mt = new MyTest();
        mt.demo();
    }
}