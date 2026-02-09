package org.example.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bar {
    Logger logger = LoggerFactory.getLogger(Bar.class);

    public void sayHi() {
        logger.info("This is info level message");
    }
}
