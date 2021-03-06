package com.sfm.service;

import java.util.List;

import com.sfm.model.CompoundFees;
import com.sfm.model.Data;
import com.sfm.model.Fees;
import com.sfm.util.JQueryDataTableParamModel;



public interface FeesService {
	
	public void addFees(Fees fees);
	public void updateFees(Fees fees);
	public Fees getFeesById(Integer feesId);
	public List<Fees> getFeesByUserId(Integer userId);
	public List<Fees> listFees();
	public void removeFees(Fees id);
	public CompoundFees getCompoundFees(Integer id);
	public Data listCompoundFees(JQueryDataTableParamModel param);
	public void removeFeesByUserId(Integer userId);
}
