package com.example;

import org.testng.annotations.*;

public class SimpleTest {
    
    @Test
    @Parameters({ "first-name" })
    public void someTest(String firstName) {
        System.out.println(firstName);
        
    }

}