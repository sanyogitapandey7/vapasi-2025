package com.tw.bll;

import com.tw.dao.SplitwiseDAOImpl;
import com.tw.entity.Expense;
import com.tw.entity.Transaction;
import com.tw.util.AppConstants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SplitwiseBLLTest {


    @Test
    public void testValidTransaction() {
        String filePath = AppConstants.ValidInputFile;
        SplitwiseDAOImpl dao=new SplitwiseDAOImpl();
        List<Expense> expenses=dao.readExpensesFromFile(filePath);
        List<Transaction> resultTransactions = dao.settleExpenses(expenses);
        Transaction transaction=new Transaction("C","B",275.0);
        assertEquals(resultTransactions.toArray()[0], transaction);
    }

    @Test
    public void testTransactionCount() {
        String filePath = AppConstants.ValidInputFile;
        SplitwiseDAOImpl dao=new SplitwiseDAOImpl();
        List<Expense> expenses=dao.readExpensesFromFile(filePath);
        List<Transaction> transactions = dao.settleExpenses(expenses);
        assertEquals(3, transactions.size());
    }

    @Test
    public void testEmptyInput() {
        String filePath = AppConstants.EmptyInputFile;
        SplitwiseDAOImpl dao=new SplitwiseDAOImpl();
        List<Expense> expenses=dao.readExpensesFromFile(filePath);
        assertTrue(expenses.isEmpty());
    }

    @Test
    public void testSettledInput() {
        String filePath = AppConstants.SettledInput;
        SplitwiseDAOImpl dao=new SplitwiseDAOImpl();
        List<Expense> expenses=dao.readExpensesFromFile(filePath);
        List<Transaction> transactions = dao.settleExpenses(expenses);
        assertTrue(transactions.isEmpty());
    }
}