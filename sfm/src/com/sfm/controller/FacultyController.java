package com.sfm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfm.model.Faculty;
import com.sfm.service.FacultyService;


@Controller
public class FacultyController {

	 private static final Logger logger = LoggerFactory
	            .getLogger(FacultyController.class);
	 
	@Autowired
	private FacultyService facultyService;
	
	 
	  
	 @RequestMapping(value = "/getFacultyName",  produces="application/json", method = RequestMethod.GET)
		public @ResponseBody
		List<Faculty> getFaculties() {
		 	List<Faculty> faculties= facultyService.listFaculty();
			return faculties;

		}

	
}