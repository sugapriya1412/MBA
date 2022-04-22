package org.vtop.CourseRegistration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vtop.CourseRegistration.service.CourseRegistrationCommonFunction;
import org.vtop.CourseRegistration.service.CourseRegistrationReadWriteService;
import org.vtop.CourseRegistration.service.ProgrammeSpecializationCurriculumCreditService;
import org.vtop.CourseRegistration.service.RegistrationLogService;
import org.vtop.CourseRegistration.service.SemesterMasterService;
import org.vtop.CourseRegistration.service.WishlistRegistrationService;
import org.vtop.CourseRegistration.service.StudentHistoryService;


@Controller
public class CourseRegistrationLoginController 
{	
	@Autowired private CourseRegistrationCommonFunction courseRegCommonFn;
	@Autowired private StudentHistoryService studentHistoryService;
	@Autowired private ProgrammeSpecializationCurriculumCreditService programmeSpecializationCurriculumCreditService;
	@Autowired private RegistrationLogService registrationLogService;
	@Autowired private WishlistRegistrationService wishlistRegistrationService;
	@Autowired private SemesterMasterService semesterMasterService;
	@Autowired private CourseRegistrationReadWriteService courseRegistrationReadWriteService;
	
	private static final Logger logger = LogManager.getLogger(CourseRegistrationLoginController.class);
	private static final String RegErrorMethod = "WS2122AD";
	
	
	@RequestMapping("/login/success")
	public String loginSuccess(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) 
						throws ServletException, IOException, ParseException
	{	
		String registerNo = (String) session.getAttribute("RegisterNumber");
		
		//Assigning IP address
		String ipAddress = request.getRemoteAddr();
		if (request != null) {
			ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null || "".equals(ipAddress)) {
            	  ipAddress = request.getRemoteAddr();
            }
        }
		logger.trace("\n ipAddress: "+ ipAddress);
		
		String urlPage = "", msg = "", currentDateTimeStr = "";
		Date currentDateTime = new Date();
		
