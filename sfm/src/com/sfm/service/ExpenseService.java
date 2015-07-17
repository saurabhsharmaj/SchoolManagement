package com.sfm.service;

import java.util.List;

import com.sfm.model.Charges;



public interface ExpenseService {
	
	public void addCharges(Charges charges);
	public void updateCharges(Charges charges);
	public Charges getChargesById(Integer chargesId);
	public List<Charges> listCharges();
	public List<Charges> listCharges(Integer deptId, Integer year, Integer semester);
	public void removeCharges(Integer id);

}
