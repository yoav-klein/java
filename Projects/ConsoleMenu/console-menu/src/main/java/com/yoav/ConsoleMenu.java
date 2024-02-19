
package com.yoav;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.lang.Runnable;


public class ConsoleMenu {
    private Scanner s = new Scanner(System.in);
    private ArrayList<ConsoleMenuItem> menuItems = new ArrayList<>();
    private String menuTitle;

    public ConsoleMenu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public void addMenuItem(String description, Command command) {
        menuItems.add(new ConsoleMenuItem(description, command));
    }

    public void displayMenu() {
        for(int i = 0; i < menuItems.size(); ++i) {
            System.out.format("%d - %s%n", i + 1, menuItems.get(i).getDescription());
        }
    }

    public void getUserChoice() {
        int choice = 0;

        while(true) {
            System.out.format("Please choose a number between 1 and %d%n", menuItems.size());
            try {
                choice = s.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("Invalid input");
                s.next();
                continue;
            }
            if((choice < 1) || (choice > menuItems.size())) {
                System.out.println("Out of range");
                continue;
            }
            break;
        }

        menuItems.get(choice - 1).execute();
    }

}