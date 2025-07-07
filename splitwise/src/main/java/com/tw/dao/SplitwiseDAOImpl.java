package com.tw.dao;

import com.tw.entity.Expense;
import com.tw.entity.Transaction;
import com.tw.util.AppConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import java.util.logging.Logger;
import java.util.logging.Level;


public class SplitwiseDAOImpl implements SplitwiseDAO {
    private static final Logger logger = Logger.getLogger(SplitwiseDAOImpl.class.getName());

    public SplitwiseDAOImpl() {
    }

    @Override
    public List<Expense> readExpensesFromFile(String filePath) {
        List<Expense> expenses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = AppConstants.ExpenseInputPattern.matcher(line.trim());
                if (matcher.matches()) {
                    i++;
                    Expense expense = parseExpenseLine(line); // If invalid, this throws and aborts
                    expenses.add(expense);
                } else {
                    throw new IOException(AppConstants.ErrorReadingFile);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, AppConstants.ErrorReadingFile, e);
        }

        return expenses;
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

            if (Math.abs(debtor.getValue()) < AppConstants.PrecisionValue) i++;
            if (Math.abs(creditor.getValue()) < AppConstants.PrecisionValue) j++;
        }

        return transactions;
    }


    private Expense parseExpenseLine(String line) throws IOException {
        Matcher matcher = AppConstants.ExpenseInputPattern.matcher(line);
        if (matcher.find()) {
            String payer = matcher.group(1);
            double amount = Double.parseDouble(matcher.group(2));
            String description = matcher.group(3).trim();
            List<String> participants = Arrays.stream(matcher.group(4).split(", "))
                    .map(String::trim)
                    .collect(Collectors.toList());
            return new Expense(payer, amount, participants, description);
        } else {
            throw new IOException(AppConstants.InvalidInputMessage);
        }
    }


}
