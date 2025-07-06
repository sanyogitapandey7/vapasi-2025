package com.tw.dao;

import com.tw.entity.Expense;
import com.tw.entity.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SplitwiseDAOImpl implements SplitwiseDAO {
   private static final Pattern EXPENSE_PATTERN = Pattern.compile("(\\w+) spent (\\d+) for (.*?) for ([A-Z](?:, [A-Z])*)");

    public SplitwiseDAOImpl() {
    }

    @Override
    public List<Expense> readExpensesFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(this::parseExpenseLine)
                    .filter(java.util.Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Transaction> settleExpenses(List<Expense> expenses) {
        Map<String, Double> balanceMap = new HashMap<>();
         for (Expense expense : expenses) {
            double share = expense.getAmount() / expense.getParticipants().size();
            for (String participant : expense.getParticipants()) {
                balanceMap.put(participant, balanceMap.getOrDefault(participant, 0.0) - share);
            }
            balanceMap.put(expense.getPayer(), balanceMap.getOrDefault(expense.getPayer(), 0.0) + expense.getAmount());
        }

        List<Map.Entry<String, Double>> creditors = balanceMap.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .toList();

        List<Map.Entry<String, Double>> debtors = balanceMap.entrySet().stream()
                .filter(e -> e.getValue() < 0)
                .sorted(Comparator.comparingDouble(Map.Entry::getValue)) // most negative first
                .toList();

        List<Transaction> transactions = new ArrayList<>();

        int i = 0, j = 0;
        while (i < debtors.size() && j < creditors.size()) {
            Map.Entry<String, Double> debtor = debtors.get(i);
            Map.Entry<String, Double> creditor = creditors.get(j);

            double debit = -debtor.getValue();
            double credit = creditor.getValue();
            double settleAmount = Math.min(debit, credit);

            transactions.add(new Transaction(debtor.getKey(), creditor.getKey(), settleAmount));

            debtor.setValue(debtor.getValue() + settleAmount);
            creditor.setValue(creditor.getValue() - settleAmount);

            if (Math.abs(debtor.getValue()) < 0.01) i++;
            if (Math.abs(creditor.getValue()) < 0.01) j++;
        }

        return transactions;
    }


    private Expense parseExpenseLine(String line) {
        Matcher matcher = EXPENSE_PATTERN.matcher(line);
        if (matcher.find()) {
            String payer = matcher.group(1);
            double amount = Double.parseDouble(matcher.group(2));
            String description = matcher.group(3).trim();
            List<String> participants = Arrays.stream(matcher.group(4).split(", "))
                    .map(String::trim)
                    .collect(Collectors.toList());
            return new Expense(payer, amount, participants, description);
        } else {
            System.err.println("Warning: Could not parse expense line: " + line);
            return null;
        }
    }

}
