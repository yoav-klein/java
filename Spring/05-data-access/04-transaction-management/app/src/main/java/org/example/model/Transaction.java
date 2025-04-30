package org.example.model;

public class Transaction {
    private int sum;
    private User sender;
    private User receiver;

    public Transaction() {
    }

    public int getSum() {
        return sum;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }
    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
    public User getReceiver() {
        return receiver;
    }
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return receiver.getName() + " Passed to " + sender.getName() + " Sum of: " + this.sum;
    }
    
}
