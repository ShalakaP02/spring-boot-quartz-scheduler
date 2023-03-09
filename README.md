# spring-boot-quartz-scheduler
**Quartz Schduler** for **multiple Email notification jobs** (Birth Day Email Notification, Work Anniversary Email Notification) using **Spring Boot**. 

# Features 
- Created 2 Jobs - Birth Day Email Notification and Work Anniversary Email Notification using quartz.
- Implemented **Triggers** for both Jobs : Simple as well as **Cron**.
- Using **spring-boot-starter-mail dependency** implemented mail functionality with smtp.gmail.com setup.
- Implemented **H2 database** for storing scheduler details.

# Pre-requisite
- Java - 11
- Maven - 3.x.x

# Steps to Setup 

- Clone this project

  git clone https://github.com/ShalakaP02/spring-boot-quartz-scheduler.git
  
- Setup Spring Mail  
  Add your gmail email id and password in applications.properties at below lines :
  
	spring.mail.host=smtp.gmail.com  
	spring.mail.port=587   
	spring.mail.username=shalaka.purandare1994@gmail.com        
	spring.mail.password=      
	
- Build and run the app using maven
	Finally, You can run the app by typing the following command from the root directory of the project -

	mvn spring-boot:run

- Customize interval/time for scheduler
  Below lines in application.properties file are used for setting the excution time of both jobs. You can customize it accordingly.
  
	#Birthday Notification Properties     
	birthday.notification.job.daily.schedule.hour=13     
	birthday.notification.job.daily.schedule.minute=45    
	birthday.notification.job.daily.schedule.second=0   
  
	#Work Notification Properties
	## cron for every 30 sec
	#work.notification.cron=0/30 * * * * ?
	## cron for every 5 min
	work.notification.cron=0 */5 * ? * *
