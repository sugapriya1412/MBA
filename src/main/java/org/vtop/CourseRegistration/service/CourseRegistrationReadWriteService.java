package org.vtop.CourseRegistration.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.Common.service.MailUtility;
import org.vtop.CourseRegistration.repository.CourseEquivalanceRegRepository;
import org.vtop.CourseRegistration.repository.CourseRegistrationRepository;
import org.vtop.CourseRegistration.repository.CourseRegistrationWaitingRepository;
import org.vtop.CourseRegistration.repository.CourseRegistrationWithdrawRepository;
import org.vtop.CourseRegistration.repository.ProjectRegistrationRepository;
import org.vtop.CourseRegistration.repository.RegistrationLogRepository;
import org.vtop.CourseRegistration.repository.StudentHistoryRepository;


@Service
@Transactional
public class CourseRegistrationReadWriteService
{
	@Autowired private CourseEquivalanceRegRepository courseEquivalanceRegRepository;
	@Autowired private CourseRegistrationRepository courseRegistrationRepository;
	@Autowired private CourseRegistrationWaitingRepository courseRegistrationWaitingRepository;
	@Autowired private CourseRegistrationWithdrawRepository courseRegistrationWithdrawRepository;
	@Autowired private ProjectRegistrationRepository projectRegistrationRepository;
	@Autowired private RegistrationLogRepository registrationLogRepository;
	@Autowired private StudentHistoryRepository studentHistoryRepository;
	
	private static final Logger logger = LogManager.getLogger(CourseRegistrationReadWriteService.class);
	private static final int keyLength = 21;//Fixing the key length to generate hash value
		
	
	//Course Equivalence Registration
	public void courseEquRegDeleteByRegisterNumberAndCourseId(String semesterSubId, String registerNumber, String courseId)
	{		
		courseEquivalanceRegRepository.deleteByRegisterNumberCourseId(semesterSubId, registerNumber, courseId);
	}
	
	
	//Course Registration
	//Procedure
	public String courseRegistrationAdd2(String psemsubid, String pclassid, String pregno, String pcourseid, 
						String pcomponent_type, String pcourse_option, Integer pregstatus, Integer pregcomponent_type, 
						String ploguserid, String plogipaddress, String pregtype, String pold_course_code, String pcalltype, 
						String pold_course_type, String pold_exam_month, String gradecategory)
	{
		return courseRegistrationRepository.registration_insert_prc(psemsubid, pclassid, pregno, pcourseid, 
					pcomponent_type, pcourse_option, pregstatus, pregcomponent_type, ploguserid, plogipaddress, 
					pregtype, pold_course_code, pcalltype, pold_course_type, pold_exam_month, gradecategory, "NONE");
	}
	
	public String courseRegistrationUpdate2(String psemsubid, String pregno, String pcourseid, String pcomponent_type,
						String pcourse_option, String poldclassid, String pnewclassid, String ploguserid, String plogipaddress,
						Integer pregstatus, Integer pregcomponent_type, String pregtype, String pold_course_code, 
						String pold_course_type, String pold_exam_month, String gradecategory)
	{
		return courseRegistrationRepository.registration_update_prc(psemsubid, pregno, pcourseid, pcomponent_type,
					pcourse_option, poldclassid, pnewclassid, ploguserid, plogipaddress, pregstatus, pregcomponent_type, 
					pregtype, pold_course_code, pold_course_type, pold_exam_month, gradecategory, "NONE");
	}
	
	public String courseRegistrationDelete(String psemsubid, String pregno, String pcourseid, String pcalltype, 
			String ploguserid, String plogipaddress, String pregtype, String poldcoursecode)
	{
		return courseRegistrationRepository.registration_delete_prc(psemsubid, pregno, pcourseid, pcalltype, 
					ploguserid, plogipaddress, pregtype, poldcoursecode, "NONE");
	}
	
	
	//Course Registration Waiting
	public void courseRegWaitingDeleteByRegisterNumberAndCourseId(String semesterSubId, String registerNumber, String courseId)
	{		
		courseRegistrationWaitingRepository.deleteByRegisterNumberCourseId(semesterSubId, registerNumber, courseId);
	}
		
