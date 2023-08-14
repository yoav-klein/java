

class Parent {
    int someNum;
    int age = 20;
    String lastName = "Johnson";
    private String secret = "Shhh...";

    Parent() {
        someNum = 1;
    }

    Parent(int num) {
        someNum = num;
    }

    void sayHello() {
        System.out.println("Hello");
    }

    void willBeOverriden() {
        System.out.println("Father will die..");
    }

    class Mother { // nested class
        void exposeSecret() {
            System.out.println("Mothers always tell secrets");
            System.out.println(secret);
        }
    }
}

class Child extends Parent {
    int age = 10; // "hiding" a field; not recommended
    int newMemberField = 23; // subclasses can declare new member fields, of course


    Child() {} // this will implicitly call the no-argument constructor of the superclass

    Child(int num) { // you can explicitly call the constructor of the superclass
        super(num);
    }

    public void printChild() {
        System.out.println(age); // displays the hiding field
        System.out.println(super.age); // displays the hidden field
        System.out.println(newMemberField); 
        System.out.println(lastName); // inherited fields can be used just like other fields
        
        //System.out.println(secret); // private members cannot be accessed

        // however, if the superclass has a nested class (which isn't private)
        // which accesses the private members of the superclass, 
        // you can access it through the nested class
        Mother m = new Mother();
        m.exposeSecret();

        sayHello(); // inherited methods can be used just like other methods

        willBeOverriden();
    }

    void willBeOverriden() {
        System.out.println("Son will live!");
    }

    void printSomeNum() {
        System.out.println(someNum);
    }

    static public void main(String[] args) {
        Child c = new Child(); // with no-argument constructor
        c.printChild();
        c.printSomeNum();

        Child c1 = new Child(23);
        c1.printSomeNum();
    }
}