package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseRegistrationModel;
import org.vtop.CourseRegistration.model.CourseRegistrationPKModel;
import org.vtop.CourseRegistration.repository.CourseRegistrationRepository;


@Service
@Transactional(readOnly=true)
public class CourseRegistrationService
{
	@Autowired private CourseRegistrationRepository courseRegistrationRepository;
	//@Autowired private StudentHistoryService studentHistoryService;
	
	//private static final Logger logger = LogManager.getLogger(CourseRegistrationService.class);
			
		
	public CourseRegistrationModel getOne(CourseRegistrationPKModel courseRegistrationPKModel)
	{
		return courseRegistrationRepository.findById(courseRegistrationPKModel).orElse(null);
	}
	
	public List<Object[]> getByRegisterNumber3(String semesterSubId, String registerNumber)
	{
		return courseRegistrationRepository.findByRegisterNumber3(semesterSubId, registerNumber);
	}
	
	public List<Object[]> getByRegisterNumberAndClassGroup(String semesterSubId, String registerNumber, String[] classGroupId)
	{
		return courseRegistrationRepository.findByRegisterNumberAndClassGroup(semesterSubId, registerNumber, classGroupId);
	}
	
	public List<CourseRegistrationModel> getByRegisterNumberCourseIdByClassGroupId(String semesterSubId, 
											String registerNumber, String courseId, String[] classGroupId)
	{
		return courseRegistrationRepository.findByRegisterNumberCourseIdByClassGroupId(semesterSubId, 
					registerNumber, courseId, classGroupId);
	}
	
	public CourseRegistrationModel getByRegisterNumberCourseIdAndType(String semesterSubId, String registerNumber, 
										String courseId, String courseType)
	{
		return courseRegistrationRepository.findByRegisterNumberCourseIdAndType(semesterSubId, registerNumber, 
					courseId, courseType);
	}
	
	public List<CourseRegistrationModel> getByRegisterNumberCourseCode(String semesterSubId, String registerNumber, 
											String courseCode)
	{
		return courseRegistrationRepository.findByRegisterNumberCourseCode(semesterSubId, registerNumber, courseCode);
	}
	
