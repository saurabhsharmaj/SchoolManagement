package com.sfm.util;

import java.util.Comparator;

import com.sfm.model.CompoundFees;

public class FeesComparator implements Comparator<CompoundFees>{

	@Override
	public int compare(CompoundFees o1, CompoundFees o2) {
		
		return o1.getNextDueDate().compareTo(o2.getNextDueDate());
	}

}
