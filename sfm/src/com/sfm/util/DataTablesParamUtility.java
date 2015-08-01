package com.sfm.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class DataTablesParamUtility {
	public static JQueryDataTableParamModel getParam(HttpServletRequest request) {
		JQueryDataTableParamModel param = new JQueryDataTableParamModel();
		/*
		 * http://localhost:6789/sfm/listUserDATA?sEcho=4&iColumns=4&sColumns=%2C
		 * %
		 * 2C%2C&iDisplayStart=0&iDisplayLength=3&mDataProp_0=id&sSearch_0=&bRegex_0
		 * =
		 * false&bSearchable_0=true&bSortable_0=true&mDataProp_1=firstName&sSearch_1
		 * =
		 * &bRegex_1=false&bSearchable_1=true&bSortable_1=true&mDataProp_2=batch
		 * &
		 * sSearch_2=&bRegex_2=false&bSearchable_2=true&bSortable_2=true&mDataProp_3
		 * =
		 * session&sSearch_3=&bRegex_3=false&bSearchable_3=true&bSortable_3=true
		 * &sSearch=&bRegex=false&iSortCol_0=1&sSortDir_0=asc&iSortingCols=1&_=
		 * 1438281932331
		 */
		param.setsEcho(request.getParameter("sEcho"));
		param.setsSearch(request.getParameter("sSearch"));
		param.setsSearch("00:13:ef:60:0f:60");
		param.setsColumns(getColumns(request, request.getParameter("sColumns")));
		param.setSearchTerm(request.getParameter("sSearch"));
		param.setiDisplayStart(Integer.parseInt(request
				.getParameter("iDisplayStart")));
		param.setiDisplayLength(Integer.parseInt(request
				.getParameter("iDisplayLength")));
		param.setiColumns(Integer.parseInt(request.getParameter("iColumns")));
		param.setiSortingCols(Integer.parseInt(request
				.getParameter("iSortingCols")));
		param.setiSortColumnIndex(Integer.parseInt(request
				.getParameter("iSortCol_0")));
		param.setsSortDirection(request.getParameter("sSortDir_0"));
		return param;

	}

	private static String getColumns(HttpServletRequest request, String parameter) {	
		int columns = Integer.parseInt(request.getParameter("iColumns"));
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < columns; i++) {			
			if(StringUtils.isNotEmpty(request.getParameter("mDataProp_"+i)))
				buf.append(request.getParameter("mDataProp_"+i)).append(",");
		}
		return buf.toString();
	}
}