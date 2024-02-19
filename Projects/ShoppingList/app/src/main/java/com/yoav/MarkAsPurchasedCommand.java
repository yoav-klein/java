
package com.yoav;

import java.util.Scanner;
import java.util.InputMismatchException;

public class MarkAsPurchasedCommand implements Command {
    private ShoppingList sl;

    MarkAsPurchasedCommand(ShoppingList sl) {
        this.sl = sl;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of item to mark as purchased");
        int itemNo = 0;
        try {
            itemNo = scanner.nextInt();
            sl.markPurchased(itemNo);
        } catch(InputMismatchException e) {
            System.out.println("Invalid input");
            scanner.next();
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Index out of bound");
        }
    }
}