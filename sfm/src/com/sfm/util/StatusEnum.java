package com.sfm.util;

public enum StatusEnum {
	ACTIVE(true),DEACTIVE(false);

	private Boolean status;
	private String StatusDesc;
	
	StatusEnum(Boolean status){
		this.status = true;
		this.StatusDesc = status?"ACTIVE":"DEACTIVE";
	}

	public Boolean getStatus(){
		return this.status;
	}

	public String statusDesc(){
		return this.StatusDesc;
	}

}
