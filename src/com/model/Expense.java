package com.smartspend.model;

import java.time.LocalDate;

public class Expense extends Transaction {
    public Expense(String user, String category, double amount, LocalDate date) {
        super(user, "Expense", category, amount, date);
    }
}
