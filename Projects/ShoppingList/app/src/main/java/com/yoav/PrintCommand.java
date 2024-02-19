
package com.yoav;

public class PrintCommand implements Command {
    private ShoppingList sl;

    PrintCommand(ShoppingList sl) {
        this.sl = sl;
    }

    public void execute() {
        sl.printList();
    }
}