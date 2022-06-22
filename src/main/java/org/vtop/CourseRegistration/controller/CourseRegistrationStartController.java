package org.vtop.CourseRegistration.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	@Autowired private StudentHistoryService studentHistoryService;
	@Autowired private CompulsoryCourseConditionDetailService compulsoryCourseConditionDetailService;
	@Autowired private CourseRegistrationReadWriteService courseRegistrationReadWriteService;
	@Autowired private CourseRegistrationCommonFunction courseRegCommonFn;

	private static final Logger logger = LogManager.getLogger(CourseRegistrationStartController.class);
	private static final String RegErrorMethod = "SS2122REG-T5";
	
		
	@PostMapping("checkRegistration")
	public String checkRegistration(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
		String urlPage = "",currentDateTimeStr="";
		Date currentDateTime = new Date();
		Integer updateStatus = 1;
		int allowStatus = 2, validateRRStatus = 2;
		String info="", returnVal = "", msg = "";
		List<Object[]> fGradeList = new ArrayList<>();
		
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress = (String) session.getAttribute("IpAddress");
		
		try
		{
			Date startDate = (Date) session.getAttribute("startDate");
			Date endDate = (Date) session.getAttribute("endDate");
			String startTime = (String) session.getAttribute("startTime");
			String endTime = (String) session.getAttribute("endTime");
			
			String studentDetails = (String) session.getAttribute("studentDetails");
			Integer regularFlag = (Integer) session.getAttribute("regularFlag");
			@SuppressWarnings("unchecked")
			List<String> registerNumberList = (List<String>) session.getAttribute("registerNumberList");
			
			if (registerNumber != null)
			{
				returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
								registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				info = statusMsg[1];
								
				if (allowStatus == 1)
				{										
					if (regularFlag == 1)
					{
						validateRRStatus = 1;
					}
					else
					{
						fGradeList = studentHistoryService.getStudentHistoryGIAndFailCourse(registerNumberList);
						if (fGradeList.size() > 0) 
						{
							validateRRStatus = 1;
						}
						else
						{
							msg = "You dont have F or N grade courses, so you are not eligible for Course Registration.";
						}
					}
				}
				logger.trace("\n allowStatus: "+ allowStatus +" | validateRRStatus: "+ validateRRStatus);
								
				if ((allowStatus == 1) && (validateRRStatus == 1))
				{
					urlPage = "mainpages/MainPage";
				}
				else
				{
					courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
					courseRegCommonFn.callCaptcha(request, response, session, model);				
					urlPage = "StudentLogin";
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
		model.addAttribute("info", msg);
		
		return urlPage;
	}
	
	@PostMapping("doBackGroundProcess")
	public String doBackGroundProcess(Model model, HttpServletRequest request, HttpSession session, 
						HttpServletResponse response) throws ServletException, IOException 
	{
		String studentHistoryStatus = "", studentCgpaData = "0|0|0";
		List<String> compulsoryCourseList = new ArrayList<String>();
		List<Object[]> objectList = new ArrayList<Object[]>();
		
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		
		try
		{
			Integer historyCallStatus = (Integer) session.getAttribute("historyCallStatus");
			Integer cgpaStatus = (Integer) session.getAttribute("cgpaStatus");
			Integer compulsoryCourseStatus = (Integer) session.getAttribute("compulsoryCourseStatus");
			String semesterSubId = (String) session.getAttribute("SemesterSubId");
			Integer programGroupId = (Integer) session.getAttribute("ProgramGroupId");
			String programSpecCode = (String) session.getAttribute("ProgramSpecCode");
			Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
			Integer studyStartYear = (Integer) session.getAttribute("StudyStartYear");
			Integer costCenterId = (Integer) session.getAttribute("costCenterId");
			String studentStudySystem = (String) session.getAttribute("studentStudySystem");
			String oldRegisterNumber = (String) session.getAttribute("OldRegNo");
			
			@SuppressWarnings("unchecked")
			List<String> registerNumberList = (List<String>) session.getAttribute("registerNumberList");
			
			
			//Processing the Student History
			if (historyCallStatus == 1) 
			{
				studentHistoryStatus = courseRegistrationReadWriteService.studentHistoryInsertProcess(registerNumber, studentStudySystem);
			}
			else
			{
				studentHistoryStatus = "SUCCESS";
			}
			logger.trace("\n studentHistoryStatus: "+ studentHistoryStatus);
			
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
				objectList.clear();
				objectList = studentHistoryService.getStaticStudentCGPAFromTable(registerNumber, programSpecId);
				if ((objectList.isEmpty()) && (registerNumberList.size() >= 2))
		    	{
					objectList.clear();
					objectList = studentHistoryService.getStaticStudentCGPAFromTable(oldRegisterNumber, programSpecId);
		    	}
				
				if (!objectList.isEmpty())
		    	{
					studentCgpaData = Float.parseFloat(objectList.get(0)[0].toString()) 
											+"|"+ Float.parseFloat(objectList.get(0)[1].toString()) 
											+"|"+ Float.parseFloat(objectList.get(0)[2].toString());
		    	}
		    }
			logger.trace("\n studentCgpaData: "+ studentCgpaData);
						
			//Processing the Compulsory Courses
			if (compulsoryCourseStatus == 1)
			{
				compulsoryCourseList = compulsoryCourseConditionDetailService.getEligibleCompulsoryCourseList(
											semesterSubId, programGroupId, studyStartYear, programSpecId, 
											registerNumberList, programSpecCode, costCenterId, 0);
			}
			logger.trace("\n compulsoryCourseList :"+ compulsoryCourseList);
			

			session.setAttribute("studentCgpaData", studentCgpaData);
			session.setAttribute("compulsoryCourseList", compulsoryCourseList);
		}
		catch (Exception exception)
		{
			logger.trace("\n Exception: "+ exception);
		}
		
		return "RegistrationStart :: ProcessJob";
	}
}
