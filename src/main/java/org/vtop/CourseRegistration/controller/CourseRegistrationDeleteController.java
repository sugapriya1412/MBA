package org.vtop.CourseRegistration.controller;

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
import org.vtop.CourseRegistration.model.CourseCatalogModel;
import org.vtop.CourseRegistration.model.CourseRegistrationModel;
import org.vtop.CourseRegistration.model.CourseRegistrationWaitingModel;
import org.vtop.CourseRegistration.service.CourseCatalogService;
import org.vtop.CourseRegistration.service.CourseEquivalanceRegService;
import org.vtop.CourseRegistration.service.CourseRegistrationCommonFunction;
import org.vtop.CourseRegistration.service.CourseRegistrationReadWriteService;
import org.vtop.CourseRegistration.service.CourseRegistrationService;
import org.vtop.CourseRegistration.service.CourseRegistrationWaitingService;
import org.vtop.CourseRegistration.service.ProgrammeSpecializationCurriculumDetailService;


@Controller
public class CourseRegistrationDeleteController 
{
	@Autowired private CourseRegistrationService courseRegistrationService;
	@Autowired private CourseEquivalanceRegService courseEquivalanceRegService;
	@Autowired private CourseRegistrationCommonFunction courseRegCommonFn;
	@Autowired private ProgrammeSpecializationCurriculumDetailService programmeSpecializationCurriculumDetailService;
	@Autowired private CourseRegistrationWaitingService courseRegistrationWaitingService;
	@Autowired private CourseCatalogService courseCatalogService;
	@Autowired private CourseRegistrationReadWriteService courseRegistrationReadWriteService;
	
