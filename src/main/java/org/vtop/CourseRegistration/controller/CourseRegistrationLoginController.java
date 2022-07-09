package org.vtop.CourseRegistration.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.vtop.CourseRegistration.mongo.model.CourseEligible;
import org.vtop.CourseRegistration.mongo.model.ProgramSpecializationCurriculumCredit;
import org.vtop.CourseRegistration.mongo.model.RegistrationSchedule;
import org.vtop.CourseRegistration.mongo.model.SemesterDetail;
import org.vtop.CourseRegistration.mongo.model.StudentDetailOthers;
import org.vtop.CourseRegistration.mongo.service.CourseRegistrationCommonMongoService;
import org.vtop.CourseRegistration.service.CourseRegistrationCommonFunction;
import org.vtop.CourseRegistration.service.CourseRegistrationReadWriteService;
import org.vtop.CourseRegistration.service.RegistrationLogService;


@Controller
public class CourseRegistrationLoginController 
{	
	@Autowired private CourseRegistrationCommonFunction courseRegCommonFn;
	//@Autowired private ProgrammeSpecializationCurriculumCreditService programmeSpecializationCurriculumCreditService;
	@Autowired private RegistrationLogService registrationLogService;
	//@Autowired private WishlistRegistrationService wishlistRegistrationService;
	//@Autowired private SemesterMasterService semesterMasterService;
	@Autowired private CourseRegistrationReadWriteService courseRegistrationReadWriteService;
	@Autowired private CourseRegistrationCommonMongoService courseRegistrationCommonMongoService;
	
