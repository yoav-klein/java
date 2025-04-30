package org.example.model;

public class Account {
    private int sum;
    private User owner;
    
    public Account(int sum, User owner) {
        this.sum = sum;
        this.owner = owner;
    }

    public Account() {
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "User: " + this.owner.getName() + ", sum: " + this.sum;
    }
    
}
