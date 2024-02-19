
package com.yoav;

import java.util.Scanner;
import java.util.InputMismatchException;

public class DeleteItemCommand implements Command {
    private ShoppingList sl;

    DeleteItemCommand(ShoppingList sl) {
        this.sl = sl;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of item to delete");
        int itemNo = 0;
        try {
            itemNo = scanner.nextInt();
            sl.removeItem(itemNo);
        } catch(InputMismatchException e) {
            System.out.println("Invalid input");
            scanner.next();
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Index out of bound");
        }
    }
}