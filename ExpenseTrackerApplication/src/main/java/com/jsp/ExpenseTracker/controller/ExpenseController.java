package com.jsp.ExpenseTracker.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.ExpenseTracker.dao.ExpenceDao;
import com.jsp.ExpenseTracker.entity.Expenses;
import com.jsp.ExpenseTracker.entity.User;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	private ExpenceDao expenseDao;
	
	@PostMapping("addExpense")
	public ModelAndView addExpense(@ModelAttribute Expenses expenses,
									HttpServletRequest request ,
									@RequestParam("expenseDate") String date) {
		
		User user=(User)request.getSession().getAttribute("userData");
		
		LocalDate convertedDate =LocalDate.parse(date);
		expenses.setDate(convertedDate);
		Expenses expensesfromDB=expenseDao.addExpenses(expenses, user.getUserId());
		
		ModelAndView mv=new ModelAndView();
		if(expensesfromDB != null) {
			//viewExpenses page
			//to redirect request to the another method of controller
			mv.setViewName("redirect:/expense/displayExpenseView"); //pass argument as url of a controller method
			System.out.println("expenses added "+expensesfromDB.getExpenseId());
			return mv;
			
		}
		//addExpenses page
		mv.setViewName("addexpenses"); //pass argument as html file name\
		mv.addObject("msg", "Invalid Details");
		return mv;
	}
		
	
	@RequestMapping("welcome")
	public String welcomeReg() {
		return "welcomereg";
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
		@RequestMapping("displayExpenseView")
		public ModelAndView displayExpense(HttpServletRequest request) {
			ModelAndView mv=new ModelAndView();
			
			User user=(User)request.getSession().getAttribute("userData");
			
			List<Expenses> listOfExpenses = expenseDao.viewExpenses(user.getUserId());
			
			mv.setViewName("viewexpenses");
			System.out.println(listOfExpenses);
			mv.addObject("list",listOfExpenses);
			return mv;
		}
		
		@RequestMapping("deleteExpenses")
		public ModelAndView deleteExpense(@RequestParam("eId") int expenseId) {
			System.out.println(expenseId);
			boolean status=expenseDao.deleteExpense(expenseId);
			ModelAndView mv=new ModelAndView();
			if(status) {
				//view expenses page
				mv.setViewName("redirect:/expense/displayExpenseView");
				return mv;
			}
			return null;
		}
	
		
		@RequestMapping("updateExpenses")
		public ModelAndView updateExpenseFromDB(@ModelAttribute Expenses expenses,
												@RequestParam("expenseDate") String date) {
			
			expenses.setDate(LocalDate.parse(date));
			Expenses expensesFromDB=expenseDao.updateExpense(expenses, expenses.getExpenseId());
			
			ModelAndView mv= new ModelAndView();
			if(expensesFromDB!=null) {
				mv.setViewName("redirect:/expense/displayExpenseView");
				return mv;
			}
			return null;
			
		}
//		public ModelAndView totalExpenses(@ModelAttribute Expenses expenses,@RequestMapping())
//		{
//			
//		}
	}
	
	
	
	
	


