
package com.yoav;

import java.lang.IndexOutOfBoundsException;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class ShoppingList implements Serializable {

    private List<Item> list = new ArrayList<Item>();

    ShoppingList() {}

    void addItem(String name, int quantity) {
        list.add(new Item(name, quantity));
    }

    void printList() {
        System.out.println("******************");
        for (int i = 0; i < list.size(); ++i) {
            Item item = list.get(i);
            System.out.format("%d: %-2d * %-10s   purchased: %b%n", 
                i + 1, item.getQuantity(), item.getName(), item.isPurchased());
        }
        if(list.isEmpty()) {
            System.out.println(">>> empty list <<<");
        }
        System.out.println("******************");
    }

    void removeItem(int i) {
        try {
            list.remove(i - 1);
        } catch(IndexOutOfBoundsException e) {
            System.err.println("Index out of range");
            throw e;
        }
    }

    void markPurchased(int i) {
        try {
            Item item = this.list.get(i - 1);
            item.markPurchased();
        } catch(IndexOutOfBoundsException e) {
            System.err.println("Index out of range");
        }
    }

    void save(ObjectOutputStream oos) {
        try {
            oos.writeObject(this.list);
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    void load(ObjectInputStream ois) {
        try {
            this.list = (List<Item>)ois.readObject();
        } catch(IOException e) {
            System.out.println(e);
        } catch(ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    int length() {
        return this.list.size();
    }
}