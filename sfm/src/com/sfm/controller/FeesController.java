package com.sfm.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfm.model.Fees;
import com.sfm.model.User;
import com.sfm.service.FeesService;
import com.sfm.service.UserService;
import com.sfm.util.Utils;

@Controller
public class FeesController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FeesService feesService;
	
	 @Value("${NoOfInstallment}")
	 private String noOfInstallmentProperty;
	 
	@RequestMapping(value="viewFeesPayments")
	public String viewFeesPayments(Map<String, Object> map) {			
//		map.put("feesPaymentList", feesService.getFeesById(18));
		User user = userService.getUserById(18);
		Set<Fees> feesList = user.getFeeses();
		for (Fees fee : feesList) {
			System.out.println(fee.getId());
		}
		
		map.put("fees", new Fees());
		map.put("user", userService.getUserById(18));
		return "feesPayment";
	}
	
	@RequestMapping(value="viewFeeDetailByUserId/{userId}")
	public String viewFeesPayments(Map<String, Object> map,
			@PathVariable("userId")Integer userId) {			
		User user = userService.getUserById(userId);		
		map.put("fees", new Fees());
		map.put("user", user);
		return "feesPayment";
	}
	
	@RequestMapping(value="addfees")
	public String addfees(Map<String, Object> map) {
		Fees f= new Fees();
		f.setUser(new User());
		map.put("fees", f);
		map.put("userId", 2);
		map.put("noOfInstallmentList", Utils.stringToArray(noOfInstallmentProperty));
		
		return "feesPayment";
	}
	
	@RequestMapping("/editfees/{userId}/{feesId}")
	public String editUser(
			@PathVariable("userId")Integer userId,@PathVariable("feesId")Integer feesId,
			Map<String, Object> map)
	{
		User user = userService.getUserById(userId);
		map.put("user", user);
		map.put("noOfInstallmentList", Utils.stringToArray(noOfInstallmentProperty));	
		map.put("action","edit");		
		map.put("fees", feesService.getFeesById(feesId));
		return "feesPayment";
	}
}