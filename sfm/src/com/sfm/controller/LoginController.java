package com.sfm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sfm.model.User;
import com.sfm.service.UserService;



@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;	
	
	@Value("${admin_username}")
	 private String adminUserName;
	
	@Value("${admin_password}")
	 private String adminPassword;
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String loginGET(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		return getLogin(error, logout);

	}
	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
	public String logoutGET(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		return  "redirect:/";

	}

	private String getLogin(String error, String logout) {
		ModelAndView model = new ModelAndView();
		
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("viewFeesPaymentList");

		return "redirect:viewFees";
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public String loginPost(HttpSession session, @RequestParam("username") String username,@RequestParam("password") String password) {
		User user = userService.validateUser(username,password);
		if(user!=null || (adminUserName.equals(username) && adminPassword.equals(password))){
			session.setAttribute("user", user);
			return "redirect:viewFees";
		} else {
			ModelAndView model = new ModelAndView();			
			model.addObject("error", "Invalid username and password!");		
			model.setViewName("viewFeesPaymentList");
			session.removeAttribute("user");
			session.invalidate();
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
		
			model.addObject("username", userDetail.getUsername());
			
		}
		
		model.setViewName("403");
		return model;

	}

}