	private static final Logger logger = LogManager.getLogger(CourseRegistrationLoginController.class);
	private static final String RegErrorMethod = "FS2223REG";
	
	
	@RequestMapping("/login/success")
	public String loginSuccess(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) 
						throws ServletException, IOException, ParseException
	{	
		String registerNo = (String) session.getAttribute("RegisterNumber");
		String ipAddress = (String) session.getAttribute("IpAddress");
		//logger.trace("\n registerNo: "+ registerNo +" | ipAddress: "+ ipAddress);
		
		String urlPage = "", msg = "", currentDateTimeStr = "";
		Date currentDateTime = new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		try
		{
			int testStatus = (Integer) session.getAttribute("testStatus");
			int regTimeCheckStatus = (Integer) session.getAttribute("regTimeCheckStatus");
			Date startDate = (Date) session.getAttribute("startDate");
			Date endDate = (Date) session.getAttribute("endDate");
			String startTime = (String) session.getAttribute("startTime");
			String endTime = (String) session.getAttribute("endTime");
			String allowStartTime = (String) session.getAttribute("allowStartTime");
						
			//logger.trace("\n testStatus: "+ testStatus +" | regTimeCheckStatus: "+ regTimeCheckStatus  
			//		+" | startDate: "+ startDate +" | endDate: "+ endDate +" | startTime: "+ startTime 
			//		+" | endTime: "+ endTime +" | allowStartTime: "+ allowStartTime +" | ipAddress: "+ ipAddress);
			
			String studentName = (String) session.getAttribute("studentName");
			int specId = (Integer) session.getAttribute("ProgramSpecId");
			String specCode = (String) session.getAttribute("ProgramSpecCode");
			String specDesc = (String) session.getAttribute("ProgramSpecDesc");
			int groupId = (Integer) session.getAttribute("ProgramGroupId");
			String programGroupCode = (String) session.getAttribute("ProgramGroupCode");				
			int studyStartYear = (Integer) session.getAttribute("StudyStartYear");
			int studentGraduateYear = (Integer) session.getAttribute("StudentGraduateYear");
			//String studentStudySystem = (String) session.getAttribute("studentStudySystem");				
			String programGroupMode = (String) session.getAttribute("programGroupMode");
			//String studEMailId = (String) session.getAttribute("studentEMailId");
			String costCentreCode = (String) session.getAttribute("costCentreCode");
			int adminAuthenticationStatus = (Integer) session.getAttribute("adminAuthenticationStatus");
			
			//logger.trace("\n registerNo: "+ registerNo +" | specId: "+ specId +" | specCode: "+ specCode 
			//		+" | groupId: "+ groupId +" | programGroupCode: "+ programGroupCode 
			//		+" | programGroupMode: "+ programGroupMode +" | costCentreCode: "+ costCentreCode 
			//		+" | studyStartYear: "+ studyStartYear +" | studentStudySystem: "+ studentStudySystem
			//		+" | studentGraduateYear: "+ studentGraduateYear +" | studEMailId: "+ studEMailId);
			
			int regSlotCheckStatus = 2; //If Permitted Schedule-> 1: Date & Time / 2: Only Date
			int historyCallStatus = 2; //Student History-> 1: Procedure/ 2: Table
			int cgpaStatus = 2; //Student CGPA & Credit Detail-> 1: Dynamic/ 2: Static
			int wishListCheckStatus = 2; //Wish list Check Status-> 1: Enable/ 2: Disable
			int PEUEAllowStatus = 1; //PE & UE Category Allow Status-> 1: Enable/ 2: Disable
			int approvalStatus = 2; //Registration Status Approval-> 1: Enable/ 2: Disable
			int waitingListStatus = 2; //Waiting List Allow Status-> 1: Enable/ 2: Disable
			int OptionNAStatus = 1; //Option Not Allowed Status-> 1: Enable/ 2: Disable
			int compulsoryCourseStatus = 1; //Compulsory Course Allow Status-> 1: Enable/ 2: Disable
			int otpStatus = 2; //OTP Send Status-> 1: Enable/ 2: Disable
			
			int maxCredit = 27, minCredit = 16, academicYear = 0, academicGraduateYear = 0, cclTotalCredit = 0, 
					activeStatus = 2, allowStatus = 2;
			int checkFlag = 2, checkFlag2 = 2, checkFlag3 = 2, checkFlag4 = 2, checkFlag5 = 2, checkFlag6 = 2;
			Integer exemptionStatus = 0, regularFlag = 2, reRegFlag = 2, schStatus = 0, graduationStatus = 0, 
						studentWishListStatus = 2;
			Integer semesterId = 0,  wlCount = 0, regCount = 0;
			Integer regCredit = 0, wlCredit= 0;
			float cclVersion = 0;
						
			String semesterSubId = "", semesterShortDesc = "", semesterDesc = "", sessioncaptchaString = "", 
						registrationMethod = "GEN", classGroupId = "", studentCgpaData = "0|0|0";
			String courseEligible = "", CGPAEligible = "", oldRegNo = "", returnVal = "", checkCourseSystem = "";
			
			String[] statusMsg = new String[]{};
			String[] egbProgram = {};
			String[] courseSystem = new String[2];
			String[] registerNumberArray = new String[2];
			List<Integer> egbProgramInt = new ArrayList<Integer>();
			List<String> registerNumberList = new ArrayList<String>();
			List<Object[]> objectList = new ArrayList<Object[]>();
			List<String> compulsoryCourseList = new ArrayList<String>();
			
			SemesterDetail semesterDetail = null;
			StudentDetailOthers studentDetailOthers = null;
			CourseEligible courseEligible2 = null;
			ProgramSpecializationCurriculumCredit programSpecializationCurriculumCredit = null;
			RegistrationSchedule registrationSchedule = null;
						
			//For getting captcha from session attribute					
			sessioncaptchaString = (String) session.getAttribute("CAPTCHA");
			logger.trace("\n sessioncaptchaString: "+ sessioncaptchaString);
																								
			//Semester Sub Id Assignment & Student Graduation Status
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
				semesterSubId = "VL20222301";						
			}
			//logger.trace("\n semesterSubId: "+ semesterSubId);

			//Semester Sub Id Details
			/*objectList.clear();
			objectList = semesterMasterService.getSemesterDetailBySemesterSubId2(semesterSubId);
			if (!objectList.isEmpty())
			{				
				semesterId = Integer.parseInt(objectList.get(0)[0].toString());
				academicYear = Integer.parseInt(objectList.get(0)[5].toString());
				academicGraduateYear = Integer.parseInt(objectList.get(0)[6].toString());
				semesterDesc = objectList.get(0)[1].toString();
				semesterShortDesc = objectList.get(0)[2].toString();
				
				classGroupId = "ALL";
			}*/
			
			semesterDetail = courseRegistrationCommonMongoService.getSemesterDetailBySemesterSubId(semesterSubId);
			if (semesterDetail != null)
			{
				semesterId = semesterDetail.getSemesterId();
				academicYear = semesterDetail.getAcademicYear();
				academicGraduateYear = semesterDetail.getGraduateYear();
				semesterDesc = semesterDetail.getDescription();
				semesterShortDesc = semesterDetail.getDescriptionShort();
				
				classGroupId = "ALL";
			}
			//logger.trace("\n semesterId: "+ semesterId +" | academicYear: "+ academicYear 
			//		+" | academicGraduateYear: "+ academicGraduateYear +" | semesterDesc: "+ semesterDesc 
			//		 +" | semesterShortDesc: "+ semesterShortDesc +" | classGroupId: "+ classGroupId);
					
			
			//Student Credit Transfer Detail/ Registration Exemption Status/ Graduation status
			/*objectList.clear();
			objectList = semesterMasterService.getStudentDetailOthersByRegisterNumberAndSemesterSubId(registerNo, semesterSubId);
			if (!objectList.isEmpty())
			{
				logger.trace("\n objectList: "+ Arrays.deepToString(objectList.get(0)));
				
				oldRegNo = (objectList.get(0)[1] == null) ? "" : objectList.get(0)[1].toString();
				graduationStatus = Integer.parseInt(objectList.get(0)[2].toString());
				exemptionStatus = Integer.parseInt(objectList.get(0)[3].toString());
			}*/
			
			studentDetailOthers = courseRegistrationCommonMongoService.getStudentDetailOthersByRegisterNumber(registerNo);
			if (studentDetailOthers != null)
			{
				oldRegNo = studentDetailOthers.getOldRegisterNumber();
				exemptionStatus = studentDetailOthers.getRegistrationExcemptionStatus();
				graduationStatus = studentDetailOthers.getExamGraduationStatus();
				studentCgpaData = studentDetailOthers.getTotalCreditRegistered() +"|"+ studentDetailOthers.getTotalCreditEarned() 
										+"|"+ studentDetailOthers.getCumulativeGradePointAverage();
				
				studentWishListStatus = (studentDetailOthers.getWishlistStatus() == null) ? 2 : studentDetailOthers.getWishlistStatus();
				
				//if ((compulsoryCourseStatus == 1) && (studentDetailOthers.getCompulsoryCourse() != null) 
				//		&& (!studentDetailOthers.getCompulsoryCourse().equals("")))
				//{
				//	compulsoryCourseList.addAll(Arrays.asList(studentDetailOthers.getCompulsoryCourse().split("\\|")));
				//}
			}
			//logger.trace("\n oldRegNo: "+ oldRegNo +" | graduationStatus: "+ graduationStatus 
			//			+" | exemptionStatus: "+ exemptionStatus);
			
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
			//logger.trace("\n registerNumberList: "+ registerNumberList +" | registerNumberArray: "+ Arrays.toString(registerNumberArray));
			
				
			//Student study system (i.e. FFCS or CAL or General)
			if (programGroupMode.equals("Twinning (1 Year)") || programGroupMode.equals("Twinning (2 Year)") 
					|| programGroupMode.equals("Twinning (3 Year)"))
			{
				programGroupMode = "Twinning";
			}
									
			//Regular Flag Assignment
			String[] courseOptionStatusArray = courseRegCommonFn.getCourseOptionStatus(programGroupCode, specCode, studentGraduateYear, 
													academicGraduateYear, semesterId, semesterSubId, studyStartYear).split("\\|");
			if ((courseOptionStatusArray != null) && (courseOptionStatusArray.length > 0))
			{
				regularFlag = Integer.parseInt(courseOptionStatusArray[0]);
				reRegFlag = Integer.parseInt(courseOptionStatusArray[1]);
			}
									
			//logger.trace("\n exemptionStatus: "+ exemptionStatus +" | programGroupMode: "+ programGroupMode
			//		+" | registrationMethod: "+ registrationMethod +" | studentStudySystem: "+ studentStudySystem 
			//		+" | regularFlag: "+ regularFlag +" | reRegFlag: "+ reRegFlag);
				
			if (graduationStatus == 0) 
			{
				checkFlag = 1;
			}
			else if (graduationStatus > 0) 
			{
				msg = "Your are eligible for graduation.  Not allowed for Registration.";
			}
							
			if (checkFlag == 1)
			{
				checkFlag = 2;
				
				if (programGroupMode.equals("Regular") || programGroupMode.equals("Twinning"))
				{
					checkFlag = 1;
				}
				else
				{
					msg = "Only regular students are eligible for Registration.";
				}
			}			
						
			//Checking the Allowed Admission Year/ Programme Group/ Programme Specialization
			if (checkFlag == 1)
			{
				if ((studyStartYear > 0) && (academicYear > 0) && (studyStartYear < academicYear))
				{	
					if (programGroupCode.equals("BSC4") || programGroupCode.equals("BDES") 
							|| programGroupCode.equals("BARCH") || programGroupCode.equals("BVOC") 
							|| programGroupCode.equals("MBA") || programGroupCode.equals("MBA5") 
							|| programGroupCode.equals("MDES")
							|| (programGroupCode.equals("RP") && costCentreCode.equals("VITBS"))
							|| (programGroupCode.equals("BTECH") && specCode.equals("BBS"))
							|| (programGroupCode.equals("BSC") && (specCode.equals("BAM") 
									|| specCode.equals("BHM") || specCode.equals("BVC"))))
					{
						msg = programGroupCode +" - "+ specDesc +" students are not allowed for Registration.";
					}
					else
					{
						checkFlag2 = 1;
					}
				}
				else
				{
					msg = "Not allowed for Registration.";
				}
			}
			
			//Checking the Registration is based on Open Hours or Scheduled Date/Time
			if (checkFlag2 == 1)
			{	
				if ((regTimeCheckStatus == 2) && (adminAuthenticationStatus == 2))
				{
					/*objectList.clear();
					objectList = semesterMasterService.getRegistrationScheduleByRegisterNumber(registerNo);
					if (!objectList.isEmpty())
					{						
						startDate = sdf.parse(objectList.get(0)[0].toString());
						endDate = startDate;
						schStatus = Integer.parseInt(objectList.get(0)[4].toString());
						
						if (regSlotCheckStatus == 1)
						{
							startTime = objectList.get(0)[1].toString();
							endTime = objectList.get(0)[2].toString();
							allowStartTime = objectList.get(0)[3].toString();
						}

						if (schStatus == 0)
						{
							checkFlag3 = 1;
						}
						else
						{
							checkFlag3 = 2;
							msg = "Your are not allowed for Course Registration.";
						}
					}
					else
					{
						checkFlag3 = 2;
						msg = "Your dont have Registration Schedule.";
					}*/
					
					registrationSchedule = courseRegistrationCommonMongoService.getRegistrationScheduleByRegisterNumber(registerNo);
					if (registrationSchedule != null)
					{						
						startDate = sdf.parse(registrationSchedule.getRegisterDate());
						endDate = startDate;
						schStatus = registrationSchedule.getStatus();
						
						if (regSlotCheckStatus == 1)
						{
							startTime = registrationSchedule.getFromTime();
							endTime = registrationSchedule.getToTime();
							allowStartTime = startTime;
						}
						//logger.trace("\n startDate: "+ startDate +" | endDate: "+ endDate 
						//		+" | startTime: "+ startTime +" | endTime: "+ endTime 
						//		+" | allowStartTime: "+ allowStartTime);

						if (schStatus == 0)
						{
							checkFlag3 = 1;
						}
						else
						{
							checkFlag3 = 2;
							msg = "Your are not allowed for Course Registration.";
						}
					}
					else
					{
						checkFlag3 = 2;
						msg = "Your dont have Registration Schedule.";
					}
				}
				else
				{
					checkFlag3 = 1;
				}
				
				if (checkFlag3 == 1)
				{
					checkFlag3 = 2;
					returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, allowStartTime, endTime, 
									registerNo, 0, ipAddress);
					statusMsg = returnVal.split("/");
					allowStatus = Integer.parseInt(statusMsg[0]);
					msg = statusMsg[1];
					//logger.trace("\n allowStatus: "+ allowStatus +" | msg: "+ msg);
	
					if (allowStatus == 1)
					{
						checkFlag3 = 1;
						msg = "";
					}
					else
					{
						msg = msg.replace(allowStartTime, startTime);
					}
				}
			}
								
