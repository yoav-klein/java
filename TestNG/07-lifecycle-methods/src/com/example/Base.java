package com.example;

import org.testng.annotations.*;
import org.testng.*;

// when inheriting from this class, the beforeInBase and afterInBase methods will be executed before and after all methods in the
// inheriting class

public class Base {
        
    @BeforeClass
    public void beforeInBase() {
        System.out.println("BEFORE IN BASE");
    }

    @AfterClass
    public void afterInBase() {
        System.out.println("AFTER IN BASE");
    }


}