	public Integer getRegisterNumberTCCountByClassGroupId(String semesterSubId, String registerNumber, 
						String[] classGroupId)
	{
		Integer tempCount = 0;
		
		tempCount = courseRegistrationRepository.findRegisterNumberTCCountByClassGroupId(semesterSubId, 
						registerNumber, classGroupId);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
		
	public Integer getCourseCountByRegisterNumberAndCourseOption(String semesterSubId, String registerNumber, 
						List<String> courseOption)
	{
		Integer tempCount = 0;
		
		tempCount = courseRegistrationRepository.findCourseCountByRegisterNumberAndCourseOption(
						semesterSubId, registerNumber, courseOption);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
		
	public Integer getGICourseCountByRegisterNumberCourseOptionAndClassGroup(String semesterSubId, String registerNumber, 
						String[] classGroupId)
	{
		Integer tempCount = 0;
		
		tempCount = courseRegistrationRepository.findCourseCountByRegisterNumberCourseOptionAndClassGroup(semesterSubId, 
						registerNumber, Arrays.asList("GI","GICE"), Arrays.asList(classGroupId));
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
		
	public Float getRegCreditByRegisterNumberAndNCCourseCode(String semesterSubId, String registerNumber, 
						List<String> ncCourseCode)
	{
		Float tempCount = 0F;
		
		tempCount = courseRegistrationRepository.findRegCreditByRegisterNumberAndNCCourseCode(semesterSubId, 
						registerNumber, ncCourseCode);
		if (tempCount == null)
		{
			tempCount = 0F;
		}
		
		return tempCount;
	}	
	
	public Integer getProjectCourseCountByRegisterNumber(String semesterSubId, String registerNumber, List<String> evaluationType)
	{
		Integer tempCount = 0;
		
		tempCount = courseRegistrationRepository.findProjectCourseCountByRegisterNumber(semesterSubId, registerNumber, evaluationType);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
	
	
	public List<Object[]> getRegisteredSlots2(String semesterSubId, String registerNumber)
	{
		return courseRegistrationRepository.findRegisteredSlots2(semesterSubId, registerNumber);
	}
	
	public List<Object[]> getRegisteredSlotsByNotClassGroup(String semesterSubId, String registerNumber, List<String> classGroupId)
	{
		return courseRegistrationRepository.findRegisteredSlotsByNotClassGroup(semesterSubId, registerNumber, classGroupId);
	}
	
	public List<Object[]> getRegisteredSlotsBySemesterAndNotClassGroup(List<String> semesterSubId, String registerNumber, List<String> classGroupId)
	{
		return courseRegistrationRepository.findRegisteredSlotsBySemesterAndNotClassGroup(semesterSubId, registerNumber, classGroupId);
	}
	
	
	public List<Object[]> getRegisteredSlotsforUpdate2(String semesterSubId, String registerNumber, String oldClassId)
	{
		return courseRegistrationRepository.findRegisteredSlotsforUpdate2(semesterSubId, registerNumber, oldClassId);
	}
	
	public List<Object[]> getRegisteredSlotsByNotClassGroupforUpdate(String semesterSubId, String registerNumber, String oldClassId, 
								List<String> classGroupId)
	{
		return courseRegistrationRepository.findRegisteredSlotsByNotClassGroupforUpdate(semesterSubId, registerNumber, oldClassId, 
					classGroupId);
	}
	
	public List<Object[]> getRegisteredSlotsBySemesterAndNotClassGroupforUpdate(List<String> semesterSubId, String registerNumber, String oldClassId, 
								List<String> classGroupId)
	{
		return courseRegistrationRepository.findRegisteredSlotsBySemesterAndNotClassGroupforUpdate(semesterSubId, registerNumber, oldClassId, 
					classGroupId);
	}	
	
	
	
	public List<Object[]> getRegistrationAndWaitingSlotDetail(String semesterSubId, String registerNumber)
	{
		return courseRegistrationRepository.findRegistrationAndWaitingSlotDetail(semesterSubId, registerNumber);
	}
	
	public List<Object[]> getRegistrationAndWaitingByNotClassGroupSlotDetail(String semesterSubId, String registerNumber, List<String> classGroupId)
	{
		return courseRegistrationRepository.findRegistrationAndWaitingByNotClassGroupSlotDetail(semesterSubId, registerNumber, classGroupId);
	}	
	
	public List<Object[]> getRegistrationAndWaitingSlotDetailByNotClassGroup(List<String> semesterSubId, String registerNumber, List<String> classGroupId)
	{
		return courseRegistrationRepository.findRegistrationAndWaitingSlotDetailByNotClassGroup(semesterSubId, registerNumber, classGroupId);
	}	
	
	public List<String> getRegisteredCourseByClassGroup(String semesterSubId, String registerNumber, 
							String[] classGroupId)
	{
		return courseRegistrationRepository.findRegisteredCourseByClassGroup(semesterSubId, registerNumber, classGroupId);
	}
	
	public List<String> getPrevSemCourseRegistrationPARequisiteByRegisterNumber(List<String> registerNumber, List<String> courseCode)
	{
		return courseRegistrationRepository.findPrevSemCourseRegistrationPARequisiteByRegisterNumber(registerNumber, courseCode);
	}
	
	public List<String> getRegistrationAndWLCourseByRegisterNumber(String semesterSubId, List<String> registerNumber)
	{
		return courseRegistrationRepository.findRegistrationAndWLCourseByRegisterNumber(semesterSubId, registerNumber);
	}
	
	public List<Object[]> getCourseRegWlSlotByStudent(String semesterSubId, String registerNumber, Integer patternId)
	{
		return courseRegistrationRepository.findCourseRegWlSlotByStudent(semesterSubId, registerNumber);
	}
	
	public List<Object[]> getCourseRegWlSlotByStudent2(String semesterSubId, String registerNumber, Integer patternId)
	{
		return courseRegistrationRepository.findCourseRegWlSlotByStudent2(semesterSubId, registerNumber);
	}
	
	public List<Object[]> getCancelCourseByRegisterNumberAndCourseCode(List<String> registerNumber, String courseCode)
	{
		return courseRegistrationRepository.findCancelCourseByRegisterNumberAndCourseCode(registerNumber, courseCode);
	}
	
	/*public List<Object[]> getPrevSemCourseRegistrationByRegisterNumber3(List<String> registerNumber, String courseCode)
	{
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		String prvSemSubId = "";
		int prvSemResultFlag = 2;
		
		prvSemRegList = courseRegistrationRepository.findPrevSemCourseRegistrationByRegisterNumber2(registerNumber, courseCode);
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
				prvSemRPList.clear();
				logger.trace("\n prvSemSubId: "+ prvSemSubId +" | courseCode: "+ courseCode);
				
				prvSemRPList = studentHistoryService.getResultPublishedCourseDataBySemRegNoAndCourseCode(
									prvSemSubId, registerNumber, courseCode);
				if (!prvSemRPList.isEmpty())
				{
					if (!studentHistoryService.getStudentHistoryGrade2(registerNumber, courseCode).isEmpty())
					{
						prvSemResultFlag = 1;
					}
					else
					{
						prvSemResultFlag = 2;
						break;
					}
				}
				else
				{
					prvSemResultFlag = 2;
					break;
				}
			}
		}
			
		if (prvSemResultFlag == 1)
		{
			prvSemRegList.clear();
		}
						
		return prvSemRegList;
	}*/
	
	/*public List<Object[]> getPrevSemCourseRegistrationCEByRegisterNumber3(List<String> registerNumber, String courseCode)
	{
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		String prvSemSubId = "", prvSemCourseCode = "";
		int prvSemResultFlag = 2;
				
		prvSemRegList = courseRegistrationRepository.findPrevSemCourseRegistrationCEByRegisterNumber2(registerNumber, courseCode);
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
				prvSemCourseCode = e[2].toString();
				prvSemRPList.clear();
				
				prvSemRPList = studentHistoryService.getResultPublishedCourseDataBySemRegNoAndCourseCode(prvSemSubId, 
										registerNumber, prvSemCourseCode);				
				if (!prvSemRPList.isEmpty())
				{
					if (!studentHistoryService.getStudentHistoryGrade2(registerNumber, prvSemCourseCode).isEmpty())
					{
						prvSemResultFlag = 1;
					}
					else
					{
						prvSemResultFlag = 2;
						break;
					}
				}
				else
				{
					prvSemResultFlag = 2;
					break;
				}
			}
		}
		
		if (prvSemResultFlag == 1)
		{
			prvSemRegList.clear();
		}
				
		return prvSemRegList;
	}*/
	
	public List<Object[]> getPrevSemCourseDetailWithCEByRegisterNumber(List<String> registerNumber, String courseCode)
	{
		return courseRegistrationRepository.findPrevSemCourseDetailWithCEByRegisterNumber(registerNumber, courseCode);
	}
		
	//For registration purpose
	public List<Object[]> getRegistrationAndWLByRegisterNumberAndCourseCode(String semesterSubId, String registerNumber, 
							String courseCode)
	{
		return courseRegistrationRepository.findRegistrationAndWLByRegisterNumberAndCourseCode(semesterSubId, registerNumber, 
					courseCode);
	}
	
	public List<Object[]> getCERegistrationAndWLByRegisterNumberAndCourseCode(String semesterSubId, String registerNumber, 
							String courseCode)
	{
		return courseRegistrationRepository.findCERegistrationAndWLByRegisterNumberAndCourseCode(semesterSubId, registerNumber, 
			courseCode);
	}

	
	public List<Object[]> getCourseOptionByRegisterNumberAndCourseCode(String semesterSubId, String registerNumber, 
								String courseCode)
	{
		return courseRegistrationRepository.findCourseOptionByRegisterNumberAndCourseCode(semesterSubId, registerNumber, 
				courseCode);
	}
	
	/*public float getPSRegisteredTotalCreditsByRegisterNumber4(List<String> registerNumber)
	{		
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		List<String> prvSemRPCourseList = new ArrayList<String>();
		String prvSemSubId = "", dpPrvSemSubId = "";
		Float prvSemTotalCredit = 0F;
		
		prvSemRegList = courseRegistrationRepository.findPSRegisteredCourseCreditsByRegisterNumber2(registerNumber);
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
			
				if (!dpPrvSemSubId.equals(prvSemSubId))
				{
					prvSemRPList.clear();
					prvSemRPCourseList.clear();
					
					prvSemRPList = studentHistoryService.getResultPublishedCourseDataBySemAndRegNo(prvSemSubId, registerNumber);
					if (!prvSemRPList.isEmpty())
					{
						for (Object[] e2: prvSemRPList)
						{
							prvSemRPCourseList.add(e2[2].toString());
						}
					}
					
					dpPrvSemSubId = prvSemSubId;
				}
				
				if (prvSemRPCourseList.isEmpty())
				{
					prvSemTotalCredit = prvSemTotalCredit + Float.parseFloat(e[2].toString());
				}
				else if ((!prvSemRPCourseList.isEmpty()) && (!prvSemRPCourseList.contains(e[1].toString())))
				{
					prvSemTotalCredit = prvSemTotalCredit + Float.parseFloat(e[2].toString());
				}
			}
		}
						
		return prvSemTotalCredit;
	}*/
	
	/*public List<String> getPrevSemResultCourseList(List<String> registerNumber)
	{
		List<String> tempPrvSemSubId = new ArrayList<String>();
		List<Object[]> tempPrvSemResultList = new ArrayList<Object[]>();
		List<String> tempPrvSemResultCourseList = new ArrayList<String>();
		
		tempPrvSemSubId = courseRegistrationRepository.findPrevSemesterSubIdByRegisterNumber(registerNumber);
		if (!tempPrvSemSubId.isEmpty())
		{
			for (String eSemSubId: tempPrvSemSubId)
			{	
				tempPrvSemResultList.clear();
				
				tempPrvSemResultList = studentHistoryService.getResultPublishedCourseDataBySemAndRegNo(eSemSubId, registerNumber);
				if (!tempPrvSemResultList.isEmpty())
				{
					for (Object[] e: tempPrvSemResultList)
					{
						tempPrvSemResultCourseList.add(e[3].toString());
					}
				}
			}
		}
				
		return tempPrvSemResultCourseList;
	}*/
	
	//Blocked Course Id List for Update
	public List<String> getBlockedCourseIdByRegisterNumberForUpdate(String semesterSubId, String registerNumber)
	{
		List<String> tempCourseIdList = new ArrayList<String>();
		
		tempCourseIdList = courseRegistrationRepository.findBlockedCourseIdByRegisterNumberForUpdate2(
								semesterSubId, registerNumber);
		if (tempCourseIdList.isEmpty())
		{
			tempCourseIdList.add("NONE");
		}
						
		return tempCourseIdList;
	}
	
	//Blocked Course Id List for Delete
	public List<String> getBlockedCourseIdByRegisterNumberForDelete(String semesterSubId, String registerNumber)
	{
		List<String> tempCourseIdList = new ArrayList<String>();
		
		tempCourseIdList = courseRegistrationRepository.findBlockedCourseIdByRegisterNumberForDelete(semesterSubId, 
								registerNumber);
		if (tempCourseIdList.isEmpty())
		{
			tempCourseIdList.add("NONE");
		}
				
		return tempCourseIdList;
	}
	
	//UE Registered Course(s)
	public List<String> getUECourseByRegisterNumber(List<String> registerNumber)
	{
		return courseRegistrationRepository.findUECourseByRegisterNumber(registerNumber);
	}
	
	public List<String> getUECourseByRegisterNumberAndCourseOption(String semesterSubId, String registerNumber, 
							String courseCategory)
	{
		List<String> tempCourseList = new ArrayList<String>();
		
		if (courseCategory.equals("UC"))
		{
			tempCourseList = courseRegistrationRepository.findUECourseByRegisterNumberAndCourseOption(semesterSubId, 
									registerNumber, Arrays.asList("RUCUE"));
		}
		else if (courseCategory.equals("PE"))
		{
			tempCourseList = courseRegistrationRepository.findUECourseByRegisterNumberAndCourseOption(semesterSubId, 
									registerNumber, Arrays.asList("RPEUE","RGA","HON"));
		}
		else if (courseCategory.equals("UE"))
		{
			tempCourseList = courseRegistrationRepository.findUECourseByRegisterNumberAndCourseOption(semesterSubId, 
									registerNumber, Arrays.asList("RUCUE","RPEUE","RGA","MIN"));
		}
		
		return tempCourseList;
	}
	
	
	//For Compulsory Course Checking
	public List<Object[]> getByRegisterNumberCourseCode3(String semesterSubId, List<String> registerNumber, 
								String courseCode)
	{
		return courseRegistrationRepository.findByRegisterNumberCourseCode3(semesterSubId, registerNumber, courseCode);
	}
	
	public List<Object[]> getByRegisterNumberCourseCodeAndExcludeSemesterSubId(List<String> registerNumber, 
								String courseCode, String semesterSubId)
	{
		return courseRegistrationRepository.findByRegisterNumberCourseCodeAndExcludeSemesterSubId(registerNumber, 
						courseCode, semesterSubId);
	}	
	
	
	/*public List<String> getPrevSemCourseDetailByRegisterNumber(List<String> registerNumber)
	{
		List<String> returnCourseCodeList = new ArrayList<String>();
		
		String prvSemSubId = "";
		List<String> courseCodeList = new ArrayList<String>();
		List<String> courseIdList = new ArrayList<String>();
		List<Object[]> tempObjectList = new ArrayList<Object[]>();
		List<Object[]> tempObjectList2 = new ArrayList<Object[]>();
				
		tempObjectList = courseRegistrationRepository.findPrevSemCourseDetailByRegisterNumber(registerNumber);
		if (!tempObjectList.isEmpty())
		{
			for (Object[] e: tempObjectList)
			{
				if (!e[0].toString().equals(prvSemSubId))
				{
					logger.trace("\n Before=> prvSemSubId: "+ prvSemSubId +" | courseIdList: "+ courseIdList);
					if ((!prvSemSubId.equals("")) && (!courseIdList.isEmpty()))
					{
						for (String str : studentHistoryService.getCSCourseCodeByRegisterNoAndCourseId(prvSemSubId, 
												registerNumber, courseIdList))
						{
							returnCourseCodeList.add(str);
						}
					}
					
					prvSemSubId = e[0].toString();
					tempObjectList2.clear();
					courseCodeList.clear();
					courseIdList.clear();
					
					tempObjectList2 = studentHistoryService.getResultPublishedCourseDataBySemAndRegNo(prvSemSubId, registerNumber);
					if (!tempObjectList2.isEmpty())
					{
						for (Object[] e2 : tempObjectList2)
						{
							courseCodeList.add(e2[3].toString());
						}
					}
					logger.trace("\n After=> prvSemSubId : "+ prvSemSubId +" | courseCodeList: "+ courseCodeList);
				}
				
				if (!courseCodeList.contains(e[2].toString()))
				{
					courseIdList.add(e[1].toString());
					returnCourseCodeList.add(e[2].toString());
				}
			}
			
			logger.trace("\n Final=> prvSemSubId: "+ prvSemSubId +" | courseIdList: "+ courseIdList);
			if ((!prvSemSubId.equals("")) && (!courseIdList.isEmpty()))
			{
				for (String str : studentHistoryService.getCSCourseCodeByRegisterNoAndCourseId(prvSemSubId, 
										registerNumber, courseIdList))
				{
					returnCourseCodeList.add(str);
				}
			}
		}
										
		return returnCourseCodeList;
	}*/
	
	
	/*public List<Object[]> getResultUnpublishedSemesterCreditDetail(List<String> registerNumber, List<String> courseOptionCode, 
								List<String> exceptCourseCode)
	{		
		List<Object[]> returnObjectList = new ArrayList<Object[]>();
		List<String> semesterSubId = new ArrayList<String>();
		
		semesterSubId = courseRegistrationRepository.findResultUnpublishedSemesterSubId();
		if (semesterSubId.isEmpty())
		{
			semesterSubId.add("NONE");
		}
		returnObjectList = courseRegistrationRepository.findByRegisterNumberCourseOptionAndExceptCourse(semesterSubId, 
								registerNumber, courseOptionCode, exceptCourseCode);
		
		return returnObjectList;
	}*/
	public List<Object[]> getResultUnpublishedSemesterCreditDetail(List<String> registerNumber, List<String> courseOptionCode)
	{				
		return courseRegistrationRepository.findResultUnpublishedSemesterCreditDetail(registerNumber, courseOptionCode);
	}
	
	
	/*public List<Object[]> getRegisteredSemesterCreditDetail(List<String> registerNumber, List<String> courseOptionCode)
	{		
		List<Object[]> returnObjectList = new ArrayList<Object[]>();
		List<String> semesterSubId = new ArrayList<String>();
		
		semesterSubId = courseRegistrationRepository.findRegisteredSemesterSubId();
		if (semesterSubId.isEmpty())
		{
			semesterSubId.add("NONE");
		}
		returnObjectList = courseRegistrationRepository.findByRegisterNumberCourseOptionAndExceptCourse(semesterSubId, 
								registerNumber, courseOptionCode, Arrays.asList("NONE"));
		
		return returnObjectList;
	}*/
	public List<Object[]> getRegisteredSemesterCreditDetail(List<String> registerNumber, List<String> courseOptionCode)
	{				
		return courseRegistrationRepository.findRegisteredSemesterCreditDetail(registerNumber, courseOptionCode);
	}
	
	
	/*public List<Object[]> getWaitingListSemesterCreditDetail(List<String> registerNumber, List<String> courseOptionCode)
	{		
		List<Object[]> returnObjectList = new ArrayList<Object[]>();
		List<String> semesterSubId = new ArrayList<String>();
		
		semesterSubId = courseRegistrationRepository.findRegisteredSemesterSubId();
		if (semesterSubId.isEmpty())
		{
			semesterSubId.add("NONE");
		}
		returnObjectList = courseRegistrationRepository.findWaitingByRegisterNumberAndCourseOption(semesterSubId, 
								registerNumber, courseOptionCode);
		
		return returnObjectList;
	}*/
	public List<Object[]> getWaitingListSemesterCreditDetail(List<String> registerNumber, List<String> courseOptionCode)
	{		
		return courseRegistrationRepository.findWaitingListSemesterCreditDetail(registerNumber, courseOptionCode);
	}
	

	public String getGradeCategory(int admissionYear, String courseCategory, String genericCourseType, String programmeGroupCode)
	{
		String returnGradeCategory = "CG";
		
		if ((admissionYear == 2018) && programmeGroupCode.equals("BSC4"))
		{
			if (courseCategory.equals("NC"))
			{
				returnGradeCategory = "NCPF";
			}
			else if (courseCategory.equals("BC"))
			{
				returnGradeCategory = "NCG";
			}	
		}
		else if ((admissionYear >= 2019) && (courseCategory.equals("NC") || courseCategory.equals("BC")))
		{
			if (genericCourseType.equals("ECA"))
			{
				returnGradeCategory = "NCPF";
			}
			else
			{
				returnGradeCategory = "NCG";
			}	
		}
		else if ((admissionYear >= 2021) && (courseCategory.equals("NGCR") || courseCategory.equals("FCNG")))
		{
			if (genericCourseType.equals("PJT"))
			{
				returnGradeCategory = "NCPF";
			}
			else
			{
				returnGradeCategory = "NCG";
			}	
		}
		
		return returnGradeCategory;
	}
	
	
	//New Service
	public List<Object[]> getRegistrationAndWLWithCEByRegisterNumberAndCourseCode(String semesterSubId, String registerNumber, 
								String courseCode)
	{
		return courseRegistrationRepository.findRegistrationAndWLWithCEByRegisterNumberAndCourseCode(semesterSubId, 
					registerNumber, courseCode);
	}
	
	public List<Object[]> getCourseDetailFromRegistrationAndStudentHistoryByRegisterNoAndCourseCode(List<String> registerNumber, 
								String courseCode)
	{
		return courseRegistrationRepository.findCourseDetailFromRegistrationAndStudentHistoryByRegisterNoAndCourseCode(
					registerNumber, courseCode);
	}
	
	public List<Object[]> getCourseDetailFromRegistrationAndStudentHistoryByRegisterNoCourseCodeAndExcludeSemester(List<String> registerNumber, 
								String courseCode, String semesterSubId)
	{
		return courseRegistrationRepository.findCourseDetailFromRegistrationAndStudentHistoryByRegisterNoCourseCodeAndExcludeSemester(
					registerNumber, courseCode, semesterSubId);
	}
	
	public List<String> getPreviousSemesterCourseByRegisterNumber(List<String> registerNumber)
	{
		return courseRegistrationRepository.findPreviousSemesterCourseByRegisterNumber(registerNumber);
	}
	
	public Float getPreviousSemesterCreditByRegisterNumber(List<String> registerNumber)
	{
		Float returnCredit = 0f;
		
		returnCredit = courseRegistrationRepository.findPreviousSemesterCreditByRegisterNumber(registerNumber);
		if (returnCredit == null)
		{
			returnCredit = 0f;
		}
		
		return returnCredit;
	}
	
	
	//Compulsory Course Registration & Allocation Status
	public List<Object[]> getCompulsoryCourseRegistrationAndAllocationStatus(String semesterSubId, String registerNumber, List<String> compCourseCode, 
								String[] classGroupId, String[] classType, String progGroupCode, String progSpecCode, String costCentreCode, 
								String[] courseSystem)
	{
		List<Object[]> returnObjectList = new ArrayList<>();
		
		if (progGroupCode.equals("RP"))
		{
			returnObjectList = courseRegistrationRepository.findCompulsoryCourseRegistrationAndAllocationForRP(semesterSubId, registerNumber, 
									compCourseCode, classGroupId, classType, courseSystem);
		}
		else
		{
			returnObjectList = courseRegistrationRepository.findCompulsoryCourseRegistrationAndAllocation(semesterSubId, registerNumber, 
									compCourseCode, classGroupId, classType, progGroupCode, progSpecCode, costCentreCode, courseSystem);
		}
		
		return returnObjectList;
	}
	
	public List<String> getByRegisterNumberAndCourseCodeForPARequisite(List<String> registerNumber, List<String> courseCode)
	{
		return courseRegistrationRepository.findByRegisterNumberAndCourseCodeForPARequisite(registerNumber, courseCode);
	}
}
