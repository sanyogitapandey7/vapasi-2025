package com.tw.com.tw.ui;
import com.tw.bll.SplitwiseBLLImpl;
import com.tw.dao.SplitwiseDAOImpl;
import com.tw.entity.Expense;
import com.tw.entity.Transaction;

import java.util.List;

public class SplitwiseConsole {
    public static void main(String[] args) {
        String filePath = "/Users/sanyogitapandey/IdeaProjects/Vapasi-2025/splitwise/src/main/java/com/tw/expenses.txt"; // Ensure this file is in the root of your project or specify full path
        SplitwiseDAOImpl dao=new SplitwiseDAOImpl();
        SplitwiseBLLImpl splitwiseBLL= new SplitwiseBLLImpl(dao);
        List<Expense> expenses = splitwiseBLL.readExpensesFromFile(filePath);
        if (expenses.isEmpty()) {
            System.out.println("No expenses found or file could not be read. Exiting.");
            return;
        }
        List<Transaction> transactions = splitwiseBLL.settleExpenses(expenses);
        System.out.println("List of transactions - ");
        if (transactions.isEmpty()) {
            System.out.println("No transactions needed. All expenses are balanced.");
        } else {
            transactions.forEach(System.out::println);
        }
    }

}
