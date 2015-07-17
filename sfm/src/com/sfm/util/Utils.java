package com.sfm.util;

import java.util.LinkedHashMap;
import java.util.Map;
 
 
public class Utils {
	
	public static Map<Integer,StatusEnum> statusList(){
		Map<Integer,StatusEnum> semester = new LinkedHashMap<Integer,StatusEnum>();
		semester.put(0, StatusEnum.ACTIVE);
		semester.put(1,StatusEnum.DEACTIVE);		
		return semester;
	}

	public static Map<Integer,String> roleList(){
		Map<Integer,String> semester = new LinkedHashMap<Integer,String>();
		semester.put(1, "Admin");
		semester.put(2,"Student");		
		return semester;
	}
	
	public static Map<Integer,String> stringToArray(String property){
		Map<Integer,String> installments = new LinkedHashMap<Integer,String>();
		String []value = property.split(",");
		for (int i = 0 ;i < value.length ; i++) {			
			installments.put(i, value[i]);
		}		
		return installments;
	}
}