	private static final Logger logger = LogManager.getLogger(CourseRegistrationDeleteController.class);
	private static final String RegErrorMethod = "FS2223REG";
	private static final List<String> crCourseOption = new ArrayList<String>(Arrays.asList("RGR","RGCE", 
																	"RGP","RGW","RPCE","RWCE","RR"));
	
	
	@PostMapping("processDeleteCourseRegistration")
	public String processDeleteCourseRegistration(String courseId, Model model, HttpSession session, 
						HttpServletRequest request) 
	{
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress = (String) session.getAttribute("IpAddress");
		Integer WaitingListStatus=(Integer) session.getAttribute("waitingListStatus");
		Integer allowStatus = 0;
		Integer updateStatus = 1;
		int delStatusFlag = 2, deleteAllowStatus = 0;
		String urlPage = "", msg = null, infoMsg = "", courseAuthStatus = "",deleteMessage="";
		List<CourseRegistrationModel> courseRegistrationModel = new ArrayList<CourseRegistrationModel>();
		List<CourseRegistrationModel> courseRegistrationModel3 = new ArrayList<CourseRegistrationModel>();
		List<Object[]> courseRegistrationModel2 = new ArrayList<Object[]>();
		List<Object[]> courseRegistrationWaitingModel2 = new ArrayList<Object[]>();
				
		String[] regStatusArr = new String[5];
		int crCourseStatus = 2;
		String ccCourseSystem = "", crCourseId = "", crCourseCode = "";
		CourseCatalogModel courseCatalog = new CourseCatalogModel();		
		
		try
		{	
			if (registerNumber != null)
			{					
				String semesterSubId = (String) session.getAttribute("SemesterSubId");
				String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
				int studyStartYear = (int) session.getAttribute("StudyStartYear");
				Integer programGroupId = (Integer) session.getAttribute("ProgramGroupId");
				String ProgramSpecCode = (String) session.getAttribute("ProgramSpecCode");
				Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
				String programGroupCode = (String) session.getAttribute("ProgramGroupCode");
				String programGroupMode = (String) session.getAttribute("programGroupMode");
				Float CurriculumVersion = (Float) session.getAttribute("curriculumVersion");
				int otpStatus = (int) session.getAttribute("otpStatus");
				
				@SuppressWarnings("unchecked")
				List<String> compCourseList = (List<String>) session.getAttribute("compulsoryCourseList");			
				Date startDate = (Date) session.getAttribute("startDate");
				Date endDate = (Date) session.getAttribute("endDate");
				String startTime = (String) session.getAttribute("startTime");
				String endTime = (String) session.getAttribute("endTime");
				
				String returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
										registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				infoMsg = statusMsg[1];
				
				courseCatalog = courseCatalogService.getOne(courseId);
				if (courseCatalog != null)
				{
					ccCourseSystem = courseCatalog.getCourseSystem();
										
					if ((courseCatalog.getCorequisite() != null) && (!courseCatalog.getCorequisite().equals("")) 
							&& (!courseCatalog.getCorequisite().equals("NONE")) && (!courseCatalog.getCorequisite().equals("NIL")))
					{
						crCourseCode = courseCatalog.getCorequisite().trim();
					}
				}
				
				if ((!ccCourseSystem.equals("NONFFCS")) && (!ccCourseSystem.equals("FFCS")) 
						&& (!ccCourseSystem.equals("CAL")) && (!crCourseCode.equals("")))
				{						
					courseRegistrationModel3 = courseRegistrationService.getByRegisterNumberCourseCode(semesterSubId, 
													registerNumber, crCourseCode);
					if (!courseRegistrationModel3.isEmpty())
					{
						for (CourseRegistrationModel e : courseRegistrationModel3)
						{
							if (crCourseOption.contains(e.getCourseOptionCode()))
							{
								crCourseId = e.getCourseCatalogModel().getCourseId();
								crCourseStatus = 1;
							}
														
							break;
						}
					}
				}
				
				switch(allowStatus)
				{
					case 1:				
						regStatusArr = courseRegCommonFn.checkRegistrationDeleteCondition(semesterSubId, registerNumber, 
											courseId, programGroupId, programGroupCode, programGroupMode, ProgramSpecCode, 
											programSpecId, studyStartYear, CurriculumVersion, compCourseList).split("\\|");
						delStatusFlag = Integer.parseInt(regStatusArr[0]);
						deleteMessage = regStatusArr[1];
						courseAuthStatus = regStatusArr[2];
												
						if ((delStatusFlag == 1) && (crCourseStatus == 1))
						{
							regStatusArr = courseRegCommonFn.checkRegistrationDeleteCondition(semesterSubId, registerNumber, 
												crCourseId, programGroupId, programGroupCode, programGroupMode, ProgramSpecCode, 
												programSpecId, studyStartYear, CurriculumVersion, compCourseList).split("\\|");
							delStatusFlag = Integer.parseInt(regStatusArr[0]);
							deleteMessage = regStatusArr[1];
						}
						
						if (otpStatus == 1)
						{
							deleteAllowStatus = 1;
						}
						else if (otpStatus == 2)
						{
							courseAuthStatus = courseRegCommonFn.generateCourseAuthKey(registerNumber, courseId, delStatusFlag, 2);
						}
						
						session.setAttribute("authStatus", courseAuthStatus);
						
						if (delStatusFlag == 1)
						{							
							courseRegistrationModel.addAll(courseRegistrationService.getByRegisterNumberCourseIdByClassGroupId(
															semesterSubId, registerNumber, courseId, classGroupId));
							if (crCourseStatus == 1)
							{
								courseRegistrationModel.addAll(courseRegistrationService.getByRegisterNumberCourseIdByClassGroupId(
										semesterSubId, registerNumber, crCourseId, classGroupId));
								session.setAttribute("crCourseId", crCourseId);
							}
														
							model.addAttribute("courseId", courseId);
							model.addAttribute("courseRegistrationModel", courseRegistrationModel);
							model.addAttribute("msg", deleteMessage);
							model.addAttribute("WaitingListStatus", WaitingListStatus);
							model.addAttribute("tlDeleteAllowStatus", deleteAllowStatus);
							
							urlPage = "mainpages/DeleteConfirmation :: section";
						}
						else
						{
							courseRegistrationModel2 = courseRegistrationService.getByRegisterNumberAndClassGroup(
																semesterSubId, registerNumber, classGroupId);
							courseRegistrationWaitingModel2 = courseRegistrationWaitingService.getWaitingCourseByRegNoWithRankByClassGroupId(
																	semesterSubId, registerNumber, classGroupId);
	
							model.addAttribute("courseRegistrationModel", courseRegistrationModel2);
							model.addAttribute("courseRegistrationWaitingModel", courseRegistrationWaitingModel2);
							model.addAttribute("blockedCourse", courseRegistrationService.getBlockedCourseIdByRegisterNumberForDelete(
									semesterSubId, registerNumber));
							model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
									getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, 
											CurriculumVersion));
							model.addAttribute("showFlag", 0);
							model.addAttribute("WaitingListStatus", WaitingListStatus);
							model.addAttribute("infoMessage", deleteMessage);
	
							urlPage = "mainpages/DeleteCourse :: section";
						}
						break;
					
					default:						
						msg = infoMsg;						
						session.setAttribute("info", msg);
						model.addAttribute("flag", 2);
						urlPage = "redirectpage";
						return urlPage;						
				}
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}
		}
		catch(Exception e)
		{
			logger.trace(e);
			
			model.addAttribute("flag", 1);
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationDeleteController", 
					"processDeleteCourseRegistration", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}
		
		return urlPage;
	}		

	@PostMapping("processDeleteCourseRegistrationOTP")
	public String processDeleteCourseRegistrationOTP(String courseId, Model model, HttpSession session, 
						HttpServletRequest request) 
	{
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress = (String) session.getAttribute("IpAddress");
		Integer WaitingListStatus=(Integer) session.getAttribute("waitingListStatus");
		Integer allowStatus = 0;
		Integer updateStatus = 1;
		
		int deleteAllowStatus = 0, redirectFlag = 2, statusFlag = 2;
		String urlPage = "", msg = null, infoMsg = "", courseAuthStatus = "", deleteMessage = "", courseCode = "";
		String[] validateStatusArr = new String[]{};
		List<CourseRegistrationModel> courseRegistrationModel = new ArrayList<CourseRegistrationModel>();
		List<Object[]> courseRegistrationModel2 = new ArrayList<Object[]>();
		List<Object[]> courseRegistrationWaitingModel2 = new ArrayList<Object[]>();
				
		try
		{	
			if (registerNumber != null)
			{					
				String semesterSubId = (String) session.getAttribute("SemesterSubId");
				String semesterDesc = (String) session.getAttribute("SemesterDesc");
				String semesterShortDesc = (String) session.getAttribute("SemesterShortDesc");
				String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
				int studyStartYear = (int) session.getAttribute("StudyStartYear");
				Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
				Float CurriculumVersion = (Float) session.getAttribute("curriculumVersion");
				String studentEMailId = (String) session.getAttribute("studentEMailId");
				int otpStatus = (int) session.getAttribute("otpStatus");
				String crCourseId = (String) session.getAttribute("crCourseId");
							
				Date startDate = (Date) session.getAttribute("startDate");
				Date endDate = (Date) session.getAttribute("endDate");
				String startTime = (String) session.getAttribute("startTime");
				String endTime = (String) session.getAttribute("endTime");
				
				String returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
										registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				infoMsg = statusMsg[1];
				
				switch(allowStatus)
				{
					case 1:
						String authStatus = (String) session.getAttribute("authStatus");
						int authCheckStatus = courseRegCommonFn.validateCourseAuthKey(authStatus, registerNumber, 
													courseId, 1);
						
						if ((authCheckStatus == 1) && (otpStatus == 1))
						{	
							CourseCatalogModel courseCatalogModel = courseCatalogService.getOne(courseId);
							if (courseCatalogModel != null)
							{
								courseCode = courseCatalogModel.getCode();
							}
														
							validateStatusArr = courseRegistrationReadWriteService.validateCourseAndSendOTP(semesterSubId, semesterDesc, 
													semesterShortDesc, registerNumber, courseId, courseCode, studentEMailId, IpAddress, 
													"DELETE").split("\\|");
							statusFlag = Integer.parseInt(validateStatusArr[0]);
							courseAuthStatus = validateStatusArr[1];
							deleteMessage = validateStatusArr[3];
							logger.trace("\n statusFlag: "+ statusFlag +" | courseAuthStatus: "+ courseAuthStatus 
									+" | deleteMessage: "+ deleteMessage); 
							
							if (statusFlag == 1)
							{
								deleteAllowStatus = 2;
								redirectFlag = 1;
							}
						}
						else
						{
							deleteMessage = "Invalid course...!";
						}						
				
						if (redirectFlag == 1)
						{
							courseRegistrationModel.addAll(courseRegistrationService.getByRegisterNumberCourseIdByClassGroupId(
									semesterSubId, registerNumber, courseId, classGroupId));
							if ((crCourseId != null) && (!crCourseId.equals("")))
							{
								courseRegistrationModel.addAll(courseRegistrationService.getByRegisterNumberCourseIdByClassGroupId(
										semesterSubId, registerNumber, crCourseId, classGroupId));
							}
							
							session.setAttribute("authStatus", courseAuthStatus);
							
							model.addAttribute("courseId", courseId);
							model.addAttribute("courseRegistrationModel", courseRegistrationModel);
							model.addAttribute("msg", deleteMessage);
							model.addAttribute("WaitingListStatus", WaitingListStatus);
							model.addAttribute("tlDeleteAllowStatus", deleteAllowStatus);
							
							urlPage = "mainpages/DeleteConfirmation :: section";
						}
						else
						{
							courseRegistrationModel2 = courseRegistrationService.getByRegisterNumberAndClassGroup(
																semesterSubId, registerNumber, classGroupId);
							courseRegistrationWaitingModel2 = courseRegistrationWaitingService.getWaitingCourseByRegNoWithRankByClassGroupId(
																	semesterSubId, registerNumber, classGroupId);
	
							model.addAttribute("courseRegistrationModel", courseRegistrationModel2);
							model.addAttribute("courseRegistrationWaitingModel", courseRegistrationWaitingModel2);
							model.addAttribute("blockedCourse", courseRegistrationService.getBlockedCourseIdByRegisterNumberForDelete(
									semesterSubId, registerNumber));
							model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
									getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, 
											CurriculumVersion));
							model.addAttribute("showFlag", 0);
							model.addAttribute("WaitingListStatus", WaitingListStatus);
							model.addAttribute("infoMessage", deleteMessage);
	
							urlPage = "mainpages/DeleteCourse :: section";
						}
						break;
					
					default:						
						msg = infoMsg;						
						session.setAttribute("info", msg);
						model.addAttribute("flag", 2);
						urlPage = "redirectpage";
						return urlPage;						
				}
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}
		}
		catch(Exception e)
		{
			logger.trace(e);
			
			model.addAttribute("flag", 1);
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationDeleteController", 
					"processDeleteCourseRegistration", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}		
		return urlPage;
	}
	
	@PostMapping("processDeleteConfirmationCourseRegistration")
	public String processDeleteConfirmationCourseRegistration(String courseId, Model model, HttpSession session, 
						HttpServletRequest request) 
	{
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress = (String) session.getAttribute("IpAddress");
		Integer WaitingListStatus = (Integer) session.getAttribute("waitingListStatus");
		
		int redirectFlag = 2, statusFlag = 2;
		String urlPage = "", msg = null, message = null, infoMsg = "";
		Integer allowStatus = 2, updateStatus = 1;
		String oldCourseId = "", pDelStatus = "", mailOTP = "", courseCode = "";
		String[] validateStatusArr = new String[]{};
		
		try
		{	
			if (registerNumber != null)
			{
				String semesterSubId = (String) session.getAttribute("SemesterSubId");
				String semesterDesc = (String) session.getAttribute("SemesterDesc");
				String semesterShortDesc = (String) session.getAttribute("SemesterShortDesc");
				String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
				Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
				int studyStartYear = (int) session.getAttribute("StudyStartYear");
				Float curriculumVersion = (Float) session.getAttribute("curriculumVersion");								
				Date startDate = (Date) session.getAttribute("startDate");
				Date endDate = (Date) session.getAttribute("endDate");
				String startTime = (String) session.getAttribute("startTime");
				String endTime = (String) session.getAttribute("endTime");
				int otpStatus = (int) session.getAttribute("otpStatus");
				String crCourseId = (String) session.getAttribute("crCourseId");
				
				String returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
										registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				infoMsg = statusMsg[1];
				
				mailOTP = request.getParameter("mailOTP");
				if ((mailOTP != null) && (!mailOTP.equals("")))
				{
					mailOTP = mailOTP.trim();
				}
				else
				{
					mailOTP = "NONE";
				}
				
				List<Object[]> courseRegistrationModel = new ArrayList<Object[]>();
				List<Object[]> courseRegistrationWaitingModel = new ArrayList<Object[]>();
				List<String> courseIdList = new ArrayList<String>();
				List<CourseRegistrationModel> courseRegistrationModel2 = new ArrayList<CourseRegistrationModel>();
				
				String authStatus = (String) session.getAttribute("authStatus");
				int authCheckStatus = courseRegCommonFn.validateCourseAuthKey(authStatus, registerNumber, courseId, 2);
				
				switch(allowStatus)
				{
					case 1:
						if(authCheckStatus == 1)
						{
							if (otpStatus == 1)
							{
								CourseCatalogModel courseCatalogModel = courseCatalogService.getOne(courseId);
								if (courseCatalogModel != null)
								{
									courseCode = courseCatalogModel.getCode();
								}
																
								validateStatusArr = courseRegistrationReadWriteService.validateCourseAndOTP(semesterSubId, 
														semesterDesc, semesterShortDesc, registerNumber, courseId, 
														courseCode, mailOTP, IpAddress, "DELETE").split("\\|");
								statusFlag = Integer.parseInt(validateStatusArr[0]);
								redirectFlag = Integer.parseInt(validateStatusArr[1]);
								
								if(validateStatusArr[2].toString().equals("SUCCESS"))
								{
									msg = "Registered Course(s) Successfully Deleted.";
								}
								else
								{
									message = validateStatusArr[2].toString();
								}
							}
							else if (otpStatus == 2)
							{
								statusFlag = 1;
								redirectFlag = 2;
							}
							logger.trace("\n statusFlag: "+ statusFlag +" | redirectFlag: "+ redirectFlag 
									+" | message: "+ message); 
			
							oldCourseId = courseEquivalanceRegService.getEquivCourseByRegisterNumberAndCourseId(
												semesterSubId, registerNumber, courseId);
							if ((oldCourseId == null) || (oldCourseId.equals(null)))
							{
								oldCourseId = "";
							}
							
							if (statusFlag == 1)
							{
								courseIdList.add(courseId);
								if ((crCourseId != null) && (!crCourseId.equals("")))
								{
									courseIdList.add(crCourseId);
								}
								
								synchronized (this)
								{
									for (String crsId : courseIdList)
									{
										pDelStatus = courseRegistrationReadWriteService.courseRegistrationDelete(semesterSubId, 
														registerNumber, crsId, "DELETE", registerNumber, IpAddress, "GEN", oldCourseId);
										if (pDelStatus.equals("SUCCESS"))
										{
											courseRegistrationReadWriteService.projectRegDeleteByRegisterNumberAndCourseId(semesterSubId, 
													registerNumber, crsId);
											msg = "Selected course successfully deleted.";
										}
										else if ((pDelStatus.equals("FAIL")) || (pDelStatus.substring(0, 5).equals("error")))
										{
											message = "Technical error.";
											courseRegistrationReadWriteService.addErrorLog(pDelStatus.toString()+"<-CODE->"+crsId, RegErrorMethod+"CourseRegistrationDeleteController", 
													"processRegisterProjectCourseDELPROC", registerNumber, IpAddress);
											courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
										}
										else
										{
											message = pDelStatus;
										}
									}
								}
							}
						}
						else
						{
							message = "Not a valid course to delete.";
						}	
												
						if (redirectFlag == 1)
						{	
							courseRegistrationModel2.addAll(courseRegistrationService.getByRegisterNumberCourseIdByClassGroupId(
									semesterSubId, registerNumber, courseId, classGroupId));
							if ((crCourseId != null) && (!crCourseId.equals("")))
							{
								courseRegistrationModel2.addAll(courseRegistrationService.getByRegisterNumberCourseIdByClassGroupId(
										semesterSubId, registerNumber, crCourseId, classGroupId));
							}
							
							model.addAttribute("courseId", courseId);
							model.addAttribute("courseRegistrationModel", courseRegistrationModel2);
							model.addAttribute("infoMessage", message);
							model.addAttribute("WaitingListStatus", WaitingListStatus);
							model.addAttribute("tlDeleteAllowStatus", 2);
							
							urlPage = "mainpages/DeleteConfirmation :: section";
						}
						else
						{
							courseRegistrationModel = courseRegistrationService.getByRegisterNumberAndClassGroup(
														semesterSubId, registerNumber, classGroupId);
							courseRegistrationWaitingModel = courseRegistrationWaitingService.getWaitingCourseByRegNoWithRankByClassGroupId(
																semesterSubId, registerNumber, classGroupId);
		
							model.addAttribute("courseRegistrationModel", courseRegistrationModel);
							model.addAttribute("courseRegistrationWaitingModel", courseRegistrationWaitingModel);
							model.addAttribute("blockedCourse", courseRegistrationService.getBlockedCourseIdByRegisterNumberForDelete(
									semesterSubId, registerNumber));
							model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
									getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, curriculumVersion));
							model.addAttribute("WaitingListStatus", WaitingListStatus);
							model.addAttribute("info", msg);
							model.addAttribute("infoMessage", message);
							
							urlPage = "mainpages/DeleteCourse::section";
						}
						break;
						
					default:						
						msg = infoMsg;						
						session.setAttribute("info", msg);
						model.addAttribute("flag", 2);
						urlPage = "redirectpage";
						return urlPage;
				}
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}
		}
		catch(Exception e)
		{
			logger.trace(e);
			
			model.addAttribute("flag", 1);
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationDeleteController", 
					"processDeleteConfirmationCourseRegistration", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}
		
		return urlPage;
	}

			
	@PostMapping("processDeleteConfirmationCourseRegistrationRirect")
	public String processDeleteConfirmationCourseRegistrationRirect(Model model, HttpSession session, 
						HttpServletRequest request) 
	{		
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress = (String) session.getAttribute("IpAddress");
		Integer WaitingListStatus=(Integer) session.getAttribute("waitingListStatus");
		
		String msg = null, infoMsg = "", urlPage = "";
		Integer allowStatus = 2;
		Integer updateStatus = 1;
					
		try
		{	
			if (registerNumber != null)
			{				
				String semesterSubId = (String) session.getAttribute("SemesterSubId");
				String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
				Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
				int studyStartYear = (int) session.getAttribute("StudyStartYear");
				Float curriculumVersion = (Float) session.getAttribute("curriculumVersion");				
				Date startDate = (Date) session.getAttribute("startDate");
				Date endDate = (Date) session.getAttribute("endDate");
				String startTime = (String) session.getAttribute("startTime");
				String endTime = (String) session.getAttribute("endTime");
				
				String returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
										registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				infoMsg = statusMsg[1];
				
				List<Object[]> courseRegistrationModel = new ArrayList<Object[]>();
				List<Object[]> courseRegistrationWaitingModel = new ArrayList<Object[]>();
				
				
				switch(allowStatus)
				{
					case 1:
						courseRegistrationModel = courseRegistrationService.getByRegisterNumberAndClassGroup(
														semesterSubId, registerNumber, classGroupId);
						courseRegistrationWaitingModel = courseRegistrationWaitingService.getWaitingCourseByRegNoWithRankByClassGroupId(
															semesterSubId, registerNumber, classGroupId);
						
						model.addAttribute("courseRegistrationModel", courseRegistrationModel);
						model.addAttribute("courseRegistrationWaitingModel", courseRegistrationWaitingModel);
						model.addAttribute("blockedCourse", courseRegistrationService.getBlockedCourseIdByRegisterNumberForDelete(
								semesterSubId, registerNumber));
						model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
								getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, curriculumVersion));
						model.addAttribute("WaitingListStatus", WaitingListStatus);
						urlPage = "mainpages/DeleteCourse::section";
						break;
						
					default:						
						msg = infoMsg;						
						session.setAttribute("info", msg);
						model.addAttribute("flag", 2);
						urlPage = "redirectpage";
						return urlPage;					
				}
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}
		}
		catch(Exception e)
		{
			logger.trace(e);
			
			model.addAttribute("flag", 1);
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationDeleteController", 
					"processDeleteConfirmationCourseRegistrationRirect", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}
		return urlPage;			
	}
	

	@PostMapping("deleteRegisteredCourse")
	public String deleteRegisteredCourse(Model model, HttpSession session, HttpServletRequest request) 
	{	
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress = (String) session.getAttribute("IpAddress");
		Integer WaitingListStatus=(Integer) session.getAttribute("waitingListStatus");
		
		String msg = null, infoMsg = "", urlPage = "";
		Integer allowStatus = 2, updateStatus = 1;
		
		try
		{	
			if (registerNumber != null)
			{				
				String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
				String semesterSubId = (String) session.getAttribute("SemesterSubId");
				Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
				int studyStartYear = (int) session.getAttribute("StudyStartYear");
				Float curriculumVersion = (Float) session.getAttribute("curriculumVersion");
				Date startDate = (Date) session.getAttribute("startDate");
				Date endDate = (Date) session.getAttribute("endDate");
				String startTime = (String) session.getAttribute("startTime");
				String endTime = (String) session.getAttribute("endTime");
				
				String returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
										registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				infoMsg = statusMsg[1];
				
				List<Object[]> courseRegistrationModel = new ArrayList<Object[]>();
				List<Object[]> courseRegistrationWaitingModel = new ArrayList<Object[]>();
				
				switch(allowStatus)
				{
					case 1:
						courseRegistrationModel = courseRegistrationService.getByRegisterNumberAndClassGroup(
														semesterSubId, registerNumber, classGroupId);
						courseRegistrationWaitingModel = courseRegistrationWaitingService.getWaitingCourseByRegNoWithRankByClassGroupId(
																semesterSubId, registerNumber, classGroupId);
						
						model.addAttribute("courseRegistrationModel", courseRegistrationModel);
						model.addAttribute("courseRegistrationWaitingModel", courseRegistrationWaitingModel);
						model.addAttribute("blockedCourse", courseRegistrationService.getBlockedCourseIdByRegisterNumberForDelete(
								semesterSubId, registerNumber));
						model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
								getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, curriculumVersion));
						model.addAttribute("showFlag", 0);
						model.addAttribute("WaitingListStatus", WaitingListStatus);
						urlPage = "mainpages/DeleteCourse::section";
						break;
					
					default:						
						msg = infoMsg;						
						session.setAttribute("info", msg);
						model.addAttribute("flag", 2);
						urlPage = "redirectpage";
						return urlPage;
				}
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}
		}
		catch(Exception e)
		{
			logger.trace(e);
			
			model.addAttribute("flag", 1);
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationDeleteController", 
					"deleteRegisteredCourse", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}
		
		return urlPage;
	}
	
	
	@PostMapping("processDeleteConfirmationCourseRegistrationWaiting")
	public String processDeleteConfirmationCourseRegistrationWaiting(String courseId, Model model, HttpSession session, 
						HttpServletRequest request) 
	{
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		Integer WaitingListStatus=(Integer) session.getAttribute("waitingListStatus");
		String IpAddress=(String) session.getAttribute("IpAddress");
		
		String msg = null, infoMsg = "", urlPage = "";
		Integer allowStatus = 2, updateStatus = 1;
		
		try
		{	
			if (registerNumber != null)
			{	
				String semesterSubId = (String) session.getAttribute("SemesterSubId");
				String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
				Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
				int studyStartYear = (int) session.getAttribute("StudyStartYear");
				Float curriculumVersion = (Float) session.getAttribute("curriculumVersion");
				String authStatus = (String) session.getAttribute("authStatus");
				Date startDate = (Date) session.getAttribute("startDate");
				Date endDate = (Date) session.getAttribute("endDate");
				String startTime = (String) session.getAttribute("startTime");
				String endTime = (String) session.getAttribute("endTime");
				
				String returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
										registerNumber, updateStatus, IpAddress);						
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				infoMsg = statusMsg[1];
								
				int authCheckStatus = courseRegCommonFn.validateCourseAuthKey(authStatus, registerNumber, courseId, 1);
				List<Object[]> courseRegistrationModel = new ArrayList<Object[]>();
				List<Object[]> courseRegistrationWaitingModel = new ArrayList<Object[]>();
				
				switch(allowStatus)
				{
					case 1:
						if(authCheckStatus == 1)
						{
							synchronized (this)
							{	
								courseRegistrationReadWriteService.courseRegWaitingAddWaitingToWaitingMove(semesterSubId, registerNumber, 
										courseId, 0, IpAddress);		
								courseRegistrationReadWriteService.courseRegWaitingDeleteByRegisterNumberAndCourseId(semesterSubId, registerNumber, 
										courseId);	
								courseRegistrationReadWriteService.courseEquRegDeleteByRegisterNumberAndCourseId(semesterSubId, registerNumber, 
										courseId);	
							}
							
							msg = "Waiting List Course(s) Successfully Deleted.";
						}
						else
						{
							msg = "Not a valid course to delete.";
						}
						
						courseRegistrationModel = courseRegistrationService.getByRegisterNumberAndClassGroup(
														semesterSubId, registerNumber, classGroupId);
						courseRegistrationWaitingModel = courseRegistrationWaitingService.getWaitingCourseByRegNoWithRankByClassGroupId(
																semesterSubId, registerNumber, classGroupId);
											
						model.addAttribute("courseRegistrationModel", courseRegistrationModel);
						model.addAttribute("courseRegistrationWaitingModel", courseRegistrationWaitingModel);
						model.addAttribute("blockedCourse", courseRegistrationService.getBlockedCourseIdByRegisterNumberForDelete(
																semesterSubId, registerNumber));
						model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
								getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, curriculumVersion));
						
						
						model.addAttribute("info", msg);
						model.addAttribute("WaitingListStatus", WaitingListStatus);
						urlPage = "mainpages/DeleteCourse::section";
						break;
					
					default:						
						msg = infoMsg;						
						session.setAttribute("info", msg);
						model.addAttribute("flag", 2);
						urlPage = "redirectpage";
						return urlPage;
				}				
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}
		}
		catch(Exception e)
		{
			logger.trace(e);
			
			model.addAttribute("flag", 1);
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationDeleteController", 
					"processDeleteConfirmationCourseRegistrationWaiting", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}
		return urlPage;				
	}
	
	@PostMapping("processDeleteCourseRegistrationWaiting")
	public String processDeleteCourseRegistrationWaiting(String courseId, Model model, HttpSession session, 
						HttpServletRequest request) 
	{
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		Integer WaitingListStatus=(Integer) session.getAttribute("waitingListStatus");
		String IpAddress=(String) session.getAttribute("IpAddress");
		
		String msg = null, urlPage = "", infoMsg = "", deleteMessage="", courseAuthStatus="";
		Integer allowStatus = 0, updateStatus = 1;
		int delStatusFlag = 2;		
		String[] regStatusArr = new String[5];
		
		try
		{	
			if (registerNumber != null)
			{				
				String semesterSubId = (String) session.getAttribute("SemesterSubId");
				String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
				int studyStartYear = (int) session.getAttribute("StudyStartYear");
				Integer programGroupId = (Integer) session.getAttribute("ProgramGroupId");
				String ProgramSpecCode = (String) session.getAttribute("ProgramSpecCode");
				Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
				String programGroupCode = (String) session.getAttribute("ProgramGroupCode");
				String programGroupMode = (String) session.getAttribute("programGroupMode");
				Float CurriculumVersion = (Float) session.getAttribute("curriculumVersion");
				
				@SuppressWarnings("unchecked")
				List<String> compCourseList = (List<String>) session.getAttribute("compulsoryCourseList");
				Date startDate = (Date) session.getAttribute("startDate");
				Date endDate = (Date) session.getAttribute("endDate");
				String startTime = (String) session.getAttribute("startTime");
				String endTime = (String) session.getAttribute("endTime");
				
				String returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
										registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				infoMsg = statusMsg[1];
				
				List<CourseRegistrationWaitingModel> courseRegistrationWaitingModel = new ArrayList<CourseRegistrationWaitingModel>();
				List<Object[]> courseRegistrationModel2 = new ArrayList<Object[]>();
				List<Object[]> courseRegistrationWaitingModel2 = new ArrayList<Object[]>();	

				switch(allowStatus)
				{
					case 1:
						regStatusArr = courseRegCommonFn.checkRegistrationDeleteCondition(semesterSubId, registerNumber, 
											courseId, programGroupId, programGroupCode, programGroupMode, ProgramSpecCode, 
											programSpecId, studyStartYear, CurriculumVersion, compCourseList).split("\\|");
						delStatusFlag = Integer.parseInt(regStatusArr[0]);
						deleteMessage = regStatusArr[1];
						courseAuthStatus = regStatusArr[2];
						
						session.setAttribute("authStatus", courseAuthStatus);
						
						if (delStatusFlag == 1)
						{
							courseRegistrationWaitingModel = courseRegistrationWaitingService.getByRegisterNumberCourseIdByClassGroupId(
																	semesterSubId, registerNumber, courseId, classGroupId);
							
							model.addAttribute("courseId", courseId);
							model.addAttribute("courseRegistrationWaitingModel", courseRegistrationWaitingModel);
							model.addAttribute("msg", deleteMessage);
							model.addAttribute("WaitingListStatus", WaitingListStatus);
							
							urlPage = "mainpages/DeleteConfirmationWaiting::section";
						}
						else
						{
							courseRegistrationModel2 = courseRegistrationService.getByRegisterNumberAndClassGroup(
									semesterSubId, registerNumber, classGroupId);
							courseRegistrationWaitingModel2 = courseRegistrationWaitingService.getWaitingCourseByRegNoWithRankByClassGroupId(
									semesterSubId, registerNumber, classGroupId);
		
							model.addAttribute("courseRegistrationModel", courseRegistrationModel2);
							model.addAttribute("courseRegistrationWaitingModel", courseRegistrationWaitingModel2);
							model.addAttribute("blockedCourse", courseRegistrationService.getBlockedCourseIdByRegisterNumberForDelete(
									semesterSubId, registerNumber));
							model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
									getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, CurriculumVersion));
							model.addAttribute("showFlag", 0);
							model.addAttribute("infoMessage", deleteMessage);
							model.addAttribute("WaitingListStatus", WaitingListStatus);
							
							urlPage = "mainpages/DeleteCourse::section";
						}
						break;
						
					default:						
						msg = infoMsg;						
						session.setAttribute("info", msg);
						model.addAttribute("flag", 2);
						urlPage = "redirectpage";
						return urlPage;
				}
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}			
		}
		catch(Exception e)
		{
			logger.trace(e);
			
			model.addAttribute("flag", 1);
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationDeleteController", 
					"processDeleteCourseRegistrationWaiting", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}
		
		return urlPage;
	}
	
	@PostMapping("processDeleteConfirmationCourseRegistrationWaitingRirect")
	public String processDeleteConfirmationCourseRegistrationWaitingRirect(Model model, HttpSession session, 
						HttpServletRequest request) 
	{
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		Integer WaitingListStatus = (Integer) session.getAttribute("waitingListStatus");
		String IpAddress = (String) session.getAttribute("IpAddress");
		
		String msg = null, urlPage = "", infoMsg = "";
		Integer allowStatus = 2, updateStatus = 1;
		
		try
		{	
			if (registerNumber != null)
			{
				String semesterSubId = (String) session.getAttribute("SemesterSubId");				
				String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
				Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
				int studyStartYear = (int) session.getAttribute("StudyStartYear");
				Float curriculumVersion = (Float) session.getAttribute("curriculumVersion");								
				Date startDate = (Date) session.getAttribute("startDate");
				Date endDate = (Date) session.getAttribute("endDate");
				String startTime = (String) session.getAttribute("startTime");
				String endTime = (String) session.getAttribute("endTime");
				
				String returnVal = courseRegistrationReadWriteService.AddorDropDateTimeCheck(startDate, endDate, startTime, endTime, 
										registerNumber, updateStatus, IpAddress);
				String[] statusMsg = returnVal.split("/");
				allowStatus = Integer.parseInt(statusMsg[0]);
				infoMsg = statusMsg[1];
				
				List<Object[]> courseRegistrationModel = new ArrayList<Object[]>();
				List<Object[]> courseRegistrationWaitingModel = new ArrayList<Object[]>();
				
				switch(allowStatus)
				{
					case 1:
						courseRegistrationModel = courseRegistrationService.getByRegisterNumberAndClassGroup(
														semesterSubId, registerNumber, classGroupId);
						courseRegistrationWaitingModel = courseRegistrationWaitingService.getWaitingCourseByRegNoWithRankByClassGroupId(
															semesterSubId, registerNumber, classGroupId);
						
						model.addAttribute("courseRegistrationModel", courseRegistrationModel);
						model.addAttribute("courseRegistrationWaitingModel", courseRegistrationWaitingModel);
						model.addAttribute("blockedCourse", courseRegistrationService.getBlockedCourseIdByRegisterNumberForDelete(
								semesterSubId, registerNumber));
						model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
								getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, curriculumVersion));
						model.addAttribute("WaitingListStatus", WaitingListStatus);
						
						urlPage = "mainpages/DeleteCourse::section";
						break;
						
					default:						
						msg = infoMsg;						
						session.setAttribute("info", msg);
						model.addAttribute("flag", 2);
						urlPage = "redirectpage";
						return urlPage;
				}
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}
		}
		catch(Exception e)
		{
			logger.trace(e);
			
			model.addAttribute("flag", 1);
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationDeleteController", 
					"processDeleteConfirmationCourseRegistrationWaitingRirect", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}
		
		return urlPage;
	}	
}
