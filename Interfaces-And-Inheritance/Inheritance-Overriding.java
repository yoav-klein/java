

class Parent {
    static void testStatic() {
        System.out.println("Static from parent");
    }

    void print() {
        System.out.println("Come to daddy");   
    }

    Parent getMan() {
        return new Parent();
    }
}

class Child extends Parent {
    // this is called method hiding, not overriding    
    static void testStatic() {
        System.out.println("Static in child");
    }

    /* 
    // you can't have an overriding method that returns a different return type
    int print() { 
        System.out.println("Baba.. Baba..");
        return 1;
    }*/
    
    // an overriding method can return a subtype of the return type of the overriden method
    Child getNumber() {
        return new Child();
    }

    @Override  // the @Override annotation makes it so that if there's no such method in the superclass, 
    // the compiler will generate an error
    void print() { 
        System.out.println("Baba.. Baba..");
    }

    static public void main(String[] args) {
        Child c = new Child();
        c.print();

        Parent p = c;
        p.testStatic();
        p.print();
        
    }
}