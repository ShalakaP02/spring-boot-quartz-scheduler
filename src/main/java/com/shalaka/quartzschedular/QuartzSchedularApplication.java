package com.shalaka.quartzschedular;

import com.shalaka.quartzschedular.constants.CommonConstants;
import com.shalaka.quartzschedular.jobs.BirthdayNotificationJob;
import com.shalaka.quartzschedular.model.Employee;
import com.shalaka.quartzschedular.utils.JobUtility;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class QuartzSchedularApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzSchedularApplication.class, args);

	}

	@Bean()
	public Map<Integer, Employee> getEmployees() throws SchedulerException {
		Map<Integer, Employee> hmap = new HashMap<>();
		try {

			hmap.put(1, new Employee(1,
					"Joe",
					CommonConstants.getSimpleDateFormat().parse("2023-03-09"),
					CommonConstants.getSimpleDateFormat().parse("2022-03-08"),
					"<joe>@gmail.com"));
			hmap.put(2, new Employee(2,
					"John",
					CommonConstants.getSimpleDateFormat().parse("2023-03-08"),
					CommonConstants.getSimpleDateFormat().parse("2022-03-09"),
					"<john>@gmail.com"));
		}catch (Exception e){}

		return hmap;
	}

//	@Bean()
//	public Scheduler scheduler(SchedulerFactoryBean factory) throws SchedulerException {
//		Scheduler scheduler = factory.getScheduler();
//		scheduler.start();
//		return scheduler;
//	}





}


