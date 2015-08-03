package com.sfm.service;

import java.util.List;

import com.sfm.model.Faculty;



public interface FacultyService {
	
	public void addFaculty(Faculty Faculty);
	public Faculty getFacultyById(Integer id);
	public void removeFaculty(Integer id);
	public List<Faculty> listFaculty();	

}
