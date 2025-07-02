package com.example;

import org.testng.annotations.*;
import org.testng.*;

public class SimpleTest {
    private int a = 0;
    
    // just to see that you can use instance variables and initialize them in BeforeClass
    @BeforeClass
    public void beforeClass() {
        System.out.println("BEFORE CLASS");
        a = 42;
    }

    @BeforeMethod(enabled=false)
    public void beforeMethod() {
        System.out.println("BEFORE METHoD");
    }

    @Test
    public void success() {
        System.out.println(a);
    }

    @Test
    public void failing() {
        Assert.assertEquals(a, 0);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AFTER METHOD");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("AFTER CLASS");
    }


}