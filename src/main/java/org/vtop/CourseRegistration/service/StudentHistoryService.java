package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.HistoryCourseData;
import org.vtop.CourseRegistration.model.StudentCGPAData;
import org.vtop.CourseRegistration.model.StudentHistoryModel;
import org.vtop.CourseRegistration.repository.CourseEquivalanceRegRepository;
import org.vtop.CourseRegistration.repository.CourseRegistrationRepository;
import org.vtop.CourseRegistration.repository.StudentHistoryRepository;


@Service
@Transactional("transactionManager")
public class StudentHistoryService
{	
	@Autowired private StudentHistoryRepository studentHistoryRepository;
	//@Autowired private CourseRegistrationService courseRegistrationService;
	@Autowired private CourseRegistrationRepository courseRegistrationRepository;
	@Autowired private CourseEquivalanceRegRepository courseEquivalanceRegRepository;
	@Autowired private CGPACalcService cgpaCalcService;
	@Autowired private CGPANonCalService cgpaNonCalService;
	
	
	//Procedure
	//To insert the fresh Data in Student History from Examination Schema
	public String studentHistoryInsertProcess(String pRegisterNumber, String pCourseSystem)
	{
		//return studentHistoryRepository.acad_student_history_insert_process(pRegisterNumber, pCourseSystem);
		return studentHistoryRepository.acad_student_history_insert_process2(pRegisterNumber, pCourseSystem, "NONE");
	}
	
	
	//To get the CGPA & its related details from Examination Schema
	/*public String studentCGPA(String pRegisterNumber, Integer pSpecId, String pCourseSystem)
	{
		if (pCourseSystem.equals("CAL"))
		{
			return studentHistoryRepository.student_cgpa_cal(pRegisterNumber, "CGPA", "CGPA", 
						new Date(), new Date(), pSpecId);
		}
		else
		{
			return studentHistoryRepository.student_cgpa_ncal(pRegisterNumber, "CGPA", "CGPA", 
						new Date(), new Date(), pSpecId);
		}
	}*/	
	public String studentCGPA(String pRegisterNumber, Integer pSpecId, String pCourseSystem)
	{
		String returnValue = "";
		StudentCGPAData cgpaData = new StudentCGPAData();
		
		if ((pRegisterNumber != null) && (!pRegisterNumber.isEmpty()) 
				&& (pCourseSystem != null) && (!pCourseSystem.isEmpty()) 
				&& (pSpecId != null) && (pSpecId > 0))
		{
			if (pCourseSystem.equals("CAL") || pCourseSystem.equals("CBCS"))
			{
				cgpaData = calculateCalCGPA(pRegisterNumber, "CGPA", "CGPA", 
								new Date(), new Date(), pSpecId.shortValue());
			}
			else
			{
				cgpaData = calculateNonCalCGPA(pRegisterNumber, "CGPA", "CGPA", 
								new Date(), new Date(), pSpecId.shortValue());
			}
		}
		
		if ((cgpaData != null) && (cgpaData.getCGPA() != null))
		{
			returnValue = cgpaData.getCreditRegistered() +"|"+ cgpaData.getCreditEarned() +"|"+ cgpaData.getCGPA();
		}
				
		return returnValue;
	}
	
	
	public List<StudentHistoryModel> getStudentHistoryPARequisite(List<String> registerNumber, String[] courseId)
	{
		return studentHistoryRepository.findStudentHistoryPARequisite(registerNumber, courseId);
	}
	
	public List<StudentHistoryModel> getStudentHistoryPARequisite2(List<String> registerNumber, List<String> courseId)
	{
		return studentHistoryRepository.findStudentHistoryPARequisite2(registerNumber, courseId);
	}
	
