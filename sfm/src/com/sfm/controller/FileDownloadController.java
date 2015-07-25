package com.sfm.controller;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lowagie.text.pdf.codec.Base64;
import com.sfm.model.User;
import com.sfm.service.ExpenseService;
import com.sfm.service.FeesService;
import com.sfm.service.UserService;
import com.sfm.util.PdfWriterUtil;
 
/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileDownloadController { 
	 
    private static final Logger logger = LoggerFactory
            .getLogger(FileDownloadController.class);
 
    @Autowired
	private UserService userService;
    
    @Autowired
	private FeesService feesService;
   
    @Autowired
    private ExpenseService expenseService;
    
    @RequestMapping(value = "/profile/{userId}")
    @ResponseBody
    public byte[] helloWorld(@PathVariable int userId) throws Exception  {
      User user = userService.getUserById(userId);
      String path = user.getUserProfile().getImageUrl();
      System.out.println("###########################path:"+path);
      if(user!=null & path!=null)
      {
    	  InputStream fis = FileDownloadController.class.getClassLoader().getResourceAsStream(path);    	  
    	  ByteArrayOutputStream bos=new ByteArrayOutputStream();
    	  int b;
    	  byte[] buffer = new byte[1024];
    	  while((b=fis.read(buffer))!=-1){
    	     bos.write(buffer,0,b);
    	  }
    	  byte[] fileBytes=bos.toByteArray();
    	  fis.close();
    	  bos.close();
    	  String encoded=Base64.encodeBytes(fileBytes);    	  
    	  return encoded.getBytes();
      }
	return null;
    }
    
    @RequestMapping(value = "/pdf/{report_name}/{userId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getPDF(HttpServletRequest request,@PathVariable("report_name") String reportName, @PathVariable("userId") Integer userId) throws Exception {
        
    	Map<Object, Object> params = new HashMap<Object, Object>();
    	params.put("test", "test");
    	getParamsByReport(userService.getUserById(userId), reportName,params);
       String logoURL = getContextPath(request,"/images/logo.png");
        FileInputStream fin = new FileInputStream(PdfWriterUtil.createPdf(reportName,logoURL,params));
        byte[] contents = IOUtils.toByteArray(fin);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = reportName+".pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
        return response;
    }


	private void getParamsByReport(User user, String reportName, Map<Object, Object> params) {
		if(reportName.equalsIgnoreCase("user_fees_report")){
			params.put("user", user);
            params.put("totalfees", user.getStudentFees());
            params.put("paymentList",  feesService.getFeesByUserId(user.getId()));
			params.put("compoundFees", feesService.getCompoundFees(user.getId()));
		} else if(reportName.equalsIgnoreCase("fees_report")){
			params.put("user", user);
			params.put("feesList", feesService.listCompoundFees());
		} else if(reportName.equalsIgnoreCase("user_exense_report")){
			params.put("user", user);
			params.put("expensesList", expenseService.getChargesByUserId(user.getId()));
		}
		
	}

	private String getContextPath(HttpServletRequest request, String resource) throws Exception {
		URL url = new URL(request.getRequestURL().toString());
		String host  = url.getHost();		
		String scheme = url.getProtocol();
		int port = url.getPort();
		
		
		return scheme+"://"+host+":"+port+"/sfm"+resource;
	}
}