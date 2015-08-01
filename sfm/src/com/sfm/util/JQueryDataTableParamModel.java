package com.sfm.util;

public class JQueryDataTableParamModel {

	private String sEcho;
	private String sSearch;
	private String sColumns;
	private int iDisplayStart;
	private int iDisplayLength;
	private int iColumns;
	private int iSortingCols;
	private int iSortColumnIndex;
	private String sSortDirection;	
	private String searchTerm;
	
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public String getsColumns() {
		return sColumns;
	}
	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}
	public int getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public int getiColumns() {
		return iColumns;
	}
	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}
	public int getiSortingCols() {
		return iSortingCols;
	}
	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}
	public int getiSortColumnIndex() {
		return iSortColumnIndex;
	}
	public void setiSortColumnIndex(int iSortColumnIndex) {
		this.iSortColumnIndex = iSortColumnIndex;
	}
	public String getsSortDirection() {
		return sSortDirection;
	}
	public void setsSortDirection(String sSortDirection) {
		this.sSortDirection = sSortDirection;
	}
	
	
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	@Override
	public String toString() {
		return "JQueryDataTableParamModel [sEcho=" + sEcho + ", sSearch="
				+ sSearch + ", sColumns=" + sColumns + ", iDisplayStart="
				+ iDisplayStart + ", iDisplayLength=" + iDisplayLength
				+ ", iColumns=" + iColumns + ", iSortingCols=" + iSortingCols
				+ ", iSortColumnIndex=" + iSortColumnIndex
				+ ", sSortDirection=" + sSortDirection + ", searchTerm="
				+ searchTerm + "]";
	}
	
	
}