	public List<Object[]> getStudentHistoryGrade2(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryGrade3(registerNumber, courseCode);
	}
	
	
	//For Course Substitution	
	public List<Object[]> getStudentHistoryCS2(List<String> registerNumber, String courseCode, String studySystem, 
								Integer specializationId, Integer studentYear, Float curriculumVersion, 
								String semesterSubId, String courseCategory, String courseOption, String basketId)
	{
		List<Object[]> courseSubList = new ArrayList<Object[]>();
		List<String> regCourseList = new ArrayList<String>();
		List<String> tempCourseList = new ArrayList<String>();
		
		//if (courseOption.equals("RGR"))
		if (courseOption.equals("RGR") || courseOption.equals("RGCE") 
				|| courseOption.equals("RGP") || courseOption.equals("RGW") 
				|| courseOption.equals("RPCE") || courseOption.equals("RWCE") 
				|| courseOption.equals("RGVC"))
		{
			regCourseList.add(courseCode);
			//tempCourseList = courseRegistrationService.getRegistrationAndWLCourseByRegisterNumber(
			//					semesterSubId, registerNumber);
			tempCourseList = courseRegistrationRepository.findRegistrationAndWLCourseByRegisterNumber(semesterSubId, registerNumber);
			if (!tempCourseList.isEmpty())
			{
				regCourseList.addAll(tempCourseList);
			}
			
			tempCourseList.clear();
			tempCourseList = courseEquivalanceRegRepository.findEquivCourseByRegisterNumber(semesterSubId, 
								registerNumber); 
			if (!tempCourseList.isEmpty())
			{
				regCourseList.addAll(tempCourseList);
			}
			
			tempCourseList.clear();
			tempCourseList = studentHistoryRepository.findCSCourseCodeByRegisterNo(semesterSubId, registerNumber); 
			if (!tempCourseList.isEmpty())
			{
				regCourseList.addAll(tempCourseList);
			}
			
			tempCourseList.clear();
			//tempCourseList = courseRegistrationService.getPrevSemCourseDetailByRegisterNumber(registerNumber); 
			tempCourseList = getPrevSemCourseDetailByRegisterNumber(registerNumber);
			if (!tempCourseList.isEmpty())
			{
				regCourseList.addAll(tempCourseList);
			}
			//System.out.println("regCourseList: "+ regCourseList);			
			
			if (studySystem.equals("CAL"))
			{
				if (courseCategory.equals("UC"))
				{
					courseSubList = studentHistoryRepository.findCSCourseByCourseCategoryAndBasketId(registerNumber, 
										regCourseList, specializationId, studentYear, curriculumVersion, courseCategory, 
										basketId);
				}
				else if (courseCategory.equals("PE"))
				{
					courseSubList = studentHistoryRepository.findStudentHistoryCS3(registerNumber, regCourseList, 
										specializationId, studentYear, curriculumVersion);
				}
				else if (courseCategory.equals("UE"))
				{
					courseSubList = studentHistoryRepository.findStudentHistoryCS4(registerNumber, regCourseList, 
										specializationId, studentYear, curriculumVersion);
				}
			}
			else
			{
				courseSubList = studentHistoryRepository.findStudentHistoryCS2(registerNumber, regCourseList);
			}
		}
		
		return courseSubList;
	}
		
	public Integer getStudentHistoryFailCourseCredits2(List<String> registerNumber)
	{
		Integer tempFailCredit = 0;
		
		tempFailCredit = studentHistoryRepository.findStudentHistoryFailCourseCredits2(registerNumber);
		if (tempFailCredit == null)
		{
			tempFailCredit = 0;
		}
		
		return tempFailCredit;
	}
		
	public List<Object[]> getStudentHistoryCEGrade3(List<String> registerNumber, String courseCode)
	{
		//return studentHistoryRepository.findStudentHistoryCEGrade3(registerNumber, courseCode);
		return studentHistoryRepository.findStudentHistoryCEGrade4(registerNumber, courseCode);
	}
	
	public List<Object[]> getStudentHistoryGIAndFailCourse(List<String> registerNumber)
	{
		return studentHistoryRepository.findStudentHistoryGIAndFailCourse(registerNumber);
	}
	
