package org.example;

public class TestBean {

    private String name;

    public TestBean(String name) {
        this.name = name;
    }
    
    public void sayHi() {
        System.out.println("Hi " + name);
    }
}
