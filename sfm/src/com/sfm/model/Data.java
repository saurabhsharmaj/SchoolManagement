package com.sfm.model;

public class Data {
	Object data;
	int length;
	int recordsTotal;
	
	public Data(){

	}
	
	public Data(Object data, int length) {
		super();
		this.data = data;
		this.length = length;
		this.recordsTotal=length;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	
}
