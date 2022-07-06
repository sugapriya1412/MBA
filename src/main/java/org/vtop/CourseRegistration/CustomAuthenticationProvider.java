package org.vtop.CourseRegistration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.vtop.CourseRegistration.mongo.model.StudentDetail;
import org.vtop.CourseRegistration.mongo.service.CourseRegistrationCommonMongoService;
import org.vtop.CourseRegistration.service.CourseRegistrationCommonFunction;
import org.vtop.CourseRegistration.service.CourseRegistrationReadWriteService;
import org.vtop.CourseRegistration.service.CustomUserDetailService;
import org.vtop.CourseRegistration.service.SemesterMasterService;


public class CustomAuthenticationProvider extends DaoAuthenticationProvider
{
	@Autowired private HttpSession session;
	@Autowired private CustomUserDetailService customUserDetailService;
	@Autowired HttpServletRequest request;
	@Autowired private SemesterMasterService semesterMasterService;
	@Autowired private CourseRegistrationCommonFunction courseRegCommonFn;
	@Autowired private CourseRegistrationReadWriteService courseRegistrationReadWriteService;

	@Autowired private CourseRegistrationCommonMongoService courseRegistrationCommonMongoService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{		
		String userId = authentication.getName().toUpperCase().trim();
		String passwordInput = authentication.getCredentials().toString().trim();
		String captchaInput = request.getParameter("captchaString").trim();
		//List<Object[]> studentDetail = new ArrayList<>();
		
		//For getting captcha from session attribute					
		String sessioncaptchaString = (String) session.getAttribute("CAPTCHA");
				
		int testStatus = 2; //Login with Password & Captcha-> 1: Enable/ 2: Disable
		int regTimeCheckStatus = 1; //Time-> 1: Open Hours/ 2: Permitted Schedule
		
		int validateDateTime = 2, validateCaptcha = 2, validateCredential = 2, validateAccount = 2;
		int specId = 0, groupId = 0, programDuration = 0, costCenterId = 0, studyStartYear = 0, lockStatus = 0, 
				feeId = 0, studentGraduateYear = 0;
		String registerNo = "", dbPassWord = "", studentName = "", specCode = "", specDesc = "", 
				programGroupCode = "", programGroupMode = "", costCentreCode = "", studentStudySystem = "", 
				eduStatus = "", eduStatusExpn = "", studEMailId = "", userCredential = "", message = "", 
				studentCategory = "";
		
		StudentDetail studentDetail2 = null;
				
		try
		{
			//Date & Time Setting
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");		
			Date startDate = sdf.parse("05-JUL-2022");
			Date endDate = sdf.parse("07-JUL-2022");
			String startTime = "10:00:00", endTime = "23:59:59", allowStartTime = "10:00:00";
			
			//Assigning IP address
			String ipAddress = request.getRemoteAddr();
			if (request != null) {
				ipAddress = request.getHeader("X-FORWARDED-FOR");
				if (ipAddress == null || "".equals(ipAddress)) {
					ipAddress = request.getRemoteAddr();
				}
			}
			logger.trace("\n ipAddress: "+ ipAddress);
			
			//Validate Date/Time Duration based on Open Hours or Permitted Schedule
			if (regTimeCheckStatus == 2)
			{
				message = courseRegCommonFn.registrationSessionDateTimeCheck(startDate);
				if (message.equals("SUCCESS"))
				{
					validateDateTime = 1;
				}
			}
			else
			{
				String[] dataTimeCheckArray = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, 
													allowStartTime, endTime, userId, 2, ipAddress).split("/");
				logger.trace("\n dataTimeCheckArray: "+ Arrays.toString(dataTimeCheckArray));
				if (Integer.parseInt(dataTimeCheckArray[0]) == 1)
				{
					validateDateTime = 1;
				}
				else
				{
					message = dataTimeCheckArray[1].replace(allowStartTime, startTime);
				}
			}
			
			//Validate Captcha
			if (validateDateTime == 1)
			{
				if ((captchaInput == null) || (captchaInput.equals("")) || (captchaInput.length() != 6))
				{	
					message = "Invalid Captcha";
				}
				else
				{
					if (testStatus == 2)
					{
						validateCaptcha = 1;
					}
					else if (captchaInput.equals(sessioncaptchaString))
					{
						validateCaptcha = 1;
					}
					else
					{
						message = "Invalid Captcha";
					}
				}
			}
			
			//Validate Credential
			if (validateCaptcha == 1)
			{
				if ((userId == null) || (userId.equals("")) || (passwordInput == null) || (passwordInput.equals(""))) 
				{
					message = "Invalid username / password";
				}
				else
				{
					/*if (testStatus == 2)
					{
						studentDetail = semesterMasterService.getStudentLoginDetailByRegisterNumber2(userId);
					}
					else
					{
						studentDetail = semesterMasterService.getStudentLoginDetailByUserName(userId);
					}
					if (!studentDetail.isEmpty())
					{							
						registerNo = studentDetail.get(0)[0].toString();
						studentName = studentDetail.get(0)[3].toString();
						specId = Integer.parseInt(studentDetail.get(0)[5].toString());
						specCode = studentDetail.get(0)[6].toString();
						specDesc = studentDetail.get(0)[7].toString();
						groupId = Integer.parseInt(studentDetail.get(0)[8].toString());
						programGroupCode = studentDetail.get(0)[9].toString();
						programGroupMode = studentDetail.get(0)[11].toString();
						programDuration = Integer.parseInt(studentDetail.get(0)[12].toString());
						costCenterId = Integer.parseInt(studentDetail.get(0)[14].toString());
						costCentreCode = studentDetail.get(0)[15].toString();
						studyStartYear = Integer.parseInt(studentDetail.get(0)[17].toString());
						studentStudySystem = studentDetail.get(0)[18].toString();
						dbPassWord = studentDetail.get(0)[19].toString();
						eduStatus = studentDetail.get(0)[20].toString();
						eduStatusExpn = studentDetail.get(0)[21].toString();
						lockStatus = Integer.parseInt(studentDetail.get(0)[22].toString());
						feeId = (studentDetail.get(0)[25] != null) ? Integer.parseInt(studentDetail.get(0)[25].toString()) : 0;
						studentCategory = (studentDetail.get(0)[26] != null) ? studentDetail.get(0)[26].toString() : "";
						
						studentGraduateYear = studyStartYear + programDuration;
												
						if (testStatus == 2)
						{
							studEMailId = "NONE";	//Testing Purpose
						}
						else
						{
							studEMailId = (studentDetail.get(0)[23] != null) ? studentDetail.get(0)[23].toString() : "NONE";
						}
																	
						if (semesterMasterService.getUserLoginValidation(userId, passwordInput, dbPassWord, testStatus) == 1)
						{
							validateCredential = 1;
						}
						else
						{
							message = "Invalid username / password";
						}
					}
					else
					{
						message = "Invalid username / password";
					}*/
					
					if (testStatus == 2)
					{
						studentDetail2 = courseRegistrationCommonMongoService.getStudentDetailByRegisterNumber(userId);
					}
					else
					{
						studentDetail2 = courseRegistrationCommonMongoService.getStudentDetailByNickName(userId);
					}
					if (studentDetail2 != null)
					{						
						registerNo = studentDetail2.getRegisterNumber();
						studentName = studentDetail2.getStudentName();
						specId = studentDetail2.getProgSpecializationId();
						specCode = studentDetail2.getProgSpecializationCode();
						specDesc = studentDetail2.getProgSpecializationDescription();
						groupId = studentDetail2.getProgGroupId();
						programGroupCode = studentDetail2.getProgGroupCode();
						programGroupMode = studentDetail2.getProgGroupMode();
						programDuration = studentDetail2.getProgGroupDuration();
						costCenterId = studentDetail2.getCentreId();
						costCentreCode = studentDetail2.getCentreCode();
						studyStartYear = studentDetail2.getAdmissionYear();
						studentStudySystem = studentDetail2.getStudySystem();
						dbPassWord = studentDetail2.getPassword();
						lockStatus = studentDetail2.getLockStatus();
						eduStatus = studentDetail2.getEducationStatus();
						eduStatusExpn = studentDetail2.getEducationStatusDescription();
						feeId = studentDetail2.getFeeId();
						studentCategory = studentDetail2.getFeeCategoryDescription();
						
						studentGraduateYear = studyStartYear + programDuration;
												
						if (testStatus == 2)
						{
							studEMailId = "NONE";	//Testing Purpose
						}
						else
						{
							studEMailId = (studentDetail2.getEmail() == null) ? "NONE" : studentDetail2.getEmail();
						}
																	
						if (semesterMasterService.getUserLoginValidation(userId, passwordInput, dbPassWord, testStatus) == 1)
						{
							validateCredential = 1;
						}
						else
						{
							message = "Invalid username / password";
						}
					}
					else
					{
						message = "Invalid username / password";
					}
				}
			}
			
			//Validate Account Status
			if (validateCredential == 1)
			{
				if (lockStatus == 0) 
				{
					validateAccount = 1;
				}
				else
				{
					message = "Your account is locked [Reason: "+ eduStatusExpn +"].";
				}
								
				if (validateAccount == 1)
				{
					validateAccount = 2;
					if ((!eduStatus.equals("DO")) && (!eduStatus.equals("GT"))) 
					{
						validateAccount = 1;
					}
					else
					{
						message = "Your are not eligible for Add or Drop.";
					}
				}
			}
			
			//Authenticate the user
			if ((validateDateTime == 1) && (validateCaptcha == 1) && (validateCredential == 1) && (validateAccount == 1))
			{	
				userCredential = registerNo +"|"+ dbPassWord +"|0";
				UserDetails userDetails = customUserDetailService.loadUserByUsername(userCredential);
			
				//Session Assignment
				session.setAttribute("RegisterNumber", registerNo);
				session.setAttribute("studentName", studentName);
				session.setAttribute("ProgramSpecId", specId);
				session.setAttribute("ProgramSpecCode", specCode);
				session.setAttribute("ProgramSpecDesc", specDesc);
				session.setAttribute("ProgramGroupId", groupId);
				session.setAttribute("ProgramGroupCode", programGroupCode);				
				session.setAttribute("StudyStartYear", studyStartYear);
				session.setAttribute("StudentGraduateYear", studentGraduateYear);
				session.setAttribute("studentStudySystem", studentStudySystem);				
				session.setAttribute("programGroupMode", programGroupMode);
				session.setAttribute("studentEMailId", studEMailId);
				session.setAttribute("costCentreCode", costCentreCode);
				session.setAttribute("costCenterId", costCenterId);
				session.setAttribute("feeId", feeId);
				session.setAttribute("studentCategory", studentCategory);
				
				session.setAttribute("testStatus", testStatus);
				session.setAttribute("regTimeCheckStatus", regTimeCheckStatus);
				session.setAttribute("startDate", startDate);
				session.setAttribute("endDate", endDate);
				session.setAttribute("startTime", startTime);
				session.setAttribute("endTime", endTime);
				session.setAttribute("allowStartTime", allowStartTime);
				session.setAttribute("IpAddress", ipAddress);
				session.setAttribute("CAPTCHA", "");
				session.setAttribute("ENCDATA", "");
										
				return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), 
								userDetails.getAuthorities());
			}
			else
			{
				throw new BadCredentialsException(message);
			}
		}
		catch (Exception x)
		{
			throw new BadCredentialsException(x.getMessage());
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
