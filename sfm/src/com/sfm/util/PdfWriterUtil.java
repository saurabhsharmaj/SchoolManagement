package com.sfm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.PdfWriter;

/**
 * The Class PdfWriterUtil.
 * 
 * @author nagarro
 */
public final class PdfWriterUtil {

    /* logger */
    private static final Logger logger = LoggerFactory.getLogger(PdfWriterUtil.class);

    /**
     * Default constructor. Instantiates a new PDF writer UTIL.
     */
    private PdfWriterUtil() {

    }   

    /**
     * Gets the html content.
     * 
     * @param content
     *            the content
     * @param logoUrl
     *            the logo url
     * @param templateName
     *            the template name
     * @return the terms n condition html data
     * @throws Exception 
     * @throws ParseErrorException 
     * @throws ResourceNotFoundException 
     */
    private static String getHTMLData(final String reportName,final String logoURL, final Map<Object, Object> params)
            throws Exception {
       
        String htmlTemplate = getHtmlTemplate(reportName);
        return VelocityUtil.velocityEval(htmlTemplate,logoURL, params);
    }

    /**
     * Gets the terms n conditions html template.
     * 
     * @param templateName
     *            the template file name
     * @return the terms n conditions template
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private static String getHtmlTemplate(final String reportName) throws IOException {
    	InputStream is = null;
    	String htmlTemplate = null;
    	try {
    		is = PdfWriterUtil.class.getClassLoader().getResourceAsStream(getTemplate(reportName));//new FileInputStream(new File(getTemplate(reportName)));
    		StringWriter writer = new StringWriter();
        	IOUtils.copy(is, writer, "UTF-8");
    		htmlTemplate = writer.toString();
    	} finally{
    		if(is != null) {
    			is.close();
    		}
    	}
    	return htmlTemplate;
    }

    private static String getTemplate(String reportName) {
		return "/vm/"+reportName+".vm";
	}

	/**
     * 
     * @param reportName
     * @param params
     * @return
     */
	public static File createPdf(String reportName,String logoURL, Map<Object, Object> params) throws Exception{

        Document document = new Document();
        File file = null;
        FileOutputStream fileOutputStream = null;
        try {
        	file = File.createTempFile("tempPdf",".pdf");
        	fileOutputStream = new FileOutputStream(file);
        	PdfWriter.getInstance(document, fileOutputStream);
        	document.open();

        	StringReader stringReader = new StringReader(getHTMLData(reportName,logoURL,params));
        	
        	HTMLWorker worker = new HTMLWorker(document);
        	
        	StyleSheet styles = new StyleSheet();  
        	
        	styles.loadTagStyle("span", "style", "font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";color:#222222"); 
        	styles.loadTagStyle("p", "after", "4");
        	
        	worker.setStyleSheet(styles);  
        	worker.parse(stringReader);

        	
        } catch (Exception e) {
            logger.error("Error while creating PDF", e);
            throw e;
        } finally {
        	if(document != null){
        		document.close();
        	}
        	
        	if(fileOutputStream != null){
        		fileOutputStream.close();
        	}
        }
        return file;
    
	}

}
