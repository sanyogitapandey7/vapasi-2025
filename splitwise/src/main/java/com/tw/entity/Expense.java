package com.tw.entity;
import java.util.List;
import java.util.Objects;

public class Expense {
    private String payer;
    private double amount;
    private List<String> participants;
    private String description;

    public Expense(String payer, double amount, List<String> participants, String description) {
        this.payer = payer;
        this.amount = amount;
        this.participants = participants;
        this.description = description;
    }

    public String getPayer() {
        return payer;
    }

    public double getAmount() {
        return amount;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Double.compare(amount, expense.amount) == 0 &&
                Objects.equals(payer, expense.payer) &&
                Objects.equals(participants, expense.participants) &&
                Objects.equals(description, expense.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payer, amount, participants, description);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "payer='" + payer + '\'' +
                ", amount=" + amount +
                ", participants=" + participants +
                ", description='" + description + '\'' +
                '}';
    }
}


