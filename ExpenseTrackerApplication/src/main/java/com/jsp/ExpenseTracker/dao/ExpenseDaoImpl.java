package com.jsp.ExpenseTracker.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.ExpenseTracker.entity.Expenses;
import com.jsp.ExpenseTracker.entity.User;
import com.jsp.ExpenseTracker.repository.ExpensesRepository;
import com.jsp.ExpenseTracker.repository.UserRepository;

@Component
public class ExpenseDaoImpl implements ExpenceDao
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ExpensesRepository expensesRepository;

	@Override
	public Expenses addExpenses(Expenses expenses, int userId) 
	{
		//chech whether userId is valid or not
		User user=userRepository.findById(userId).orElse(null);
		if(user!=null)
		{
			// add Expenses
			// to add user Object in Expense Object
			expenses.setUserInfo(user);
			return expensesRepository.save(expenses);
		}
		return null;
	}

	@Override
	public List<Expenses> viewExpenses(int userId) 
	{
		// check whether given userId id valid or not
		User user=userRepository.findById(userId).orElse(null);
		if(user!=null)
		{
			// fetch expenses related to user;
			return user.getExpenses();
		}
		return null;
	}

	@Override
	public Expenses updateExpense(Expenses expenses, int expenseId) 
	{
		Expenses expensesFromDb =expensesRepository.findById(expenseId).orElse(null);
		if(expensesFromDb!=null)
		{
			expensesFromDb.setAmount(expenses.getAmount());
			expensesFromDb.setDate(expenses.getDate());
			expensesFromDb.setDescription(expenses.getDescription());
			expensesFromDb.setExpenseCategory(expenses.getExpenseCategory());
			//expensesFromDb.setUserInfo(expenses.getUserInfo());
			return expensesRepository.save(expensesFromDb);
		}
		return null;
	}

	@Override
	public boolean deleteExpense(int expenseId) 
	{
		try {
			expensesRepository.deleteById(expenseId);
			return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Expenses findByExpenseId(int expenseId) 
	{
		Expenses expenses = expensesRepository.findById(expenseId).orElse(null);
		return expenses;
	}
	
	
	
}