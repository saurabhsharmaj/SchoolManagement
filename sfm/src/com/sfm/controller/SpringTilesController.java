package com.sfm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class SpringTilesController {

	@RequestMapping(value="home")
	public ModelAndView home(Model model) {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("message", "m working!!");
		return mv;
	}
	

}