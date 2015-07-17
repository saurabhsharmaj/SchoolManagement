package com.sfm.util;

public enum BatchEnum {
	FIRST(1),SECOND(2),THRID(3),FOURTH(4),FIFTH(5);
	
	private Integer id;
	private String batchName;
	
	BatchEnum(Integer id){
		this.id = id;
		switch (id) {
		case 1:
			batchName="First";
			break;
		case 2:
			batchName="Second";
			break;
		case 3:
			batchName="Third";
			break;
		case 4:
			batchName="Fourth";
			break;
		case 5:
			batchName="Fifth";
			break;		
		}
	}

	public Integer getId(){
		return this.id;
	}

	public String batchName(){
		return this.batchName;
	}


}
