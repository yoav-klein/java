
package com.yoav;

import java.lang.Runnable;

class ConsoleMenuItem {
    private String description;
    private Command command;

    ConsoleMenuItem(String description, Command command) {
        this.description = description;
        this.command = command;
    }

    void execute() {
        this.command.execute();
    }

    String getDescription() {
        return this.description;
    }

}