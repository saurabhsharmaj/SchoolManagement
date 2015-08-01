package com.sfm.model;

public class Data {
	Object data;
	int iTotalRecords;
	int iTotalDisplayRecords;
	
	public Data(){

	}
	
	public Data(Object data,int filterResultSize, int length) {
		super();
		this.data = data;		
		System.out.println("lenght: "+length+" filter length:"+ filterResultSize);
		iTotalDisplayRecords = filterResultSize;
		iTotalRecords = length;
		
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	
}
