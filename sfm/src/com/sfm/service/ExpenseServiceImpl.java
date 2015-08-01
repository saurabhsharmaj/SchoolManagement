package com.sfm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfm.dao.Dao;
import com.sfm.model.Charges;
import com.sfm.model.CompoundExpenses;



@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired 
	private Dao dao;
	
	@Transactional
	public void addCharges(Charges charges) {
		dao.add(charges);
	}

	@Transactional
	public void removeCharges(Integer id) {
		dao.remove(id);
	}

	@Transactional
	public List<Charges> listCharges() {
		return dao.list(Charges.class);
	}

	@Transactional
	public void updateCharges(Charges charges) {
		dao.update(charges);
	}

	@Transactional
	public Charges getChargesById(Integer chargesId) {
		return (Charges) dao.getById(chargesId,Charges.class);
	}	

	@Override
	public List<CompoundExpenses> listCompoundExpenses() {
		return dao.listCompoundExpenses();
	}

	@Override
	public List<Charges> getChargesByUserId(Integer userId) {
		return dao.getChargesByUserId(userId);
	}
}