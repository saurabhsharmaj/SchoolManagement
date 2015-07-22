package com.sfm.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sfm.model.User;
import com.sfm.model.UserProfile;
import com.sfm.service.UserService;
import com.sfm.util.Utils;

@Controller
public class UserController {

	 private static final Logger logger = LoggerFactory
	            .getLogger(UserController.class);
	 
	@Autowired
	private UserService userService;
	
	 @Value("${SESSION_YEAR}")
	 private String yearProperties;
	
	 @Value("${BATCH_NAME}")
	 private String batchProperty;
	 
	 @Value("${STREAM}")
	 private String stream;
	 
	  
	 @RequestMapping(value = "/getUserNames",  produces="application/json", method = RequestMethod.GET)
		public @ResponseBody
		List<User> getTags(@RequestParam String userName) {
		 	List<User> users= getSearchUserNames(userName);
			return users;

		}

	 
		
	private List<User> getSearchUserNames(String userName) {
		return userService.listUsersByName(userName);
	}

	@RequestMapping(value="viewUserList")
	public String listRoutes(Map<String, Object> map) {
		map.put("userList", userService.listUsers());
		return "viewUserList";
	}

	private void addStatisFields(Map<String, Object> map) {
		map.put("sessionYearList", Utils.stringToArray(yearProperties));
		map.put("batchList", Utils.stringToArray(batchProperty));
		map.put("statusList", Utils.statusList());
		//userprofile
		map.put("roleList", Utils.roleList());
		map.put("streamList", Utils.stringToArray(stream));
	}

	@RequestMapping(
			value = "/user/add",
			method = RequestMethod.POST
			)
	public String addRoute(@ModelAttribute("user") 
	User user, BindingResult result,@RequestParam("fileName")String fileName,@RequestParam("file") MultipartFile file) 
	{
		UserProfile profile = user.getUserProfile();
		profile.setImageUrl(uploadImage(fileName,file));
		user.setUserProfile(profile);
		profile.setUser(user);
		if(null == user.getId()) {
			userService.addUser(user);	
		}
		else {
			userService.updateUser(user);
		}
		return "redirect:/viewUserList";
	}

	private String uploadImage(String fileName, MultipartFile file) {
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "webapps/sfm/tmpFiles");
                System.out.println("------------------------> "+dir.getAbsolutePath());
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
 
                return "/tmpFiles/"+fileName;
            } catch (Exception e) {
            	logger.error("You failed to upload " + fileName + " => " + e);
            }
        } else {
        	logger.info("You failed to upload " + fileName
                    + " because the file was empty.");
        }
		return "";
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