		try
		{
			int testStatus = (Integer) session.getAttribute("testStatus");
			
			String studentName = (String) session.getAttribute("studentName");
			int specId = (Integer) session.getAttribute("ProgramSpecId");
			String specCode = (String) session.getAttribute("ProgramSpecCode");
			String specDesc = (String) session.getAttribute("ProgramSpecDesc");
			int groupId = (Integer) session.getAttribute("ProgramGroupId");
			String programGroupCode = (String) session.getAttribute("ProgramGroupCode");				
			int studyStartYear = (Integer) session.getAttribute("StudyStartYear");
			int studentGraduateYear = (Integer) session.getAttribute("StudentGraduateYear");
			String studentStudySystem = (String) session.getAttribute("studentStudySystem");				
			String programGroupMode = (String) session.getAttribute("programGroupMode");
			String studEMailId = (String) session.getAttribute("studentEMailId");
			String costCentreCode = (String) session.getAttribute("costCentreCode");
			int costCenterId = (Integer) session.getAttribute("costCenterId");
			int feeId = (Integer) session.getAttribute("feeId");
			
			logger.trace("\n registerNo: "+ registerNo +" | specId: "+ specId +" | specCode: "+ specCode 
					+" | groupId: "+ groupId +" | programGroupCode: "+ programGroupCode 
					+" | programGroupMode: "+ programGroupMode +" | costCentreCode: "+ costCentreCode 
					+" | studyStartYear: "+ studyStartYear +" | studentStudySystem: "+ studentStudySystem
					+" | studentGraduateYear: "+ studentGraduateYear +" | studEMailId: "+ studEMailId);
			
			int regSlotCheckStatus = 2; //If Permitted Schedule-> 1: Date & Time / 2: Only Date
			int regTimeCheckStatus = 1; //Time-> 1: Open Hours/ 2: Permitted Schedule
			int wishListCheckStatus = 2; //1: Enable/ 2: Disable
			int historyCallStatus = 2; //Student History-> 1: Procedure/ 2: Table
			int cgpaStatus = 1; //Student CGPA & Credit Detail-> 1: Dynamic/ 2: Static
			
			int PEUEAllowStatus = 1; //PE & UE Category Allow Status-> 1: Enable/ 2: Disable
			int approvalStatus = 1; //Registration Status Approval-> 1: Enable/ 2: Disable
			int waitingListStatus = 2; //Waiting List Allow Status-> 1: Enable/ 2: Disable
			int OptionNAStatus = 1; //Option Not Allowed Status-> 1: Enable/ 2: Disable
			int compulsoryCourseStatus = 1; //Compulsory Course Allow Status-> 1: Enable/ 2: Disable
			int otpStatus = 2; //OTP Send Status-> 1: Enable/ 2: Disable
			
			int maxCredit = 27, minCredit = 16, academicYear = 0, academicGraduateYear = 0, cclTotalCredit = 0, 
					activeStatus = 2, allowStatus = 2;
			int checkFlag = 2, checkFlag2 = 2, checkFlag3 = 2, checkFlag4 = 2, checkFlag5 = 2, checkFlag6 = 2, 
					checkFlag7 = 2;
			Integer updateStatus = 0, exemptionStatus = 0, regularFlag = 2, reRegFlag = 2, schStatus = 0, 
						wsltCount = 0, graduationStatus = 0;
			float cclVersion = 0;
			
			
			String semesterSubId = "", semesterShortDesc = "", semesterDesc = "", sessioncaptchaString = "", 
						registrationMethod = "GEN", classGroupId = "";
			String courseEligible = "", CGPAEligible = "", oldRegNo = "", studentCategory = "", returnVal = "", 
						checkCourseSystem = "";
					
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");		
			Date startDate = format.parse("29-MAR-2022");
			Date endDate = format.parse("01-MAY-2022");
			String startTime = "10:00:00", endTime = "23:59:59", allowStartTime = "10:00:00";
			
			String[] statusMsg = new String[]{};
			String[] egbProgram = {};
			String[] courseSystem = new String[2];
			String[] registerNumberArray = new String[2];
			List<Integer> egbProgramInt = new ArrayList<Integer>();
			List<String> registerNumberList = new ArrayList<String>();
					
			Integer semesterId = 0,  wlCount = 0, regCount = 0;
			Integer regCredit = 0, wlCredit= 0;
					
			List<Object[]> lcObjList = new ArrayList<Object[]>();
						
			//For getting captcha from session attribute					
			sessioncaptchaString = (String) session.getAttribute("CAPTCHA");
			logger.trace("\n sessioncaptchaString: "+ sessioncaptchaString);
			
			//Checking the Registration Date/Time Duration based on Slot or General
			if (regTimeCheckStatus == 2)
			{
				msg = courseRegCommonFn.registrationSessionDateTimeCheck(startDate);
				if (msg.equals("SUCCESS"))
				{
					checkFlag = 1;
				}
			}
			else
			{
				returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, allowStartTime, endTime, 
								registerNo, updateStatus, ipAddress);
				statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				msg = statusMsg[1];
				logger.trace("\n allowStatus: "+ allowStatus +" | msg: "+ msg);
				
				if (allowStatus == 1)
				{
					checkFlag = 1;
					msg = "";
				}
				else
				{
					msg = msg.replace(allowStartTime, startTime);
				}
			}
																		
