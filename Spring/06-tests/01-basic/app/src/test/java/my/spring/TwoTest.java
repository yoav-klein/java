package my.spring;

import org.springframework.test.context.*;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.beans.factory.annotation.Autowired;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@ContextConfiguration(classes = {AppConfig.class})
public class TwoTest extends AbstractTestNGSpringContextTests  {
    private Foo foo;
    private Bar bar;

    @Autowired
    void setFoo(Foo foo) {
        this.foo = foo;
    }

    @Autowired
    void setBar(Bar bar) {
        this.bar = bar;
    }

    @Test(dependsOnMethods = { "fooTwo" })
    public void fooOne() {
        System.out.println("fooOne: " + this.foo.getNumber());
        this.foo.setNumber(this.foo.getNumber() + 1);
    }

    @Test public void fooTwo() {
        System.out.println("fooTwo: " + this.foo.getNumber());
        this.foo.setNumber(this.foo.getNumber() + 1);
    }

 
    @Test public void fooThree() {
        System.out.println("fooThree: " + this.foo.getNumber());
        this.foo.setNumber(this.foo.getNumber() + 1);
    }
}