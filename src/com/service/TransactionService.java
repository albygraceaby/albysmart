package com.smartspend.service;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import com.smartspend.model.*;

public class TransactionService {
    private final String filePath = "data/transactions.csv";
    private Scanner sc = new Scanner(System.in);

    public void addIncome(String username) {
        System.out.print("Enter income source: ");
        String cat = sc.nextLine();
        System.out.print("Enter amount: ");
        double amt = sc.nextDouble(); sc.nextLine();
        Income inc = new Income(username, cat, amt, LocalDate.now());
        saveTransaction(inc);
    }

    public void addExpense(String username) {
        System.out.print("Enter expense category: ");
        String cat = sc.nextLine();
        System.out.print("Enter amount: ");
        double amt = sc.nextDouble(); sc.nextLine();
        Expense exp = new Expense(username, cat, amt, LocalDate.now());
        saveTransaction(exp);
    }

    private void saveTransaction(Transaction t) {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(t.getUsername() + "," + t.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewTransactions(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("--- Transactions ---");
            while ((line = br.readLine()) != null) {
                if (line.startsWith(username + ",")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