			//Checking whether the Register No. is existed or not
			if (checkFlag == 1)
			{
				//Semester Sub Id Assignment
				if (programGroupCode.equals("MBA") || programGroupCode.equals("MBA5")) 
				{
					semesterSubId = "NONE";
				}
				else if (programGroupCode.equals("RP") && costCentreCode.equals("VITBS"))
				{
					semesterSubId = "NONE";
				}
				else
				{
					semesterSubId = "VL20212205";						
				}
				logger.trace("\n semesterSubId: "+ semesterSubId);

				//Semester Sub Id Details
				lcObjList.clear();
				lcObjList = semesterMasterService.getSemesterDetailBySemesterSubId2(semesterSubId);
				if(!lcObjList.isEmpty())
				{
					for (Object[] e : lcObjList)
					{
						semesterId = Integer.parseInt(e[0].toString());
						academicYear = Integer.parseInt(e[5].toString());
						academicGraduateYear = Integer.parseInt(e[6].toString());
						semesterDesc = e[1].toString();
						semesterShortDesc = e[2].toString();
						break;
					}
					classGroupId = "ALL03";
				}
				logger.trace("\n semesterId: "+ semesterId +" | academicYear: "+ academicYear 
						+" | academicGraduateYear: "+ academicGraduateYear +" | classGroupId: "+ classGroupId);
					
				//Student Credit Transfer Detail
				oldRegNo = semesterMasterService.getStudentCreditTransferOldRegisterNumberByRegisterNumber(registerNo);					
				if ((oldRegNo != null) && (!oldRegNo.equals("")))
				{
					registerNumberList.add(registerNo);
					registerNumberList.add(oldRegNo);
					
					registerNumberArray[0] = registerNo;
					registerNumberArray[1] = oldRegNo;
				}
				else
				{
					registerNumberList.add(registerNo);
					
					registerNumberArray[0] = registerNo;
					registerNumberArray[1] = "";
				}
				logger.trace("\n registerNumberList: "+ registerNumberList +" | registerNumberArray: "+ registerNumberArray.toString());
				
				//Get the ExemptionStatus
				exemptionStatus = registrationLogService.getRegistrationExemptionReasonTypeBySemesterSubIdAndRegisterNumber(
										semesterSubId, registerNo);
					
				//Student Graduation status
				graduationStatus = studentHistoryService.getGraduationValue(registerNumberList);
					
				//Student study system (i.e. FFCS or CAL or General)
				if (programGroupMode.equals("Twinning (1 Year)") || programGroupMode.equals("Twinning (2 Year)") 
						|| programGroupMode.equals("Twinning (3 Year)"))
				{
					programGroupMode = "Twinning";
				}
										
				//Regular Flag Assignment
				regularFlag = courseRegCommonFn.getCourseStatusOrCount(1, programGroupCode, specCode, studentGraduateYear, 
									academicGraduateYear, semesterId, semesterSubId, studyStartYear);
				reRegFlag = courseRegCommonFn.getCourseStatusOrCount(2, programGroupCode, specCode, studentGraduateYear, 
								academicGraduateYear, semesterId, semesterSubId, studyStartYear);
										
				logger.trace("\n exemptionStatus: "+ exemptionStatus +" | programGroupMode: "+ programGroupMode
						+" | registrationMethod: "+ registrationMethod +" | studentStudySystem: "+ studentStudySystem 
						+" | regularFlag: "+ regularFlag +" | reRegFlag: "+ reRegFlag);
					
				if (graduationStatus == 0) 
				{
					checkFlag2 = 1;
				}
				else if (graduationStatus > 0) 
				{
					msg = "Your are eligible for graduation.  Not allowed for Add or Drop.";
				}
								
				if (checkFlag2 == 1)
				{
					checkFlag2 = 2;
					
					if (programGroupMode.equals("Regular") || programGroupMode.equals("Twinning"))
					{
						checkFlag2 = 1;
					}
					else
					{
						msg = "Only regular students are eligible for Add or Drop.";
					}
				}
			}
						
			//Checking the Allowed Admission Year/ Programme Group/ Programme Specialization
			if (checkFlag2 == 1)
			{
				if (studyStartYear == academicYear)
				{
					if (programGroupCode.equals("BBA") || programGroupCode.equals("BCA") 
							|| programGroupCode.equals("BCOM") || programGroupCode.equals("BSC") 
							|| programGroupCode.equals("BSC4") || programGroupCode.equals("BDES") 
							|| programGroupCode.equals("MDES") || programGroupCode.equals("BARCH")
							|| programGroupCode.equals("BVOC") 
							|| programGroupCode.equals("MBA") || programGroupCode.equals("MBA5") 
							|| (programGroupCode.equals("RP") && costCentreCode.equals("VITBS"))
							|| (programGroupCode.equals("BTECH") && specCode.equals("BBS")))
					{
						msg = programGroupCode +" - "+ specDesc +" students are not allowed for Add or Drop.";
					}
					else
					{
						checkFlag3 = 1;
					}
				}
				else
				{
					if (academicYear == 0)
					{
						msg = "You are not eligible for Add or Drop.";
					}
					else
					{
						msg = studyStartYear +" students are not allowed for Add or Drop.";
					}
				}
			}
			
