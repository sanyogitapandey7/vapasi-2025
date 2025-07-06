package com.tw.bll;

import com.tw.dao.SplitwiseDAOImpl;
import com.tw.entity.Expense;
import com.tw.entity.Transaction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SplitwiseBLLTest {


    @Test
    public void testTransactionCount() {
        String filePath = "/Users/sanyogitapandey/IdeaProjects/Vapasi-2025/splitwise/src/test/java/com/tw/bll/expenseTest1.txt";
        SplitwiseDAOImpl dao=new SplitwiseDAOImpl();
        SplitwiseBLLImpl splitwiseBLL= new SplitwiseBLLImpl(dao);
        List<Expense> expenses=splitwiseBLL.readExpensesFromFile(filePath);
        List<Transaction> transactions = splitwiseBLL.settleExpenses(expenses);
        assertEquals(3, transactions.size());
    }

    @Test
    public void testEmptyInput() {
        String filePath = "/Users/sanyogitapandey/IdeaProjects/Vapasi-2025/splitwise/src/test/java/com/tw/bll/expenseTest3.txt";
        SplitwiseDAOImpl dao=new SplitwiseDAOImpl();
        SplitwiseBLLImpl splitwiseBLL= new SplitwiseBLLImpl(dao);
        List<Expense> expenses=splitwiseBLL.readExpensesFromFile(filePath);
        List<Transaction> transactions = splitwiseBLL.settleExpenses(expenses);
        assertTrue(transactions.isEmpty());
    }
}