	//Course Registration Waiting Move
	public void courseRegWaitingAddWaitingToWaitingMove(String semesterSubId, String registerNumber, String courseId, int waitStatus, 
						String ipaddress)
	{		
		courseRegistrationWaitingRepository.insertWaitingToWaitingMove(semesterSubId, registerNumber, courseId, 
				waitStatus, ipaddress);
	}
	
	
	//Course Registration Withdraw
	public List<Object[]> getCourseWithdrawOTP(String semesterSubId, String registerNumber, String courseId, 
								Integer otpReasonType)
	{
		return courseRegistrationWithdrawRepository.findCourseWithdrawOTP(semesterSubId, registerNumber, courseId, 
					otpReasonType);
	}

	public void addCourseWithdrawOTP(String semesterSubId, String registerNumber, String courseId, 
						Integer otpReasonType, String mailOTP, String mobileOTP, String userId, String ipAddress)
	{
		courseRegistrationWithdrawRepository.insertCourseWithdrawOTP(semesterSubId, registerNumber, courseId, 
				otpReasonType, mailOTP, mobileOTP, userId, ipAddress);
	}
	
	public void modifyCourseWithdrawOTP(String semesterSubId, String registerNumber, String courseId, 
					Integer otpReasonType, String mailOTP, String mobileOTP, String userId, String ipAddress)
	{
		courseRegistrationWithdrawRepository.insertCRWOTPBackup(semesterSubId, registerNumber, courseId, 
				otpReasonType, userId, ipAddress);
		
		courseRegistrationWithdrawRepository.updateCourseWithdrawOTP(semesterSubId, registerNumber, courseId, 
				otpReasonType, mailOTP, mobileOTP, userId, ipAddress);
	}
	
	public void modifyCourseWithdrawOTPResponse(String semesterSubId, String registerNumber, String courseId, 
					Integer otpReasonType, String mobileOTPResponse)
	{
		courseRegistrationWithdrawRepository.updateCourseWithdrawOTPResponse(semesterSubId, registerNumber, 
				courseId, otpReasonType, mobileOTPResponse);
	}	
	
	public void modifyWithdrawOTPConfirmationStatus(String semesterSubId, String registerNumber, String courseId, 
					Integer otpReasonType, int mailOTPStatus, int mobileOTPStatus, String userId, String ipAddress)
	{
		courseRegistrationWithdrawRepository.updateWithdrawOTPConfirmationStatus(semesterSubId, registerNumber, 
				courseId, otpReasonType, mailOTPStatus, mobileOTPStatus, userId, ipAddress);
	}
	
	
	//Project Registration
	public void projectRegDeleteByRegisterNumberAndCourseId(String semesterSubId, String registerNumber, String courseId)
	{		
		projectRegistrationRepository.deleteByRegisterNumberCourseId(semesterSubId, registerNumber, courseId);
	}
	
	
	//Registration Log
	public void updateRegistrationLogLogoutTimeStamp(String ipAddress, String registerNumber)
	{
		registrationLogRepository.UpdateLogoutTimeStamp(ipAddress, registerNumber);
	}
		
	public void addErrorLog(String exceptionMessage, String packageName, String programName, 
					String userId, String ipAddress)
	{
		if (exceptionMessage.length() >= 1000)
		{
			exceptionMessage = exceptionMessage.substring(1, 999);
		}
	
		if (packageName.length() >= 100)
		{
			packageName = packageName.substring(1, 99);
		}
	
		if (programName.length() >= 250)
		{
			programName = programName.substring(1, 249);
		}
	
		registrationLogRepository.InsertErrorLog(exceptionMessage, "ACADEMICS", packageName, 
			programName, userId, ipAddress);
	}
	
