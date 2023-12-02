package com.jsp.ExpenseTracker.dao;

import java.util.List;

import com.jsp.ExpenseTracker.entity.Expenses;

public interface ExpenceDao {

	Expenses addExpenses(Expenses expenses,int userId);
	
	List<Expenses> viewExpenses(int userId);
	
	Expenses updateExpense(Expenses expenses,int expenseId);
	
	boolean deleteExpense(int expenseId);
	
	Expenses findByExpenseId(int expenseId);

}
