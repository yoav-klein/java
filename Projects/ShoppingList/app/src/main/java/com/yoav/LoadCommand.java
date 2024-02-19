
package com.yoav;

import java.io.ObjectInputStream;

public class LoadCommand implements Command {
    private ShoppingList sl;
    private ObjectInputStream in;

    LoadCommand(ShoppingList sl, ObjectInputStream in) {
        this.sl = sl;
        this.in = in;
    }

    public void execute() {
        this.sl.load(this.in);
    }
}