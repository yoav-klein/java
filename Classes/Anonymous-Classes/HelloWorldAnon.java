public class HelloWorldAnon {
  
    interface HelloWorld {
        public void greet();
        public void greetSomeone(String someone);
    }
    public void printValue() {
        System.out.println(someValue);
    }

    int someValue = 10;

    public void sayHello() {
        // this is a local class, not an anonymous class      
        class EnglishGreeting implements HelloWorld {
            String name = "world";
            public void greet() {
                greetSomeone("world");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }
      
        HelloWorld englishGreeting = new EnglishGreeting();
        
        // these are anonymous classes
        HelloWorld frenchGreeting = new HelloWorld() {
            String name = "tout le monde";
            public void greet() {
                greetSomeone("tout le monde");
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);

                someValue = 15;
            }
        };
        
        HelloWorld spanishGreeting = new HelloWorld() {
            static int x = 1;
            String name = "mundo";
            public void greet() {
                greetSomeone("mundo");
                printValue();
            }
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hola, " + name);
            }
        };
        englishGreeting.greet();
        frenchGreeting.greetSomeone("Fred");
        spanishGreeting.greet();
    }

    public static void main(String... args) {
        HelloWorldAnon myApp =
            new HelloWorldAnon();
        myApp.sayHello();
    }            
}