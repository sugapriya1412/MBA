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
public class CGPACalcService
{
	//private static DecimalFormat df = new DecimalFormat("0.00" );
	
	@Autowired private StudentHistoryService studentHistoryService;
	
	public StudentCGPAData calculateCalCGPA(String PRegNo, String PType, String PAdlPra, Date PFromExamMonth, 
								Date PToExamMonth, Short PProgSplnId)
	{
		List<HistoryCourseData> historyCourseList = new ArrayList<>();
		try
		{
			List<Object[]>  tempHistoryList = null;
			if ((PType.equals("CGPA") || PType.equals("HOSTEL_NCGPA")) &&  PRegNo !=null)
			{
				tempHistoryList = studentHistoryService.getStudentHistoryForCgpaCalc(PRegNo, PProgSplnId);
			}

			else if (PType.equals("GPA") &&  PRegNo!=null &&  PFromExamMonth!=null &&  PProgSplnId!=null)
			{
				tempHistoryList = studentHistoryService.getStudentHistoryForGpaCalc(PRegNo, PProgSplnId, PFromExamMonth);
			}
			else if( PType.equals("CGPA_UPTO_EXAMMONTH") && PRegNo!=null && PToExamMonth!=null && PProgSplnId != null)
			{
				tempHistoryList = studentHistoryService.getStudentHistoryForCgpaCalc(PRegNo, PProgSplnId,PToExamMonth);
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
		return doProcess(PRegNo, PType, PAdlPra, historyCourseList,PProgSplnId);
	}


	public StudentCGPAData doProcess(String PRegNo,String PType,String PAdlPra,List<HistoryCourseData> historyCourseList,Short PProgSplnId)
	{
		Integer LoopCounter;
		Float tot_cre_reg;
		Float tot_cre_ear ;
		Float tot_arrear ;
		Float tot_gradepoint ;
		Float tot_course ;
		Float tot_passfailcredits ;
		Float vGradePoint ;
		Float CGPA ;
		Integer returnType;  // 0 : CGPA/GPA|tot_arrear|tot_cre_reg|tot_cre_ear|tot_course
		Integer scount ;
		Integer acount ;
		Integer bcount ;
		Integer ccount ;
		Integer dcount ;
		Integer ecount ;
		Integer fcount ;
		Integer ncount ;
		StudentCGPAData cgpaData = new StudentCGPAData();
		cgpaData.setRegisterNo(PRegNo);
		cgpaData.setPgmSpecId(PProgSplnId.intValue());
		returnType=0;

		if( PAdlPra.equals("1"))
			returnType=1;

		LoopCounter=0;
		tot_cre_reg=0.0f;
		tot_course=0.0f;
		tot_cre_reg=0.0f;    
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
		for (HistoryCourseData hCourse : historyCourseList) {

			if (!hCourse.getGrade().equals("W") && !hCourse.getGrade().equals("U"))
			{
				tot_cre_reg=tot_cre_reg+hCourse.getCredits();

				if (hCourse.getGrade().equals("P")  || hCourse.getCourseType().equals("ECA")) //then  --07-Feb-2019 onwards
				{
					tot_passfailcredits=tot_passfailcredits + (hCourse.getCredits());              

					if  (hCourse.getGrade().equals("P")) //then              --07-Feb-2019 onwards
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
			tot_course=tot_course+1;
			LoopCounter=LoopCounter+1;
			vGradePoint=studentHistoryService.getGradePoint(hCourse.getGrade(),hCourse.getCredits());
			if (!hCourse.getGrade().equals("W") && !hCourse.getGrade().equals("U") && !hCourse.getGrade().equals("P"))
			{
				if( vGradePoint==0.0f )
					tot_arrear= tot_arrear+1;  
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
			else if (hCourse.getGrade().equals("F"))
				fcount= fcount + 1;
			else if (hCourse.getGrade().equals("N"))
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
			cgpaData.setTotalCourse(tot_course+"");
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
			cgpaData.setTotalCourse(tot_course+"");
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
			cgpaData.setTotalCourse(tot_course+"");
		}

		return cgpaData;

	}

}
