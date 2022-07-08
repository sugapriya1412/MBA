package org.vtop.CourseRegistration.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.vtop.CourseRegistration.NetAssist;
import org.vtop.CourseRegistration.model.EmployeeProfile;
import org.vtop.CourseRegistration.model.PatternTimeMasterModel;
import org.vtop.CourseRegistration.model.SemesterDetailsModel;
import org.vtop.CourseRegistration.model.SlotTimeMasterModel;
import org.vtop.CourseRegistration.model.StudentsLoginDetailsModel;
import org.vtop.CourseRegistration.service.CourseRegistrationCommonFunction;
import org.vtop.CourseRegistration.service.CourseRegistrationReadWriteService;
import org.vtop.CourseRegistration.service.CourseRegistrationService;
import org.vtop.CourseRegistration.service.CourseRegistrationWaitingService;
import org.vtop.CourseRegistration.service.ProgrammeSpecializationCurriculumCreditService;
import org.vtop.CourseRegistration.service.ProgrammeSpecializationCurriculumDetailService;
import org.vtop.CourseRegistration.service.RegistrationLogService;
import org.vtop.CourseRegistration.service.SemesterMasterService;

import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;


@Controller
public class CourseRegistrationPageController 
{
	@Autowired private CourseRegistrationService courseRegistrationService;
	@Autowired private CourseRegistrationWaitingService courseRegistrationWaitingService;
	@Autowired private RegistrationLogService registrationLogService;
	@Autowired private CourseRegistrationCommonFunction courseRegCommonFn;
	@Autowired private ProgrammeSpecializationCurriculumCreditService programmeSpecializationCurriculumCreditService;
	@Autowired private ProgrammeSpecializationCurriculumDetailService programmeSpecializationCurriculumDetailService;
	@Autowired private SemesterMasterService semesterMasterService;
	@Autowired private CourseRegistrationReadWriteService courseRegistrationReadWriteService;
	
	private static final Logger logger = LogManager.getLogger(CourseRegistrationPageController.class);
	private static final String RegErrorMethod = "FS2223REG";
	

	@RequestMapping(value = "SessionTimedOut", method = { RequestMethod.POST, RequestMethod.GET })
	public String sessionError(@CookieValue(value = "RegisterNumber") String registerNumber, Model model, 
						HttpServletRequest request, HttpServletResponse response, HttpSession session) 
						throws ServletException, IOException 
	{
		String page = "";		
		Cookie[] cookies = request.getCookies();
		
		if (cookies!=null)
		{
			for (Cookie cookie : cookies) 
			{
				if(cookie.getName().equals(registerNumber))
				{
					if (registrationLogService.isExist(registerNumber)) 
					{
						courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(request.getRemoteAddr(), registerNumber);
						
						cookie = new Cookie("RegisterNumber", null);
						cookie.setMaxAge(0);
						cookie.setSecure(true);
						cookie.setHttpOnly(true);
						response.addCookie(cookie);
						request.getSession().invalidate();				
					}
				}				
				
				model.addAttribute("message", "Session Expired");
				model.addAttribute("error", "Try Logout and Log-in");
				model.addAttribute("errno", 3);
				page = "CustomErrorPage";
			}			
		}
		else
		{
			courseRegCommonFn.callCaptcha(request,response,session,model);			
			model.addAttribute("flag", 2);			
			page = "redirectpage";							
		}	
		
		return page;
	}

