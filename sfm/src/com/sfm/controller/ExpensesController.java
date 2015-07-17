package com.sfm.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfm.model.Charges;
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
		List<Charges> expensesList = new ArrayList<Charges>();
		
		Charges c1 = new Charges(userService.getUserById(2), 1, "Stationary", new BigDecimal(2000), "saurabh", new Date());
		c1.setId(1);
		expensesList.add(c1);
		
		Charges c2 = new Charges(userService.getUserById(2), 1, "Medical", new BigDecimal(1000), "saurabh", new Date());
		c1.setId(2);
		expensesList.add(c2);
		
		map.put("expensesList", expensesList);
		return "viewExpensesList";
	}
	
	@RequestMapping(value="addExpense")
	public String listFees(Map<String, Object> map) {
		Charges c1 = new Charges();
		map.put("expense", c1);
		map.put("expenseTypeList", Utils.stringToArray(expenseTypeProperty));
		
		return "viewExpensesPage";
	}
	
	@RequestMapping("/editExpense/{userId}/{feesId}")
	public String editUser(
			@PathVariable("userId")Integer userId,@PathVariable("feesId")Integer feesId,
			Map<String, Object> map)
	{
		map.put("expenseTypeList", Utils.stringToArray(expenseTypeProperty));	
		map.put("action","edit");
		Charges c1 = new Charges(userService.getUserById(2), 1, "Stationary", new BigDecimal(2000), "saurabh", new Date());
		c1.setId(1);
		c1.setUser(userService.getUserById(userId));
		map.put("expense", c1);
		map.put("userId", 2);

		return "viewExpensesPage";
	}
}