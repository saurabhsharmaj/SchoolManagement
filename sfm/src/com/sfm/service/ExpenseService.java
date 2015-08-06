package com.sfm.service;

import java.util.List;

import com.sfm.model.Charges;
import com.sfm.model.CompoundExpenses;



public interface ExpenseService {
	
	public void addCharges(Charges charges);
	public void updateCharges(Charges charges);
	public Charges getChargesById(Integer chargesId);
	public List<Charges> listCharges();	
	public void removeCharges(Charges id);
	public List<CompoundExpenses> listCompoundExpenses();
	public List<Charges> getChargesByUserId(Integer userId);

}
