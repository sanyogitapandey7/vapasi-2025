package com.tw.dao;

import com.tw.entity.Expense;
import com.tw.entity.Transaction;

import java.util.List;

public interface SplitwiseDAO {
    List<Expense> readExpensesFromFile(String filePath);

    List<Transaction> settleExpenses(List<Expense> expenses);
}
