package com.sfm.dao;

import java.io.Serializable;
import java.util.List;

import com.sfm.model.Fees;

public interface Dao<T, PK extends Serializable> {
    public void add(T t);
	public void update(T t);
	public List<T> list(Class< T > clazzToSet);
	public T getById(Integer id,Class< T > clazzToSet);
	public void remove(T t);
	public List<T> list(Integer deptId, Integer year, Integer semester,
			Class<T> class1);
	public List<Fees> listFees();
}
