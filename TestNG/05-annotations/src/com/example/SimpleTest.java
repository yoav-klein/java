package com.example;

import org.testng.annotations.*;

public class SimpleTest {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before suite in SimpleTest");
    }

    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated
    }

    @Test
    public void myTest() {
        System.out.println("Some test");
    }

}