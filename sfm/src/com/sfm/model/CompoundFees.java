package com.sfm.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CompoundFees {

	private Integer id;
	private String fullName;
	private String fatherName;
	private Double totalFees;
	private Double totalExpenses;
	private Double totalPaidFees;
	private Double totalAdditionCharges;
	private Double totalPendingFees;
	private Date nextDueDate;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)	
	public Integer getId() {
		return this.id;
	}
		
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Double getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(Double totalFees) {
		this.totalFees = totalFees;
	}

	public Double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public Double getTotalPaidFees() {
		return totalPaidFees;
	}

	public void setTotalPaidFees(Double totalPaidFees) {
		this.totalPaidFees = totalPaidFees;
	}

	public Double getTotalPendingFees() {
		return totalPendingFees;
	}

	public void setTotalPendingFees(Double totalPendingFees) {
		this.totalPendingFees = totalPendingFees;
	}

	public Date getNextDueDate() {
		return nextDueDate;
	}

	public void setNextDueDate(Date nextDueDate) {
		this.nextDueDate = nextDueDate;
	}
	
	

	public Double getTotalAdditionCharges() {
		return totalAdditionCharges;
	}

	public void setTotalAdditionCharges(Double totalAdditionCharges) {
		this.totalAdditionCharges = totalAdditionCharges;
	}

	@Override
	public String toString() {
		return "CompoundFees [id=" + id + ", totalFees=" + totalFees
				+ ", totalExpenses=" + totalExpenses + ", totalPaidFees="
				+ totalPaidFees + ", totalPendingFees=" + totalPendingFees
				+ ", nextDueDate=" + nextDueDate + "]";
	}

	



	
}
