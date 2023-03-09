package com.shalaka.quartzschedular;

import com.shalaka.quartzschedular.jobs.BirthdayNotificationJob;
import com.shalaka.quartzschedular.jobs.WorkAnniversaryNotificationJob;
import com.shalaka.quartzschedular.utils.JobUtility;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Component
public class AllJobsToSchedule {

	Logger logger = LoggerFactory.getLogger(AllJobsToSchedule.class);

	@Autowired
	private  Scheduler scheduler;

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Value("${birthday.notification.job.daily.schedule.hour}")
	private Integer hour;

	@Value("${birthday.notification.job.daily.schedule.minute}")
	private Integer minute;

	@Value("${birthday.notification.job.daily.schedule.second}")
	private Integer second;

	@Value("${work.notification.cron}")
	private String cronExpression;


	@EventListener({ ApplicationReadyEvent.class })
	public void onStartUp() {
		try {
			jobsToSchedule();
		}catch (Exception e){
			logger.error(e.getMessage());
		}

	}

	public void jobsToSchedule() throws SchedulerException {

		// Job 1
		birthdayNotificationJob();

		// Job 2
		workAnniversaryNotificationJob();
	}

	public void birthdayNotificationJob()  throws SchedulerException {
		JobDataMap map = new JobDataMap();
		map.put("subject","Birthday Wishes !!!");
		map.put("group","email-job");
		map.put(BirthdayNotificationJob.class.getSimpleName(),"");
		JobDetail jobDetail = JobUtility.buildJobDetail(BirthdayNotificationJob.class,map);

		Trigger trigger = JobUtility.buildJobTriggerDaily(jobDetail,
				Date.from(LocalDateTime.now().plusSeconds(5).atZone(ZoneId.systemDefault()).toInstant()),
				DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
						.withIntervalInHours(24).onEveryDay()
						.startingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(hour,minute,second)));
		//SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(10000).repeatForever());

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.scheduleJob(jobDetail, trigger);
	}

	public void workAnniversaryNotificationJob()  throws SchedulerException {
		JobDataMap map = new JobDataMap();
		map.put("group","email-job");
		map.put(WorkAnniversaryNotificationJob.class.getSimpleName(),"");
		JobDetail jobDetail = JobUtility.buildJobDetail(WorkAnniversaryNotificationJob.class,map);

		Trigger trigger = JobUtility.buildJobTriggerCron(jobDetail,
				Date.from(LocalDateTime.now().plusSeconds(5).atZone(ZoneId.systemDefault()).toInstant()),
				CronScheduleBuilder.cronSchedule(cronExpression));

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.scheduleJob(jobDetail, trigger);
	}



}