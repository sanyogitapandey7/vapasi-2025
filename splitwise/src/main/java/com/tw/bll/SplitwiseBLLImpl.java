package com.tw.bll;

import com.tw.dao.SplitwiseDAO;
import com.tw.dao.SplitwiseDAOImpl;
import com.tw.entity.Expense;
import com.tw.entity.Transaction;

import java.util.List;

public class SplitwiseBLLImpl implements SplitwiseBLL {
    SplitwiseDAO dao;
    public SplitwiseBLLImpl(SplitwiseDAOImpl splitwiseDAO)
    {
        this.dao=splitwiseDAO;
    }
    @Override
    public List<Expense> readExpensesFromFile(String filePath) {
        return dao.readExpensesFromFile(filePath);
    }

    @Override
    public List<Transaction> settleExpenses(List<Expense> expenses) {
        return dao.settleExpenses(expenses);
    }
}
