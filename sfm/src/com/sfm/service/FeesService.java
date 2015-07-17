package com.sfm.service;

import java.util.List;

import com.sfm.model.Fees;



public interface FeesService {
	
	public void addFees(Fees fees);
	public void updateFees(Fees fees);
	public Fees getFeesById(Integer feesId);
	public List<Fees> listFees();
	public List<Fees> listFees(Integer deptId, Integer year, Integer semester);
	public void removeFees(Integer id);

}
