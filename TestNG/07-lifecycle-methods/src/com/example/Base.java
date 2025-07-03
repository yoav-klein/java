package com.example;

import org.testng.annotations.*;
import org.testng.*;

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