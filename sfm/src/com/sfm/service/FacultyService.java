package com.sfm.service;

import java.util.List;

import com.sfm.model.Attendance;
import com.sfm.model.Faculty;



public interface FacultyService {
	
	public void addFaculty(Faculty Faculty);
	public Faculty getFacultyById(Integer id);
	public void removeFaculty(Faculty id);
	public List<Faculty> listFaculty();
	public void saveAttendance(Attendance attendance);
	public List<Faculty> listAttendanceByFaculty(Integer facultyId, Integer month);
	public void removeFacultyAttendance(Integer attendanceId);
	public Attendance getAttendanceById(Integer attendanceId);	

}
