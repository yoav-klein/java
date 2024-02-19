
package com.yoav;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveCommand implements Command {
    private ShoppingList sl;
    private ObjectOutputStream oos;

    SaveCommand(ShoppingList sl, ObjectOutputStream oos) {
        this.sl = sl;
        this.oos = oos;
    }

    public void execute() {
        try {
            sl.save(oos);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}