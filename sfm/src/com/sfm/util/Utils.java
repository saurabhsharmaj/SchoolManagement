package com.sfm.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.taglibs.standard.extra.spath.Step;
 
 
public class Utils {
	
	static Map<Integer,String> streamMap = new HashMap<Integer,String>();
	
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
	
	public static Map<Integer,String> stringToArray(String property,String key){
		Map<Integer,String> installments = new LinkedHashMap<Integer,String>();
		String []value = property.split(",");
		for (int i = 0 ;i < value.length ; i++) {			
			if(key.equalsIgnoreCase("stream")){
				streamMap.put(i, value[i]);
			} else {
				installments.put(i, value[i]);
			}
			
		}
		if(key.equalsIgnoreCase("stream")){
			return streamMap;
		}
		return installments;
	}

	public static String getStreamName(String id) {
		// ARTS,COMMERCE,SCIENCE,OTHER		
		return "arts";// streamMap.get(new Integer(id));
	}
}