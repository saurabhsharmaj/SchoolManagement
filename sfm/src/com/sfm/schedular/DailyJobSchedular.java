package com.sfm.schedular;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DailyJobSchedular implements Job {

    @Override
    public void execute(final JobExecutionContext ctx)
            throws JobExecutionException {
        System.out.println("Executing Job"+Calendar.getInstance().getTimeInMillis());

    }

}