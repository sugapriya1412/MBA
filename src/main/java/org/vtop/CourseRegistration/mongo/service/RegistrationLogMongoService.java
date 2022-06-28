package org.vtop.CourseRegistration.mongo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vtop.CourseRegistration.mongo.model.RegistrationLog;
import org.vtop.CourseRegistration.mongo.repository.RegistrationLogMongoRepository;


@Service
public class RegistrationLogMongoService
{
	@Autowired private RegistrationLogMongoRepository registrationLogMongoRepository;
	
	private static final Logger logger = LogManager.getLogger(RegistrationLogMongoService.class);
	
	
	public void saveRegistrationLog(String registerNumber, int logStatus, String loginIpAddress, String logoutIpAddress)
	{
		RegistrationLog registrationLog = new RegistrationLog();
		
		registrationLog.setRegisterNumber(registerNumber);
		registrationLog.setLogStatus(logStatus);
		registrationLog.setLoginTimeStamp(null);
		registrationLog.setLoginIpAddress(loginIpAddress);
		registrationLog.setActiveTimeStamp(null);
		registrationLog.setLogoutTimeStamp(null);
		registrationLog.setLoginIpAddress(logoutIpAddress);
		
		registrationLogMongoRepository.save(registrationLog);
	}
	
	
	//Checking the Data/Time & Update the Active Time Stamp
	public String AddorDropDateTimeCheck(Date startDate, Date endDate, String startTime, String endTime,
						String registerNo, int updateStatus, String ipAddress)
	{	
		int timeCheckFlag = 2;
		String timeCheckMessage = "NONE", presentDateTime = "", presentTime = "";
		Long startTimeVal = 0L, endTimeVal = 0L, presentTimeVal = 0L;
		
		Date presentDate = null;
		DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		
		try
		{
			if ((startDate != null) && (endDate != null) && (startTime != null) && (!startTime.equals("")) 
					&& (endTime != null) && (!endTime.equals("")) && (registerNo != null) && (!registerNo.equals("")) 
					&& (ipAddress != null) && (!ipAddress.equals("")))
			{
				startTimeVal = Long.parseLong(startTime.replace(":", ""));
				endTimeVal = Long.parseLong(endTime.replace(":", ""));
				
				presentDateTime = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());					
				String[] presentDateTimeArr = presentDateTime.split(" ");
				presentDate = format.parse(presentDateTimeArr[0]);
				presentTime = presentDateTimeArr[1];
				presentTimeVal = Long.parseLong(presentTime.replace(":", ""));
				
				logger.trace("\n StartDate: "+ startDate +" | StartTime: "+ startTime 
					+" | Start Time Value: " + startTimeVal);
				logger.trace("\n EndDate: "+ endDate +" | EndTime: "+ endTime +" | EndTimeValue: " + endTimeVal);
				logger.trace("\n PresentDate: "+ presentDate +" | PresentTime: "+ presentTime
						 				+" | PresentTimeValue: " + presentTimeVal);
								
				//Based on fixed Date & Time
				if ((presentDate.compareTo(startDate) >= 0) && (presentDate.compareTo(endDate) <= 0))
				{				
					if ((startDate.compareTo(endDate) == 0) && (presentDate.compareTo(startDate) == 0) 
							&& (presentTimeVal < startTimeVal))
					{
						timeCheckMessage = "Registration starts at "+ startTime +" Hrs.";
					}
					else if ((startDate.compareTo(endDate) == 0) && (presentDate.compareTo(startDate) == 0) 
								&& (presentTimeVal >= startTimeVal) && (presentTimeVal <= endTimeVal))
					{
						timeCheckMessage = "Success.";
						timeCheckFlag = 1;
					}
					else if ((startDate.compareTo(endDate) == 0) && (presentDate.compareTo(startDate) == 0) 
								&& (presentTimeVal > endTimeVal))
					{
						timeCheckMessage = "Registration closed.";
					}
					else if ((startDate.compareTo(endDate) != 0) && (presentDate.compareTo(startDate) == 0) 
								&& (presentTimeVal < startTimeVal))
					{
						timeCheckMessage = "Registration starts at "+ startTime +" Hrs.";
					}
					else if ((startDate.compareTo(endDate) != 0) && (presentDate.compareTo(startDate) == 0) 
								&& (presentTimeVal >= startTimeVal))
					{
						timeCheckMessage = "Success.";
						timeCheckFlag = 1;
					}
					else if ((startDate.compareTo(endDate) != 0) && (presentDate.compareTo(startDate) > 0) 
								&& (presentDate.compareTo(endDate) < 0))
					{
						timeCheckMessage = "Success.";
						timeCheckFlag = 1;
					}
					else if ((startDate.compareTo(endDate) != 0) && (presentDate.compareTo(endDate) == 0) 
								&& (presentTimeVal <= endTimeVal))
					{
						timeCheckMessage = "Success.";
						timeCheckFlag = 1;
					}
					else
					{
						timeCheckMessage = "Registration closed.";
					}
				}
				else
				{
					if (presentDate.compareTo(endDate) > 0)
					{
						timeCheckMessage = "Registration closed.";
					}
					else
					{
						timeCheckMessage = "Registration will start on "+ new SimpleDateFormat("dd-MMM-yyyy").format(startDate) 
												+" at "+ startTime +" Hrs.";
					}
				}
				
				if ((timeCheckFlag == 1) && (updateStatus == 1))
				{
					//updateRegistrationLogActiveTimeStamp(registerNo);
				}
			}
			else
			{
				timeCheckMessage = "Session timed out.  Kindly logout and login again.";
			}
		}
		catch (Exception ex)
		{
			logger.trace(ex);
		}

		return timeCheckFlag +"/"+ timeCheckMessage;
	}
}
