package com.sfm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sfm.model.Fees;
import com.sfm.model.User;
import com.sfm.service.FeesService;
import com.sfm.service.UserService;
import com.sfm.util.Utils;

@Controller
public class FeesController {/*
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FeesService feesService;
	
	 @Value("${NoOfInstallment}")
	 private String noOfInstallmentProperty;
	 
	 @Value("${TOTAL_FEES}")
	 private String totalFees="50000";
	 
	 
	@RequestMapping(value="viewFeesPayments")
	public String viewFeesPayments(Map<String, Object> map) {	
		map.put("user", userService.getUserById(18));	
		map.put("feesPaymentList", feesService.listCompoundFees());		
		return "viewFeesPaymentList";
	}
	
	@RequestMapping(value="viewFeeDetailByUserId/{userId}")
	public String viewFeesPayments(Map<String, Object> map,
			@PathVariable("userId")Integer userId) {			
		User user = userService.getUserById(userId);	
		Fees fees = new Fees();
		fees.setUser(user);
		fees.setTotalFees(Double.valueOf("50000"));
		map.put("fees", fees);
		map.put("user", user);
		map.put("action","add");
		return "feesPayment";
	}
	
	@RequestMapping(value="addfees")
	public String addfees(Map<String, Object> map) {
		Fees f= new Fees();
		f.setUser(new User());
		map.put("fees", f);
		map.put("userId", 2);
		map.put("noOfInstallmentList", Utils.stringToArray(noOfInstallmentProperty,"noOfInstallmentProperty"));
		
		return "feesPayment";
	}
	
	@RequestMapping("/editfees/{userId}/{feesId}")
	public String editUser(
			@PathVariable("userId")Integer userId,@PathVariable("feesId")Integer feesId,
			Map<String, Object> map)
	{
		User user = userService.getUserById(userId);
		//map.put("user", user);
		map.put("noOfInstallmentList", Utils.stringToArray(noOfInstallmentProperty,"noOfInstallmentProperty"));	
		map.put("action","edit");
		Fees fees = feesService.getFeesById(feesId);
		fees.setUser(user);
		map.put("fees", feesService.getFeesById(feesId));
		return "feesPayment";
	}
	
	@RequestMapping(value = {"/savefees/","/savefees/{userId}"}, method = RequestMethod.POST)
	public String addExpense(@PathVariable("userId")Integer userId, @ModelAttribute("fees") 
	Fees fees,Map<String, Object> map) {			
		feesService.addFees(fees);			
		return "redirect:/viewFeeDetailByUserId/"+fees.getUser().getId();
	}*/

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FeesService feesService;
		
	 @Value("${NoOfInstallment}")
	 private String noOfInstallmentProperty;
	 
	 @Value("${TOTAL_FEES}")
	 private String totalFees="50000";
	 
	@RequestMapping(value="viewFees")
	public String viewFees(Map<String, Object> map) {
		map.put("user", userService.getUserById(18));//TODO
		map.put("feesList", feesService.listCompoundFees());
		return "viewFeeslist";	
	}
	
	@RequestMapping(value="viewFeesByUserId/{userId}")
	public String viewFeesByUserId(@PathVariable("userId")Integer userId,Map<String, Object> map) {		
		map.put("feesList", feesService.getFeesByUserId(userId));
		Fees fees = new Fees();
		fees.setUser(userService.getUserById(userId));
		map.put("fees", fees);
		return "viewFeesPage";
	}
	
	@RequestMapping(value="addFees")
	public String listFees(Map<String, Object> map) {
		Fees f = new Fees();
		map.put("fees", f);
		map.put("noOfInstallmentList", Utils.stringToArray(noOfInstallmentProperty,"noOfInstallmentProperty"));		
		return "viewFeesPage";
	}
	
	@RequestMapping("/editFees/{userId}/{feesId}")
	public String editFees(
			@PathVariable("userId")Integer userId,@PathVariable("feesId")Integer feesId,
			Map<String, Object> map)
	{
		
		map.put("action","edit");
		User user = userService.getUserById(userId);
		map.put("user", user);
		map.put("feesList", feesService.getFeesByUserId(userId));
		Fees fees = feesService.getFeesById(feesId);
		fees.setUser(user);
		map.put("fees", fees);

		return "viewFeesPage";
	}	
		
		@RequestMapping(value = "/saveFees/{userId}", method = RequestMethod.POST)
		public String saveFees(@ModelAttribute("fees") 
		Fees fees,Map<String, Object> map) {			
			feesService.addFees(fees);			
			return "redirect:/viewFeesByUserId/"+fees.getUser().getId();
		}

}