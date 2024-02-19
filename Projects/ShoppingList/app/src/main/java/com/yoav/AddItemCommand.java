
package com.yoav;

import java.util.Scanner;
import java.util.InputMismatchException;

public class AddItemCommand implements Command {
    private ShoppingList sl;

    AddItemCommand(ShoppingList sl) {
        this.sl = sl;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Item name:");
        String itemName = scanner.next();
        System.out.println("Quantity:");
        int quantity = 0;
        try {
            quantity = scanner.nextInt();
        } catch(InputMismatchException e) {
            System.err.println("Invalid input, aborting...");
            scanner.next();
            return;
        }

        sl.addItem(itemName, quantity);
    }
}