package com.sfm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfm.model.User;
import com.sfm.model.Userprofile;
import com.sfm.service.UserService;
import com.sfm.util.Utils;

@Controller
public class UserProfileController {
	
	@Autowired
	private UserService userService;
		
	@RequestMapping(value="editProfile/{userId}")
	public String editProfile(
			@PathVariable("userId")Integer userId,
			Map<String, Object> map){
		User user= userService.getUserById(userId);
		map.put("user", user);
		map.put("userId", userId);
		map.put("roleList", Utils.roleList());
		map.put("userProfile", new Userprofile());
		return "addUserProfile";
	}	
}