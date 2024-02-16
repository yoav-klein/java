
package com.yoav;

import java.lang.Runnable;

class ConsoleMenuItem {
    private String description;
    private Runnable function;

    ConsoleMenuItem(String description, Runnable function) {
        this.description = description;
        this.function = function;
    }

    void execute() {
        this.function.run();
    }

    String getDescription() {
        return this.description;
    }

}