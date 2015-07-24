package com.sfm.util;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.generic.DateTool;

public class VelocityUtil {

	public static String velocityEval(String template,String logoURL,
			Map<Object, Object> parameters) throws ResourceNotFoundException, ParseErrorException, Exception {

		if (template == null || template.trim().isEmpty() || parameters == null || parameters.isEmpty()) {
			return null;
		} else {
			StringWriter writer = new StringWriter();
			try {
				VelocityContext context = new VelocityContext();
				
				Set<Object> keySet = parameters.keySet();
				Iterator<Object> iter = keySet.iterator();
				while (iter.hasNext()) {
					Object key = iter.next();
					Object value = parameters.get(key);
					context.put(key.toString(), value);
				}
				context.put("logoURL", logoURL);
				context.put("date", new DateTool());
				Velocity.init();
				Velocity.evaluate(context, writer, "convertString", template);
			} catch (Exception e) {
				System.out.printf("Error while processing input-string-" + template + " ", e);
			}
			return writer.getBuffer().toString();
		}
	
	}

}
