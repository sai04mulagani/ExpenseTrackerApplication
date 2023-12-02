package com.jsp.ExpenseTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.ExpenseTracker.dao.ExpenceDao;
import com.jsp.ExpenseTracker.entity.Expenses;

@Controller
public class AppController {

	@RequestMapping("/")
	public String index() {
		return "mainpage";
	}
	
	@RequestMapping("add")
	public String add() {
		return "addexpenses";
	}
	
	@RequestMapping("filter")
	public String filter() {
		return "filterexpenses";
	}
	
	@RequestMapping("total")
	public String total() {
		return "gettotal";
	}
	
	@RequestMapping("login")
	public String loginPage() {
		return "instagramlogin";
	}
	
	
	@RequestMapping("register")
	public String registerPage() {
		return "RegisterPage";
	}
	
	@RequestMapping("view")
	public String view() {
		return "viewexpenses";
	}
	
	@RequestMapping("welcome")
	public String welcomeReg() {
		return "welcomereg";
	}	
	
	@Autowired
	private ExpenceDao expenceDao;
	
	@RequestMapping("updateExpenses")
	public String updateExpense(@RequestParam("eId") int expenseId , Model model ) {
		Expenses expenses=expenceDao.findByExpenseId(expenseId);
		model.addAttribute("expense",expenses);
		
		return "UpdateExpense";
	}
}             