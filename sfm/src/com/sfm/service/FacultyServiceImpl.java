package com.sfm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfm.dao.Dao;
import com.sfm.model.Faculty;

@Service
public class FacultyServiceImpl implements FacultyService {

	@Autowired 
	private Dao dao;
	
	@Override
	public void addFaculty(Faculty faculty) {
		dao.save(faculty);
	}

	@Override
	public Faculty getFacultyById(Integer id) {
		return (Faculty) dao.getById(id, Faculty.class);
	}

	@Override
	public void removeFaculty(Integer id) {
		dao.remove(id);
	}

	@Override
	public List<Faculty> listFaculty() {
		return dao.list(Faculty.class);
	}

}
