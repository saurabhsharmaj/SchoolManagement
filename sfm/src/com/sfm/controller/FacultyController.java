package com.sfm.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfm.model.Attendance;
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


	@RequestMapping(value = "/getAttendanceByFacultyId/{facultyId}",  produces="application/json", method = RequestMethod.POST)
	public @ResponseBody
	List<Faculty> listAttendanceByFacultyId(@PathVariable("facultyId")Integer facultyId) {
		List<Faculty> faculties= facultyService.listAttendanceByFaculty(facultyId);
		return faculties;

	}
	
	@RequestMapping(value="/saveAttendance/{id}",  produces="application/json", method = RequestMethod.POST)
	public @ResponseBody List<Faculty> editUser(
			@PathVariable("id")Integer facultyId,
			HttpServletRequest request, HttpServletResponse response,Map<String, Object> map)
			{
				String id =  request.getParameter("id");		
				String attendanceDate = request.getParameter("attendanceDate");
				String noOfHours = request.getParameter("noOfHours");
				System.out.println("faculty Id:"+id +" noOfHour:"+noOfHours);
				Faculty faculty = facultyService.getFacultyById(facultyId);
				Attendance attendance = new Attendance(id,faculty, new Date(attendanceDate),new Double(noOfHours));				
				facultyService.saveAttendance(attendance);				
				return listAttendanceByFacultyId(facultyId);
			}


}