package com.example;

import org.testng.annotations.*;

public class SimpleTest {
    private int a = 0;
    
    @BeforeClass
    public void setUp() {
        System.out.println("BEFORE CLASS");
        a = 42;
    }

    @Test(groups = {"fast"})
    public void aFastTest() {
        System.out.println(a);
    }


}