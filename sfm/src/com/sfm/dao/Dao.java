package com.sfm.dao;

import java.io.Serializable;
import java.util.List;

import com.sfm.model.Charges;
import com.sfm.model.CompoundExpenses;
import com.sfm.model.CompoundFees;
import com.sfm.model.Data;
import com.sfm.model.Faculty;
import com.sfm.model.Fees;
import com.sfm.model.User;
import com.sfm.util.JQueryDataTableParamModel;

public interface Dao<T, PK extends Serializable> {
    public void save(T t);	
	public List<T> list(Class< T > clazzToSet);
	public int listCount(Class< T > clazzToSet);
	public T getById(Integer id,Class< T > clazzToSet);
	public void remove(T t);
	public Data list(JQueryDataTableParamModel param, Class< T > clazzToSet);
	public List<Fees> listFees();
	
	public Data listCompoundFees(JQueryDataTableParamModel param);
	public List<Fees> listFeesByUserId(Integer userId);
	
	public List<CompoundExpenses> listCompoundExpenses();
	public List<Charges> getChargesByUserId(Integer userId);
	
	public List<User> listUsersByName(String userName);
	public CompoundFees getCompoundFeesByUserId(Integer id);
	
	public User validateUser(String username, String password);
	public List<Faculty> listAttendanceByFacultyId(Integer facultyId,Integer month,
			Class<Faculty> clazz);
	public void removeFeesByUserId(Integer userId);
	public void removeFacultyAttendance(Integer attendanceId);
}
