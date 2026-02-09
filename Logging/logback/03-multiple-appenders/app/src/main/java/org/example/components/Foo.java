package org.example.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Foo {
    Logger logger = LoggerFactory.getLogger(Foo.class);

    public void sayHi() {
        logger.debug("This is a debug message");
    }
}
