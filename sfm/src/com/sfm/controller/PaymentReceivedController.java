package com.sfm.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfm.model.User;

@Controller
public class PaymentReceivedController {
	
	
		
	@RequestMapping(value="paymentRecived")
	public String listRoutes(Map<String, Object> map) {
		map.put("user", new User());	
		return "paymentRecived";
	}	
}