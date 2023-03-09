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
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class WorkAnniversaryNotificationJob implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(WorkAnniversaryNotificationJob.class);

    @Autowired
    SendEmailNotification emailNotification;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private Map<Integer, Employee> getEmployees;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("WorkAnniversaryNotificationJob Start................");

        try {
            Date date1 = CommonConstants.getSimpleDateFormat().parse(CommonConstants.getSimpleDateFormat().format(new Date()));
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            for (Map.Entry<Integer, Employee> mapEntry : getEmployees.entrySet()) {
                Date date2 = mapEntry.getValue().getWorkJoiningDate();
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(date2);

                if (calendar2.get(Calendar.MONTH) == calendar1.get(Calendar.MONTH)
                        && calendar2.get(Calendar.DATE) == calendar1.get(Calendar.DATE)) {
                    JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
                    String subject = "Work Anniversary Wishes !!!";
                    emailNotification.sendMail(mailProperties.getUsername(), mapEntry.getValue().getEmail(), subject,
                            "Dear "+mapEntry.getValue().getName()+", <br><br>" + "Happy Work Anniversary !!");
                    LOG.info(" Email sent : To  " +mapEntry.getValue().getName() );
                }


            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        LOG.info("WorkAnniversaryNotificationJob End................");
    }
}
