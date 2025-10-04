package com.smartspend.model;

import java.time.LocalDate;

public class Income extends Transaction {
    public Income(String user, String category, double amount, LocalDate date) {
        super(user, "Income", category, amount, date);
    }
}
