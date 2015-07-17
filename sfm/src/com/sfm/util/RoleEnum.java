package com.sfm.util;

public enum RoleEnum {
	ADMIN(1),STUDENT(2);

	private Integer roleId;
	private String roleName;
	
	RoleEnum(Integer id){
		this.roleId = id;
		this.roleName = id==1?"Admin":"Student";
	}

	public Integer getRoleId(){
		return this.roleId;
	}

	public String roleName(){
		return this.roleName;
	}

}
