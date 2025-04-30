package org.example.model;

public class Transaction {
    private int sum;
    private int senderId;
    private int receiverId;

    public Transaction() {
    }

    public Transaction(int sum, int senderId, int receiverId) {
        this.sum = sum;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public int getSum() {
        return sum;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }
    public int getSenderId() {
        return senderId;
    }
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
    public int getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
    
}
