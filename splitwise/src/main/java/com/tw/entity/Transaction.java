package com.tw.entity;

import java.util.Objects;

public class Transaction {
    private String payer;
    private String receiver;
    private double amount;

    public Transaction(String payer, String receiver, double amount) {
        this.amount = Math.round(amount * 100.0) / 100.0;
        this.payer = payer;
        this.receiver = receiver;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(amount, that.amount) == 0 &&
                Objects.equals(payer, that.payer) &&
                Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payer, receiver, amount);
    }

    @Override
    public String toString() {
        return payer + " pays " + receiver + " " + amount;
    }
}
