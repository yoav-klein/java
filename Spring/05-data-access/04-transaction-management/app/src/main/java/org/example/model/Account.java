package org.example.model;

public class Account {
    private int id;
    private User owner;
    
    public Account(int id, User owner) {
        this.id = id;
        this.owner = owner;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
}
