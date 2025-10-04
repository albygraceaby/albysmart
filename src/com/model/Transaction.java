package com.smartspend.model;

import java.time.LocalDate;

public class Transaction {
    private String username;
    private String type; // Income or Expense
    private String category;
    private double amount;
    private LocalDate date;

    public Transaction(String username, String type, String category, double amount, LocalDate date) {
        this.username = username;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getUsername() { return username; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return date + "," + type + "," + category + "," + amount;
    }
}
