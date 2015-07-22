package com.sfm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfm.dao.Dao;
import com.sfm.model.CompoundFees;
import com.sfm.model.Fees;



@Service
public class FeesServiceImpl implements FeesService {

	@Autowired 
	private Dao dao;
	
	@Transactional
	public void addFees(Fees fees) {
		dao.add(fees);
	}

	@Transactional
	public void removeFees(Integer id) {
		dao.remove(id);
	}

	@Transactional
	public List<Fees> listFees() {
		return dao.listFees();
	}

	@Transactional
	public void updateFees(Fees fees) {
		dao.update(fees);
	}

	@Transactional
	public Fees getFeesById(Integer feesId) {
		return (Fees) dao.getById(feesId,Fees.class);
	}

	@Override
	public List<Fees> listFees(Integer deptId, Integer year, Integer semester) {
		return dao.list(deptId, year, semester,Fees.class);
	}

	@Override
	public List<CompoundFees> listCompoundFees() {
		return dao.listCompoundFees();
	}
}