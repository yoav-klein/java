package org.example.business.model;

public class Account {
    private int balance;
    private User owner;

    public Account() {
    }

    public Account(int balance, User owner) {
        this.balance = balance;
        this.owner = owner;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void push(int sum) {
        this.balance -= sum;
    }

    public void pull(int sum) {
        this.balance += sum;
    }
    
}
