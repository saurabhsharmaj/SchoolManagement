package com.sfm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfm.model.CompoundFees;
import com.sfm.model.Data;
import com.sfm.model.Fees;
import com.sfm.model.User;
import com.sfm.service.FeesService;
import com.sfm.service.UserService;
import com.sfm.util.DataTablesParamUtility;
import com.sfm.util.JQueryDataTableParamModel;
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
	 
	 @Value("${dueDateNotificationCriteria}")
	 private Integer dueDateNotificationCriteria;
	
	 @RequestMapping(value = "/listFeesDATA",  produces="application/json", method = RequestMethod.GET)
		public @ResponseBody
		Data listFeesDATA(HttpServletRequest request){
		 JQueryDataTableParamModel param = DataTablesParamUtility.getParam(request);		  
		 Data data = feesService.listCompoundFees(param);		
		return data;
	 }
	 
	@RequestMapping(value="viewFees")
	public String viewFees(HttpSession session, Map<String, Object> map) {
		map.put("user", (User)session.getAttribute("user"));
		map.put("feesList", feesService.listCompoundFees(null));
		return "viewFeeslist";	
	}
	
	@RequestMapping(value="viewFeesByUserId/{userId}")
	public String viewFeesByUserId(@PathVariable("userId")Integer userId, HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {		
		List<Fees> feesList = feesService.getFeesByUserId(userId);
		PagedListHolder pagedListHolder = new PagedListHolder(feesList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		map.put("pagedListHolder", pagedListHolder);
		
		map.put("feesList", feesList);
		CompoundFees compoundFees = feesService.getCompoundFees(userId);
		map.put("compoundFees", compoundFees);
		Fees fees = new Fees();
		fees.setUser(userService.getUserById(userId));
		map.put("fees", fees);
		return "viewFeesPage";
	}
	
	@RequestMapping(value="addFees")
	public String listFees(Map<String, Object> map , HttpServletRequest request, HttpServletResponse response) {
		
		List<Fees> feesList = feesService.getFeesByUserId(-1);
		PagedListHolder pagedListHolder = new PagedListHolder(feesList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		map.put("pagedListHolder", pagedListHolder);
		
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
			@PathVariable("userId")Integer userId,@PathVariable("feesId")Integer feesId, HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> map)
	{
		
		List<Fees> feesList = feesService.getFeesByUserId(userId);
		PagedListHolder pagedListHolder = new PagedListHolder(feesList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		map.put("pagedListHolder", pagedListHolder);
		
		map.put("action","edit");
		User user = userService.getUserById(userId);
		map.put("user", user);
		map.put("feesList", feesList);
		map.put("compoundFees", feesService.getCompoundFees(userId));
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
		List<CompoundFees> getUserByNextPaymentDate() {
			List<CompoundFees> feesList= new ArrayList<CompoundFees>();
			Data data = feesService.listCompoundFees(null);
			for (CompoundFees cf : (List<CompoundFees>)data.getData()) {
				if(Utils.checkDateBetweenCriteria(cf.getNextDueDate(), dueDateNotificationCriteria) && cf.getTotalPaidFees() > 0){
					feesList.add(cf);
				}
			}
			return feesList;

		}
		
		@RequestMapping("/deletefees/{feesId}")
		public @ResponseBody String deleteFees(
				@PathVariable("feesId") Integer feesId)
		{
			Fees fees = feesService.getFeesById(feesId);
			feesService.removeFees(fees);
			return "/sfm/viewFeesByUserId/"+fees.getUser().getId();
		}
}