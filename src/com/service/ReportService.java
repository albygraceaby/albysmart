package com.smartspend.service;

import java.io.*;
import java.time.LocalDate;

public class ReportService {
    private final String txnFile = "data/transactions.csv";
    private final String reportDir = "data/";

    public void generateMonthlyReport(String username) {
        double totalIncome = 0, totalExpense = 0;
        LocalDate now = LocalDate.now();
        String fileName = reportDir + username + "_report.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(txnFile));
             FileWriter fw = new FileWriter(fileName)) {

            String line;
            fw.write("=== Monthly Report for " + username + " ===\n");
            fw.write("Month: " + now.getMonth() + " " + now.getYear() + "\n\n");

            while ((line = br.readLine()) != null) {
                if (line.startsWith(username + ",")) {
                    String[] p = line.split(",");
                    LocalDate date = LocalDate.parse(p[1]);
                    if (date.getMonth() == now.getMonth()) {
                        String type = p[2];
                        double amt = Double.parseDouble(p[4]);
                        fw.write(line + "\n");
                        if (type.equals("Income")) totalIncome += amt;
                        else totalExpense += amt;
                    }
                }
            }

            fw.write("\nTotal Income: " + totalIncome);
            fw.write("\nTotal Expense: " + totalExpense);
            fw.write("\nNet Savings: " + (totalIncome - totalExpense));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Report generated at " + fileName);
    }

    public String getReportFile(String username) {
        return reportDir + username + "_report.txt";
    }
}
