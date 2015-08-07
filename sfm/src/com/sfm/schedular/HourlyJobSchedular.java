package com.sfm.schedular;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HourlyJobSchedular implements Job {

    @Override
    public void execute(final JobExecutionContext ctx)
            throws JobExecutionException {
        String path = backupDataWithOutDatabase("mysqldump","localhost","3306","teneqs","teneqs","sfm","c:/");
    }

    public static String backupDataWithOutDatabase(String dumpExePath, String host, String port, String user, String password, String database, String backupPath) {
    	
    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	Date date = new Date();
    	String filepath = database  + "-(" + dateFormat.format(date) + ").sql";
    	
    	try {
    	Process p = null;   	
    	 
    	String batchCommand = "";
    	if (password != "") {
    	//only backup the data not included create database
    	batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --password=" + password + " " + database + " > \"" + backupPath + "" + filepath + "\"";
    	} else {
    	batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " " + database + " > \"" + backupPath + "" + filepath + "\"";
    	}
    	 
    	Runtime runtime = Runtime.getRuntime();
    	p = runtime.exec(new String[] { "cmd.exe", "/c", batchCommand });
    	int processComplete = p.waitFor();
    	 
    	if (processComplete == 0) {    	
    		System.out.println("Backup created successfully for without DB " + database + " in " + host + ":" + port);
    	} else {    	
    		System.out.println("Could not create the backup for without DB " + database + " in " + host + ":" + port);
    	}
    	 
    	} catch (IOException ioe) {
    		ioe.printStackTrace();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return backupPath + "" + filepath;
    	}
}