			//Checking the Student Eligibility Criteria
			if (checkFlag3 == 1)
			{
				/*objectList.clear();
				objectList = semesterMasterService.getCourseEligibleProgramByProgGroupId(groupId);
				if (!objectList.isEmpty())
				{					
					courseEligible = objectList.get(0)[0].toString();
					CGPAEligible = (objectList.get(0)[1] != null) ? objectList.get(0)[1].toString() : "";
					checkFlag4 = 1;
				}
				else
				{
					msg = "Your are not eligible for registration.";	
				}*/
				
				courseEligible2 = courseRegistrationCommonMongoService.getCourseEligibleByProgGroupId(groupId);
				if (courseEligible2 != null)
				{
					courseEligible = courseEligible2.getProgEligible();
					CGPAEligible = (courseEligible2.getProgCGPA() != null) ? courseEligible2.getProgCGPA() : "";
					checkFlag4 = 1;
				}
				else
				{
					msg = "Your are not eligible for registration.";	
				}
			}
						
			//Checking whether the Student is already login or not. 
			if (checkFlag4 == 1)
			{									
				if (testStatus == 2)
				{
					checkFlag5 = 1;
				}
				else
				{
					activeStatus = courseRegCommonFn.ActivePresentDateTimeCheck(registerNo);
					if (activeStatus == 1) 
					{
						checkFlag5 = 1;
					}
					else
					{
						//msg = "You have already logged in (or) not properly logged out.  Try again after 5 minutes.";
						msg = "You have already logged in (or) not properly logged out.  Try again after 2 minutes.";
					}
				}
			}
					
