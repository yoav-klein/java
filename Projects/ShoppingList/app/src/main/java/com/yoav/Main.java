
package com.yoav;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Console;
import java.io.Reader;

import java.util.Scanner;
import java.util.InputMismatchException;

import java.lang.RuntimeException;

import com.yoav.ConsoleMenu;

public class Main {

    private static boolean shouldRun = true;

    private final static String fileName = "shopping-list.data";

    static public void save(ShoppingList sl) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fileName));  
            sl.save(oos);
        } catch(FileNotFoundException e) {
            System.out.println(e);
        } catch(IOException e) {
            System.out.println(e);
        }

    }

    static public void load(ShoppingList sl) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            sl.load(ois);
        } catch(IOException e) { 
            System.out.println(e);
            throw new RuntimeException();
        }

    }

    static void addItem(ShoppingList sl) {
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

    static void stop() {
        Main.shouldRun = false;
    }

    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ShoppingList sl = new ShoppingList();

        ConsoleMenu start = new ConsoleMenu("What shopping list to work on?");
        start.addMenuItem("Load from a file", () -> load(sl));
        start.addMenuItem("New shopping list", () -> {});

        start.getUserChoice();
        
        Integer shouldStop = 0;
        ConsoleMenu mainMenu = new ConsoleMenu("What to do now?");

        mainMenu.addMenuItem("Display the list", () -> sl.printList());
        mainMenu.addMenuItem("Add an item to the list", () -> addItem(sl));
        mainMenu.addMenuItem("Mark item as purchased", () -> {
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
        });
        mainMenu.addMenuItem("Delete an item from the list", () -> {
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
        });
        mainMenu.addMenuItem("Save the list", () -> save(sl));
        mainMenu.addMenuItem("Exit", () -> stop() );
        do {
            mainMenu.displayMenu();
            mainMenu.getUserChoice();
        }
        while(shouldRun);
            
    }
}