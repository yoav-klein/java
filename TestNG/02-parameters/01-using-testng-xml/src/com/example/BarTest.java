package com.example;

import org.testng.annotations.*;

public class BarTest {

    @Parameters({ "global-name" })
    @Test
    public void testSingleString(String firstName) {
        System.out.println("Global name: " + firstName);
        assert "Cedric".equals(firstName);
        
    }

    @Parameters({ "test-name" })
    @Test
    public void testSingleString2(String firstName) {
        System.out.println("Test name: " + firstName);
        assert "Cedric".equals(firstName);
        
    }

}