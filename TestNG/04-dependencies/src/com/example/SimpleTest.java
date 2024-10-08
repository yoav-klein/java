package com.example;

import org.testng.annotations.*;

public class SimpleTest {

    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated
    }

    @Test(groups = {"fast"}, dependsOnGroups = {"slow"})
    public void aFastTest() {
        System.out.println("Fast test");
    }

    @Test(groups = {"slow"})
    public void aSlowTest1() {
        System.out.println("Slow test");
    }

    @Test(groups = {"slow"})
    public void aSlowTest2() throws Exception {
        throw new Exception("Ooopss");
    }

}