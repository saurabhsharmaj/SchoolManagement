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
		List<Fees> feesPaymentList = new ArrayList<Fees>();
		/*
		User user, BigDecimal totalFees, Integer noOfInstallment,
		BigDecimal paidFees, BigDecimal pendingFees,
		BigDecimal additionCharges, Date nextPaymentDueDate,
		String updateBy, Date updatedOn*/
		Fees f = new Fees(userService.getUserById(2),new BigDecimal(3000),3,new BigDecimal(2000),new BigDecimal(1000),new BigDecimal(0),new Date(),"saurabh",new Date());
		f.setId(2);
		feesPaymentList.add(f);
		Fees f2 = new Fees(userService.getUserById(2),new BigDecimal(5000),1,new BigDecimal(5000),new BigDecimal(0),new BigDecimal(0),new Date(),"saurabh",new Date());
		f2.setId(2);
		feesPaymentList.add(f2);
		map.put("feesPaymentList", feesPaymentList);
		return "viewFeesPaymentList";
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
		map.put("noOfInstallmentList", Utils.stringToArray(noOfInstallmentProperty));	
		map.put("action","edit");
		Fees f= new Fees();//Retieve Fees Objet from DB.
		f.setId(2);
		f.setUser(userService.getUserById(userId));
		map.put("fees", f);
		map.put("userId", 2);

		return "feesPayment";
	}
}