
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


import java.lang.RuntimeException;

import com.yoav.ConsoleMenu;

public class Main {

    private static boolean shouldRun = true;

    private final static String fileName = "shopping-list.data";

    static void stop() {
        Main.shouldRun = false;
    }

    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ShoppingList sl = new ShoppingList();

        ConsoleMenu start = new ConsoleMenu("What shopping list to work on?");
        FileOutputStream fos = null;
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(fileName);
            start.addMenuItem("Load from a file", new LoadCommand(sl, new ObjectInputStream(fis)));
        } catch(IOException e) {
            System.out.println(e);
        }
        
        start.addMenuItem("New shopping list", () -> {}); // do nothing

        start.getUserChoice();
        
        Integer shouldStop = 0;
        ConsoleMenu mainMenu = new ConsoleMenu("What to do now?");
        
        mainMenu.addMenuItem("Display the list", new PrintCommand(sl));
        mainMenu.addMenuItem("Add an item to the list", new AddItemCommand(sl));
        mainMenu.addMenuItem("Mark item as purchased", new MarkAsPurchasedCommand(sl));
        try {
            fos = new FileOutputStream(fileName);
            mainMenu.addMenuItem("Save the list", new SaveCommand(sl, new ObjectOutputStream(fos)));
        } catch(IOException e) {
            System.out.println(e);
        }
        mainMenu.addMenuItem("Delete an item from the list", new DeleteItemCommand(sl));
        mainMenu.addMenuItem("Exit", () -> { shouldRun = false; });


        do {
            mainMenu.displayMenu();
            try {
                mainMenu.getUserChoice();
            } catch(Exception e) {
                System.out.println("Exception: " + e);
            }
        }
        while(shouldRun);
        
        try {
            fos.close();
            fis.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}