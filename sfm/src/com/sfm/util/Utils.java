package com.sfm.util;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
 
 
public class Utils {
	
	static Map<Integer,String> streamMap = new HashMap<Integer,String>();
	static Map<Integer,String> batchMap = new HashMap<Integer,String>();
	static Map<Integer,String> sessionMap = new HashMap<Integer,String>();
	
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
			} else if(key.equalsIgnoreCase("session")){
				sessionMap.put(i, value[i]);
			} else if(key.equalsIgnoreCase("batch")){
				batchMap.put(i, value[i]);
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
		if(StringUtils.isEmpty(id)){
			return id;
		}
		return streamMap.get(new Integer(id));
	}
	
	public static String getExpenseTypeName(String id) {
		// ARTS,COMMERCE,SCIENCE,OTHER		
		return streamMap.get(new Integer(id));
	}

	public static String getBatchName(String batch) {
		if(StringUtils.isEmpty(batch)){
			return batch;
		}
		return batchMap.get(new Integer(batch));
	}

	public static String getSessionName(String session) {
		if(StringUtils.isEmpty(session)){
			return session;
		}
		return sessionMap.get(new Integer(session));
	}

	public static Double getValue(Double value) {
		return (value==null)?0:value.doubleValue();
	}
	
	public static String getContextPath(HttpServletRequest request, String resource) throws Exception {
		URL url = new URL(request.getRequestURL().toString());
		String host  = url.getHost();		
		String scheme = url.getProtocol();
		int port = url.getPort();
		
		
		return scheme+"://"+host+":"+port+"/sfm"+resource;
	}

	public static boolean checkDateBetweenCriteria(Date nextDueDate,
			Integer dueDateNotificationCriteria) {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, dueDateNotificationCriteria);		
		return nextDueDate.after(new Date()) && nextDueDate.before(date.getTime());
	}	
}