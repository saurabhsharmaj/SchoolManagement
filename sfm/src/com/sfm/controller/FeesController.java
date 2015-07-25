package com.sfm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 
	 @Value("${TOTAL_FEES}")
	 private Double totalFees;
	 
	@RequestMapping(value="viewFees")
	public String viewFees(HttpSession session, Map<String, Object> map) {
		map.put("user", (User)session.getAttribute("user"));
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
		User user = new User();
		user.setStudentFees(totalFees);
		f.setUser(user);
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
		Fees fees,@PathVariable("userId")Integer userId,Map<String, Object> map) {			
			fees.setTotalFees(userService.getUserById(userId).getStudentFees());
			feesService.addFees(fees);			
			return "redirect:/viewFeesByUserId/"+fees.getUser().getId();
		}		
		
		@RequestMapping(value = "/getUserByNextPaymentDate",  produces="application/json", method = RequestMethod.GET)
		public @ResponseBody
		List<Fees> getTags() {
			List<Fees> feesList= new ArrayList<Fees>();
			List<Object[]> dataList = feesService.getUserByNextPaymentDate();
			for (Object[] objects : dataList) {
				User u = (User) objects[0];
				Fees f = (Fees) objects[1];
				f.setUser(u);
				feesList.add(f);
			}
			return feesList;

		}
}