	public void addRegistrationLog(String registerNumber, String ipAddress)
	{
		registrationLogRepository.AddRegistrationLog(registerNumber, ipAddress);
	}
	
	public void updateRegistrationLogActiveTimeStamp(String registerNumber)
	{
		registrationLogRepository.UpdateActiveTimeStamp(registerNumber);
	}
	
	public void updateRegistrationLogLoginTimeStamp2(String ipAddress, String registerNumber)
	{
		registrationLogRepository.UpdateLoginTimeStamp2(ipAddress, registerNumber);
	}
	
	public void updateRegistrationLogLogoutTimeStamp2(String ipAddress, String registerNumber)
	{
		registrationLogRepository.UpdateLogoutTimeStamp2(ipAddress, registerNumber);
	}
	
	
	//Student History
	//Procedure - To insert the fresh Data in Student History from Examination Schema
	/*public String studentHistoryInsertProcess(String pRegisterNumber, String pCourseSystem)
	{
		return studentHistoryRepository.acad_student_history_insert_process2(pRegisterNumber, pCourseSystem, "NONE");
	}*/
	public String studentHistoryInsertProcess(String pRegisterNumber, String pCourseSystem)
	{
		String returnStatus = "SUCCESS";
		float days = 0, hours = 0;
		boolean executeStatus = false;
		List<Object[]> objectList = new ArrayList<>();
		
		objectList = studentHistoryRepository.findLastUpdatedPeriodByRegisterNumber(pRegisterNumber);
		if (objectList.isEmpty())
		{
			executeStatus = true;
		}
		else
		{
			if (objectList.get(0)[0] == null)
			{
				executeStatus = true;
			}
			else
			{
				days = Float.parseFloat(objectList.get(0)[1].toString());
				hours = Float.parseFloat(objectList.get(0)[2].toString());
				logger.trace("\n days : "+ days +" | hours: "+ hours);
				
				if ((days >= 1) || (hours >= 6))
				{
					executeStatus = true;
				}
			}
		}
		logger.trace("\n executeStatus : "+ executeStatus);
		
		if (executeStatus)
		{
			returnStatus = studentHistoryRepository.acad_student_history_insert_process2(pRegisterNumber, pCourseSystem, "NONE");
		}		
		
		return returnStatus;
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
					updateRegistrationLogActiveTimeStamp(registerNo);
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
	
	//Validate Course & Sending the OTP
	public String validateCourseAndSendOTP(String semesterSubId, String semesterDesc, String semesterShortDesc, 
						String registerNumber, String courseId, String courseCode, String studentEMailId, 
						String IpAddress, String processType)
	{	
		int validateStatus = 2, otpReasonType = 0, flag = 2, flag2 = 2, flag3 = 2;	
		String msg = "NONE", authKeyVal = "NONE", mobileOTP = "NONE", emailOTP = "NONE", mailSubject = "", 
				mailBody = "", mailStatus = "";
		List<Object[]> objectList = new ArrayList<Object[]>();
		
		logger.trace("\n semesterSubId: "+ semesterSubId +" | registerNumber: "+ registerNumber 
				+" | courseId: "+ courseId +" | studentEMailId: "+ studentEMailId +" | IpAddress: "+ IpAddress 
				+" | processType: "+ processType);
								
		try
		{
			//Validating the Mobile No.
			if((studentEMailId != null) && (!studentEMailId.equals("")) && (!studentEMailId.equals("NONE")))
			{
				flag = 1;
			}
			else
			{
				msg = "E-Mail Id is not properly updated.  Kindly check your profile and update it."; 
			}
			
			//Validating the Process Type.
			if (flag == 1)
			{
				if (processType.equals("DELETE") || processType.equals("MODIFY"))
				{
					flag2 = 1;
				}
				else
				{
					msg = "Invalid process type.  Kindly proceed once again."; 
				}
			}
									
			//Sent & Check the Mail Status			
			if (flag2 == 1)
			{					
				mobileOTP = getRandomOtp(5);
				emailOTP = getRandomOtp(5);
				if (emailOTP.equals(mobileOTP))
				{
					mobileOTP = getRandomOtp(5);
				}
				
				if (processType.equals("DELETE"))
				{					
					mailSubject = semesterShortDesc +" - Course Delete OTP";					
					mailBody = "<html><body><b>Dear Student ("+ registerNumber +"),</b><br><br>"+ System.lineSeparator();
					mailBody = mailBody +"OTP to delete the course "+ courseCode +" is <b>"+ emailOTP +"</b> for "
								+ semesterDesc +".";
					mailBody = mailBody +"<br><br>This is an auto generated mail.  Kindly don't reply or send message "
								+"to this mail id."+ System.lineSeparator() +"<br><br>Thanks !!</body></html>";
					otpReasonType = 2;
				}
				else if (processType.equals("MODIFY"))
				{
					mailSubject = semesterShortDesc +" - Course Modify OTP";
					mailBody = "<html><body><b>Dear Student ("+ registerNumber +"),</b><br><br>"+ System.lineSeparator();
					mailBody = mailBody +"OTP to modify the course "+ courseCode +" is <b>"+ emailOTP +"</b> for "
								+ semesterDesc +".";
					mailBody = mailBody +"<br><br>This is an auto generated mail.  Kindly don't reply or send message "
								+"to this mail id."+ System.lineSeparator() +"<br><br>Thanks !!</body></html>";
					otpReasonType = 3;
				}
				
				//Add or Update the OTP Data
				objectList = getCourseWithdrawOTP(semesterSubId, registerNumber, courseId, otpReasonType);
				if (objectList.isEmpty())
				{
					addCourseWithdrawOTP(semesterSubId, registerNumber, courseId, otpReasonType, emailOTP, mobileOTP, 
							registerNumber, IpAddress);
				}
				else
				{
					modifyCourseWithdrawOTP(semesterSubId, registerNumber, courseId, otpReasonType, emailOTP, mobileOTP, 
							registerNumber, IpAddress);
				}
		
				//Sending E-Mail
				mailStatus = MailUtility.triggerMail(mailSubject, mailBody, "", studentEMailId);
				if (mailStatus.length() > 250)
				{
					mailStatus = mailStatus.substring(0, 250);
				}
				
				//Update the Mail Response Status
				modifyCourseWithdrawOTPResponse(semesterSubId, registerNumber, courseId, otpReasonType, mailStatus);
				
				if (mailStatus.equals("SUCCESS"))
				{
					flag3 = 1;
				}
				else
				{
					msg = "Not a valid E-Mail Id.  Kindly check your profile and update it."; 
				}
			}
			logger.trace("\n Flag: "+ flag +" | Flag2: "+ flag2 +" | Flag3: "+ flag3);
				
			if ((flag == 1) && (flag2 == 1) && (flag3 == 1))
			{	
				validateStatus = 1;
				msg = "SUCCESS";
			}
		}
		catch (Exception ex)
		{
			logger.trace(ex);
		}
			
		//Generating the Authentication Key Value
		authKeyVal = generateCourseAuthKey(registerNumber, courseId, validateStatus, 2);
			
		logger.trace("\n validateStatus: "+ validateStatus +" | authKeyVal: "+ authKeyVal 
		 		+" | emailOTP: "+ emailOTP +" | msg: "+ msg);
			
		return validateStatus +"|"+ authKeyVal +"|"+ emailOTP +"|"+ msg;
	}
	
	//Validate Course & OTP
	public String validateCourseAndOTP(String semesterSubId, String semesterDesc, String semesterShortDesc, 
						String registerNumber, String courseId, String courseCode, String emailOTP, 
						String IpAddress, String processType)
	{	
		int validateStatus = 2, otpReasonType = 0, otpAllowFlag = 2, flag = 2, flag2 = 2;							
		String msg = "NONE", emailOTPDB = "NONE";
		List<Object[]> objectList = new ArrayList<Object[]>();
		
		logger.trace("\n semesterSubId: "+ semesterSubId +" | registerNumber: "+ registerNumber 
				+" | courseId: "+ courseId +" | emailOTP: "+ emailOTP +" | IpAddress: "+ IpAddress 
				+" | processType: "+ processType);
		
		try
		{			
			//Validating the Process Type.
			if (processType.equals("DELETE"))
			{
				otpReasonType = 2;
				flag = 1;
			}
			else if (processType.equals("MODIFY"))
			{
				otpReasonType = 3;
				flag = 1;
			}
			else
			{
				msg = "Invalid process type.  Kindly proceed once again."; 
			}
						
			//Checking the OTP
			if (flag == 1)
			{
				objectList = getCourseWithdrawOTP(semesterSubId, registerNumber, courseId, otpReasonType);
				if (!objectList.isEmpty())
				{
					for (Object[] e: objectList)
					{
						emailOTPDB = e[1].toString();
						break;
					}
					
					if (emailOTPDB.equals(emailOTP))
					{
						flag2 = 1;
					}
					else
					{
						otpAllowFlag = 1;
						msg = "You entered the wrong OTP, kindly enter your E-Mail OTP properly "+
								"for the course "+ courseCode +"."; 
					}
				}
				else
				{
					msg = "You did not receive OTP for the course "+ courseCode +".  Kindly proceed once again."; 
				}
			}
			logger.trace("\n Flag: "+ flag +" | Flag2: "+ flag2);
			
			if ((flag == 1) && (flag2 == 1))
			{	
				//Confirm the OTP Status
				modifyWithdrawOTPConfirmationStatus(semesterSubId, registerNumber, courseId, otpReasonType, 2, 2, 
						registerNumber, IpAddress);
				
				validateStatus = 1;
				msg = "SUCCESS";
			}
		}
		catch (Exception ex)
		{
			logger.trace(ex);
		}
		logger.trace("\n validateStatus: "+ validateStatus +" | otpAllowFlag: "+ otpAllowFlag +" | msg: "+ msg);
			
		return validateStatus +"|"+ otpAllowFlag +"|"+ msg;
	}	
	
	public String getRandomOtp(Integer keyLength)
	{
		String sDefaultChars = "123456789abcdefghjkmnpqrxyzABCDEFGHJKMNPQRXYZ";
		Integer iKeyLength =keyLength;
		Integer iDefaultCharactersLength = sDefaultChars.length();
		String sMyKey="";
		for(int iCounter=1;iCounter<=iKeyLength;iCounter++)
		{
			Integer iPickedChar=(int) ((iDefaultCharactersLength*Math.random())+1);
			if(iPickedChar>=sDefaultChars.length())
				sMyKey = sMyKey+(sDefaultChars.substring(sDefaultChars.length()-1));
			else
				sMyKey = sMyKey+(sDefaultChars.substring(iPickedChar,iPickedChar+1));
		}
		
		return sMyKey;
	}
	
	//Generate Course Authorization Key
	public String generateCourseAuthKey(String registerNumber, String courseId, int validateStatus, int levelType)
	{
		String authKeyVal = "NONE";
		Sdc_common_functions scf = new Sdc_common_functions();
		
		if ((registerNumber == null) || registerNumber.equals(""))
		{
			registerNumber = "NONE";
		}
		
		if ((courseId == null) || courseId.equals(""))
		{
			courseId = "NONE";
		}
		
		if (levelType == 1)
		{
			authKeyVal = scf.Encrypt_String3(registerNumber +"_"+ courseId +"_"+ validateStatus +"_1", keyLength);
		}
		else if (levelType == 2)
		{
			authKeyVal = scf.Encrypt_String3(registerNumber +"_"+ courseId +"_"+ validateStatus +"_2", keyLength);
		}
		
		return authKeyVal;
	}
}