			//Checking the Wishlist Registration
			if (checkFlag5 == 1)
			{
				if (wishListCheckStatus == 1)
				{
					if (programGroupCode.equals("RP") || (studentGraduateYear <= academicGraduateYear))
					{
						checkFlag6 = 1;
					}
					else if ((exemptionStatus == 1) || (exemptionStatus == 3))
					{
						checkFlag6 = 1;
					}
					else
					{
						/*wsltCount = wishlistRegistrationService.getRegisterNumberTCCount2(semesterSubId, 
										new String[]{Arrays.toString(classGroupId.split("/"))}, registerNo);
						if (wsltCount >= 1)
						{
							checkFlag6 = 1;
						}
						else
						{
							msg = "You did not register any course in Wishlist.  So you are not eligible for Course Registration.";
						}*/
						
						if (studentWishListStatus == 1)
						{
							checkFlag6 = 1;
						}
						else
						{
							msg = "You did not register any course in Wishlist.  So you are not eligible for Course Registration.";
						}
					}
				}
				else
				{
					checkFlag6 = 1;
				}
			}
						
			logger.trace("\n checkFlag: "+ checkFlag +" | checkFlag2: "+ checkFlag2 +" | checkFlag3: "+ checkFlag3 
					+" | checkFlag4: "+ checkFlag4 +" | checkFlag5: "+ checkFlag5 +" | checkFlag6: "+ checkFlag6);
			
