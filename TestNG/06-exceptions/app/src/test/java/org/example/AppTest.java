/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import org.testng.annotations.*;
import static org.testng.Assert.*;

public class AppTest {
    @Test(expectedExceptions=MyException.class) 
    public void appHasAGreeting() throws MyException {
        App classUnderTest = new App();
        classUnderTest.messyMethod();
    }
}
