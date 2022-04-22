package org.vtop.CourseRegistration;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.vtop.CourseRegistration.service.CustomUserDetailService;
import org.vtop.CourseRegistration.service.SemesterMasterService;


public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired private HttpSession session;
	@Autowired private CustomUserDetailService customUserDetailService;
	@Autowired HttpServletRequest request;
	@Autowired private SemesterMasterService semesterMasterService;
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{		
		String userId = authentication.getName().toUpperCase().trim();
		String passwordInput = authentication.getCredentials().toString().trim();
		String captchaInput = request.getParameter("captchaString").trim();
		List<Object[]> studentDetail = new ArrayList<>();
		
		//For getting captcha from session attribute					
		String sessioncaptchaString = (String) session.getAttribute("CAPTCHA");
		logger.trace("\n sessioncaptchaString: "+ sessioncaptchaString);
		
		int testStatus = 2; //Login with Password & Captcha-> 1: Enable/ 2: Disable
		int validateCaptcha = 2, validateCredential = 2, validateAccount = 2;
		int specId = 0, groupId = 0, programDuration = 0, costCenterId = 0, studyStartYear = 0, lockStatus = 0, 
				feeId = 0, studentGraduateYear = 0;
		String registerNo = "", dbPassWord = "", studentName = "", specCode = "", specDesc = "", 
				programGroupCode = "", programGroupMode = "", costCentreCode = "", studentStudySystem = "", 
				eduStatus = "", eduStatusExpn = "", studEMailId = "", userCredential = "";
		
		try
		{
			//Validate Captcha
			if ((captchaInput == null) || (captchaInput.equals("")) || (captchaInput.length() != 6))
			{					
				throw new BadCredentialsException("Invalid Captcha");
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
					throw new BadCredentialsException("Invalid Captcha");
				}
			}
			
			//Validate Credential
			if (validateCaptcha == 1)
			{
				if ((userId == null) || (userId.equals("")) || (passwordInput == null) || (passwordInput.equals(""))) 
				{
					throw new BadCredentialsException("Invalid username / password");
				}
				else
				{
					if (testStatus == 2)
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
						studentName = studentDetail.get(0)[2].toString();
						specId = Integer.parseInt(studentDetail.get(0)[4].toString());
						specCode = studentDetail.get(0)[5].toString();
						specDesc = studentDetail.get(0)[6].toString();
						groupId = Integer.parseInt(studentDetail.get(0)[7].toString());
						programGroupCode = studentDetail.get(0)[8].toString();
						programGroupMode = studentDetail.get(0)[10].toString();
						programDuration = Integer.parseInt(studentDetail.get(0)[11].toString());
						costCenterId = Integer.parseInt(studentDetail.get(0)[13].toString());
						costCentreCode = studentDetail.get(0)[14].toString();
						studyStartYear = Integer.parseInt(studentDetail.get(0)[16].toString());
						studentStudySystem = studentDetail.get(0)[17].toString();
						dbPassWord = studentDetail.get(0)[18].toString();
						eduStatus = studentDetail.get(0)[19].toString();
						lockStatus = Integer.parseInt(studentDetail.get(0)[20].toString());
						feeId = (studentDetail.get(0)[21] != null) ? Integer.parseInt(studentDetail.get(0)[21].toString()) : 0;
						eduStatusExpn = studentDetail.get(0)[22].toString();
						
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
							throw new BadCredentialsException("Invalid username / password");
						}
					}
					else
					{
						throw new BadCredentialsException("Invalid username / password");
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
					throw new BadCredentialsException("Your account is locked [Reason: "+ eduStatusExpn +"].");
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
						throw new BadCredentialsException("Your are not eligible for Add or Drop.");
					}
				}
			}
			
			//Authenticate the user
			if ((validateCaptcha == 1) && (validateCredential == 1) && (validateAccount == 1))
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
				
				session.setAttribute("testStatus", testStatus);
				session.setAttribute("CAPTCHA", "");
				session.setAttribute("ENCDATA", "");
						
				return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
			}
		}
		catch (Exception x)
		{
			throw new BadCredentialsException(x.getMessage());
		}
				
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
