package com.sfm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfm.dao.Dao;
import com.sfm.model.Attendance;
import com.sfm.model.Faculty;

@Transactional
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

	@Override
	public void saveAttendance(Attendance attendance) {
		dao.save(attendance);
		
	}

	@Override
	public List<Faculty> listAttendanceByFaculty(Integer facultyId , Integer month) {
		// TODO Auto-generated method stub
		return dao.listAttendanceByFacultyId(facultyId, month, Attendance.class);
	}
}
