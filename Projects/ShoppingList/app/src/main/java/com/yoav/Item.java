
package com.yoav;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private int quantity;
    private boolean purchased;

    Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.purchased = false;
    }

    void markPurchased() {
        this.purchased = true;
    }

    String getName() {
        return this.name;
    }

    int getQuantity() {
        return this.quantity;
    }

    boolean isPurchased() { 
        return this.purchased;
    }

}