			if ((checkFlag == 1) && (checkFlag2 == 1) && (checkFlag3 == 1) && (checkFlag4 == 1) && (checkFlag5 == 1) && (checkFlag6 == 1))
			{				
				String studentDetails = registerNo +" - "+ studentName +" - "+ specCode +" - "+ specDesc +" - "+ programGroupCode;
				
				//Eligible program id split
				egbProgram =  courseEligible.split("/");
				for (String e: egbProgram)
				{
					egbProgramInt.add(Integer.parseInt(e));									
				}
				//logger.trace("\n egbProgram: "+ egbProgram +" | egbProgramInt: "+ egbProgramInt);
												
				//Fixing the Minimum & Maximum credit
				String[] creditLimitArr = courseRegCommonFn.getMinimumAndMaximumCreditLimit(semesterSubId, registerNo, 
												programGroupCode, costCentreCode, studyStartYear, studentGraduateYear, 
												academicGraduateYear, semesterId, specCode).split("\\|");
				minCredit = Integer.parseInt(creditLimitArr[0]);
				maxCredit = Integer.parseInt(creditLimitArr[1]);
				//logger.trace("\n minCredit: "+ minCredit +" | maxCredit: "+ maxCredit);
												
				currentDateTimeStr = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(currentDateTime);	

				//Getting Curriculum Detail
				/*objectList.clear();
				objectList = programmeSpecializationCurriculumCreditService.getMaxVerDetailBySpecIdAndAdmYear2(specId, studyStartYear);
				if (!objectList.isEmpty())
				{						
					cclVersion = Float.parseFloat(objectList.get(0)[0].toString());
					cclTotalCredit = Integer.parseInt(objectList.get(0)[5].toString());
					checkCourseSystem = objectList.get(0)[8].toString();
				}*/
				
				programSpecializationCurriculumCredit = courseRegistrationCommonMongoService.getPrgSpecCurriculumCreditBySpecIdAndAdmissionYear
															(specId, studyStartYear);
				if (programSpecializationCurriculumCredit != null)
				{
					cclVersion = programSpecializationCurriculumCredit.getCurriculumVersion();
					cclTotalCredit = programSpecializationCurriculumCredit.getTotalCredits();
					checkCourseSystem = programSpecializationCurriculumCredit.getCourseSystem();
				}
				//logger.trace("\n curriculumVersion: "+ cclVersion +" | cclTotalCredit: "+ cclTotalCredit 
				//		+" | checkCourseSystem: "+ checkCourseSystem);
				
				
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
				//logger.trace("\n courseSystem: "+ Arrays.toString(courseSystem) 
				//		+" | registrationMethod (After): "+ registrationMethod);
				
								
				//Login status update
				objectList.clear();
				objectList = registrationLogService.getRegistrationLogByRegisterNumber(registerNo);
				if (objectList.isEmpty())
				{
					courseRegistrationReadWriteService.addRegistrationLog(registerNo, ipAddress);
				}
				else
				{
					courseRegistrationReadWriteService.updateRegistrationLogLoginTimeStamp2(ipAddress, registerNo);
				}
								
				//Cookie assignment
				Cookie cookie = new Cookie("RegisterNumber", registerNo);
				cookie.setSecure(true);
				cookie.setHttpOnly(true);
				cookie.setMaxAge(-1);
				response.addCookie(cookie);
								
				//Session assignment
				session.setMaxInactiveInterval(5 * 60);
				
				if (egbProgramInt.size()>0)
				{
					session.setAttribute("EligibleProgramLs", egbProgramInt);									
				}
				
				session.setAttribute("SemesterSubId", semesterSubId);
				session.setAttribute("SemesterId", semesterId);
				session.setAttribute("SemesterDesc", semesterDesc);
				session.setAttribute("SemesterShortDesc", semesterShortDesc);
				session.setAttribute("registerNumberList", registerNumberList);
				session.setAttribute("registerNumberArray", registerNumberArray);
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
				session.setAttribute("acadGraduateYear", academicGraduateYear);
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
				
				session.setAttribute("studentCgpaData", studentCgpaData);
				session.setAttribute("compulsoryCourseList", compulsoryCourseList);
				session.setAttribute("startDate", startDate);
				session.setAttribute("endDate", endDate);
				session.setAttribute("startTime", startTime);
				session.setAttribute("endTime", endTime);
				session.setAttribute("allowStartTime", allowStartTime);
				
				session.setAttribute("corAuthStatus", "NONE");
				session.setAttribute("authStatus", "NONE");
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
