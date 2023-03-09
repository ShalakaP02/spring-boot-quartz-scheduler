package com.shalaka.quartzschedular.jobs;

import com.shalaka.quartzschedular.constants.CommonConstants;
import com.shalaka.quartzschedular.model.Employee;
import com.shalaka.quartzschedular.utils.SendEmailNotification;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BirthdayNotificationJob  implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(BirthdayNotificationJob.class);

    @Autowired
    SendEmailNotification emailNotification;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private Map<Integer, Employee> getEmployees;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("BirthdayNotificationJob Start................");

        try {

            Date date1 = CommonConstants.getSimpleDateFormat().parse(CommonConstants.getSimpleDateFormat().format(new Date()));
            for (Map.Entry<Integer,Employee> mapEntry : getEmployees.entrySet()) {
                Date date2 = mapEntry.getValue().getBirthDate();
                if(date1.equals(date2)){
                    JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
                    String subject = jobDataMap.getString("subject");
                    emailNotification.sendMail(mailProperties.getUsername(), mapEntry.getValue().getEmail(), subject,
                            "Dear "+mapEntry.getValue().getName()+", <br><br>" + "Many Many Happy Returns Of The Day !!");
                    LOG.info(" Email sent : To  " +mapEntry.getValue().getName() );
                }
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        LOG.info("BirthdayNotificationJob End................");
    }
}
