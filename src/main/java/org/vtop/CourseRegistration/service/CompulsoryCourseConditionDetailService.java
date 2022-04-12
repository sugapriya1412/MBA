package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.CompulsoryCourseConditionDetailRepository;


@Service
@Transactional(readOnly=true)
public class CompulsoryCourseConditionDetailService
{	
	@Autowired private CompulsoryCourseConditionDetailRepository compulsoryCourseConditionDetailRepository;
	@Autowired private StudentHistoryService studentHistoryService;
	@Autowired private CourseRegistrationService courseRegistrationService;
	@Autowired private SemesterMasterService semesterMasterService;
	
	private static final Logger logger = LogManager.getLogger(CompulsoryCourseConditionDetailService.class);
		
		
	public List<String> getEligibleCompulsoryCourseList(String semesterSubId, Integer progGroupId, Integer studentBatch, 
								Integer progSpecId, List<String> registerNumber, String progSpecCode, Integer costCenterId, 
								Integer studentSemester)
	{
		List<String> tempCompCourseList = new ArrayList<String>();
		
		int preReqType = 0, preReqValue = 0, preReqValue2 = 0, compCheckFlag = 2, mark = 0, allowCompFlag = 1;
		String preReqParam = "", preReqParam2 = "", preReqParam3 = "", grade = "NONE", courseId = "", pcmbStatus = "";
		String[] preReqParamArray = new String[]{};
		List<Object[]> tempObjectList = new ArrayList<Object[]>();
		List<Object[]> tempObjectList2 = new ArrayList<Object[]>();
				
		logger.trace("\n semesterSubId: "+ semesterSubId +" | progGroupId: "+ progGroupId 
				+" | studentBatch: "+ studentBatch +" | progSpecId: "+ progSpecId 
				+" | registerNumber: "+ registerNumber +" | progSpecCode: "+ progSpecCode 
				+" | costCenterId: "+ costCenterId +" | studentSemester: "+ studentSemester);
		
		if (allowCompFlag == 1)
		{
			tempObjectList = compulsoryCourseConditionDetailRepository.findCompulsoryCourseListByProgSpecId2(
									semesterSubId, progGroupId, studentBatch, progSpecId.toString(), 
									costCenterId.toString());
		}
		
		//Checking the Pre-requisite Condition is satisfied or not
		if (!tempObjectList.isEmpty())
		{
			for(Object[] e : tempObjectList)
			{
				compCheckFlag = 2;
				preReqType = 0;
				preReqValue = 0;
				preReqValue2 = 0;
				preReqParam = "";
				preReqParam2 = "";
				preReqParam3 = "";
				grade = "NONE";
				mark = 0;
				
				preReqParamArray = new String[]{};
				tempObjectList2.clear();
								
				courseId = e[0].toString();
				preReqType = Integer.parseInt(e[1].toString());
				
				if (e[2] != null)
				{
					preReqParamArray = e[2].toString().split("\\|");
					for (int i = 0; i < preReqParamArray.length; i++)
					{
						if (i == 0)
						{
							preReqParam = (preReqParamArray[i] == null) ? "" : preReqParamArray[i].trim();
						}
						else if (i == 1)
						{
							preReqParam2 = (preReqParamArray[i] == null) ? "" : preReqParamArray[i].trim();
						}
						else if (i == 2)
						{
							preReqParam3 = (preReqParamArray[i] == null) ? "" : preReqParamArray[i].trim();
						}
					}
				}
				logger.trace("\n preReqType: "+ preReqType +" | preReqParam: "+ preReqParam 
						+" | preReqParam2: "+ preReqParam2 +" | preReqParam3: "+ preReqParam3);
				
				//Default
				if (preReqType == 1)
				{
					compCheckFlag = 1;	
				}
				//Based on Grade with Student History
				else if (preReqType == 2)
				{
					tempObjectList2 = studentHistoryService.getStudentHistoryGrade2(registerNumber, preReqParam2);
					if (!tempObjectList2.isEmpty())
					{
						for (Object[] e2: tempObjectList2)
						{
							grade = e2[0].toString();
							break;
						}
					}
					
					if ((preReqParam.equals("GD1")) && (grade.equals("S") || grade.equals("A") 
								|| grade.equals("B")))
					{
						compCheckFlag = 1;
					}
					else if ((preReqParam.equals("GD2")) && (grade.equals("C") || grade.equals("D") 
									|| grade.equals("E") || grade.equals("F") || grade.equals("N") 
									|| grade.equals("N1") || grade.equals("N2") || grade.equals("N3") 
									|| grade.equals("N4") || grade.equals("NONE")))
					{
						compCheckFlag = 1;
					}
				}
				//Based on Mark with SPT Result
				else if (preReqType == 3)
				{
					if (!preReqParam2.equals(""))
					{
						preReqValue = Integer.parseInt(preReqParam2);
					}
					
					if (!preReqParam3.equals(""))
					{
						preReqValue2 = Integer.parseInt(preReqParam3);
					}
					
					tempObjectList2 = semesterMasterService.getSPTResultByRegisterNumberAndSemester(registerNumber, studentSemester);
					if (!tempObjectList2.isEmpty())
					{
						for (Object[] e2: tempObjectList2)
						{
							mark = Integer.parseInt(e2[0].toString());
							break;
						}
					}
					
					if ((preReqParam.equals("LE")) && (mark <= preReqValue))
					{
						compCheckFlag = 1;
					}
					else if ((preReqParam.equals("GT")) && (mark > preReqValue))
					{
						compCheckFlag = 1;
					}
					else if ((preReqParam.equals("EQ")) && (mark == preReqValue))
					{
						compCheckFlag = 1;
					}
					else if ((preReqParam.equals("LT")) && (mark < preReqValue))
					{
						compCheckFlag = 1;
					}
					else if ((preReqParam.equals("GE")) && (mark >= preReqValue))
					{
						compCheckFlag = 1;
					}
					else if ((preReqParam.equals("BW")) && (mark >= preReqValue) && (mark <= preReqValue2))
					{
						compCheckFlag = 1;
					}
				}
				//Based on Previous Semester Registration
				else if (preReqType == 4)
				{
					for (String prqp : preReqParam2.split("/"))
					{
						logger.trace("\n prqp: "+ prqp);
						tempObjectList2 = courseRegistrationService.getByRegisterNumberCourseCode3(preReqParam, 
												registerNumber, prqp);
						if (!tempObjectList2.isEmpty())
						{
							compCheckFlag = 1;
							break;
						}
					}
				}
				//Based on Mark with EPT Result
				else if (preReqType == 5)
				{
					if (!preReqParam2.equals(""))
					{
						preReqValue = Integer.parseInt(preReqParam2);
					}
					
					if (!preReqParam3.equals(""))
					{
						preReqValue2 = Integer.parseInt(preReqParam3);
					}
					
					tempObjectList2 = semesterMasterService.getEPTDetailByRegisterNumber(registerNumber);
					if (!tempObjectList2.isEmpty())
					{
						for (Object[] e2: tempObjectList2)
						{
							mark = Integer.parseInt(e2[0].toString());
							break;
						}
						
						if ((preReqParam.equals("LE")) && (mark <= preReqValue))
						{
							compCheckFlag = 1;
						}
						else if ((preReqParam.equals("GT")) && (mark > preReqValue))
						{
							compCheckFlag = 1;
						}
						else if ((preReqParam.equals("EQ")) && (mark == preReqValue))
						{
							compCheckFlag = 1;
						}
						else if ((preReqParam.equals("LT")) && (mark < preReqValue))
						{
							compCheckFlag = 1;
						}
						else if ((preReqParam.equals("GE")) && (mark >= preReqValue))
						{
							compCheckFlag = 1;
						}
						else if ((preReqParam.equals("BW")) && (mark >= preReqValue) && (mark <= preReqValue2))
						{
							compCheckFlag = 1;
						}
					}
				}
				//Completed based on Student History & Previous Semester Registration
				else if (preReqType == 6)
				{
					tempObjectList2 = studentHistoryService.getStudentHistoryGrade2(registerNumber, preReqParam);
					if (!tempObjectList2.isEmpty())
					{
						compCheckFlag = 1;
					}
					
					if (compCheckFlag == 2)
					{
						tempObjectList2.clear();
						tempObjectList2 = courseRegistrationService.getPrevSemCourseRegistrationByRegisterNumber3(
												registerNumber, preReqParam);
						if (!tempObjectList2.isEmpty())
						{
							compCheckFlag = 1;
						}
					}
				}
				//Not Completed based on Student History & Previous Semester Registration
				else if (preReqType == 7)
				{
					tempObjectList2 = studentHistoryService.getStudentHistoryGrade2(registerNumber, preReqParam);
					if (tempObjectList2.isEmpty())
					{
						compCheckFlag = 1;
					}
					
					if (compCheckFlag == 1)
					{
						compCheckFlag = 2;
						tempObjectList2.clear();
						tempObjectList2 = courseRegistrationService.getPrevSemCourseRegistrationByRegisterNumber3(
												registerNumber, preReqParam);
						if (tempObjectList2.isEmpty())
						{
							compCheckFlag = 1;
						}
					}
				}
				//Based on PCMB status
				else if (preReqType == 8)
				{
					pcmbStatus = semesterMasterService.getPCMBStatusByRegisterNumber(registerNumber);
					if ((pcmbStatus == null) || (pcmbStatus.equals("")) || (pcmbStatus.equals("NONE")))
					{
						pcmbStatus = semesterMasterService.getPCMBStatusFromAdmissionsByRegisterNumber(registerNumber.get(0));
					}
					if ((pcmbStatus == null) || (pcmbStatus.equals("")))
					{
						pcmbStatus = "NONE";
					}
										
					if (!pcmbStatus.equals("NONE"))
					{
						for (String prqp : preReqParam.split("/"))
						{
							if (prqp.equals(pcmbStatus))
							{
								compCheckFlag = 1;
								break;
							}
						}
					}
				}
				
				//Checking in Academic History
				if (compCheckFlag == 1)
				{
					tempObjectList2.clear();
					tempObjectList2 = studentHistoryService.getStudentHistoryGrade2(registerNumber, courseId);
					if (tempObjectList2.isEmpty())
					{
						compCheckFlag = 1;
					}
					else
					{
						compCheckFlag = 2;
					}
				}
				
				//Checking in Course Registration By Excluding Current Semester
				if (compCheckFlag == 1)
				{
					tempObjectList2.clear();
					tempObjectList2 = courseRegistrationService.getByRegisterNumberCourseCodeAndExcludeSemesterSubId(
											registerNumber, courseId, semesterSubId);
					if (tempObjectList2.isEmpty())
					{
						compCheckFlag = 1;
					}
					else
					{
						compCheckFlag = 2;
					}
				}
				
				logger.trace("\n "+ Arrays.deepToString(e));
				logger.trace("\n compCheckFlag: "+ compCheckFlag);
				if (compCheckFlag == 1)
				{
					tempCompCourseList.add(courseId);
				}
			}
		}
		
		return tempCompCourseList;
	}	
		
	public List<String> getSoftSkillCourseList(String semesterSubId, Integer progGroupId, Integer studentBatch)
	{
		return compulsoryCourseConditionDetailRepository.findSoftSkillCourseList(semesterSubId, progGroupId, studentBatch);
	}
	
	public List<Object[]> getByCourseId(String semesterSubId, Integer progGroupId, Integer studentBatch, String courseId)
	{
		return compulsoryCourseConditionDetailRepository.findByCourseId(semesterSubId, progGroupId, studentBatch, courseId);
	}
}
