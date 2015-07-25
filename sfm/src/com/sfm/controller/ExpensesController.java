package com.sfm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sfm.model.Charges;
import com.sfm.model.User;
import com.sfm.service.ExpenseService;
import com.sfm.service.UserService;
import com.sfm.util.Utils;

@Controller
public class ExpensesController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExpenseService expenseService;
	
	 @Value("${EXPENSES_TYPE}")
	 private String expenseTypeProperty;
	 
	@RequestMapping(value="viewExpenses")
	public String listRoutes(Map<String, Object> map) {
		map.put("user", userService.getUserById(18));
		map.put("expensesList", expenseService.listCompoundExpenses());
		return "viewExpensesList";	
	}
	
	@RequestMapping(value="viewExpensesByUserId/{userId}")
	public String listRoutes(@PathVariable("userId")Integer userId,Map<String, Object> map) {		
		map.put("expensesList", expenseService.getChargesByUserId(userId));
		Charges charges = new Charges();
		charges.setUser(userService.getUserById(userId));
		map.put("expense", charges);
		return "viewExpensesPage";
	}
	
	@RequestMapping(value="addExpense")
	public String listFees(Map<String, Object> map) {
		Charges c1 = new Charges();
		c1.setExpenseType(2);
		map.put("expense", c1);
		map.put("expenseTypeList", Utils.stringToArray(expenseTypeProperty,"expenseTypeProperty"));
		
		return "viewExpensesPage";
	}
	
	@RequestMapping("/editExpense/{userId}/{expenseId}")
	public String editUser(
			@PathVariable("userId")Integer userId,@PathVariable("expenseId")Integer expenseId,
			Map<String, Object> map)
	{
		map.put("expenseTypeList", Utils.stringToArray(expenseTypeProperty,"expenseTypeProperty"));	
		map.put("action","edit");
		User user = userService.getUserById(userId);
		map.put("user", user);
		map.put("expensesList", expenseService.getChargesByUserId(userId));
		Charges expense = expenseService.getChargesById(expenseId);
		expense.setUser(user);
		map.put("expense", expense);

		return "viewExpensesPage";
	}	
		
		@RequestMapping(value = "/saveExpense/{userId}", method = RequestMethod.POST)
		public String addExpense(@ModelAttribute("expense") 
		Charges expense,Map<String, Object> map) {			
			expenseService.addCharges(expense);			
			return "redirect:/viewExpensesByUserId/"+expense.getUser().getId();
		}
}