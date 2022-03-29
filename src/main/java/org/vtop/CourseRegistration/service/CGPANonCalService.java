package org.vtop.CourseRegistration.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vtop.CourseRegistration.model.HistoryCourseData;
import org.vtop.CourseRegistration.model.StudentCGPAData;


@Service
public class CGPANonCalService
{
	@Autowired private StudentHistoryService studentHistoryService;
	 
	public StudentCGPAData calculateNonCalCGPA(String PRegNo, String PType, String PAdlPra, 
								Date PFromExamMonth, Date PToExamMonth, Short PProgSplnId)
	{
		List<HistoryCourseData> historyCourseList = new ArrayList<>();
		
		try
		{
			List<Object[]>  tempHistoryList = null;
			if ((PType.equals("CGPA") || PType.equals("HOSTEL_NCGPA")) &&  PRegNo!=null )
			{
				tempHistoryList = studentHistoryService.getStudentHistoryForCgpaNonCalCalc(PRegNo, PProgSplnId);
				//----Only Upto 2004 BTech IYear Credits not Included SPSRC:='select distinct a.regno,a.sem,a.subcode,a.credits,a.grade,a.papertype,a.exammonth,A.COURSEOPT from (SELECT a.cid,a.regno,a.sem,a.subcode, a.subjects,a.papertype,a.credits, a.grade, a.exammonth,A.COURSEOPT FROM (SELECT * FROM coehistoryadmin.finalresult x where not exists (select * from coehistoryadmin.coursechange where x.cid=cid and x.regno=regno and x.subcode=osubcode) and REGNO = ''' || substr(PREGNO,1,instr(PREGNO,'|')-1) || ''' AND CID=' || PProgSplnId ||' and  CREDITS IS NOT NULL AND COURSEOPT=''NIL'' AND GRADE<>''---'') a, (select * from coehistoryadmin.finalresult where REGNO = ''' || substr(PREGNO,1,instr(PREGNO,'|')-1) || ''' AND CID=' || PProgSplnId ||' and CREDITS IS NOT NULL AND COURSEOPT=''NIL'' AND GRADE<>''---'' AND UPPER(SEM)<>''I YEAR'' and SEM<>''I'' AND SEM<>''II'') b where  a.regno=b.regno and a.subcode=b.subcode GROUP BY a.cid,a.regno,a.sem,a.subcode, a.subjects, a.credits,a.papertype, a.grade, a.exammonth,A.COURSEOPT Having a.exammonth >= Max(b.exammonth))a ';
			}
			else if( PType.equals("GPA") && PRegNo!=null &&  PFromExamMonth != null  &&  PProgSplnId!=null)
			{
				tempHistoryList = studentHistoryService.getStudentHistoryForGpaNonCalCalc(PRegNo, PProgSplnId, PFromExamMonth);
			}
			else if (PType.equals("CGPA_UPTO_EXAMMONTH") && PRegNo != null &&  PToExamMonth !=null  &&   PProgSplnId!=null )//THEN --PAdlPra ProgId|ExamMonth RequiredExammonthWise CGPA --if_001    
			{
				tempHistoryList = studentHistoryService.getStudentHistoryForCgpaNonCalCalc(PRegNo, PProgSplnId,PToExamMonth);
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
			return doProcess(PRegNo, PType, PAdlPra,PProgSplnId, historyCourseList);
		}

	public StudentCGPAData doProcess(String PRegNo,String PType,String PAdlPra,Short PProgSplnId,List<HistoryCourseData> historyCourseList)
	{
		Integer LoopCounter;
		Float tot_cre_reg ;
		Float tot_sub ;
		Float vGradePoint ;
		Float tot_arrear ;
		Float tot_cre_ear ;
		Float tot_gradepoint ;
		Float tot_passfailcredits ;
		Float CGPA ;


		Integer returnType =0; 
		Integer scount =0;
		Integer acount =0;
		Integer bcount =0;
		Integer ccount =0;
		Integer dcount =0;
		Integer ecount =0;
		Integer fcount =0;
		Integer ncount =0;

		returnType=0;

		if (PAdlPra.equals("1"))
		{
			returnType=1;
		}


		

		LoopCounter=0;
		tot_cre_reg=0.0f;
		tot_sub=0.0f;
		tot_cre_reg=0.0f;
		tot_sub=0.0f;
		vGradePoint=0.0f;
		tot_arrear=0.0f;
		tot_cre_ear=0.0f;
		tot_gradepoint=0.0f;
		tot_passfailcredits=0.0f;
		scount=0;
		acount=0;
		bcount=0;
		ccount=0;
		dcount=0;
		ecount=0;
		fcount=0;
		ncount=0;
		StudentCGPAData cgpaData = new StudentCGPAData();
		cgpaData.setRegisterNo(PRegNo);
		cgpaData.setPgmSpecId(PProgSplnId.intValue());
		for (HistoryCourseData hCourse : historyCourseList) {

			if (!hCourse.getGrade().equals("W") && !hCourse.getGrade().equals("U")) 
			{
				tot_cre_reg=tot_cre_reg+(hCourse.getCredits());
				if (hCourse.getCourseCode().equals("MBA700")) 
				{
					if (((PProgSplnId==28 || PProgSplnId==65) && Integer.parseInt(PRegNo.substring(0,2))>=9) || (PProgSplnId==68 || PProgSplnId==57))
					{
						if (hCourse.getGrade().equals("P"))
						{
							tot_passfailcredits=tot_passfailcredits + (hCourse.getCredits());
							tot_cre_ear=tot_cre_ear+(hCourse.getCredits());
						}
					}
					
				}
				else if (hCourse.getCourseCode().substring(0,3).equals("EXC")) 
				{

					if ((getGradeLevel(hCourse.getGrade())<5) )//then --21-Feb-2019
					{
						tot_passfailcredits=tot_passfailcredits + (hCourse.getCredits());
					}

					if (hCourse.getGrade().equals("P"))
					{
						tot_cre_ear=tot_cre_ear+(hCourse.getCredits());
					}
				}
			}
			if (hCourse.getGrade().equals("W") && hCourse.getCourseOption().equals("NIL"))
			{
				tot_cre_reg=tot_cre_reg+(hCourse.getCredits());
				tot_arrear= tot_arrear+1;
			}
			tot_sub=tot_sub+1;
			LoopCounter=LoopCounter+1;
			vGradePoint=studentHistoryService.getGradePoint(hCourse.getGrade(),hCourse.getCredits());
			if (!hCourse.getGrade().equals("W") && !hCourse.getGrade().equals("U") && !hCourse.getGrade().equals("P"))
			{
				if (vGradePoint==0)
				{
					tot_arrear= tot_arrear+1;  
				}
				else
				{
					tot_cre_ear=tot_cre_ear+(hCourse.getCredits());
					tot_gradepoint=tot_gradepoint+vGradePoint;
				}
			}
			if (hCourse.getGrade().equals("S"))
				scount= scount + 1;
			else if (hCourse.getGrade().equals("A"))
				acount= acount + 1;
			else if (hCourse.getGrade().equals("B"))
				bcount= bcount + 1;
			else if (hCourse.getGrade().equals("C"))
				ccount= ccount + 1;
			else if (hCourse.getGrade().equals("D"))
				dcount= dcount + 1;
			else if (hCourse.getGrade().equals("E"))
				ecount= ecount + 1;
			else if (hCourse.getGrade().equals("F") ||hCourse.getGrade().toUpperCase().equals("FAIL") )
				fcount= fcount + 1;
			else if (hCourse.getGrade().equals("N") || hCourse.getGrade().equals("AAA"))
				ncount= ncount + 1;

		}

		if (tot_cre_reg > 0 && (tot_cre_reg-tot_passfailcredits)>0)// then --if_004
		{
			CGPA=tot_gradepoint/(tot_cre_reg-tot_passfailcredits);
		}
		else 
		{
			CGPA=0.0f;
		}

		if (returnType==0 && PType.equals("HOSTEL_NCGPA"))
		{
			cgpaData.setCreditRegistered(tot_cre_reg+"");
			cgpaData.setCreditEarned(tot_cre_ear+"");
			cgpaData.setCGPA(CGPA.toString());
			cgpaData.setTotalArrear(tot_arrear+"");
			cgpaData.setTotalCourse(tot_sub+"");
			cgpaData.setSGradeCount(scount+"");
			cgpaData.setaGradeCount(acount+"");
			cgpaData.setbGradeCount(bcount+"");
			cgpaData.setcGradeCount(ccount+"");
			cgpaData.setdGradeCount(dcount+"");
			cgpaData.seteGradeCount(ecount+"");
			cgpaData.setfGradeCount(fcount+"");
			cgpaData.setnGradeCount(ncount+"");

		}
		else if (returnType==0 )
		{

			cgpaData.setCreditRegistered(tot_cre_reg+"");
			cgpaData.setCreditEarned(tot_cre_ear+"");
			
			BigDecimal cgpaTemp =  new BigDecimal(CGPA.toString());
			cgpaTemp=cgpaTemp.setScale(2, BigDecimal.ROUND_HALF_UP);
			cgpaData.setCGPA((cgpaTemp).toString());
			
			cgpaData.setTotalArrear(tot_arrear+"");
			cgpaData.setTotalCourse(tot_sub+"");
			cgpaData.setSGradeCount(scount+"");
			cgpaData.setaGradeCount(acount+"");
			cgpaData.setbGradeCount(bcount+"");
			cgpaData.setcGradeCount(ccount+"");
			cgpaData.setdGradeCount(dcount+"");
			cgpaData.seteGradeCount(ecount+"");
			cgpaData.setfGradeCount(fcount+"");
			cgpaData.setnGradeCount(ncount+"");
		}
		else if (returnType==1)
		{
			cgpaData.setCreditRegistered(tot_cre_reg+"");
			cgpaData.setCreditEarned(tot_cre_ear+"");
			
			BigDecimal cgpaTemp =  new BigDecimal(CGPA.toString());
			cgpaTemp=cgpaTemp.setScale(2, BigDecimal.ROUND_HALF_UP);
			cgpaData.setCGPA((cgpaTemp).toString());
			
			cgpaData.setTotalArrear(tot_arrear+"");
			cgpaData.setTotalCourse(tot_sub+"");
		}

return cgpaData;
			
	}

	public static Integer getGradeLevel ( String grade)
	{
		Integer gradelevel = 0;
		switch (grade) {

		case "S" :  
			gradelevel =   (10);
			break;
		case "A" :
			gradelevel =   (9);
			break;
		case "B" :
			gradelevel =   (8);
			break;
		case "C" :
			gradelevel =   (7);
			break;
		case "D" :
			gradelevel =   (6);
			break;
		case "E" :
			gradelevel =   (5);
			break;
		case "PASS"  :
			gradelevel =   (4);
			break;
		case "P" :
			gradelevel =   (4);
			break;
		case "U" :
			gradelevel =   (4);
			break;
		case "Fail" :
			gradelevel =   (3);  
			break;
		case "F"  :
			gradelevel =   (3);  
			break;
		case "AAA" :
			gradelevel =   (2);
			break;
		case "N" :
			gradelevel =   (2);
			break;
		case "WWW":
			gradelevel =   (1);
			break;
		case "W" :
			gradelevel =   (1);
			break;
		default : 
			gradelevel =   (0);
			break;
		}
		return gradelevel;
	}
}