	@RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.POST})
	public String home(HttpServletRequest httpServletRequest, Model model, HttpSession session, 
							HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String userAgent = httpServletRequest.getHeader("user-agent");
		UserAgent ua = UserAgent.parseUserAgentString(userAgent);
		Version browserVersion = ua.getBrowserVersion();
		String browserName = ua.getBrowser().toString();
		String userSessionId = null;
		
		String currentDateTimeStr;	
		
		Date currentDateTime = new Date();
		
		currentDateTimeStr = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(currentDateTime);
		
		model.addAttribute("CurrentDateTime", currentDateTimeStr);
		
		session.setAttribute("baseURL", NetAssist.getBaseURL(httpServletRequest));		

		int majVersion = Integer.parseInt(browserVersion.getMajorVersion());
		
		if (browserName.equalsIgnoreCase("Firefox") && majVersion < 50) 
		{
			model.addAttribute("message", "Outdated Web Browser Error!");
			model.addAttribute("error", "Kindly Update Your Browser. We recommend to use Mozilla Firefox or Google Chorme for better experience.");
		} 
		else if (browserName.equalsIgnoreCase("Chrome") && majVersion < 50) 
		{
			model.addAttribute("message", "Outdated Web Browser Error!");
			model.addAttribute("error", "Kindly Update Your Browser. We recommend to use Mozilla Firefox or Google Chorme for better experience.");
			return "ErrorPage";
		}
		else if (browserName.equalsIgnoreCase("EDGE14") && majVersion == 14) 
		{
			model.addAttribute("message", "Outdated Web Browser Error!");
			model.addAttribute("error", "Kindly Update Your Borwser. We recommend to use Mozilla Firefox or Google Chorme for better experience.");
			return "ErrorPage";
		} 
		else if (browserName.equalsIgnoreCase("OPERA") && majVersion < 40) 
		{
			model.addAttribute("message", "Outdated Web Browser Error!");
			model.addAttribute("error", "Kindly Update Your Borwser. We recommend to use Mozilla Firefox or Google Chorme for better experience.");
			return "ErrorPage";
		} 
		else if (browserName.contains("IE")) 
		{
			model.addAttribute("message", "Outdated Web Browser Error!");
			model.addAttribute("error", "Kindly Update Your Borwser. We recommend to use Mozilla Firefox or Google Chorme for better experience.");
			return "ErrorPage";
		}

		userSessionId = (String) session.getAttribute("userSessionId");

		if (userSessionId == null) 
		{
			session.setAttribute("userSessionId", session.getId());
		}
		
		courseRegCommonFn.callCaptcha(request, response, session, model);
		session.setAttribute("CAPTCHA", session.getAttribute("CAPTCHA"));
		
		return "StudentLogin";
	}
		
	@RequestMapping("/login/error")
	public String loginError(Model model, HttpServletRequest request, HttpServletResponse response,
						@RequestParam(value = "error", required = false) String error)
	{
	    String file = "StudentLogin";
	    HttpSession session = request.getSession(false);
	    String errMsg = "";
	    
	    try
	    {
	    	AuthenticationException exp = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	    	if (exp != null)
	    	{
	    		errMsg = exp.getMessage();
	    	}
	    	
	    	courseRegCommonFn.callCaptcha(request, response, session, model);
	    }
	    catch (Exception x)
	    {
	    	logger.trace(x);
	    	errMsg = x.getMessage();
	    }
		model.addAttribute("CurrentDateTime", new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date()));
	    model.addAttribute("info", errMsg);

	    return file;
	}
	
	@PostMapping("viewStudentLogin1")
	public String viewStudentLogin1(Model model, HttpServletRequest request, HttpSession session, 
						HttpServletResponse response) throws ServletException, IOException 
	{
		session.setAttribute("baseURL", NetAssist.getBaseURL(request));
		String userSessionId = (String) session.getAttribute("userSessionId");

		if (userSessionId == null) 
		{
			session.setAttribute("userSessionId", session.getId());
		}
		
		courseRegCommonFn.callCaptcha(request, response, session, model);
		session.setAttribute("CAPTCHA", session.getAttribute("CAPTCHA"));
		
		return "StudentLogin::test";
	}	

	@RequestMapping(value = "ServerLimit", method = { RequestMethod.POST, RequestMethod.GET })
	public String serverLimit(Model model, HttpSession session, HttpServletRequest request) throws ServletException 
	{
		String page = "CustomErrorPage";
		String baseURL = NetAssist.getBaseURL(request);
		logger.trace("BaseUrl - " + baseURL);
		
		request.getSession().invalidate();
		model.addAttribute("message", "");
		model.addAttribute("error", " Please Note: Try one of the following Servers <br/><br/>");
		model.addAttribute("errno", 99);

		return page;
	}

	@RequestMapping(value = "AlreadyLogin", method = { RequestMethod.POST, RequestMethod.GET })
	public String AlreadyLogin(Model model,HttpSession session, HttpServletRequest request, 
						HttpServletResponse response) throws ServletException 
	{
		String page = "CustomErrorPage";

		Cookie cookie = new Cookie("RegisterNumber", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		request.getSession().invalidate();
		model.addAttribute("message", "Multi-Tab Access");
		model.addAttribute("error", "Multiple Tabs Access prevented !!!");
		model.addAttribute("errno", 6);
		
		return page;
	}
	
	@GetMapping("signOut")
	public String signOut(Model model,HttpServletRequest request) 
	{
		model.addAttribute("message", " V TOP ");
		model.addAttribute("error", " Thank you For Using V TOP Course Registration Portal .");
		request.getSession().invalidate();	
		
		return "CustomErrorPage";
	}
	
	@GetMapping("noscript")
	public String noscript(Model model) 
	{
		model.addAttribute("message", "JavaScript Error");
		model.addAttribute("error", "Kindly Enable JavaScript in Your Browser to Access V-TOP.");
		
		return "ErrorPage";
	}

	@RequestMapping(value = "processLogout", method = { RequestMethod.POST, RequestMethod.GET })
	public String doLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
						Model model) throws ServletException, IOException 
	{
		String page = "", info = null, currentDateTimeStr = "",logoutMsg="";
		int loAllowFlag = 2;
		float regCredit=0, wlRegCredit=0, totalRegCredit=0;
		
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress=(String) session.getAttribute("IpAddress");
		
		try 
		{
			//int studyStartYear = (int) session.getAttribute("StudyStartYear");
			Integer StudentGraduateYear = (Integer) session.getAttribute("StudentGraduateYear");
			Integer academicGraduateYear = (Integer) session.getAttribute("acadGraduateYear");
			String ProgramGroupCode = (String) session.getAttribute("ProgramGroupCode");
			//Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
			String SemesterSubId = (String) session.getAttribute("SemesterSubId");
			Integer minCredit = (Integer) session.getAttribute("minCredit");
			Integer maxCredit = (Integer) session.getAttribute("maxCredit");			
			//Float curriculumVersion = (Float) session.getAttribute("curriculumVersion");
			List<String> ncCourseList = new ArrayList<String>();
			Integer PEUEAllowStatus = (Integer) session.getAttribute("PEUEAllowStatus");
			Integer waitingListStatus = (Integer) session.getAttribute("waitingListStatus");
			
			if ((registerNumber != null) && (registrationLogService.isExist(registerNumber)))
			{
				info = (String) session.getAttribute("info");
				
				//courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
				//model.addAttribute("flag", 4);			
				//page = "redirectpage";
				
				if (ProgramGroupCode.equals("RP") || ProgramGroupCode.equals("IEP"))
				{
					loAllowFlag = 1;
				}
				else if (StudentGraduateYear <= academicGraduateYear)
				{
					loAllowFlag = 1;
				}
				else if (PEUEAllowStatus == 1)
				{
					//ncCourseList = programmeSpecializationCurriculumDetailService.getNCCourseByYearAndCCVersion(programSpecId, 
					//					studyStartYear, curriculumVersion);
					ncCourseList.add("NONE");
					regCredit = courseRegistrationService.getRegCreditByRegisterNumberAndNCCourseCode(SemesterSubId, registerNumber, 
									ncCourseList);
					if (waitingListStatus == 1)
					{
						wlRegCredit = courseRegistrationWaitingService.getRegCreditByRegisterNumberAndNCCourseCode(SemesterSubId, 
											registerNumber, ncCourseList);
					}
					totalRegCredit = regCredit + wlRegCredit;
					
					//Minimum Credit Check	
					if (totalRegCredit >= (float) minCredit)
					{
						loAllowFlag = 1;
					}
				}
				else
				{
					loAllowFlag = 1;
				}
								
				if (loAllowFlag == 1) 
				{
					courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
					model.addAttribute("flag", 4);			
					page = "redirectpage";
				}
				else
				{
					currentDateTimeStr = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date());
					logoutMsg="Minimum of "+minCredit+" credits needed for 'Sign Out'.";
					model.addAttribute("CurrentDateTime", currentDateTimeStr);
					model.addAttribute("regCredit", regCredit);
					model.addAttribute("wlCredit", wlRegCredit);
					model.addAttribute("maxCredit", maxCredit);
					model.addAttribute("studentDetails", session.getAttribute("studentDetails"));
					model.addAttribute("logoutMsg", logoutMsg);
					
					return "mainpages/MainPage";
				}
			}
			else
			{
				model.addAttribute("flag", 4);			
				page = "redirectpage";
			}
			
			model.addAttribute("info", info);
			Cookie cookie = new Cookie("RegisterNumber", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			request.getSession().invalidate();
			
			return page;
		} 
		catch (Exception ex) 
		{
			logger.trace(ex);
			
			model.addAttribute("info", "Login with your Username and Password");
			courseRegistrationReadWriteService.addErrorLog(ex.toString(), RegErrorMethod+"CourseRegistrationPageController", 
					"processLogout", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			courseRegCommonFn.callCaptcha(request,response,session,model);
			session.setAttribute("CAPTCHA",session.getAttribute("CAPTCHA"));
			
			//page = "StudentLogin";
			page = "redirectpage";
			
			return page;
		}	
	}
	

	/*@PostMapping(value="ViewCredits")
	public String ViewCredits(Model model, String creditDetailshowFlag, HttpSession session, HttpServletRequest request) 
	{
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String semesterSubId = (String) session.getAttribute("SemesterSubId");
		String[] classGroupId = session.getAttribute("classGroupId").toString().split("/");
		Integer WaitingListStatus=(Integer) session.getAttribute("waitingListStatus");
		List<String> ncCourseList = new ArrayList<String>();
		
		String urlPage = "";
		int regCount = 0, wlCount = 0;
		float wlCredit = 0, regCredit = 0;
		String IpAddress=(String) session.getAttribute("IpAddress");
		Integer minCredit = (Integer) session.getAttribute("minCredit");
		Integer maxCredit = (Integer) session.getAttribute("maxCredit");
		
		try
		{
			if(creditDetailshowFlag.equals("true"))
			{
				if (registerNumber!=null)
				{	
					ncCourseList.add("NONE");
					regCredit = courseRegistrationService.getRegCreditByRegisterNumberAndNCCourseCode(semesterSubId, 
									registerNumber, ncCourseList);
					regCount = courseRegistrationService.getRegisterNumberTCCountByClassGroupId(semesterSubId, registerNumber, 
									classGroupId);
					
					if (WaitingListStatus == 1)
					{
						wlCredit = courseRegistrationWaitingService.getRegCreditByRegisterNumberAndNCCourseCode(semesterSubId, 
								registerNumber, ncCourseList);
						wlCount = courseRegistrationWaitingService.getRegisterNumberCRWCountByClassGroupId(semesterSubId, 
								registerNumber, classGroupId);
					}
					
					model.addAttribute("regCredit", regCredit);
					model.addAttribute("regCount", regCount);
					model.addAttribute("wlCount", wlCount);
					model.addAttribute("wlCredit", wlCredit);
					model.addAttribute("minCredit", minCredit);
					model.addAttribute("maxCredit", maxCredit);
					model.addAttribute("creditDetailshowFlag", creditDetailshowFlag);
					model.addAttribute("WaitingListStatus", WaitingListStatus);
					
					urlPage = "mainpages/MainPage::creditsFragment";
				}
				else
				{
					model.addAttribute("flag", 1);
					urlPage = "redirectpage";
					return urlPage;
				}
			}
			else
			{
				model.addAttribute("creditDetailshowFlag", creditDetailshowFlag);
				urlPage = "mainpages/MainPage::creditsFragment";
			}
		}
		catch(Exception e)
		{
			logger.trace(e);
			
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationPageController", 
					"ViewCredits", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			model.addAttribute("flag", 1);
			urlPage = "redirectpage";
			return urlPage;			
		}		

		return urlPage;
	}*/
	
	@PostMapping("viewCurriculumCredits")
	public String viewCurriculumCredits(HttpSession session,Model model)
	{
		Float cgpa = 0F;
		String urlPage="";
		List<Object[]> cclCtgCreditList = new ArrayList<Object[]>();
		List<String> regnoList = new ArrayList<String>();
		
		Integer WaitingListStatus = (Integer) session.getAttribute("waitingListStatus");
		Integer programSpecId = (Integer) session.getAttribute("ProgramSpecId");
		int studyStartYear = (int) session.getAttribute("StudyStartYear");
		String studentStudySystem = (String) session.getAttribute("studentStudySystem");
		Float curriculumVersion = (Float) session.getAttribute("curriculumVersion");
		String semesterSubId = (String) session.getAttribute("SemesterSubId");
		String registerNo = (String) session.getAttribute("RegisterNumber");
		String OldRegNo = (String) session.getAttribute("OldRegNo");
		String IpAddress=(String) session.getAttribute("IpAddress");
		String studentCgpaData = (String) session.getAttribute("studentCgpaData");
		
		try 
		{
			if(registerNo!=null)
			{
				regnoList.add(registerNo);
				if ((OldRegNo != null) && (!OldRegNo.equals("")))
				{
					regnoList.add(OldRegNo);
				}
				cclCtgCreditList = programmeSpecializationCurriculumCreditService.getCurrentSemRegCurCtgCreditByRegisterNo(
			                   			programSpecId, studyStartYear, curriculumVersion, semesterSubId, regnoList, WaitingListStatus);
				
				//Student CGPA Detail
				if ((studentCgpaData != null) && (!studentCgpaData.equals("")))
		    	{
					String[] studentCgpaArr = studentCgpaData.split("\\|");
								    	
					cgpa = Float.parseFloat(studentCgpaArr[2]);
		    	}
								
				model.addAttribute("cclCtgCreditList", cclCtgCreditList);
				model.addAttribute("studentStudySystem", studentStudySystem);
				model.addAttribute("WaitingListStatus", WaitingListStatus);
				model.addAttribute("tlCgpa", cgpa);
				
				urlPage = "mainpages/ViewCurriculumCredits::section";
			}
			else
			{
				model.addAttribute("flag", 1);
				urlPage = "redirectpage";
				return urlPage;
			}
		} 
		catch (Exception e) 
		{
			logger.trace(e);
			
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationPageController", 
					"viewCurriculumCredits", registerNo, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNo);
			model.addAttribute("flag", 1);
			urlPage = "redirectpage";
			return urlPage;			
		}
		
		return urlPage;
	}
	
	@PostMapping("viewRegistered")
	public String viewRegistered(Model model, HttpSession session, HttpServletRequest request) 
	{
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress = (String) session.getAttribute("IpAddress");
		Integer WaitingListStatus = (Integer) session.getAttribute("waitingListStatus");
		Integer minCredit = (Integer) session.getAttribute("minCredit");
		Integer maxCredit = (Integer) session.getAttribute("maxCredit");
		
		String urlPage = "", msg = null, infoMsg = "", checkCourseId = "";
		List<Object[]> courseRegistrationWaitingModel = new ArrayList<Object[]>();
		List<Object[]> courseRegistrationModel = new ArrayList<Object[]>();
		List<Integer> patternIdList = new ArrayList<Integer>();
		List<Object[]> regCreditList = new ArrayList<Object[]>();
		List<String> ncCourseList = new ArrayList<String>();
		
		int allowStatus = 2, regCount = 0, wlCount = 0, ncCount = 0;
		Integer updateStatus = 1;
		float wlCredit = 0, regCredit = 0, ncCredit = 0;
						
		try
		{
			if (registerNumber!=null)
			{
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
				
				StudentsLoginDetailsModel studentsLoginDetailsModel = new StudentsLoginDetailsModel();
				studentsLoginDetailsModel = semesterMasterService.getStudentLoginDetailByRegisterNumber(registerNumber);
								
				switch(allowStatus)
				{
					case 1:
												
						regCreditList.add(new Object[] {"Minimum", minCredit, "-"});
						regCreditList.add(new Object[] {"Maximum", maxCredit, "-"});

						if ((semesterSubId !=null) &&(registerNumber !=null))
						{
							ncCourseList = programmeSpecializationCurriculumDetailService.getNCCourseByYearAndCCVersion(programSpecId, 
													studyStartYear, curriculumVersion);
							
							courseRegistrationModel = courseRegistrationService.getByRegisterNumber3(semesterSubId, registerNumber);
							if (!courseRegistrationModel.isEmpty())
							{
								for (Object[] obj : courseRegistrationModel) 
								{
									//logger.trace("\n Pattern Id: "+ obj[29].toString() +" | Slot Id: "+obj[30].toString());
									
									if ((Integer.parseInt(obj[30].toString()) > 0) && (!patternIdList.contains(Integer.parseInt(obj[29].toString()))))
									{
										patternIdList.add(Integer.parseInt(obj[29].toString()));
									}
									
									regCredit = regCredit + Float.parseFloat(obj[14].toString());
									
									if (ncCourseList.contains(obj[3].toString()))
									{
										ncCredit = ncCredit + Float.parseFloat(obj[14].toString());
									}
									
									if (!obj[2].toString().equals(checkCourseId))
									{
										regCount++;
										checkCourseId = obj[2].toString();
										
										if (ncCourseList.contains(obj[3].toString()))
										{
											ncCount++;
										}
									}
								}
							}
						}
						regCreditList.add(new Object[] {"Registered (Including Non-Credit Category)", regCredit, regCount});
											
						if (WaitingListStatus == 1)
						{
							courseRegistrationWaitingModel = courseRegistrationWaitingService.getWaitingCourseByRegNoWithRank(
																			semesterSubId, registerNumber);
							if (!courseRegistrationModel.isEmpty())
							{
								checkCourseId = "";
								
								for (Object[] obj : courseRegistrationModel) 
								{
									wlCredit = wlCredit + Float.parseFloat(obj[10].toString());
									
									if (!obj[1].toString().equals(checkCourseId))
									{
										wlCount++;
										checkCourseId = obj[1].toString();
									}
								}
							}
							
							regCreditList.add(new Object[] {"Waiting (Including Non-Credit Category)", wlCredit, wlCount});
						}
						
						if (!ncCourseList.isEmpty())
						{
							regCreditList.add(new Object[] {"Non-Credit Category", ncCredit, ncCount});
						}
											
						List<Object[]> ttObjList = new ArrayList<Object[]>();
						Map<Integer, List<String>> slotTypeMapList = new HashMap<Integer, List<String>>();
						Map<Integer, List<Object[]>> ttSessionList = new HashMap<Integer, List<Object[]>>();
						Map<Integer, List<Object[]>> weekDayList = new HashMap<Integer, List<Object[]>>();
						Map<String, List<Object[]>> tmMapList = new HashMap<String, List<Object[]>>();
						Map<String, List<Object[]>> wdsMapList = new HashMap<String, List<Object[]>>();
						Map<String, List<Object[]>> stmMapList = new HashMap<String, List<Object[]>>();
						Map<String, String> regMapList = new HashMap<String, String>();
												
						SemesterDetailsModel sdm = new SemesterDetailsModel();	
						String hashKey = "", hashValue = "";;						
						
						sdm = semesterMasterService.getSemesterDetailBySemesterSubId(semesterSubId);
						if (!patternIdList.isEmpty())
						{
							for (Integer patternId : patternIdList)
							{								
								//Slot Type Map List
								ttObjList.clear();
								ttObjList = semesterMasterService.getPatternTimeMasterSlotTypeByPatternId(Arrays.asList(patternId));
								if (!ttObjList.isEmpty())
								{
									for (Object[] parameters : ttObjList)
									{
										if(slotTypeMapList.containsKey(patternId))
										{
											List<String> mapTempList = slotTypeMapList.get(patternId);
											mapTempList.add(parameters[1].toString());
											slotTypeMapList.replace(patternId, mapTempList);
										}
										else
										{
											List<String> mapTempList = new ArrayList<String>();
											mapTempList.add(parameters[1].toString());
											slotTypeMapList.put(patternId, mapTempList);
										}
									}
								}
								
								//Session Map List
								ttObjList.clear();
								ttObjList = semesterMasterService.getTTPatternDetailSessionSlotByPatternId(patternId);
								if (!ttObjList.isEmpty())
								{
									for (Object[] parameters : ttObjList)
									{
										if(ttSessionList.containsKey(patternId))
										{
											List<Object[]> mapTempList = ttSessionList.get(patternId);
											mapTempList.add(parameters);
											ttSessionList.replace(patternId, mapTempList);
										}
										else
										{
											List<Object[]> mapTempList = new ArrayList<Object[]>();
											mapTempList.add(parameters);
											ttSessionList.put(patternId, mapTempList);
										}
									}
								}
								
								//Week Day Map List
								ttObjList.clear();
								ttObjList = semesterMasterService.getSlotTimeMasterWeekDayList(patternId);	
								if (!ttObjList.isEmpty())
								{
									for (Object[] parameters : ttObjList)
									{
										if(weekDayList.containsKey(patternId))
										{
											List<Object[]> mapTempList = weekDayList.get(patternId);
											mapTempList.add(parameters);
											weekDayList.replace(patternId, mapTempList);
										}
										else
										{
											List<Object[]> mapTempList = new ArrayList<Object[]>();
											mapTempList.add(parameters);
											weekDayList.put(patternId, mapTempList);
										}
									}
								}
								
								//Slot Detail Map List
								ttObjList.clear();
								ttObjList = semesterMasterService.getPatternTimeMasterSlotDetailByPatternId(patternId);
								if (!ttObjList.isEmpty())
								{
									for (Object[] parameters : ttObjList)
									{
										hashKey = patternId +"_"+ parameters[0].toString() +"_"+ parameters[2].toString();
										if(tmMapList.containsKey(hashKey))
										{
											List<Object[]> mapTempList = tmMapList.get(hashKey);
											mapTempList.add(parameters);
											tmMapList.put(hashKey, mapTempList);
										}
										else
										{
											List<Object[]> mapTempList = new ArrayList<Object[]>();
											mapTempList.add(parameters);
											tmMapList.put(hashKey, mapTempList);
										}
									}
								}								
								
								//Week Day Session Map List
								ttObjList.clear();
								ttObjList = semesterMasterService.getSlotTimeMasterWeekDaySessionList(patternId);
								if (!ttObjList.isEmpty())
								{
									for (Object[] parameters : ttObjList)
									{
										hashKey = patternId +"_"+ parameters[0].toString() +"_"+ parameters[1].toString();
										if(wdsMapList.containsKey(hashKey))
										{
											List<Object[]> mapTempList = wdsMapList.get(hashKey);
											mapTempList.add(parameters);
											wdsMapList.put(hashKey, mapTempList);
										}
										else
										{
											List<Object[]> mapTempList = new ArrayList<Object[]>();
											mapTempList.add(parameters);
											wdsMapList.put(hashKey, mapTempList);
										}
									}
								}
								
								//Slot Time Map List
								ttObjList.clear();
								ttObjList = semesterMasterService.getSlotTimeMasterByPatternId(patternId);
								if (!ttObjList.isEmpty())
								{
									for (Object[] parameters : ttObjList)
									{
										hashKey = patternId +"_"+ parameters[0].toString() +"_"+ parameters[1].toString() 
														+"_"+ parameters[2].toString();
										if(stmMapList.containsKey(hashKey))
										{
											List<Object[]> mapTempList = stmMapList.get(hashKey);
											mapTempList.add(parameters);
											stmMapList.put(hashKey, mapTempList);
										}
										else
										{
											List<Object[]> mapTempList = new ArrayList<Object[]>();
											mapTempList.add(parameters);
											stmMapList.put(hashKey, mapTempList);
										}
									}
								}
								
								//Registered Map List
								ttObjList.clear();
								ttObjList = courseRegistrationService.getCourseRegWlSlotByStudent2(semesterSubId,registerNumber, patternId);
								if (!ttObjList.isEmpty())
								{					
									for (Object[] parameters : ttObjList)
									{
										hashKey = patternId +"_"+ parameters[0].toString() +"_"+ parameters[1].toString() 
														+"_"+ parameters[2].toString();
										hashValue = parameters[2].toString() +"-"+ parameters[3].toString() 
														+"-"+ parameters[4].toString() +"-"+ parameters[5].toString() 
														+"-"+ parameters[8].toString();
										logger.trace("\n hashKey: "+ hashKey +" | hashValue: "+ hashValue);
										if(regMapList.containsKey(hashKey))
										{
											hashValue = regMapList.get(hashKey) +"/ "+ hashValue;
											regMapList.replace(hashKey, hashValue);
										}
										else
										{
											regMapList.put(hashKey, hashValue);
										}
									}
								}
							}
														
							model.addAttribute("patternIdList", patternIdList);
							model.addAttribute("slotTypeMapList", slotTypeMapList);
							model.addAttribute("ttSessionList", ttSessionList);
							model.addAttribute("weekDayList", weekDayList);
							model.addAttribute("tmMapList", tmMapList);
							model.addAttribute("wdsMapList", wdsMapList);
							model.addAttribute("stmMapList", stmMapList);
							model.addAttribute("regMapList", regMapList);
						}
																		
						model.addAttribute("sdm", sdm);
						model.addAttribute("cDate", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(new Date()));
						model.addAttribute("courseRegistrationModel", courseRegistrationModel);
						model.addAttribute("courseRegistrationWaitingModel", courseRegistrationWaitingModel);
						model.addAttribute("WaitingListCourse",	courseRegistrationWaitingService.getRegisterNumberCRWCount(semesterSubId, registerNumber));
						model.addAttribute("studentsLoginDetailsModel", studentsLoginDetailsModel);
						model.addAttribute("showFlag", 0);
						model.addAttribute("curriculumMapList", programmeSpecializationCurriculumDetailService.
								getCurriculumBySpecIdYearAndCCVersionAsMap(programSpecId, studyStartYear, curriculumVersion));
						//model.addAttribute("WaitingListStatus", WaitingListStatus);
						//model.addAttribute("regCredit", regCredit);
						//model.addAttribute("regCount", regCount);
						//model.addAttribute("wlCount", wlCount);
						//model.addAttribute("wlCredit", wlCredit);
						//model.addAttribute("minCredit", minCredit);
						//model.addAttribute("maxCredit", maxCredit);
						
						model.addAttribute("regCreditList", regCreditList);
						
						session.removeAttribute("registrationOption");
						urlPage = "mainpages/ViewRegistered::section";
						
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
			
			courseRegistrationReadWriteService.addErrorLog(e.toString(), RegErrorMethod+"CourseRegistrationPageController", 
					"viewRegistered", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			model.addAttribute("flag", 1);
			urlPage = "redirectpage";
			return urlPage;
		}
		
		return urlPage;
	}
	
	@PostMapping(value = "getSchoolWiseGuideList")
	public String getSchoolWiseGuideList(String guideSchoolOpt , Model model, HttpSession session, HttpServletRequest request) 
	{	
		
		String urlPage = "";
		String registerNumber = (String) session.getAttribute("RegisterNumber");
		String IpAddress=(String) session.getAttribute("IpAddress");
		
		try
		{
			Integer costCentreId = 0;
			if ((guideSchoolOpt != null) && (!guideSchoolOpt.equals("")))
			{
				costCentreId =Integer.parseInt(guideSchoolOpt);
			}
			List<EmployeeProfile> employeeList = semesterMasterService.getEmployeeProfileByCentreId(costCentreId);
			model.addAttribute("employeeList", employeeList);
			model.addAttribute("costCentreId", costCentreId);
			urlPage = "mainpages/ProjectRegistration::ProjectGuideFragment";			
		}
		catch(Exception e)
		{
			logger.trace(e);
			
			model.addAttribute("flag", 1);
			courseRegistrationReadWriteService.addErrorLog(e.toString()+" <-Guide School-> "+guideSchoolOpt, RegErrorMethod+"CourseRegistrationPageController", 
					"getSchoolWiseGuideList", registerNumber, IpAddress);
			courseRegistrationReadWriteService.updateRegistrationLogLogoutTimeStamp2(IpAddress,registerNumber);
			urlPage = "redirectpage";
			return urlPage;
		}
		
		return urlPage;	
	}
	
	List<String> getStartingTimeTableSlots(Integer patternId, List<PatternTimeMasterModel> list1)
	{		
		BigDecimal bg;
		List<Object[]> listMax = semesterMasterService.getTTPatternDetailMaxSlots(patternId);
		List<String> listTimeTableSlots = new ArrayList<String>();
		
		int fnMax = 0, anMax = 0, enMax=0;
		String sesMax = "";
		try
		{
			for (int m= 0; m< listMax.size(); m++)
			{
				sesMax =listMax.get(m)[1].toString(); 
				if (sesMax.equals("FN"))
				{
					bg = new BigDecimal(listMax.get(m)[0].toString());
					fnMax = bg.intValue();
				}
				if (sesMax.equals("AN"))
				{
					bg = new BigDecimal(listMax.get(m)[0].toString());
					anMax =bg.intValue();
				}
				if (sesMax.equals("EN"))
				{
					bg = new BigDecimal(listMax.get(m)[0].toString());
					enMax = bg.intValue();
				}			
			}
		}
		catch(Exception ex)
		{
			logger.trace(ex);
		}
							
		//THEORY STARTING TIMINGS 
		int i = 1;
		for(PatternTimeMasterModel ls: list1)
		{
			String slName = ls.getPtmPkId().getSlotName().substring(0, 2);
			if (slName.equals("FN"))
			{
				listTimeTableSlots.add(ls.getStartingTime().toString().substring(0, 5));
				i++;
			}				
		}
		i = i-1;
		if (i < fnMax)
		{
			for (int j=1; j <= fnMax - i; j++)
			{
				listTimeTableSlots.add("-");
			}
		}
		
		listTimeTableSlots.add("Lunch");
		i=1;
		for(PatternTimeMasterModel ls: list1)
		{
			String slName = ls.getPtmPkId().getSlotName().substring(0, 2);
			if (slName.equals("AN"))
			{
				listTimeTableSlots.add(ls.getStartingTime().toString().substring(0, 5));
				i++;
			}
		}
		i = i-1;
		if (i < anMax)
		{
			for (int j=1; j <= anMax - i; j++)
			{
				listTimeTableSlots.add("-");
			}
		}
		
		
		i=1;
		for(PatternTimeMasterModel ls: list1)
		{
			String slName = ls.getPtmPkId().getSlotName().substring(0, 2);
			if (slName.equals("EN"))
			{
				listTimeTableSlots.add(ls.getStartingTime().toString().substring(0, 5));
				i++;
			}				
		}	
		i = i-1;
		if (i < enMax)
		{
			for (int j=1; j <= enMax - i; j++)
			{
				listTimeTableSlots.add("-");
			}
		}
		
							
		for (int k = 0; k < listTimeTableSlots.size(); k++)
		{
		
		}
		
		return listTimeTableSlots;
	}
	
	
	List<String> getEndingTimeTableSlots(Integer patternId, List<PatternTimeMasterModel> list1)
	{		
		BigDecimal bg;
		List<Object[]> listMax = semesterMasterService.getTTPatternDetailMaxSlots(patternId);
		List<String> listTimeTableSlots = new ArrayList<String>();
		
		int fnMax = 0, anMax = 0, enMax=0;
		String sesMax;
		try
		{
			for (int m= 0; m< listMax.size(); m++)
			{
				sesMax =listMax.get(m)[1].toString(); 
				if (sesMax.equals("FN"))
				{
					bg = new BigDecimal(listMax.get(m)[0].toString());
					fnMax = bg.intValue();
				}
				if (sesMax.equals("AN"))
				{
					bg = new BigDecimal(listMax.get(m)[0].toString());
					anMax =bg.intValue();
				}
				if (sesMax.equals("EN"))
				{
					bg = new BigDecimal(listMax.get(m)[0].toString());
					enMax = bg.intValue();
				}			
			}
		}
		catch(Exception ex)
		{
			logger.trace(ex);
		}
		
		
		//THEORY STARTING TIMINGS 
		int i = 1;
		for(PatternTimeMasterModel ls: list1)
		{
			String slName = ls.getPtmPkId().getSlotName().substring(0, 2);
			if (slName.equals("FN"))
			{
				listTimeTableSlots.add(ls.getEndingTime().toString().substring(0, 5));
				i++;
			}				
		}
		i = i-1;
		if (i < fnMax)
		{
			for (int j=1; j <= fnMax - i; j++)
			{
				listTimeTableSlots.add("-");
			}
		}
		
		listTimeTableSlots.add("Lunch");
		i=1;
		for(PatternTimeMasterModel ls: list1)
		{
			String slName = ls.getPtmPkId().getSlotName().substring(0, 2);
			if (slName.equals("AN"))
			{
				listTimeTableSlots.add(ls.getEndingTime().toString().substring(0, 5));
				i++;
			}
		}
		i = i-1;
		if (i < anMax)
		{
			for (int j=1; j <= anMax - i; j++)
			{
				listTimeTableSlots.add("-");
			}
		}
		
		
		i=1;
		for(PatternTimeMasterModel ls: list1)
		{
			String slName = ls.getPtmPkId().getSlotName().substring(0, 2);
			if (slName.equals("EN"))
			{
				listTimeTableSlots.add(ls.getEndingTime().toString().substring(0, 5));
				i++;
			}				
		}	
		i = i-1;
		if (i < enMax)
		{
			for (int j=1; j <= enMax - i; j++)
			{
				listTimeTableSlots.add("-");
			}
		}
							
		return listTimeTableSlots;
	}
	
	List<Object[]> getTimeTableSlots(String semesterSubId, String registerNumber, Integer patternId,
						List<SlotTimeMasterModel> slotTimeMasterList) 
	{
		BigDecimal bg;
		List<Object[]> listMax = semesterMasterService.getTTPatternDetailMaxSlots(patternId);
		List<String> listTimeTableSlots = new ArrayList<String>();
		List<Object[]> listTimeTableSlots1 = new ArrayList<Object[]>();
	
		int fnMax = 0, anMax = 0, enMax = 0;
		String sesMax;
		try 
		{
			for (int m = 0; m < listMax.size(); m++) 
			{
				sesMax = listMax.get(m)[1].toString();
				if (sesMax.equals("FN")) 
				{
					bg = new BigDecimal(listMax.get(m)[0].toString());
					fnMax = bg.intValue();
				}
				if (sesMax.equals("AN")) 
				{
					bg = new BigDecimal(listMax.get(m)[0].toString());
					anMax = bg.intValue();
				}
				if (sesMax.equals("EN")) 
				{
					bg = new BigDecimal(listMax.get(m)[0].toString());
					enMax = bg.intValue();
				}
			}
		} 
		catch (Exception ex) 
		{
			logger.trace(ex);
		}
	
		int i = 1;
		
		List<Object[]> regSlots = courseRegistrationService.getCourseRegWlSlotByStudent(semesterSubId,
						registerNumber, patternId);
		Map<String, List<Object[]>> tempMap = new HashMap<>();
	
		for (Object[] parameters : regSlots) 
		{
			if (tempMap.containsKey(parameters[1])) 
			{
				List<Object[]> temp = tempMap.get(parameters[1]);
				temp.add(parameters);
				tempMap.put(parameters[1].toString(), temp);
			} 
			else 
			{
				List<Object[]> temp = new ArrayList<>();
				temp.add(parameters);
				tempMap.put(parameters[1].toString(), temp);
			}
		}
	
		for (SlotTimeMasterModel ls : slotTimeMasterList) 
		{
			String slName = ls.getSession();
			if (slName.equals("FN")) 
			{
				String[] tempArr = new String[2];
				tempArr[0] = ls.getStmPkId().getSlot();
				if (tempMap.containsKey(ls.getStmPkId().getWeekdays())) 
				{
					for (Object[] obj : tempMap.get(ls.getStmPkId().getWeekdays())) 
					{
						if (obj[0].equals(ls.getStmPkId().getSlot())) 
						{
							tempArr[0] = obj[2] + "-" + obj[3] + "-" + obj[0] + "-" + obj[4];
							tempArr[1] = "#CCFF33";
							break;
						} 
						else 
						{
							tempArr[1] = "";
						}
					}
				} 
				else 
				{
					tempArr[1] = "";
				}
	
				listTimeTableSlots1.add(tempArr);
				i++;
			}
		}
		
		i = i - 1;
		if (i < fnMax) 
		{
			for (int j = 1; j <= fnMax - i; j++) 
			{
				listTimeTableSlots.add("-");
				String[] tempArr = new String[2];
				tempArr[0] = "-";
				tempArr[1] = "";
				listTimeTableSlots1.add(tempArr);
			}
		}
	
		String[] tempArrLunch = new String[2];
		tempArrLunch[0] = "Lunch";
		tempArrLunch[1] = "#e2e2e2";
		listTimeTableSlots1.add(tempArrLunch);
		i = 1;
		for (SlotTimeMasterModel ls : slotTimeMasterList) 
		{
			String slName = ls.getSession();
			if (slName.equals("AN")) {
				String[] tempArr = new String[2];
				tempArr[0] = ls.getStmPkId().getSlot();
				if (tempMap.containsKey(ls.getStmPkId().getWeekdays())) 
				{
					for (Object[] obj : tempMap.get(ls.getStmPkId().getWeekdays())) 
					{
						if (obj[0].equals(ls.getStmPkId().getSlot())) 
						{
							tempArr[0] = obj[2] + "-" + obj[3] + "-" + obj[0] + "-" + obj[4];
							tempArr[1] = "#CCFF33";
							break;
						} 
						else 
						{
							tempArr[1] = "";
						}
					}
	
				} 
				else 
				{
					tempArr[1] = "";
				}
	
				listTimeTableSlots1.add(tempArr);
				i++;
			}
		}
		i = i - 1;
		if (i < anMax) 
		{
			for (int j = 1; j <= anMax - i; j++) 
			{
				listTimeTableSlots.add("-");
				String[] tempArr = new String[2];
				tempArr[0] = "-";
				tempArr[1] = "";
				listTimeTableSlots1.add(tempArr);
			}
		}
	
		i = 1;
		for (SlotTimeMasterModel ls : slotTimeMasterList) 
		{
			String slName = ls.getSession();
			if (slName.equals("EN")) 
			{
				String[] tempArr = new String[2];
				tempArr[0] = ls.getStmPkId().getSlot();
				if (tempMap.containsKey(ls.getStmPkId().getWeekdays())) 
				{
					for (Object[] obj : tempMap.get(ls.getStmPkId().getWeekdays())) 
					{
						if (obj[0].equals(ls.getStmPkId().getSlot())) 
						{
							tempArr[0] = obj[2] + "-" + obj[3] + "-" + obj[0] + "-" + obj[4];
							tempArr[1] = "#CCFF33";
							break;
						} 
						else 
						{
							tempArr[1] = "";
						}
					}
				} 
				else 
				{
					tempArr[1] = "";
				}
	
				listTimeTableSlots1.add(tempArr);
				i++;
			}
		}
		i = i - 1;
		if (i < enMax) 
		{
			for (int j = 1; j <= enMax - i; j++) 
			{
				listTimeTableSlots.add("-");
				String[] tempArr = new String[2];
				tempArr[0] = "-";
				tempArr[1] = "";
				listTimeTableSlots1.add(tempArr);
			}
		}
		
		for (int k = 0; k < listTimeTableSlots1.size(); k++)
		{
			
		}
		return listTimeTableSlots1;
	}
}