			//Checking the Registration is based on Open Hours or Scheduled Date/Time
			if (checkFlag3 == 1)
			{	
				if (regTimeCheckStatus == 2)
				{
					lcObjList.clear();
					lcObjList = semesterMasterService.getRegistrationScheduleByRegisterNumber(registerNo);
					if(!lcObjList.isEmpty())
					{
						for (Object[] e: lcObjList)
						{
							startDate = format.parse(e[0].toString());
							endDate = startDate;
							schStatus = Integer.parseInt(e[4].toString());
							
							if (regSlotCheckStatus == 1)
							{
								startTime = e[1].toString();
								endTime = e[2].toString();
								allowStartTime = e[3].toString();
							}
							
							break;
						}

						if (schStatus == 0)
						{
							checkFlag4 = 1;
						}
						else
						{
							checkFlag4 = 2;
							msg = "Your are not allowed for Course Registration.";
						}
					}
					else
					{
						checkFlag4 = 2;
						msg = "Your dont have Registration Schedule.";
					}
				}
				else
				{
					checkFlag4 = 1;
				}
				
				if (checkFlag4 == 1)
				{
					checkFlag4 = 2;
					returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, allowStartTime, endTime, 
									registerNo, updateStatus, ipAddress);
					statusMsg = returnVal.split("/");
					allowStatus = Integer.parseInt(statusMsg[0]);
					msg = statusMsg[1];
					logger.trace("\n allowStatus: "+ allowStatus +" | msg: "+ msg);
	
					if (allowStatus == 1)
					{
						checkFlag4 = 1;
						msg = "";
					}
					else
					{
						msg = msg.replace(allowStartTime, startTime);
					}
				}
			}
								
			//Checking the Student Eligibility Criteria
			if (checkFlag4 == 1)
			{
				lcObjList.clear();
				lcObjList = semesterMasterService.getCourseEligibleProgramByProgGroupId(groupId);
				if (!lcObjList.isEmpty())
				{
					for (Object[] e :lcObjList)
					{
						courseEligible = e[0].toString();
						CGPAEligible = (e[1] != null)?e[1].toString():"";
						break;
					}
					checkFlag5 = 1;
				}
				else
				{
					msg = "Your are not eligible for registration.";	
				}
			}
						
			//Checking whether the Student is already login or not. 
			if (checkFlag5 == 1)
			{									
				if (testStatus == 2)
				{
					checkFlag6 = 1;
				}
				else
				{
					activeStatus = courseRegCommonFn.ActivePresentDateTimeCheck(registerNo);
					if (activeStatus == 1) 
					{
						checkFlag6 = 1;
					}
					else
					{
						msg = "You have already logged in (or) not properly logged out.  Try again after 5 minutes.";
					}
				}
			}
					
			//Checking the Wishlist Registration
			if (checkFlag6 == 1)
			{
				if (wishListCheckStatus == 1)
				{
					if (programGroupCode.equals("RP") || (studentGraduateYear <= academicGraduateYear))
					{
						checkFlag7 = 1;
					}
					else if ((exemptionStatus == 1) || (exemptionStatus == 3))
					{
						checkFlag7 = 1;
					}
					else
					{
						wsltCount = wishlistRegistrationService.getRegisterNumberTCCount2(semesterSubId, 
										new String[]{Arrays.toString(classGroupId.split("/"))}, registerNo);
						if (wsltCount >= 1)
						{
							checkFlag7 = 1;
						}
						else
						{
							msg = "You did not register any course in Wishlist.  So you are not eligible for Add or Drop.";
						}
					}
				}
				else
				{
					checkFlag7 = 1;
				}
			}			
			logger.trace("\n checkFlag: "+ checkFlag +" | checkFlag2: "+ checkFlag2 +" | checkFlag3: "+ checkFlag3 
					+" | checkFlag4: "+ checkFlag4 +" | checkFlag5: "+ checkFlag5 +" | checkFlag6: "+ checkFlag6 
					+" | checkFlag7: "+ checkFlag7);
			
			if ((checkFlag == 1) && (checkFlag2 == 1) && (checkFlag3 == 1) && (checkFlag4 == 1) 
					&& (checkFlag5 == 1) && (checkFlag6 == 1) && (checkFlag7 == 1))
			{				
				String studentDetails = registerNo +" - "+ studentName +" - "+ specCode +" - "+ specDesc +" - "+ programGroupCode;
				
				//Eligible program id split
				egbProgram =  courseEligible.split("/");
				for (String e: egbProgram)
				{
					egbProgramInt.add(Integer.parseInt(e));									
				}
				logger.trace("\n egbProgram: "+ egbProgram +" | egbProgramInt: "+ egbProgramInt);
												
				//Fixing the Minimum & Maximum credit
				String[] creditLimitArr = courseRegCommonFn.getMinimumAndMaximumCreditLimit(semesterSubId, registerNo, 
												programGroupCode, costCentreCode, studyStartYear, studentGraduateYear, 
												academicGraduateYear, semesterId, specCode).split("\\|");
				minCredit = Integer.parseInt(creditLimitArr[0]);
				maxCredit = Integer.parseInt(creditLimitArr[1]);
				logger.trace("\n minCredit: "+ minCredit +" | maxCredit: "+ maxCredit);
												
				currentDateTimeStr = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(currentDateTime);	

				//Getting Curriculum Detail
				lcObjList.clear();
				lcObjList = programmeSpecializationCurriculumCreditService.getMaxVerDetailBySpecIdAndAdmYear2(specId, studyStartYear);
				if (!lcObjList.isEmpty())
				{
					for (Object[] e :lcObjList)
					{
						cclVersion = Float.parseFloat(e[0].toString());
						cclTotalCredit = Integer.parseInt(e[5].toString());
						checkCourseSystem = e[8].toString();
						break;
					}
				}
				logger.trace("\n curriculumVersion: "+ cclVersion +" | cclTotalCredit: "+ cclTotalCredit 
						+" | checkCourseSystem: "+ checkCourseSystem);
				
				
				//Check & Assign the course system
				if (programGroupCode.equals("RP") || programGroupCode.equals("IEP"))
				{
					courseSystem[0] = "FFCS";
					courseSystem[1] = "CAL";
					
					registrationMethod = "FFCS";
				}
				else
				{
					if ((checkCourseSystem != null) && (!checkCourseSystem.equals("")) 
							 && (!checkCourseSystem.equals("FFCS")) && (!checkCourseSystem.equals("NONFFCS")))
					{
						courseSystem[0] = checkCourseSystem;
						courseSystem[1] = "NONE";
						
						registrationMethod = "CAL";
					}
					else
					{
						courseSystem[0] = "FFCS";
						courseSystem[1] = "NONE";
						
						registrationMethod = "FFCS";
					}
				}
				logger.trace("\n courseSystem: "+ Arrays.toString(courseSystem) 
						+" | registrationMethod (After): "+ registrationMethod);
				
								
				//Login status update
				lcObjList.clear();
				lcObjList = registrationLogService.getRegistrationLogByRegisterNumber(registerNo);
				if (lcObjList.isEmpty())
				{
					courseRegistrationReadWriteService.addRegistrationLog(registerNo, ipAddress);
				}
				else
				{
					courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(ipAddress, registerNo);
				}
				
				//To get the student category
				lcObjList.clear();
				lcObjList = semesterMasterService.getStudentFeeCategoryByFeeIdAndSpecId(feeId, specId);
				if (!lcObjList.isEmpty())
				{
					for (Object[] e: lcObjList)
					{
						studentCategory = e[3].toString();
						break;
					}
				}
				logger.trace("\n studentCategory: "+ studentCategory);
				
				//Cookie assignment
				Cookie cookie = new Cookie("RegisterNumber", registerNo);
				cookie.setSecure(true);
				cookie.setHttpOnly(true);
				cookie.setMaxAge(-1);
				response.addCookie(cookie);
								
				//Session assignment
				session.setMaxInactiveInterval(15 * 60);
				
				if (egbProgramInt.size()>0)
				{
					session.setAttribute("EligibleProgramLs", egbProgramInt);									
				}
				
				session.setAttribute("SemesterSubId", semesterSubId);
				session.setAttribute("SemesterId", semesterId);
				session.setAttribute("SemesterDesc", semesterDesc);
				session.setAttribute("SemesterShortDesc", semesterShortDesc);
				session.setAttribute("IpAddress", ipAddress);
				
				session.setAttribute("RegisterNumber", registerNo);
				session.setAttribute("registerNumberList", registerNumberList);
				session.setAttribute("registerNumberArray", registerNumberArray);
				session.setAttribute("ProgramSpecId", specId);
				session.setAttribute("ProgramSpecCode", specCode);
				
				session.setAttribute("ProgramGroupId", groupId);
				session.setAttribute("ProgramGroupCode", programGroupCode);				
				session.setAttribute("StudyStartYear", studyStartYear);
				session.setAttribute("StudentGraduateYear", studentGraduateYear);
				
												
				session.setAttribute("OldRegNo", oldRegNo);
				session.setAttribute("registrationMethod", registrationMethod);
				session.setAttribute("minCredit", minCredit);
				session.setAttribute("maxCredit", maxCredit);
				session.setAttribute("classGroupId", classGroupId);
				session.setAttribute("StudySystem", courseSystem);				
				session.setAttribute("EligibleProgram", courseEligible);
				session.setAttribute("CGPAProgram", CGPAEligible);				
				session.setAttribute("curriculumVersion", cclVersion);
				
				session.setAttribute("cclTotalCredit", cclTotalCredit);
				session.setAttribute("studentDetails", studentDetails);
				session.setAttribute("studentStudySystem", studentStudySystem);				
				session.setAttribute("programGroupMode", programGroupMode);
				session.setAttribute("studentCategory", studentCategory);
				session.setAttribute("studentEMailId", studEMailId);
				session.setAttribute("acadGraduateYear", academicGraduateYear);
				
				session.setAttribute("costCentreCode", costCentreCode);
				session.setAttribute("costCenterId", costCenterId);
				session.setAttribute("startDate", startDate);
				session.setAttribute("endDate", endDate);
				session.setAttribute("startTime", startTime);
				session.setAttribute("endTime", endTime);
				
				session.setAttribute("testStatus", testStatus);
				session.setAttribute("regularFlag", regularFlag);
				session.setAttribute("reRegFlag", reRegFlag);
				session.setAttribute("PEUEAllowStatus", PEUEAllowStatus);
				session.setAttribute("approvalStatus", approvalStatus);
				session.setAttribute("waitingListStatus", waitingListStatus);
				session.setAttribute("OptionNAStatus", OptionNAStatus);
				session.setAttribute("compulsoryCourseStatus", compulsoryCourseStatus);
				session.setAttribute("otpStatus", otpStatus);
				session.setAttribute("historyCallStatus", historyCallStatus);
				session.setAttribute("cgpaStatus", cgpaStatus);
								
				//session.setAttribute("pageAuthKey", courseRegCommonFn.generatePageAuthKey(registerNo, 1));
				session.setAttribute("corAuthStatus", "NONE");
				session.setAttribute("authStatus", "NONE");
								
				//Clear the captcha string & image
				session.setAttribute("CAPTCHA", "");
				session.setAttribute("ENCDATA", "");
				
				model.addAttribute("studySystem", courseSystem);				
				model.addAttribute("regCredit", regCredit);
				model.addAttribute("regCount", regCount);
				model.addAttribute("wlCount", wlCount);
				model.addAttribute("maxCredit", maxCredit);
				model.addAttribute("wlCredit", wlCredit);
				
				model.addAttribute("regularFlag", regularFlag);
				model.addAttribute("CurrentDateTime", currentDateTimeStr);	
				model.addAttribute("studentDetails", studentDetails);
								
				model.addAttribute("startDate", new SimpleDateFormat("dd-MMM-yyyy").format(startDate));
				model.addAttribute("startTime", startTime);
				model.addAttribute("endTime", endTime);
				
				urlPage = "RegistrationStart";
			}
			else
			{
				courseRegCommonFn.callCaptcha(request,response,session,model);				
				urlPage = "StudentLogin";				
			}			
		}
		catch(Exception e)
		{
			logger.trace(e);
			
			//Clear the captcha string & image
			session.setAttribute("CAPTCHA", "");
			session.setAttribute("ENCDATA", "");
			
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationLoginController", 
					"processStudentLogin", registerNo, ipAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(ipAddress, registerNo);
			
			courseRegCommonFn.callCaptcha(request, response, session, model);
			msg = "Invalid Details.";
			urlPage = "StudentLogin";
			
			return urlPage;
		}
		
		currentDateTimeStr = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(currentDateTime);
		model.addAttribute("CurrentDateTime", currentDateTimeStr);
		model.addAttribute("info", msg);
		
		return urlPage;		
	}	
}
