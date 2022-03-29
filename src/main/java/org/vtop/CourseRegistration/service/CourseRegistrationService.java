package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseRegistrationModel;
import org.vtop.CourseRegistration.model.CourseRegistrationPKModel;
import org.vtop.CourseRegistration.repository.CourseRegistrationRepository;


@Service
@Transactional("academicsTransactionManager")
public class CourseRegistrationService
{
	@Autowired private CourseRegistrationRepository courseRegistrationRepository;
	@Autowired private StudentHistoryService studentHistoryService;
		
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
	
	public List<Object[]> getRegisteredSlotsforUpdate2(String semesterSubId, String registerNumber, String oldClassId)
	{
		return courseRegistrationRepository.findRegisteredSlotsforUpdate2(semesterSubId, registerNumber, oldClassId);
	}
	
	public List<Object[]> getRegistrationAndWaitingSlotDetail(String semesterSubId, String registerNumber)
	{
		return courseRegistrationRepository.findRegistrationAndWaitingSlotDetail(semesterSubId, registerNumber);
	}
	
	public List<String> getRegisteredCourseByClassGroup(String semesterSubId, String registerNumber, 
							String[] classGroupId)
	{
		return courseRegistrationRepository.findRegisteredCourseByClassGroup(semesterSubId, registerNumber, classGroupId);
	}
	
	public List<String> getPrevSemCourseRegistrationPARequisiteByRegisterNumber(List<String> registerNumber, 
			List<String> courseCode)
	{
		return courseRegistrationRepository.findPrevSemCourseRegistrationPARequisiteByRegisterNumber(registerNumber, courseCode);
	}
	
	public List<String> getRegistrationAndWLCourseByRegisterNumber(String semesterSubId, List<String> registerNumber)
	{
		return courseRegistrationRepository.findRegistrationAndWLCourseByRegisterNumber(semesterSubId, registerNumber);
	}
	
	
	//Procedure
	public String courseRegistrationAdd2(String psemsubid, String pclassid, String pregno, String pcourseid, 
						String pcomponent_type, String pcourse_option, Integer pregstatus, Integer pregcomponent_type, 
						String ploguserid, String plogipaddress, String pregtype, String pold_course_code, String pcalltype, 
						String pold_course_type, String pold_exam_month)
	{
		return courseRegistrationRepository.registration_insert_prc(psemsubid, pclassid, pregno, pcourseid, 
					pcomponent_type, pcourse_option, pregstatus, pregcomponent_type, ploguserid, plogipaddress, 
					pregtype, pold_course_code, pcalltype, pold_course_type, pold_exam_month, "NONE");
	}
	
	public String courseRegistrationUpdate2(String psemsubid, String pregno, String pcourseid, String pcomponent_type,
			String pcourse_option, String poldclassid, String pnewclassid, String ploguserid, String plogipaddress,
			Integer pregstatus, Integer pregcomponent_type, String pregtype, String pold_course_code, 
			String pold_course_type, String pold_exam_month)
	{
		return courseRegistrationRepository.registration_update_prc(psemsubid, pregno, pcourseid, pcomponent_type,
					pcourse_option, poldclassid, pnewclassid, ploguserid, plogipaddress, pregstatus, pregcomponent_type, 
					pregtype, pold_course_code, pold_course_type, pold_exam_month, "NONE");
	}
	
	public String courseRegistrationDelete(String psemsubid, String pregno, String pcourseid, String pcalltype, 
			String ploguserid, String plogipaddress, String pregtype, String poldcoursecode)
	{
		return courseRegistrationRepository.registration_delete_prc(psemsubid, pregno, pcourseid, pcalltype, 
					ploguserid, plogipaddress, pregtype, poldcoursecode, "NONE");
	}
	
	
	public List<Object[]> getCourseRegWlSlotByStudent(String semesterSubId, String registerNumber, Integer patternId)
	{
		//return courseRegistrationRepository.findCourseRegWlSlotByStudent(semesterSubId, registerNumber, patternId);
		return courseRegistrationRepository.findCourseRegWlSlotByStudent(semesterSubId, registerNumber);
	}
	
	public List<Object[]> getCourseRegWlSlotByStudent2(String semesterSubId, String registerNumber, Integer patternId)
	{
		//return courseRegistrationRepository.findCourseRegWlSlotByStudent2(semesterSubId, registerNumber, patternId);
		return courseRegistrationRepository.findCourseRegWlSlotByStudent2(semesterSubId, registerNumber);
	}
	
	public List<Object[]> getCancelCourseByRegisterNumberAndCourseCode(List<String> registerNumber, String courseCode)
	{
		return courseRegistrationRepository.findCancelCourseByRegisterNumberAndCourseCode(registerNumber, courseCode);
	}
	
