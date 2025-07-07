package com.tw.ui;

import com.tw.dao.SplitwiseDAOImpl;
import com.tw.entity.Expense;
import com.tw.entity.Transaction;
import com.tw.util.AppConstants;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SplitwiseConsole {
    private static final Logger logger = Logger.getLogger(SplitwiseConsole.class.getName());

    public static void main(String[] args) {
        SplitwiseDAOImpl dao = new SplitwiseDAOImpl();
        List<Expense> expenses = dao.readExpensesFromFile(AppConstants.ExpenseFilePath);
        if (expenses.isEmpty()) {
            logger.log(Level.SEVERE, AppConstants.ErrorReadingFile);
            return;
        }
        List<Transaction> transactions = dao.settleExpenses(expenses);
        logger.info("List of transactions - ");
        if (transactions.isEmpty()) {
            logger.info(AppConstants.Settled);
        } else {
            transactions.forEach(System.out::println);
        }
    }

}