	public List<String> getStudentHistoryFailComponentCourseType(List<String> registerNumber, String courseId, 
							String examMonth)
	{
		return studentHistoryRepository.findStudentHistoryFailComponentCourseType(registerNumber, courseId, examMonth);
	}
	
	public List<Object[]> getArrearRegistrationByRegisterNumberAndCourseCode3(List<String> registerNumber, String courseCode)
	{		
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		int prvSemResultFlag = 2;
		String prvSemSubId = "";
		
		prvSemRegList = studentHistoryRepository.findArrearRegistrationByRegisterNumberAndCourseCode2(registerNumber, 
								courseCode);
		//prvSemRegList = studentHistoryRepository.findArrearRegistrationByRegisterNumberAndCourseCode3(registerNumber, 
		//						courseCode);
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
				prvSemResultFlag = 2;
				prvSemRPList.clear();
				
				prvSemRPList = getResultPublishedCourseDataForRARBySemRegNoAndCourseCode(prvSemSubId, registerNumber, courseCode);
				if (!prvSemRPList.isEmpty())
				{
					prvSemResultFlag = 1;
				}
				else
				{
					break;
				}
			}
		
			if (prvSemResultFlag == 1)
			{
				prvSemRegList.clear();
			}
		}
						
		return prvSemRegList;
	}
	
	public List<Object[]> getArrearCERegistrationByRegisterNumberAndCourseCode3(List<String> registerNumber, String courseCode)
	{
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		int prvSemResultFlag = 2;
		String prvSemSubId = "", prvSemCourseCode = "";
				
		prvSemRegList = studentHistoryRepository.findArrearCERegistrationByRegisterNumberAndCourseCode2(
							registerNumber, courseCode);
		//prvSemRegList = studentHistoryRepository.findArrearCERegistrationByRegisterNumberAndCourseCode3(
		//					registerNumber, courseCode);
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{				
				prvSemSubId = e[0].toString();
				prvSemCourseCode = e[4].toString();
				prvSemResultFlag = 2;
				prvSemRPList.clear();
				
				prvSemRPList = getResultPublishedCourseDataForRARBySemRegNoAndCourseCode(prvSemSubId, registerNumber, 
									prvSemCourseCode);
				if (!prvSemRPList.isEmpty())
				{
					prvSemResultFlag = 1;
				}
				else
				{
					break;
				}
			}
		
			if (prvSemResultFlag == 1)
			{
				prvSemRegList.clear();
			}
		}
				
		return prvSemRegList;
	}
		
	public List<Object[]> getCourseChangeHistoryByRegisterNumberAndCourseCode2(List<String> registerNumber, String courseCode)
	{
		//return studentHistoryRepository.findCourseChangeHistoryByRegisterNumberAndCourseCode2(registerNumber, courseCode);
		return studentHistoryRepository.findCourseChangeHistoryByRegisterNumberAndCourseCode3(registerNumber, courseCode);
	}
	
	
	//Research Program
	public Integer getRPApprovalStatusByRegisterNumber(String registerNumber)
	{
		Integer tempStatus = 2;
		
		tempStatus = studentHistoryRepository.findRPApprovalStatusByRegisterNumber(registerNumber);
		if (tempStatus == null)
		{
			tempStatus = 2;
		}
		
		return tempStatus;
	}
	
	public List<String> getRPCourseWorkByRegisterNumber(String registerNumber)
	{
		List<String> tempCourseList = new ArrayList<String>();
		 
		if (getRPApprovalStatusByRegisterNumber(registerNumber) == 1)
		{
			tempCourseList = studentHistoryRepository.findRPCourseWorkByRegisterNumber(registerNumber);
		}
		
		if (tempCourseList.isEmpty())
		{
			tempCourseList.add("NONE");
		}
		
		return tempCourseList;
	}
	
	public List<String> getCSCourseCodeByRegisterNoAndCourseId(String semesterSubId, List<String> registerNumber, 
								List<String> courseId)
	{
		return studentHistoryRepository.findCSCourseCodeByRegisterNoAndCourseId(semesterSubId, registerNumber, 
					courseId);
	}
	
	public List<Object[]> getByRegisterNumberCourseOptionAndGrade(List<String> registerNumber, List<String> courseOptionCode, 
								List<String> grade)
	{
		return studentHistoryRepository.findByRegisterNumberCourseOptionAndGrade(registerNumber, courseOptionCode, grade);
	}
	
	
	
	//***************************************
	//Examinations Result & Graduation Check
	//***************************************
	
	public List<Object[]> getResultPublishedCourseDataBySemAndRegNo(String semesterSubId, List<String> regNoList)
	{
		return studentHistoryRepository.findResultPublishedCourseDataBySemAndRegNo(semesterSubId, regNoList);
	}
	
	public List<Object[]> getResultPublishedCourseDataBySemRegNoAndCourseCode(String semesterSubId, List<String> regNoList, 
								String courseCode)
	{
		return studentHistoryRepository.findResultPublishedCourseDataBySemRegNoAndCourseCode(semesterSubId, regNoList, 
					courseCode);
	}
	
	public List<Object[]> getResultPublishedCourseDataForRARBySemRegNoAndCourseCode(String semesterSubId, List<String> regNoList, 
									String courseCode)
	{
		return studentHistoryRepository.findResultPublishedCourseDataForRARBySemRegNoAndCourseCode(semesterSubId, regNoList, 
					courseCode);
	}
	
	public List<Object[]> getStaticStudentCGPAFromTable(String registerNumber, Integer specializationId)
	{
		return studentHistoryRepository.findStaticStudentCGPAFromTable(registerNumber, specializationId);
	}
	
	public Integer getGraduationValue(List<String> registerNumber)
	{
		return studentHistoryRepository.findGraduationValue(registerNumber);
	}
	
	public List<Object[]> getStudentHistoryForCgpaCalc(String regNo, Short pgmSpecId)
	{
		return studentHistoryRepository.findStudentHistoryForCgpaCalc(regNo, pgmSpecId);
	}
	
	public List<Object[]> getStudentHistoryForGpaCalc(String regNo, Short pgmSpecId, Date examMonth)
	{
		return studentHistoryRepository.findStudentHistoryForGpaCalc(regNo, pgmSpecId, examMonth);
	}
	
	public List<Object[]> getStudentHistoryForCgpaCalc(String regNo, Short pgmSpecId, Date examMonth)
	{
		return studentHistoryRepository.findStudentHistoryForCgpaCalc(regNo, pgmSpecId, examMonth);
	}
	
	public List<Object[]> getStudentHistoryForCgpaNonCalCalc(String regNo, Short pgmSpecId)
	{
		return studentHistoryRepository.findStudentHistoryForCgpaNonCalCalc(regNo, pgmSpecId);
	}
	
	public List<Object[]> getStudentHistoryForGpaNonCalCalc(String regNo, Short pgmSpecId, Date examMonth)
	{
		return studentHistoryRepository.findStudentHistoryForGpaNonCalCalc(regNo, pgmSpecId, examMonth);
	}
	
	public List<Object[]> getStudentHistoryForCgpaNonCalCalc(String regNo, Short pgmSpecId, Date examMonth)
	{
		return studentHistoryRepository.findStudentHistoryForCgpaNonCalCalc(regNo, pgmSpecId, examMonth);
	}
		
	public float getGradePoint(String grade, Float credits)
	{
		float gradePoint = 0;
		
		switch (grade)
		{
			case "S":
				gradePoint= 10*credits;
				break;
			case "A":
				gradePoint= 9*credits;
				break;
			case "B":
				gradePoint= 8*credits;
				break;
			case "C":
				gradePoint= 7*credits;
				break;
			case "D":
				gradePoint= 6*credits;
				break;
			case "E":
				gradePoint= 5*credits;
				break;
			default:
				gradePoint= 0;
				break;
		}
		
		return gradePoint;
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
						for (String str : getCSCourseCodeByRegisterNoAndCourseId(prvSemSubId, 
												registerNumber, courseIdList))
						{
							returnCourseCodeList.add(str);
						}
					}
					
					prvSemSubId = e[0].toString();
					tempObjectList2.clear();
					courseCodeList.clear();
					courseIdList.clear();
					
					tempObjectList2 = getResultPublishedCourseDataBySemAndRegNo(prvSemSubId, registerNumber);
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
				for (String str : getCSCourseCodeByRegisterNoAndCourseId(prvSemSubId, 
										registerNumber, courseIdList))
				{
					returnCourseCodeList.add(str);
				}
			}
		}
										
		return returnCourseCodeList;
	}
	
	public StudentCGPAData calculateCalCGPA(String PRegNo, String PType, String PAdlPra, Date PFromExamMonth, 
								Date PToExamMonth, Short PProgSplnId)
	{
		List<HistoryCourseData> historyCourseList = new ArrayList<>();
	
		try
		{
			List<Object[]>  tempHistoryList = null;
			
			if ((PType.equals("CGPA") || PType.equals("HOSTEL_NCGPA")) &&  PRegNo !=null)
			{
				tempHistoryList = getStudentHistoryForCgpaCalc(PRegNo, PProgSplnId);
			}
			
			else if (PType.equals("GPA") &&  PRegNo!=null &&  PFromExamMonth!=null &&  PProgSplnId!=null)
			{
				tempHistoryList = getStudentHistoryForGpaCalc(PRegNo, PProgSplnId, PFromExamMonth);
			}
			else if( PType.equals("CGPA_UPTO_EXAMMONTH") && PRegNo!=null && PToExamMonth!=null && PProgSplnId != null)
			{
				tempHistoryList = getStudentHistoryForCgpaCalc(PRegNo, PProgSplnId,PToExamMonth);
			}
			
			if(tempHistoryList!=null)
			{
				for (Object[] row : tempHistoryList) {
					HistoryCourseData hData = new HistoryCourseData();
					hData.setRegno(row[0].toString());
					hData.setCourseCode(row[1].toString());
					hData.setCourseType(row[2].toString());
					hData.setCredits(Float.parseFloat(row[3].toString()));
					hData.setGrade(row[4].toString());
					hData.setCourseOption(row[5]!=null?row[5].toString():null);
					historyCourseList.add(hData);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return cgpaCalcService.doProcess(PRegNo, PType, PAdlPra, historyCourseList,PProgSplnId);
	}
	
	public StudentCGPAData calculateNonCalCGPA(String PRegNo, String PType, String PAdlPra, Date PFromExamMonth, 
								Date PToExamMonth, Short PProgSplnId)
	{
		List<HistoryCourseData> historyCourseList = new ArrayList<>();
			
		try
		{
			List<Object[]>  tempHistoryList = null;
			
			if ((PType.equals("CGPA") || PType.equals("HOSTEL_NCGPA")) &&  PRegNo!=null )
			{
				tempHistoryList = getStudentHistoryForCgpaNonCalCalc(PRegNo, PProgSplnId);
				//----Only Upto 2004 BTech IYear Credits not Included SPSRC:='select distinct a.regno,a.sem,a.subcode,a.credits,a.grade,a.papertype,a.exammonth,A.COURSEOPT from (SELECT a.cid,a.regno,a.sem,a.subcode, a.subjects,a.papertype,a.credits, a.grade, a.exammonth,A.COURSEOPT FROM (SELECT * FROM coehistoryadmin.finalresult x where not exists (select * from coehistoryadmin.coursechange where x.cid=cid and x.regno=regno and x.subcode=osubcode) and REGNO = ''' || substr(PREGNO,1,instr(PREGNO,'|')-1) || ''' AND CID=' || PProgSplnId ||' and  CREDITS IS NOT NULL AND COURSEOPT=''NIL'' AND GRADE<>''---'') a, (select * from coehistoryadmin.finalresult where REGNO = ''' || substr(PREGNO,1,instr(PREGNO,'|')-1) || ''' AND CID=' || PProgSplnId ||' and CREDITS IS NOT NULL AND COURSEOPT=''NIL'' AND GRADE<>''---'' AND UPPER(SEM)<>''I YEAR'' and SEM<>''I'' AND SEM<>''II'') b where  a.regno=b.regno and a.subcode=b.subcode GROUP BY a.cid,a.regno,a.sem,a.subcode, a.subjects, a.credits,a.papertype, a.grade, a.exammonth,A.COURSEOPT Having a.exammonth >= Max(b.exammonth))a ';
			}
			else if( PType.equals("GPA") && PRegNo!=null &&  PFromExamMonth != null  &&  PProgSplnId!=null)
			{
				tempHistoryList = getStudentHistoryForGpaNonCalCalc(PRegNo, PProgSplnId, PFromExamMonth);
			}
			else if (PType.equals("CGPA_UPTO_EXAMMONTH") && PRegNo != null &&  PToExamMonth !=null  &&   PProgSplnId!=null )//THEN --PAdlPra ProgId|ExamMonth RequiredExammonthWise CGPA --if_001    
			{
				tempHistoryList = getStudentHistoryForCgpaNonCalCalc(PRegNo, PProgSplnId,PToExamMonth);
			}
			
			if(tempHistoryList!=null)
			{
				for (Object[] row : tempHistoryList) {
					HistoryCourseData hData = new HistoryCourseData();
					hData.setRegno(row[0].toString());
					hData.setCourseCode(row[1].toString());
					hData.setCourseType(row[2].toString());
					hData.setCredits(Float.parseFloat(row[3].toString()));
					hData.setGrade(row[4].toString());
					hData.setCourseOption(row[5]!=null?row[5].toString():null);
					historyCourseList.add(hData);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return cgpaNonCalService.doProcess(PRegNo, PType, PAdlPra,PProgSplnId, historyCourseList);
	}
	
	
	/*public StudentHistoryModel getOne(StudentHistoryPKModel studentHistoryPKModel)
	{
		return studentHistoryRepository.findOne(studentHistoryPKModel);
	}
		
	public List<StudentHistoryModel> getAll()
	{
		return studentHistoryRepository.findAll();
	}
		
	public List<StudentHistoryModel> getByRegisterNumber(List<String> registerNumber)
	{
		return studentHistoryRepository.findByRegisterNumber(registerNumber);
	}
	
	public List<StudentHistoryModel> getByRegisterNumberCourseId(List<String> registerNumber, String courseId)
	{
		return studentHistoryRepository.findByRegisterNumberCourseId(registerNumber, courseId);
	}
		
	public StudentHistoryModel getStudentHistoryGrade(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryGrade(registerNumber, courseCode);
	}
	
	public String getStudentHistoryDistinctGrade(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryDistinctGrade(registerNumber, courseCode);
	}
	
	public List<StudentHistoryModel> getStudentHistoryCEGrade(List<String> registerNumber, String courseId)
	{
		return studentHistoryRepository.findStudentHistoryCEGrade(registerNumber, courseId);
	}
	
	public List<Object[]> getStudentHistoryCEGrade2(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryCEGrade2(registerNumber, courseCode);
	}*/
	
	
	/*public List<Object[]> getResultPublishedCourseDataForRARBySemAndRegNo(String semesterSubId, List<String> regNoList)
	{
		return studentHistoryRepository.getResultPublishedCourseDataForRARBySemAndRegNo(semesterSubId, regNoList);
	}*/
	
	
	/*public List<String> getStudentHistoryCourseType(List<String> registerNumber, String courseId)
	{
		return studentHistoryRepository.findStudentHistoryCourseType(registerNumber, courseId);
	}
	
	public String getStudentHistoryGenericCourseType(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryGenericCourseType(registerNumber, courseCode);
	}
	
	public List<StudentHistoryModel> getStudentHistoryCS(List<String> registerNumber, String courseCode)
	{
		return studentHistoryRepository.findStudentHistoryCS(registerNumber, courseCode);
	}*/
	
	/*public List<StudentHistoryModel> getStudentHistoryFailCourse(List<String> registerNumber)
	{
		return studentHistoryRepository.findStudentHistoryFailCourse(registerNumber);
	}
		
	public Integer getStudentHistoryFailCourseCredits(List<String> registerNumber)
	{
		Integer tempFailCredit = 0;
		
		tempFailCredit = studentHistoryRepository.findStudentHistoryFailCourseCredits(registerNumber);
		if (tempFailCredit == null)
		{
			tempFailCredit = 0;
		}
		
		return tempFailCredit;
	}*/
	
	/*public List<Object[]> getStudentHistoryFailCourse2(List<String> registerNumber)
	{
		return studentHistoryRepository.findStudentHistoryFailCourse2(registerNumber);
	}*/
	
	/*public List<Object[]> getStudentHistoryNotAllowedGrade(List<String> registerNumber, String courseId, String examMonth)
	{
		return studentHistoryRepository.findStudentHistoryNotAllowedGrade(registerNumber, courseId, examMonth);
	}
	
	//For Arrears Registration
	public List<Object[]> getArrearRegistrationByRegisterNumberAndCourseCode(String registerNumber, String courseCode)
	{
		return studentHistoryRepository.findArrearRegistrationByRegisterNumberAndCourseCode(registerNumber, courseCode);
	}
	
	public List<Object[]> getArrearRegistrationByRegisterNumberAndCourseCode2(String registerNumber, String courseCode)
	{		
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		String prvSemSubId = "";
		
		//prvSemRegList = studentHistoryRepository.findArrearRegistrationByRegisterNumberAndCourseCode(registerNumber, 
		//					courseCode);
		
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
				break;
			}
		
			prvSemRPList = getResultPublishedCourseDataForRARBySemAndRegNo(prvSemSubId, Arrays.asList(registerNumber));
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
		}
						
		return prvSemRegList;
	}*/
	
	/*public List<Object[]> getArrearCERegistrationByRegisterNumberAndCourseCode(String registerNumber, String courseCode)
	{
		return studentHistoryRepository.findArrearCERegistrationByRegisterNumberAndCourseCode(registerNumber, courseCode);
	}
	
	public List<Object[]> getArrearCERegistrationByRegisterNumberAndCourseCode2(String registerNumber, String courseCode)
	{
		List<Object[]> prvSemRegList = new ArrayList<Object[]>();
		List<Object[]> prvSemRPList = new ArrayList<Object[]>();
		String prvSemSubId = "", prvSemCourseCode = "";
				
		prvSemRegList = studentHistoryRepository.findArrearCERegistrationByRegisterNumberAndCourseCode(registerNumber, 
							courseCode);
		
		if (!prvSemRegList.isEmpty())
		{
			for (Object[] e: prvSemRegList)
			{
				prvSemSubId = e[0].toString();
				prvSemCourseCode = e[4].toString();
				break;
			}
		
			prvSemRPList = getResultPublishedCourseDataForRARBySemAndRegNo(prvSemSubId, Arrays.asList(registerNumber));
			
			if (!prvSemRPList.isEmpty())
			{
				for (Object[] e: prvSemRPList)
				{
					if (e[3].toString().equals(prvSemCourseCode))
					{
						prvSemRegList.clear();
						break;
					}
				}
			}
		}
				
		return prvSemRegList;
	}*/
	
	//For Course Change or Course Substitution history
	/*public List<Object[]> getCourseChangeHistoryByRegisterNumberAndCourseCode(String registerNumber, String courseCode)
	{
		return studentHistoryRepository.findCourseChangeHistoryByRegisterNumberAndCourseCode(registerNumber, courseCode);
	}*/
	
	/*public List<String> getCSCourseCodeByRegisterNo(String semesterSubId, List<String> registerNumber)
	{
		return studentHistoryRepository.findCSCourseCodeByRegisterNo(semesterSubId, registerNumber);
	}*/
}
