package com.smartspend;

import com.smartspend.service.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthService authService = new AuthService();
        TransactionService txnService = new TransactionService();
        ReportService reportService = new ReportService();
        EmailService emailService = new EmailService();

        System.out.println("=== Welcome to SmartSpend Budget Tracker ===");
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        if (!authService.login(user, pass)) {
            System.out.println("User not found. Creating new account...");
            authService.register(user, pass);
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. Generate Monthly Report");
            System.out.println("5. Email Report");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> txnService.addIncome(user);
                case 2 -> txnService.addExpense(user);
                case 3 -> txnService.viewTransactions(user);
                case 4 -> reportService.generateMonthlyReport(user);
                case 5 -> emailService.sendEmail(user, reportService.getReportFile(user));
                case 6 -> exit = true;
                default -> System.out.println("Invalid choice");
            }
        }
        System.out.println("Goodbye 👋");
        sc.close();
    }
}
