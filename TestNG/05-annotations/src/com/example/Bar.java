package com.example;

import org.testng.annotations.*;

public class Bar {
    @BeforeSuite
    void beforeSuite() {
        System.out.println("Before suite in Bar");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest in Bar");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest in Bar");
    }

    @BeforeClass
    void beforeClass() {
        System.out.println("BeforeClass in Bar");
    }

    @Test
    void barTest1() {
        System.out.println("Bar test1");
    }
    
    @Test
    void barTest2() {
        System.out.println("Bar test2");
    }

    @AfterClass
    void afterClass() {
        System.out.println("AfterClass in Bar");
    }
}