	public List<Object[]> getPrevSemCourseRegistrationByRegisterNumber3(List<String> registerNumber, String courseCode)
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
				//System.out.println("prvSemSubId: "+ prvSemSubId +" | courseCode: "+ courseCode);
				
				prvSemRPList = studentHistoryService.getResultPublishedCourseDataBySemRegNoAndCourseCode(
									prvSemSubId, registerNumber, courseCode);
				if (!prvSemRPList.isEmpty())
				{
					//prvSemResultFlag = 1;
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
	}
	
	public List<Object[]> getPrevSemCourseRegistrationCEByRegisterNumber3(List<String> registerNumber, String courseCode)
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
					//prvSemResultFlag = 1;
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
	
	public float getPSRegisteredTotalCreditsByRegisterNumber4(List<String> registerNumber)
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
	}
	
	public List<String> getPrevSemResultCourseList(List<String> registerNumber)
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
	}
	
	//Blocked Course Id List for Update
	public List<String> getBlockedCourseIdByRegisterNumberForUpdate(String semesterSubId, String registerNumber)
	{
		List<String> tempCourseIdList = new ArrayList<String>();
		
		//tempCourseIdList = courseRegistrationRepository.findBlockedCourseIdByRegisterNumberForUpdate(
		//						semesterSubId, registerNumber);
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
	
	
	public List<String> getPrevSemCourseDetailByRegisterNumber(List<String> registerNumber)
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
					//System.out.println("Before=> prvSemSubId: "+ prvSemSubId +" | courseIdList: "+ courseIdList);
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
					//System.out.println("After=> prvSemSubId : "+ prvSemSubId +" | courseCodeList: "+ courseCodeList);
				}
				
				if (!courseCodeList.contains(e[2].toString()))
				{
					courseIdList.add(e[1].toString());
					returnCourseCodeList.add(e[2].toString());
				}
			}
			
			//System.out.println("Final=> prvSemSubId: "+ prvSemSubId +" | courseIdList: "+ courseIdList);
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
	}
	
	public List<Object[]> getResultUnpublishedSemesterCreditDetail(List<String> registerNumber, List<String> courseOptionCode, 
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
	}
	
	public List<Object[]> getRegisteredSemesterCreditDetail(List<String> registerNumber, List<String> courseOptionCode)
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
	}
	
	public List<Object[]> getWaitingListSemesterCreditDetail(List<String> registerNumber, List<String> courseOptionCode)
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
	}

	
	/*public CourseRegistrationModel saveOne(CourseRegistrationModel courseRegistrationModel)
	{
		return courseRegistrationRepository.save(courseRegistrationModel);
	}*/
	
	/*public List<CourseRegistrationModel> getAll(String semesterSubId)
	{
		return courseRegistrationRepository.findBySemesterSubId(semesterSubId);
	}
		
	public List<CourseRegistrationModel> getByRegisterNumber(String semesterSubId, String registerNumber)
	{
		return courseRegistrationRepository.findByRegisterNumber(semesterSubId, registerNumber);
	}
	
	public List<CourseRegistrationModel> getByRegisterNumberByClassGroupId(String semesterSubId, String registerNumber, 
												String[] classGroupId)
	{
		return courseRegistrationRepository.findByRegisterNumberByClassGroupId(semesterSubId, registerNumber, classGroupId);
	}*/
	
	/*public List<CourseRegistrationModel> getByRegisterNumberCourseId(String semesterSubId, String registerNumber, 
											String courseId)
	{
		return courseRegistrationRepository.findByRegisterNumberCourseId(semesterSubId,	registerNumber, courseId);
	}*/
	
	/*public List<CourseRegistrationModel> getByRegisterNumberCourseCode2(String semesterSubId, String registerNumber, 
												String courseCode)
	{
		return courseRegistrationRepository.findByRegisterNumberCourseCode2(semesterSubId, registerNumber, courseCode);
	}
	
	public void classUpdate(String semesterSubId, String registerNumber, String courseId, 
					String courseType, String classId, String userId, Date timestamp, 
					String ipaddress)
	{				
		courseRegistrationRepository.updateClassIdByRegisterNumberCourseIdType(semesterSubId, 
				registerNumber, courseId, courseType, classId, userId, timestamp, ipaddress);
	}
	
	public void statusUpdate(String semesterSubId, String registerNumber, String courseId, Integer statusNumber)
	{		
		courseRegistrationRepository.updateStatusNoByRegisterNumberCourseId(semesterSubId, registerNumber, 
				courseId, statusNumber);
	}
	
	public void deleteByRegisterNumberCourseId(String semesterSubId, String registerNumber, String courseId)
	{		
		courseRegistrationRepository.deleteByRegisterNumberCourseId(semesterSubId, registerNumber, courseId);
	}
	
	//Registration Count
	public Integer getRegisterNumberTCCount(String semesterSubId, String registerNumber)
	{
		Integer tempCount = 0;
		
		tempCount = courseRegistrationRepository.findRegisterNumberTCCount(semesterSubId, registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}*/
	
	/*public Integer getRegisterNumberRCCount(String semesterSubId, String registerNumber)
	{
		Integer tempCount = 0;
		
		tempCount = courseRegistrationRepository.findRegisterNumberRCCount(semesterSubId, registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
	
	public Integer getRegisterNumberRCCountByClassGroupId(String semesterSubId, String registerNumber, 
						String[] classGroupId)
	{
		Integer tempCount = 0;
		
		tempCount = courseRegistrationRepository.findRegisterNumberRCCountByClassGroupId(semesterSubId, registerNumber, 
						classGroupId);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}*/
	
	/*public Integer getRegisterNumbeGICount(String semesterSubId, String registerNumber)
	{
		Integer tempCount = 0;
		
		tempCount = courseRegistrationRepository.findRegisterNumbeGICount(semesterSubId, registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}*/
	
	/*public Integer getRegisterNumbeICCCount(String semesterSubId, String registerNumber)
	{
		Integer tempCount = 0;
		
		tempCount = courseRegistrationRepository.findRegisterNumbeICCCount(semesterSubId, registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}*/
	
	/*public Integer getPSRegisteredTotalCreditsByRegisterNumber(String[] semesterSubId, String registerNumber)
	{
		Integer tempCount = 0;
		
		tempCount = courseRegistrationRepository.findPSRegisteredTotalCreditsByRegisterNumber(semesterSubId, registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
	
	public Integer getPSRegisteredTotalCreditsByRegisterNumber2(String registerNumber)
	{
		Integer tempCount = 0;
		
		tempCount = courseRegistrationRepository.findPSRegisteredTotalCreditsByRegisterNumber2(registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}*/
	
	/*public List<String> getRegisteredSlots(String semesterSubId, String registerNumber)
	{
		return courseRegistrationRepository.findRegisteredSlots(semesterSubId, registerNumber);
	}*/
		
	/*public List<String> getRegisteredSlotsforUpdate(String semesterSubId, String registerNumber, String oldClassId)
	{
		return courseRegistrationRepository.findRegisteredSlotsforUpdate(semesterSubId, registerNumber, oldClassId);
	}*/
	
	/*public List<String> getRegisteredCourse(String semesterSubId, String registerNumber)
	{
		return courseRegistrationRepository.findRegisteredCourse(semesterSubId, registerNumber);
	}*/
	
	/*public List<CourseRegistrationModel> getRegisterNumberCredits(String semesterSubId, String registerNumber)
	{
		return courseRegistrationRepository.findRegisterNumberCredits(semesterSubId, registerNumber);
	}
	
	public String getStudentPrvSemRegCourseCheck(String[] semesterSubId, String registerNumber, String courseCode)
	{
		return courseRegistrationRepository.findStudentPrvSemRegCourseCheck(semesterSubId, registerNumber, courseCode);
	}
	
	public List<String> getStudentPrvSemPARequisite(String[] semesterSubId, List<String> registerNumber, 
			List<String> courseCode)
	{
		return courseRegistrationRepository.findStudentPrvSemPARequisite(semesterSubId, registerNumber, courseCode);
	}
		
	public void insertFromWaitingToRegistration(String semesterSubId, String registerNumber, String courseId)
	{		
		courseRegistrationRepository.insertWaitingToRegistration(semesterSubId, 
				registerNumber, courseId);
	}*/
		
	
	/*//For Procedure
	public String courseRegistrationAdd(String psemsubid, String pclassid, String pregno, String pcourseid, 
			String pcomponent_type, String pcourse_option, Integer pregstatus, Integer pregcomponent_type, 
			String ploguserid, String plogipaddress, String pregtype, String pold_course_code, String pcalltype)
	{
		String pold_course_type = "", pold_exam_month = ""; 
		if ((!pold_course_code.equals(null)) && (!pold_course_code.equals("")))
		{
			pold_course_type = pcomponent_type;
		}
		
		return courseRegistrationRepository.registration_insert_prc(psemsubid, pclassid, pregno, pcourseid, 
				pcomponent_type, pcourse_option, pregstatus, pregcomponent_type, ploguserid, plogipaddress, 
				pregtype, pold_course_code, pcalltype, pold_course_type, pold_exam_month);
	}*/
		
	/*public String courseRegistrationUpdate(String psemsubid, String pregno, String pcourseid, String pcomponent_type,
			String pcourse_option, String poldclassid, String pnewclassid, String ploguserid, String plogipaddress,
			Integer pregstatus, Integer pregcomponent_type, String pregtype, String pold_course_code)
	{
		String pold_course_type = "", pold_exam_month = ""; 
		if ((!pold_course_code.equals(null)) && (!pold_course_code.equals("")))
		{
			pold_course_type = pcomponent_type;
		}
		
		return courseRegistrationRepository.registration_update_prc(psemsubid, pregno, pcourseid, pcomponent_type,
				pcourse_option, poldclassid, pnewclassid, ploguserid, plogipaddress, pregstatus, pregcomponent_type, 
				pregtype, pold_course_code, pold_course_type, pold_exam_month);
	}*/
	
	/*public String getStudentCourseCancelCheck(List<String> registerNumber, String courseCode)
	{
		return courseRegistrationRepository.findStudentCourseCancelCheck(registerNumber, courseCode);
	}*/
		
	/*public List<Object[]> getPrevSemCourseRegistrationByRegisterNumber(String registerNumber, String courseCode)
	{
		return courseRegistrationRepository.findPrevSemCourseRegistrationByRegisterNumber(registerNumber, courseCode);
	}
	
	public List<Object[]> getPrevSemCourseRegistrationByRegisterNumber2(String registerNumber, String courseCode)
	{
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		String prvSemSubId = "";
		
		prvSemRegList = courseRegistrationRepository.findPrevSemCourseRegistrationByRegisterNumber(registerNumber, courseCode);
		
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
				break;
			}
		
			prvSemRPList = studentHistoryService.getResultPublishedCourseDataBySemAndRegNo(prvSemSubId, 
								Arrays.asList(registerNumber));
		}
		
		if (!prvSemRPList.isEmpty())
		{
			for (Object[] e: prvSemRPList)
			{
				if (e[3].toString().equals(courseCode))
				{
					prvSemRegList.clear();
					break;
				}
			}
		}
						
		return prvSemRegList;
	}*/
	
	/*public List<Object[]> getPrevSemCourseRegistrationCEByRegisterNumber(String registerNumber, String courseCode)
	{
		return courseRegistrationRepository.findPrevSemCourseRegistrationCEByRegisterNumber(registerNumber, courseCode);
	}
	
	public List<Object[]> getPrevSemCourseRegistrationCEByRegisterNumber2(String registerNumber, String courseCode)
	{
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		String prvSemSubId = "", prvSemCourseCode = "";
				
		prvSemRegList = courseRegistrationRepository.findPrevSemCourseRegistrationCEByRegisterNumber(registerNumber, courseCode);
		
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
				prvSemCourseCode = e[2].toString();
				break;
			}
		}
		
		prvSemRPList = studentHistoryService.getResultPublishedCourseDataBySemAndRegNo(prvSemSubId, Arrays.asList(registerNumber));
		if (!prvSemRPList.isEmpty())
		{
			for (Object[] e: prvSemRPList)
			{
				//if (e[3].toString().equals(courseCode))
				if (e[3].toString().equals(prvSemCourseCode))
				{
					prvSemRegList.clear();
					break;
				}
			}
		}
				
		return prvSemRegList;
	}*/
	
	/*public Integer getPSRegisteredTotalCreditsByRegisterNumber3(String registerNumber)
	{		
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		List<String> prvSemRPCourseList = new ArrayList<String>();
		String prvSemSubId = "", dpPrvSemSubId = "";
		Integer prvSemTotalCredit = 0;
		
		prvSemRegList = courseRegistrationRepository.findPSRegisteredCourseCreditsByRegisterNumber(registerNumber);
		
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
			
				if (!dpPrvSemSubId.equals(prvSemSubId))
				{
					prvSemRPList.clear();
					prvSemRPCourseList.clear();
					
					prvSemRPList = studentHistoryService.getResultPublishedCourseDataBySemAndRegNo(prvSemSubId, Arrays.asList(registerNumber));
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
					prvSemTotalCredit = prvSemTotalCredit + Integer.parseInt(e[2].toString());
				}
				else if ((!prvSemRPCourseList.isEmpty()) && (!prvSemRPCourseList.contains(e[1].toString())))
				{
					prvSemTotalCredit = prvSemTotalCredit + Integer.parseInt(e[2].toString());
				}
			}
		}
						
		return prvSemTotalCredit;
	}*/
}
