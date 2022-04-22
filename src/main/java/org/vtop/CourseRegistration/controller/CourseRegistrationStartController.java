package org.vtop.CourseRegistration.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.vtop.CourseRegistration.service.CompulsoryCourseConditionDetailService;
import org.vtop.CourseRegistration.service.CourseRegistrationCommonFunction;
import org.vtop.CourseRegistration.service.CourseRegistrationReadWriteService;
import org.vtop.CourseRegistration.service.StudentHistoryService;


@Controller
public class CourseRegistrationStartController
{
	@Autowired private CourseRegistrationCommonFunction courseRegCommonFn;
	@Autowired private StudentHistoryService studentHistoryService;
	@Autowired private CompulsoryCourseConditionDetailService compulsoryCourseConditionDetailService;
	@Autowired private CourseRegistrationReadWriteService courseRegistrationReadWriteService;

	private static final Logger logger = LogManager.getLogger(CourseRegistrationStartController.class);
	private static final String RegErrorMethod = "WS2122AD";
	
		
	@PostMapping("checkRegistration")
	public String checkRegistration(Model model,HttpSession session,HttpServletRequest request)
	{
		String urlPage = "",currentDateTimeStr="";
		Date currentDateTime = new Date();
		Integer updateStatus = 1;
		int pageAuthStatus = 2, allowStatus = 2, checkFlag = 2;
		String info="", returnVal = "", studentHistoryStatus = "", studentCgpaData = "0|0|0";
				
		List<Object[]> fGradeList = new ArrayList<Object[]>();
		List<Object[]> lcObjList = new ArrayList<Object[]>();
		List<String> compulsoryCourseList = new ArrayList<String>();
		
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress = (String) session.getAttribute("IpAddress");
		
		try
		{
			Date startDate = (Date) session.getAttribute("startDate");
			Date endDate = (Date) session.getAttribute("endDate");
			String startTime = (String) session.getAttribute("startTime");
			String endTime = (String) session.getAttribute("endTime");
			
			Integer historyCallStatus = (Integer) session.getAttribute("historyCallStatus");
			Integer cgpaStatus = (Integer) session.getAttribute("cgpaStatus");
			Integer compulsoryCourseStatus = (Integer) session.getAttribute("compulsoryCourseStatus");
			String studentDetails = (String) session.getAttribute("studentDetails");
			String studentStudySystem = (String) session.getAttribute("studentStudySystem");
			Integer regularFlag = (Integer) session.getAttribute("regularFlag");
			String oldRegisterNumber = (String) session.getAttribute("OldRegNo");
			String semesterSubId = (String) session.getAttribute("SemesterSubId");
			Integer programGroupId = (Integer) session.getAttribute("ProgramGroupId");
			String programSpecCode = (String) session.getAttribute("ProgramSpecCode");
			Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
			Integer studyStartYear = (Integer) session.getAttribute("StudyStartYear");
			Integer costCenterId = (Integer) session.getAttribute("costCenterId");
			
			@SuppressWarnings("unchecked")
			List<String> registerNumberList = (List<String>) session.getAttribute("registerNumberList");
			
			//String pageAuthKey = (String) session.getAttribute("pageAuthKey");			
			//pageAuthStatus = courseRegCommonFn.validatePageAuthKey(pageAuthKey, registerNumber, 1);
			
			//if ((registerNumber != null) && (pageAuthStatus == 1))
			if (registerNumber != null)
			{
				returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
								registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				info = statusMsg[1];
								
				if (allowStatus == 1)
				{
					if (historyCallStatus == 1) 
					{
						studentHistoryStatus = courseRegistrationReadWriteService.studentHistoryInsertProcess(registerNumber, studentStudySystem);
					}
					else
					{
						studentHistoryStatus = "SUCCESS";
					}
					logger.trace("\n studentHistoryStatus: "+ studentHistoryStatus);
					
					if (studentHistoryStatus.equals("SUCCESS"))
					{
						if (regularFlag == 1)
						{
							checkFlag = 1;
						}
						else
						{
							fGradeList = studentHistoryService.getStudentHistoryGIAndFailCourse(Arrays.asList(registerNumber, oldRegisterNumber));
							if (fGradeList.size() > 0) 
							{
								checkFlag = 1;
							}
							else
							{
								info = "You dont have F or N grade courses, so you are not eligible for Add/Drop.";
							}
						}
					}
					else
					{
						info = "You dont have grade history to proceed for Add/Drop.";
					}
				}
				
				if ((allowStatus == 1) && (checkFlag == 1))
				{
					//To get the Student CGPA Detail. 1- Dynamic/ 2- Static
					//Data: Credit Registered | Credit Earned | CGPA
					if (cgpaStatus == 1)
					{										
						studentCgpaData = studentHistoryService.studentCGPA(registerNumber, programSpecId, studentStudySystem);
						if (((studentCgpaData == null) || (studentCgpaData.equals(""))) && (registerNumberList.size() >= 2))
						{
							studentCgpaData = studentHistoryService.studentCGPA(oldRegisterNumber, programSpecId, studentStudySystem);
						}
					}
					else
					{
						lcObjList.clear();
						lcObjList = studentHistoryService.getStaticStudentCGPAFromTable(registerNumber, programSpecId);
						if ((lcObjList.isEmpty()) && (registerNumberList.size() >= 2))
				    	{
							lcObjList.clear();
							lcObjList = studentHistoryService.getStaticStudentCGPAFromTable(oldRegisterNumber, programSpecId);
				    	}
						
						if (!lcObjList.isEmpty())
				    	{
							for (Object[] e: lcObjList)
							{
								studentCgpaData = Float.parseFloat(e[0].toString()) +"|"+ Float.parseFloat(e[1].toString()) 
														+"|"+ Float.parseFloat(e[2].toString());
								break;
							}
				    	}
				    }
					logger.trace("\n studentCgpaData: "+ studentCgpaData);
													
					//Check & Assign the Compulsory Courses
					if (compulsoryCourseStatus == 1)
					{
						compulsoryCourseList = compulsoryCourseConditionDetailService.getEligibleCompulsoryCourseList(
													semesterSubId, programGroupId, studyStartYear, programSpecId, 
													registerNumberList, programSpecCode, costCenterId, 0);
					}
					logger.trace("\n compulsoryCourseList :"+ compulsoryCourseList);
					
					session.setAttribute("studentCgpaData", studentCgpaData);
					session.setAttribute("compulsoryCourseList", compulsoryCourseList);
					//session.setAttribute("pageAuthKey", courseRegCommonFn.generatePageAuthKey(registerNumber, 2));
					
					urlPage = "mainpages/MainPage";
				}
				else
				{
					//session.setAttribute("pageAuthKey", courseRegCommonFn.generatePageAuthKey(registerNumber, 1));
					urlPage = "RegistrationStart";
				}
			}
			else
			{
				model.addAttribute("flag", 1);
				courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
				urlPage = "redirectpage";
			}
			
			currentDateTimeStr = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(currentDateTime);
			model.addAttribute("CurrentDateTime", currentDateTimeStr);
			model.addAttribute("studentDetails", studentDetails);
			
			model.addAttribute("startDate", new SimpleDateFormat("dd-MMM-yyyy").format(startDate));
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
			model.addAttribute("info", info);
		}
		catch(Exception ex)
		{
			logger.trace(ex);
			
			model.addAttribute("flag", 1);
			courseRegistrationReadWriteService.addErrorLog(ex.toString(), RegErrorMethod+"CourseRegistrationStartController", 
					"checkRegistration", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";			
		}
		
		return urlPage;
	}
}
