package com.sfm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sfm.model.User;
import com.sfm.service.UserService;
import com.sfm.util.Utils;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	 @Value("${SESSION_YEAR}")
	 private String yearProperties;
	
	 @Value("${BATCH_NAME}")
	 private String batchProperty;
	 
	@RequestMapping(value="viewUserList")
	public String listRoutes(Map<String, Object> map) {
		map.put("userList", userService.listUsers());
		return "viewUserList";
	}

	private void addStatisFields(Map<String, Object> map) {
		map.put("sessionYearList", Utils.stringToArray(yearProperties));
		map.put("batchList", Utils.stringToArray(batchProperty));
		map.put("statusList", Utils.statusList());
	}

	@RequestMapping(
			value = "/user/add",
			method = RequestMethod.POST
			)
	public String addRoute(@ModelAttribute("user") 
	User user, BindingResult result) 
	{
		if(null == user.getId()) {
			userService.addUser(user);	
		}
		else {
			userService.updateUser(user);
		}
		return "redirect:/viewUserList";
	}

	@RequestMapping("/deleteuser/{userId}")
	public String deleteUser(
			@PathVariable("userId") Integer userId)
	{
		userService.removeUser(userId);
		return "redirect:/viewUserList";
	}

	@RequestMapping("/addUser")
	public String addUser(			
			Map<String, Object> map)
	{		
		addStatisFields(map);
		map.put("action","add");
		map.put("user", new User());			
		return "editUser";
	}

	@RequestMapping("/edituser/{userId}")
	public String editUser(
			@PathVariable("userId")Integer userId,
			Map<String, Object> map)
	{
		addStatisFields(map);		
		map.put("action","edit");
		map.put("user", userService.getUserById(userId));	

		return "editUser";
	}
}