
package com.yoav;

import com.yoav.ConsoleMenu;

public class Main {

    private static boolean shouldRun = true;

    private final static String fileName = "shopping-list.data";

    static void stop() {
        Main.shouldRun = false;
    }

    static public void main(String[] args) {
        
        ShoppingList sl = new ShoppingList();

        ConsoleMenu start = new ConsoleMenu("What shopping list to work on?");
        
        start.addMenuItem("Load from a file", new LoadCommand(sl, fileName));
        start.addMenuItem("New shopping list", () -> {}); // do nothing

        start.getUserChoice();
        

        Integer shouldStop = 0;
        ConsoleMenu mainMenu = new ConsoleMenu("What to do now?");
        
        mainMenu.addMenuItem("Display the list", new PrintCommand(sl));
        mainMenu.addMenuItem("Add an item to the list", new AddItemCommand(sl));
        mainMenu.addMenuItem("Mark item as purchased", new MarkAsPurchasedCommand(sl));
        mainMenu.addMenuItem("Save list", new SaveCommand(sl, fileName));
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
        
    }
}