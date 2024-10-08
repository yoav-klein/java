package com.example;

import org.testng.annotations.*;

public class Foo {
    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest in Foo");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest in Foo");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod in Foo");
    }

    @Test
    public void fooTest1() {
        System.out.println("Foo test1");
    }
    
    @Test
    public void fooTest2() {
        System.out.println("Foo test2");
    }
}
