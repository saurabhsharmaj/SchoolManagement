package com.sfm.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.sfm.model.Data;
import com.sfm.model.User;
import com.sfm.model.UserProfile;
import com.sfm.service.UserService;
import com.sfm.util.DataTablesParamUtility;
import com.sfm.util.JQueryDataTableParamModel;
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
	 
	 @Value("${TOTAL_FEES}")
	 private Double totalFees;
	 
	 
	 @Value("${Page_Size}")
	 private int PAGE_SIZE =10;
	  
	 @RequestMapping(value = "/getUserNames",  produces="application/json", method = RequestMethod.GET)
		public @ResponseBody
		List<User> getTags(@RequestParam String userName) {
		 	List<User> users= getSearchUserNames(userName);
			return users;

		}

	 @RequestMapping(value = "/listUserDATA",  produces="application/json", method = RequestMethod.GET)
		public @ResponseBody
		Data getUsers(HttpServletRequest request){
		 JQueryDataTableParamModel param = DataTablesParamUtility.getParam(request);		  
		 Data data = userService.listUsers(param);		
		return data;
	 }
		
	private List<User> getSearchUserNames(String userName) {
		return userService.listUsersByName(userName);
	}

	@RequestMapping(value="viewUserList")
	public String listRoutes(HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) {		
		return "viewUserList";
	}

	private void addStatisFields(Map<String, Object> map) {
		map.put("sessionYearList", Utils.stringToArray(yearProperties,"year"));
		map.put("batchList", Utils.stringToArray(batchProperty,"batch"));
		map.put("statusList", Utils.statusList());
		//userprofile
		map.put("roleList", Utils.roleList());
		map.put("streamList", Utils.stringToArray(stream,"stream"));
	}

	@RequestMapping(
			value = "/user/add",
			method = RequestMethod.POST
			)
	public String addRoute(@ModelAttribute("user") 
	User user, BindingResult result,@RequestParam("fileName")String fileName,@RequestParam("file") MultipartFile file) 
	{
		
		if(null == user.getId()) {
			UserProfile profile = user.getUserProfile();
			profile.setImageUrl(uploadImage(fileName,file));
			user.setUserProfile(profile);
			profile.setUser(user);
			userService.addUser(user);	
		}
		else {
			user.getUserProfile().setImageUrl(uploadImage(fileName,file));
			System.out.println("## u :"+user.getId() + "### Profile Id:"+user.getUserProfile().getId());
			user.getUserProfile().setUser(user);
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
		userService.removeUser(userService.getUserById(userId));
		return "redirect:/viewUserList";
	}

	@RequestMapping("/addUser")
	public String addUser(			
			Map<String, Object> map)
	{		
		addStatisFields(map);
		map.put("action","add");
		User user = new User();
		user.setStudentFees(totalFees);
		user.setStatus(0);
		UserProfile profile = new UserProfile();
		profile.setRoleId(2);
		user.setUserProfile(profile);
		map.put("user", user);			
		return "editUser";
	}

	@RequestMapping("/edituser/{userId}")
	public String editUser(
			@PathVariable("userId")Integer userId,
			Map<String, Object> map)
	{
		addStatisFields(map);		
		map.put("action","edit");
		User user = userService.getUserById(userId);
		System.out.println("#Profile:#"+user.getUserProfile().getImageUrl());
		map.put("user", userService.getUserById(userId));	

		return "editUser";
	}
}