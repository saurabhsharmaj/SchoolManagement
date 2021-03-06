package com.sfm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfm.dao.Dao;
import com.sfm.model.CompoundFees;
import com.sfm.model.Data;
import com.sfm.model.Fees;
import com.sfm.util.JQueryDataTableParamModel;


@Transactional
@Service
public class FeesServiceImpl implements FeesService {

	@Autowired 
	private Dao dao;
	
	@Transactional
	public void addFees(Fees fees) {
		//update all nextpaymentDueDate Null.		
		dao.setNullNextPaymentDate(fees.getUser());
		dao.save(fees);
	}

	@Transactional
	public void removeFees(Fees id) {
		dao.remove(id);
	}

	@Transactional
	public List<Fees> listFees() {
		return dao.listFees();
	}

	@Transactional
	public void updateFees(Fees fees) {
		dao.save(fees);
	}

	@Transactional
	public Fees getFeesById(Integer feesId) {
		return (Fees) dao.getById(feesId,Fees.class);
	}	

	@Override
	public List<Fees> getFeesByUserId(Integer userId) {		
		return dao.listFeesByUserId(userId);
	}

	@Override
	public CompoundFees getCompoundFees(Integer id) {
		return dao.getCompoundFeesByUserId(id);
	}

	@Override
	public Data listCompoundFees(JQueryDataTableParamModel param) {
		return dao.listCompoundFees(param);
	}

	@Override
	public void removeFeesByUserId(Integer userId) {
		dao.removeFeesByUserId(userId);		
	}
}