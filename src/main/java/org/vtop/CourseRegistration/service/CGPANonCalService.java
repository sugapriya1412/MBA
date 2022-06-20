package org.vtop.CourseRegistration.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.HistoryCourseData;
import org.vtop.CourseRegistration.model.StudentCGPAData;


@Service
@Transactional(readOnly=true)
public class CGPANonCalService
{
	@Autowired private SemesterMasterService semesterMasterService;	 
	
	public StudentCGPAData doProcess(String PRegNo, String PType, String PAdlPra, Short PProgSplnId, List<HistoryCourseData> historyCourseList)
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
			vGradePoint = semesterMasterService.getGradePoint(hCourse.getGrade(),hCourse.getCredits());
			
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
