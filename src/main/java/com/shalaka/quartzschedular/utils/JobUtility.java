package com.shalaka.quartzschedular.utils;

import org.quartz.*;

import java.time.ZonedDateTime;
import java.util.Date;

public final class JobUtility {

    private JobUtility(){

    }

    public static JobDetail buildJobDetail(final Class jobClass, final JobDataMap jobDataMap) {
        return JobBuilder
                .newJob(jobClass)
                .withIdentity(jobClass.getSimpleName(), jobDataMap.getString("group"))
                .setJobData(jobDataMap)
                .build();
    }

    // Daily Interval trigger
    public static Trigger buildJobTriggerDaily(JobDetail jobDetail, Date startAt, DailyTimeIntervalScheduleBuilder builder) {

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName())
                .startAt(startAt)
                .withSchedule(builder)
                .build();

    }

    // Cron interval trigger
    public static Trigger buildJobTriggerCron(JobDetail jobDetail, Date startAt, CronScheduleBuilder builder) {

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName())
                .startAt(startAt)
                .withSchedule(builder)
                .build();

    }
}


