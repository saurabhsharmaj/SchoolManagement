package com.sfm.dao;

import java.io.Serializable;
import java.util.List;

import com.sfm.model.Charges;
import com.sfm.model.CompoundExpenses;
import com.sfm.model.CompoundFees;
import com.sfm.model.Fees;
import com.sfm.model.User;

public interface Dao<T, PK extends Serializable> {
    public void add(T t);
	public void update(T t);
	public List<T> list(Class< T > clazzToSet);
	public T getById(Integer id,Class< T > clazzToSet);
	public void remove(T t);
	public List<T> list(Integer deptId, Integer year, Integer semester,
			Class<T> class1);
	public List<Fees> listFees();
	
	public List<CompoundFees> listCompoundFees();
	public List<Fees> listFeesByUserId(Integer userId);
	
	public List<CompoundExpenses> listCompoundExpenses();
	public List<Charges> getChargesByUserId(Integer userId);
	
	public List<User> listUsersByName(String userName);
	public CompoundFees getCompoundFees(Integer id);
	
	public List<Object[]> getUserByNextPaymentDate();
	public User validateUser(String username, String password);
}
