package org.vtop.CourseRegistration.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.StudentHistoryModel;
import org.vtop.CourseRegistration.model.StudentHistoryPKModel;


@Repository
@Transactional
public interface StudentHistoryRepository extends JpaRepository<StudentHistoryModel, StudentHistoryPKModel>
{	
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
			"a.studentHistoryPKId.courseId in (select b.courseId from CourseCatalogModel b where "+
			"b.code in (?2)) and a.grade in ('A','B','C','D','E','S','U','R','F','Fail','P','Pass') "+
			"and a.courseTypeComponentModel.component in (1,3) order by a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findStudentHistoryPARequisite(List<String> registerNumber, String[] courseCode);
	
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
			"a.grade in ('A','B','C','D','E','S','U','R','F','Fail','P','Pass','N','N1','N2','N3','N4') and "+
			"a.courseTypeComponentModel.component in (1,3) and (a.courseCode in (?2) or (a.courseCode in "+
			"(select c.equivalentCourseCode from CourseEquivalancesModel c where c.courseCode in (?2))) or "+
			"(a.courseCode in (select d.courseCode from CourseEquivalancesModel d where d.equivalentCourseCode in (?2))))")
	List<StudentHistoryModel> findStudentHistoryPARequisite2(List<String> registerNumber, List<String> courseCode);
	
	@Query(value="select a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, b.TITLE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"to_char(a.EXAM_MONTH,'DD-MON-YYYY') as exam_date from academics.STUDENT_HISTORY a, "+
					"academics.CRS_CTG_MAX_VERSION_VIEW b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and a.COURSE_CODE not in (?2) and a.GRADE in ('Fail','F','N','N1','N2','N3','N4') and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ETH','ELA','EPJ','PJT','SS','OC','ECA') and "+
					"a.COURSE_CODE=b.CODE and a.COURSE_CODE not in "+
					"(select distinct COURSE_CODE from academics.STUDENT_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER "+
					"in (?1) and GRADE in ('S','A','B','C','D','E','U','P','Pass')) and a.COURSE_CODE not in "+
					"(select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES where COURSE_CODE in (?2)) "+
					"and a.COURSE_CODE not in (select COURSE_CODE from academics.COURSE_EQUIVALANCES where "+ 
					"EQUIVALENT_COURSE_CODE in (?2)) order by a.COURSE_CODE", nativeQuery=true)
	List<Object[]> findStudentHistoryCS2(List<String> registerNumber, List<String> courseCode);
	
	
	//Substitution Courses for PE Category
	@Query(value="select a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, b.TITLE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"to_char(a.EXAM_MONTH,'DD-MON-YYYY') as exam_date from academics.STUDENT_HISTORY a, "+
					"academics.CRS_CTG_MAX_VERSION_VIEW b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and a.COURSE_CODE not in (?2) and a.GRADE in ('Fail','F','N','N1','N2','N3','N4') and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ETH','ELA','EPJ','PJT','SS','OC','ECA') and "+
					"a.COURSE_CODE=b.CODE and a.COURSE_CODE not in "+
					"(select distinct COURSE_CODE from academics.STUDENT_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER "+
					"in (?1) and GRADE in ('S','A','B','C','D','E','U','P','Pass')) and a.COURSE_CODE not in "+
					"(select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES where COURSE_CODE in (?2)) "+
					"and a.COURSE_CODE not in (select COURSE_CODE from academics.COURSE_EQUIVALANCES where "+ 
					"EQUIVALENT_COURSE_CODE in (?2)) and a.COURSE_CODE not in "+
					"(select b.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b "+
					"where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 and a.ADMISSION_YEAR=?4 and a.CURRICULUM_VERSION=?5 "+
					"and a.CATALOG_TYPE='CC' and a.COURSE_CATEGORY not in ('PE') and a.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.COURSE_ID) and a.COURSE_CODE not in "+
					"(select c.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 and a.ADMISSION_YEAR=?4 "+
					"and a.CURRICULUM_VERSION=?5 and a.CATALOG_TYPE='BC' and a.COURSE_CATEGORY not in ('PE') and "+
					"a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID) order by a.COURSE_CODE", nativeQuery=true)
	List<Object[]> findStudentHistoryCS3(List<String> registerNumber, List<String> courseCode, Integer specializationId, 
						Integer studentYear, Float curriculumVersion);
	
	
	//Substitution Courses for UE Category
	@Query(value="select a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, b.TITLE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"to_char(a.EXAM_MONTH,'DD-MON-YYYY') as exam_date from academics.STUDENT_HISTORY a, "+
					"academics.CRS_CTG_MAX_VERSION_VIEW b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and a.COURSE_CODE not in (?2) and a.GRADE in ('Fail','F','N','N1','N2','N3','N4') and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ETH','ELA','EPJ','PJT','SS','OC','ECA') and "+
					"a.COURSE_CODE=b.CODE and a.COURSE_CODE not in "+
					"(select distinct COURSE_CODE from academics.STUDENT_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER "+
					"in (?1) and GRADE in ('S','A','B','C','D','E','U','P','Pass')) and a.COURSE_CODE not in "+
					"(select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES where COURSE_CODE in (?2)) "+
					"and a.COURSE_CODE not in (select COURSE_CODE from academics.COURSE_EQUIVALANCES where "+ 
					"EQUIVALENT_COURSE_CODE in (?2)) and a.COURSE_CODE not in "+
					"(select b.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b "+
					"where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 and a.ADMISSION_YEAR=?4 and a.CURRICULUM_VERSION=?5 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID) and "+
					"a.COURSE_CODE not in (select c.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.BASKET_COURSE_CATALOG b, ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 "+
					"and a.ADMISSION_YEAR=?4 and a.CURRICULUM_VERSION=?5 and a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 "+
					"and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID) "+
					"order by a.COURSE_CODE", nativeQuery=true)
	List<Object[]> findStudentHistoryCS4(List<String> registerNumber, List<String> courseCode, 
						Integer specializationId, Integer studentYear, Float curriculumVersion);
	
	
	@Query(value="select a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, b.TITLE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"to_char(a.EXAM_MONTH,'DD-MON-YYYY') as exam_date from academics.STUDENT_HISTORY a, "+
					"academics.CRS_CTG_MAX_VERSION_VIEW b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and a.COURSE_CODE not in (?2) and a.GRADE in ('Fail','F','N','N1','N2','N3','N4') and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ETH','ELA','EPJ','PJT','SS','OC','ECA') and "+
					"a.COURSE_CODE=b.CODE and a.COURSE_CODE not in (select distinct COURSE_CODE from "+
					"academics.STUDENT_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and GRADE in "+
					"('S','A','B','C','D','E','U','P','Pass')) and a.COURSE_CODE not in "+
					"(select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES where COURSE_CODE in (?2)) "+
					"and a.COURSE_CODE not in (select COURSE_CODE from academics.COURSE_EQUIVALANCES where "+
					"EQUIVALENT_COURSE_CODE in (?2)) and a.COURSE_CODE in "+
					"(select c.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?3 and a.ADMISSION_YEAR=?4 "+
					"and a.CURRICULUM_VERSION=?5 and a.CATALOG_TYPE='BC' and a.COURSE_CATEGORY=?6 and "+
					"a.COURSE_BASKET_ID=?7 and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID) order by a.COURSE_CODE", nativeQuery=true)
	List<Object[]> findCSCourseByCourseCategoryAndBasketId(List<String> registerNumber, List<String> courseCode, 
						Integer specializationId, Integer studentYear, Float curriculumVersion, String courseCategory, 
						String basketId);
		
	@Query("select sum(a.credit) as FCredits from StudentHistoryModel a where (a.studentHistoryPKId.registerNumber "+
			"in ?1) and a.grade in ('Fail','F','N','N1') and a.courseTypeComponentModel.component in (1,3)")
	Integer findStudentHistoryFailCourseCredits2(List<String> registerNumber);
	
	@Query(value="select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
					"as GEN_COURSE_TYPE, (case when (a.EXAM_MONTH is null) then to_char(CURRENT_DATE,'DD-MON-YYYY') "+
					"else to_char(a.EXAM_MONTH,'DD-MON-YYYY') end) as examMonth, "+
					"(case when (a.GRADE = 'S') then 1 when (a.GRADE = 'U') then 2 when (a.GRADE = 'P') then 3 "+
					"when (a.GRADE = 'Pass') then 4 when (a.GRADE = 'A') then 5 when (a.GRADE = 'B') then 6 "+
					"when (a.GRADE = 'C') then 7 when (a.GRADE = 'D') then 8 when (a.GRADE = 'E') then 9 "+
					"when (a.GRADE = 'R') then 10 when (a.GRADE = 'F') then 11 when (a.GRADE = 'Fail') then 12 "+
					"when ((a.GRADE = 'N') or (a.GRADE = 'N1') or (a.GRADE = 'N2') or (a.GRADE = 'N3') or "+
					"(a.GRADE = 'N4')) then 13 when (a.GRADE = 'W') then 14 when (a.GRADE = 'WWW') then 15 "+
					"when (a.GRADE = 'AAA') then 16 else 17 end) as grade_order from academics.STUDENT_HISTORY a, "+
					"academics.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and "+
					"a.COURSE_CODE=?2 and b.COMPONENT in (1,3) and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE "+
					"order by grade_order", nativeQuery=true)
	List<Object[]> findStudentHistoryGrade3(List<String> registerNumber, String courseCode);
	
	@Query(value="select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
					"as GEN_COURSE_TYPE, (case when (a.EXAM_MONTH is null) then to_char(CURRENT_DATE,'DD-MON-YYYY') "+ 
					"else to_char(a.EXAM_MONTH,'DD-MON-YYYY') end) as examMonth, "+ 
					"(case when (a.GRADE = 'S') then 1 when (a.GRADE = 'U') then 2 when (a.GRADE = 'P') then 3 "+ 
					"when (a.GRADE = 'Pass') then 4 when (a.GRADE = 'A') then 5 when (a.GRADE = 'B') then 6 "+ 
					"when (a.GRADE = 'C') then 7 when (a.GRADE = 'D') then 8 when (a.GRADE = 'E') then 9 "+ 
					"when (a.GRADE = 'R') then 10 when (a.GRADE = 'F') then 11 when (a.GRADE = 'Fail') then 12 "+ 
					"when ((a.GRADE = 'N') or (a.GRADE = 'N1') or (a.GRADE = 'N2') or (a.GRADE = 'N3') or "+ 
					"(a.GRADE = 'N4')) then 13 when (a.GRADE = 'W') then 14 when (a.GRADE = 'WWW') then 15 "+ 
					"when (a.GRADE = 'AAA') then 16 else 17 end) as grade_order from "+
					"academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b where "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+ 
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and (a.COURSE_CODE in "+
					"(select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES where COURSE_CODE=?2) "+
					"or a.COURSE_CODE in (select COURSE_CODE from academics.COURSE_EQUIVALANCES where "+
					"EQUIVALENT_COURSE_CODE=?2)) order by grade_order", nativeQuery=true)
	List<Object[]> findStudentHistoryCEGrade4(List<String> registerNumber, String courseCode);
		
	@Query(value="select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
					"as GEN_COURSE_TYPE from academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.GRADE in ('Fail','F','Y','N','N1','N2','N3','N4') "+
					"and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE order by a.COURSE_CODE", 
					nativeQuery=true)
	List<Object[]> findStudentHistoryGIAndFailCourse(List<String> registerNumber);
	
	@Query(value="select distinct a.CRSTYPCMPNTMASTER_COURSE_TYPE from academics.STUDENT_HISTORY a, "+
					"academics.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and a.COURSE_CATALOG_COURSE_ID=?2 and EXAM_MONTH=to_date(?3,'DD-MON-YYYY') and "+
					"a.GRADE in ('Y','N1','N2','N3','N4') and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE "+
					"and b.COMPONENT=2 order by a.CRSTYPCMPNTMASTER_COURSE_TYPE desc", nativeQuery=true)
	List<String> findStudentHistoryFailComponentCourseType(List<String> registerNumber, String courseId, 
						String examMonth);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, (case when (REGISTRATION_TYPE = 'FAR') "+
					"then 'FAT as Arrear' when (REGISTRATION_TYPE = 'FAR_RFAT') then 'FAT as Arrear' "+
					"when (REGISTRATION_TYPE = 'RAR') then 'Regular Arrear' when (REGISTRATION_TYPE = 'RAR_RFAT') "+
					"then 'Regular Arrear' end) as reg_type_desc, "+
					"(case when (REGISTRATION_TYPE = 'FAR') then 1 when (REGISTRATION_TYPE = 'FAR_RFAT') then 2 "+
					"when (REGISTRATION_TYPE = 'RAR') then 3 when (REGISTRATION_TYPE = 'RAR_RFAT') then 4 "+
					"else 5 end) as reg_type_no, START_DATE from ACADEMICS.ARREAR_COURSE_REG_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and code=?2 order by START_DATE desc, "+
					"SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearRegistrationByRegisterNumberAndCourseCode2(List<String> registerNumber, String courseCode);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, (case when (REGISTRATION_TYPE = 'FAR') "+
					"then 'FAT as Arrear' when (REGISTRATION_TYPE = 'FAR_RFAT') then 'FAT as Arrear' "+
					"when (REGISTRATION_TYPE = 'RAR') then 'Regular Arrear' when (REGISTRATION_TYPE = 'RAR_RFAT') "+
					"then 'Regular Arrear' end) as reg_type_desc, "+
					"(case when (REGISTRATION_TYPE = 'FAR') then 1 when (REGISTRATION_TYPE = 'FAR_RFAT') then 2 "+
					"when (REGISTRATION_TYPE = 'RAR') then 3 when (REGISTRATION_TYPE = 'RAR_RFAT') then 4 "+
					"else 5 end) as reg_type_no, CODE as course_code, START_DATE from ACADEMICS.ARREAR_COURSE_REG_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and (code in (select EQUIVALENT_COURSE_CODE from "+
					"ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2) or CODE in "+
					"(select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
					"order by START_DATE desc, SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearCERegistrationByRegisterNumberAndCourseCode2(List<String> registerNumber, String courseCode);
	
	@Query(value="select distinct COURSE_CODE from ACADEMICS.STUDENT_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER in (?2) "+
					"and COURSE_CATALOG_COURSE_ID in (select EQUIVALANCE_COURSE_ID from ACADEMICS.COURSE_EQUIVALANCE_REG "+
					"where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER in (?2)) "+
					"order by COURSE_CODE", nativeQuery=true)
	List<String> findCSCourseCodeByRegisterNo(String semesterSubId, List<String> registerNumber);
	
	@Query(value="select distinct COURSE_CODE from ACADEMICS.STUDENT_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER in (?2) "+
					"and COURSE_CATALOG_COURSE_ID in (select EQUIVALANCE_COURSE_ID from ACADEMICS.COURSE_EQUIVALANCE_REG "+
					"where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and "+
					"COURSE_CATALOG_COURSE_ID in (?3)) order by COURSE_CODE", nativeQuery=true)
	List<String> findCSCourseCodeByRegisterNoAndCourseId(String semesterSubId, List<String> registerNumber, 
					List<String> courseId);
	
	
	@Query(value="select PRE_CRSCATALOG_COURSE_ID, PRE_CRSCATALOG_COURSE_CODE, COURSE_CHANGE_OPTION from "+
					"EXAMINATIONS.STUDENT_COURSE_CHANGE_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and PRE_CRSCATALOG_COURSE_CODE=?2 and COURSE_CHANGE_OPTION not in ('CVCC','CVNN')", 
					nativeQuery=true)
	List<Object[]> findCourseChangeHistoryByRegisterNumberAndCourseCode3(List<String> registerNumber, 
						String courseCode);
	
		
	//Procedure
	//Old Method:  To insert the fresh Data in Student History from Examination Schema
	//@Procedure(name="call student_history_insert_process", procedureName="EXAMINATIONS.ACAD_STUDENT_HISTORY_PROCESS")
	//String acad_student_history_insert_process(String pRegisterNumber, String pCourseSystem);*/
	@Query(value="call EXAMINATIONS.ACAD_STUDENT_HISTORY_PROCESS (?1, ?2, ?3)", nativeQuery=true)
	String acad_student_history_insert_process(String pRegisterNumber, String pCourseSystem, String returnValue);
	
	//New Method:  To insert the fresh Data in Student History from Examination Schema with N grade concept
	//@Procedure(name="student_history_insert_process2", procedureName="EXAMINATIONS.ACAD_STUDENT_HISTORY_PROCESS_N")
	//String acad_student_history_insert_process2(String pRegisterNumber, String pCourseSystem);
	@Query(value="call EXAMINATIONS.ACAD_STUDENT_HISTORY_PROCESS_N (?1, ?2, ?3)", nativeQuery=true)
	String acad_student_history_insert_process2(String pRegisterNumber, String pCourseSystem, String returnValue);
		
		
	//To get the CAL Student CGPA & its related details from Examination Schema
	//@Procedure(name="student_cgpa", procedureName="examinations.cgpa_cal")
	//String student_cgpa_cal(String pRegisterNumber, String cgp1, String cgpa2, Date fromDate, Date toDate, 
	//			Integer specId);
	
	//To get the CAL Student CGPA & its related details from Examination Schema
	//@Procedure(name="student_cgpa", procedureName="examinations.cgpa_ncal")
	//String student_cgpa_ncal(String pRegisterNumber, String cgp1, String cgpa2, Date fromDate, Date toDate, 
	//			Integer specId);
	
	
	//Research Queries	
	@Query(value="select MEET_STATUS from RESEARCH.RESEARCH_MEETING where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and "+
					"RSRCHMEETINGTYPE_MEET_TYPE_ID=1 and MEET_STATUS=1", nativeQuery=true)
	Integer findRPApprovalStatusByRegisterNumber(String registerNumber);
	
	@Query(value="select distinct b.CODE from RESEARCH.COURSEWORK_OPTION_ALLOCATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER=?1 and a.COURSE_TYPE='RHR' and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID order by b.CODE", nativeQuery=true)
	List<String> findRPCourseWorkByRegisterNumber(String registerNumber);
	
	@Query(value="select a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE as generic_course_type, "+
					"b.DESCRIPTION as generic_course_type_desc, a.CREDIT, a.COURSE_OPTION_MASTER_CODE "+
					"from ACADEMICS.STUDENT_HISTORY a, ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b where "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE not in (?2) and "+
					"a.GRADE not in (?3) and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and b.COMPONENT in (1,3) and "+
					"(a.COURSE_CODE, a.EXAM_MONTH) in "+
					"(select a.COURSE_CODE, max(a.EXAM_MONTH) from ACADEMICS.STUDENT_HISTORY a, ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE not in (?2) and "+
					"a.GRADE not in (?3) and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and b.COMPONENT in (1,3) "+
					"group by a.COURSE_CODE) order by a.COURSE_CODE", nativeQuery=true) 
	List<Object[]> findByRegisterNumberCourseOptionAndGrade(List<String> registerNumber, List<String> courseOptionCode, List<String> grade);
	
	
	
	//***************************************
	//Examinations Result & Graduation Check
	//***************************************
		
	//Previous semester result published course list
	@Query(value="SELECT a.* FROM ("
			+ " SELECT  GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID,GNE.STDNTSLGNDTLS_REGISTER_NUMBER,CC.COURSE_ID, CC.CODE COURSE_CODE, "
			+ " CC.GENERIC_COURSE_TYPE,GNE.GRADE FROM EXAMINATIONS.GRADE_NON_EMBEDDED GNE "
			+ " INNER JOIN ACADEMICS.COURSE_CATALOG CC ON GNE.COURSE_CATALOG_COURSE_ID=CC.COURSE_ID "
			+ " INNER JOIN (SELECT * FROM ACADEMICS.COURSE_ALLOCATION CAT "
			+ " INNER JOIN HRMS.EMPLOYEE_PROFILE EP ON EP.EMPLOYEE_ID=CAT.ERP_ID "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC1 ON EP.COST_CENTRE_CENTRE_ID=VCC1.CENTRE_ID) CA "
			+ " ON GNE.COURSE_ALLOCATION_CLASS_ID=CA.CLASS_ID "
			+ " INNER JOIN (SELECT * FROM ADMISSIONS.STUDENTS_LOGIN_DETAILS SLD  "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC2 ON Sld.COST_CENTRE=VCC2.CENTRE_ID  "
			+ " INNER JOIN ADMISSIONS.STUDENT_BASE SB ON SLD.APPLICATION_NO=SB.APPLICATION_NUMBER) SD  "
			+ " ON GNE.STDNTSLGNDTLS_REGISTER_NUMBER=SD.REG_NO "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC ON SD.COST_CENTRE=VCC.CENTRE_ID  "
			+ " INNER JOIN (SELECT  PG.PROGRAMME_MODE,PG.PROGRAMME_NAME,PSS.DESCRIPTION, "
			+ " PSS.PROGRAMME_SPECIALIZATION_ID FROM VTOPMASTER.PROGRAMME_GROUP PG  "
			+ " INNER JOIN VTOPMASTER.PROGRAMME_SPECIALIZATION PSS ON "
			+ " PG.PROGRAMME_GROUP_ID=PSS.PRGRM_GROUP_PROGRAMME_GROUP_ID) PS  "
			+ " ON SD.PRGSPL_PRGRM_SPECIALIZATION_ID=PS.PROGRAMME_SPECIALIZATION_ID "
			+ " WHERE GNE.RESULT_DECLARED_DATE IS NOT NULL AND GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1  "
			+ " and gne.stdntslgndtls_register_number in (?2) "
			+ " UNION ALL "
			+ " SELECT GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID,GNE.STDNTSLGNDTLS_REGISTER_NUMBER,"
			+ " CC.COURSE_ID,CC.CODE COURSE_CODE,CC.GENERIC_COURSE_TYPE,GNE.GRADE "
			+ " FROM EXAMINATIONS.GRADE_EMBEDDED GNE "
			+ " INNER JOIN ACADEMICS.COURSE_CATALOG CC ON GNE.COURSE_CATALOG_COURSE_ID=CC.COURSE_ID "
			+ " INNER JOIN (SELECT * FROM ACADEMICS.COURSE_ALLOCATION CAT "
			+ " INNER JOIN HRMS.EMPLOYEE_PROFILE EP ON EP.EMPLOYEE_ID=CAT.ERP_ID "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC1 ON EP.COST_CENTRE_CENTRE_ID=VCC1.CENTRE_ID "
			+ " ) CA ON GNE.COURSE_ALLOCATION_CLASS_ID=CA.CLASS_ID "
			+ " INNER JOIN (SELECT * FROM ADMISSIONS.STUDENTS_LOGIN_DETAILS SLD  "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC2 ON Sld.COST_CENTRE=VCC2.CENTRE_ID  "
			+ " INNER JOIN ADMISSIONS.STUDENT_BASE SB ON SLD.APPLICATION_NO=SB.APPLICATION_NUMBER) SD  "
			+ " ON GNE.STDNTSLGNDTLS_REGISTER_NUMBER=SD.REG_NO "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC ON SD.COST_CENTRE=VCC.CENTRE_ID  "
			+ " INNER JOIN (SELECT  PG.PROGRAMME_MODE,PG.PROGRAMME_NAME,PSS.DESCRIPTION,"
			+ " PSS.PROGRAMME_SPECIALIZATION_ID FROM VTOPMASTER.PROGRAMME_GROUP PG  "
			+ " INNER JOIN VTOPMASTER.PROGRAMME_SPECIALIZATION PSS ON "
			+ " PG.PROGRAMME_GROUP_ID=PSS.PRGRM_GROUP_PROGRAMME_GROUP_ID) PS  "
			+ " ON SD.PRGSPL_PRGRM_SPECIALIZATION_ID=PS.PROGRAMME_SPECIALIZATION_ID "
			+ " WHERE GNE.RESULT_DECLARED_DATE IS NOT NULL AND "
			+ " GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and gne.stdntslgndtls_register_number in (?2)) a "
			+ " ORDER BY a.STDNTSLGNDTLS_REGISTER_NUMBER, a.COURSE_CODE ", nativeQuery=true)
	List<Object[]> findResultPublishedCourseDataBySemAndRegNo(String semesterSubId, List<String> regNoList);
	
	@Query(value="SELECT a.* FROM ("
			+ " SELECT GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID, GNE.STDNTSLGNDTLS_REGISTER_NUMBER, CC.COURSE_ID, "
			+ " CC.CODE COURSE_CODE, CC.GENERIC_COURSE_TYPE, GNE.GRADE FROM EXAMINATIONS.GRADE_NON_EMBEDDED GNE "
			+ " INNER JOIN ACADEMICS.COURSE_CATALOG CC ON GNE.COURSE_CATALOG_COURSE_ID=CC.COURSE_ID "
			+ " INNER JOIN (SELECT * FROM ACADEMICS.COURSE_ALLOCATION CAT "
			+ " INNER JOIN HRMS.EMPLOYEE_PROFILE EP ON EP.EMPLOYEE_ID=CAT.ERP_ID "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC1 ON EP.COST_CENTRE_CENTRE_ID=VCC1.CENTRE_ID) CA "
			+ " ON GNE.COURSE_ALLOCATION_CLASS_ID=CA.CLASS_ID "
			+ " INNER JOIN (SELECT * FROM ADMISSIONS.STUDENTS_LOGIN_DETAILS SLD  "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC2 ON Sld.COST_CENTRE=VCC2.CENTRE_ID  "
			+ " INNER JOIN ADMISSIONS.STUDENT_BASE SB ON SLD.APPLICATION_NO=SB.APPLICATION_NUMBER) SD  "
			+ " ON GNE.STDNTSLGNDTLS_REGISTER_NUMBER=SD.REG_NO "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC ON SD.COST_CENTRE=VCC.CENTRE_ID  "
			+ " INNER JOIN (SELECT  PG.PROGRAMME_MODE,PG.PROGRAMME_NAME,PSS.DESCRIPTION, "
			+ " PSS.PROGRAMME_SPECIALIZATION_ID FROM VTOPMASTER.PROGRAMME_GROUP PG  "
			+ " INNER JOIN VTOPMASTER.PROGRAMME_SPECIALIZATION PSS ON "
			+ " PG.PROGRAMME_GROUP_ID=PSS.PRGRM_GROUP_PROGRAMME_GROUP_ID) PS  "
			+ " ON SD.PRGSPL_PRGRM_SPECIALIZATION_ID=PS.PROGRAMME_SPECIALIZATION_ID "
			+ " WHERE GNE.RESULT_DECLARED_DATE IS NOT NULL AND GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1  "
			+ " and gne.stdntslgndtls_register_number in (?2) "
			+ " UNION ALL "
			+ " SELECT GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID, GNE.STDNTSLGNDTLS_REGISTER_NUMBER, "
			+ " CC.COURSE_ID, CC.CODE COURSE_CODE, CC.GENERIC_COURSE_TYPE, GNE.GRADE "
			+ " FROM EXAMINATIONS.GRADE_EMBEDDED GNE "
			+ " INNER JOIN ACADEMICS.COURSE_CATALOG CC ON GNE.COURSE_CATALOG_COURSE_ID=CC.COURSE_ID "
			+ " INNER JOIN (SELECT * FROM ACADEMICS.COURSE_ALLOCATION CAT "
			+ " INNER JOIN HRMS.EMPLOYEE_PROFILE EP ON EP.EMPLOYEE_ID=CAT.ERP_ID "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC1 ON EP.COST_CENTRE_CENTRE_ID=VCC1.CENTRE_ID "
			+ " ) CA ON GNE.COURSE_ALLOCATION_CLASS_ID=CA.CLASS_ID "
			+ " INNER JOIN (SELECT * FROM ADMISSIONS.STUDENTS_LOGIN_DETAILS SLD  "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC2 ON Sld.COST_CENTRE=VCC2.CENTRE_ID  "
			+ " INNER JOIN ADMISSIONS.STUDENT_BASE SB ON SLD.APPLICATION_NO=SB.APPLICATION_NUMBER) SD  "
			+ " ON GNE.STDNTSLGNDTLS_REGISTER_NUMBER=SD.REG_NO "
			+ " INNER JOIN VTOPMASTER.COST_CENTRE VCC ON SD.COST_CENTRE=VCC.CENTRE_ID  "
			+ " INNER JOIN (SELECT  PG.PROGRAMME_MODE,PG.PROGRAMME_NAME,PSS.DESCRIPTION,"
			+ " PSS.PROGRAMME_SPECIALIZATION_ID FROM VTOPMASTER.PROGRAMME_GROUP PG  "
			+ " INNER JOIN VTOPMASTER.PROGRAMME_SPECIALIZATION PSS ON "
			+ " PG.PROGRAMME_GROUP_ID=PSS.PRGRM_GROUP_PROGRAMME_GROUP_ID) PS  "
			+ " ON SD.PRGSPL_PRGRM_SPECIALIZATION_ID=PS.PROGRAMME_SPECIALIZATION_ID "
			+ " WHERE GNE.RESULT_DECLARED_DATE IS NOT NULL AND GNE.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "
			+ " gne.stdntslgndtls_register_number in (?2)) a where a.COURSE_CODE=?3 "
			+ " ORDER BY a.STDNTSLGNDTLS_REGISTER_NUMBER, a.COURSE_CODE", nativeQuery=true)
	List<Object[]> findResultPublishedCourseDataBySemRegNoAndCourseCode(String semesterSubId, List<String> regNoList, 
						String courseCode);
		
	@Query(value="select a.* from ("
			+ " select gne.semstr_details_semester_sub_id, gne.stdntslgndtls_register_number, cc.course_id,"
			+ " cc.code course_code, cc.generic_course_type, gne.grade from examinations.arrear_grade_non_embedded gne "
			+ " inner join academics.course_catalog cc on gne.course_catalog_course_id=cc.course_id"
			+ " inner join examinations.arrear_course_allocation_rar ca on gne.arr_course_allocation_class_id=ca.class_id"
			+ " inner join (select * from admissions.students_login_details sld "
			+ " inner join vtopmaster.cost_centre vcc2 on sld.cost_centre=vcc2.centre_id "
			+ " inner join admissions.student_base sb on sld.application_no=sb.application_number) sd "
			+ " on gne.stdntslgndtls_register_number=sd.reg_no  "
			+ " inner join vtopmaster.cost_centre vcc on sd.cost_centre=vcc.centre_id "
			+ " inner join (select  pg.programme_mode,pg.programme_name,pss.description, "
			+ " pss.programme_specialization_id from vtopmaster.programme_group pg "
			+ " inner join vtopmaster.programme_specialization pss on "
			+ " pg.programme_group_id=pss.prgrm_group_programme_group_id) ps "
			+ " on sd.prgspl_prgrm_specialization_id=ps.programme_specialization_id "
			+ " where gne.result_declared_date is not null and gne.semstr_details_semester_sub_id=?1"
			+ " and gne.stdntslgndtls_register_number in (?2) union all "
			+ " select gne.semstr_details_semester_sub_id,gne.stdntslgndtls_register_number,"
			+ " cc.course_id,cc.code course_code,cc.generic_course_type,gne.grade"
			+ " from examinations.arrear_grade_embedded gne"
			+ " inner join academics.course_catalog cc on gne.course_catalog_course_id=cc.course_id"
			+ " inner join examinations.arrear_course_allocation_rar ca on gne.arr_course_allocation_class_id=ca.class_id"
			+ " inner join (select * from admissions.students_login_details sld"
			+ " inner join vtopmaster.cost_centre vcc2 on sld.cost_centre=vcc2.centre_id "
			+ " inner join admissions.student_base sb on sld.application_no=sb.application_number) sd"
			+ " on gne.stdntslgndtls_register_number=sd.reg_no "
			+ " inner join vtopmaster.cost_centre vcc on sd.cost_centre=vcc.centre_id"
			+ " inner join (select  pg.programme_mode,pg.programme_name,pss.description,"
			+ " pss.programme_specialization_id from vtopmaster.programme_group pg"
			+ " inner join vtopmaster.programme_specialization pss on"
			+ " pg.programme_group_id=pss.prgrm_group_programme_group_id) ps"
			+ " on sd.prgspl_prgrm_specialization_id=ps.programme_specialization_id"
			+ " where gne.result_declared_date is not null and gne.semstr_details_semester_sub_id=?1"
			+ " and gne.stdntslgndtls_register_number in (?2)) a where a.course_code=?3"
			+ " order by a.stdntslgndtls_register_number, a.course_code", nativeQuery=true)
	List<Object[]> findResultPublishedCourseDataForRARBySemRegNoAndCourseCode(String semesterSubId, List<String> regNoList, 
						String courseCode);
	
	
	//Student CGPA
	@Query(value="select TOTAL_CREDITS_REGISTERED, TOTAL_CREDITS_EARNED, CUMULATIVE_GRADE_POINT_AVERAGE,"
		      + " NUMBER_OF_S_GRADE, NUMBER_OF_A_GRADE,NUMBER_OF_B_GRADE, NUMBER_OF_C_GRADE, NUMBER_OF_D_GRADE,"
		      + " NUMBER_OF_E_GRADE,  NUMBER_OF_F_GRADE, NUMBER_OF_N_GRADE from EXAMINATIONS.STUDENT_CGPA_HISTORY"
		      + " where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and PROGRAMME_SPECIALIZATION_ID=?2 and LOCK_STATUS=0"
		      + " and MODIFIED_TIMESTAMP=(select max(MODIFIED_TIMESTAMP) from EXAMINATIONS.STUDENT_CGPA_HISTORY"
		      + " where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and PROGRAMME_SPECIALIZATION_ID=?2 and LOCK_STATUS=0)", 
		      nativeQuery=true)
	List<Object[]> findStaticStudentCGPAFromTable(String registerNumber, Integer specializationId);
	
	//Student graduation eligibility
	@Query(value="select count(a.*) from ("+
					"select gs.stdntslgndtls_register_number from examinations.graduate_student gs where "+
					"gs.Stdntslgndtls_Register_Number in (?1) and NOT EXISTS "+
					"(SELECT * FROM examinations.graduate_student where Stdntslgndtls_Register_Number like '%MBI%' "+
					"and gs.stdntslgndtls_register_number=Stdntslgndtls_Register_Number AND gs.programme_specialization_id=27) "+
					"UNION ALL "+
					"select grs.stdntslgndtls_register_number from examinations.graduate_research_scholar grs where "+
					"grs.stdntslgndtls_register_number in (?1)) a", nativeQuery=true)
	Integer findGraduationValue(List<String> registerNumber);
	
	//CGPA Calculation
	@Query(value="select a_sgh.stdntslgndtls_register_number,a_sgh.course_catalog_code,a_sgh.course_type,a_sgh.credits, a_sgh.grade," + 
			"   a_sgh.course_option  from " + 
			"   (select * from examinations.student_grade_history x where " + 
			"   programme_specialization_id=?2  and stdntslgndtls_register_number=?1  and not credits is null and " + 
			"   active<>'N' and course_option='NIL' and course_type not in ('ETH','ELA','EPJ')   " + 
			"   and not exists ( select * from examinations.student_course_change_history where " + 
			"   stdntslgndtls_register_number=x.stdntslgndtls_register_number and pre_crscatalog_course_code=x.course_catalog_code" + 
			"   and not course_change_option in('CECC','CVCC','CENC') ))" + 
			"   a_sgh, (select * from examinations.student_grade_history x" + 
			"   where programme_specialization_id=?2 and stdntslgndtls_register_number=?1  and not credits is null and " + 
			"   active<>'N' and course_option='NIL' and grade<>'---' and course_type not in ('ETH','ELA','EPJ')) b_sgh" + 
			"   where a_sgh.stdntslgndtls_register_number=b_sgh.stdntslgndtls_register_number" + 
			"   and a_sgh.course_catalog_code=b_sgh.course_catalog_code" + 
			"   and a_sgh.course_type=b_sgh.course_type " + 
			"   group by a_sgh.stdntslgndtls_register_number, a_sgh.course_catalog_code, a_sgh.course_type, a_sgh.credits," + 
			"   a_sgh.grade,a_sgh.course_option" + 
			"   having examinations.GRADE_LEVEL(a_sgh.grade)>=max(examinations.GRADE_LEVEL(b_sgh.grade)) order by  a_sgh.course_catalog_code ",nativeQuery=true)
	List<Object[]> findStudentHistoryForCgpaCalc(String regNo, Short pgmSpecId);
		
	@Query(value="select a_sgh.stdntslgndtls_register_number,a_sgh.course_catalog_code,a_sgh.course_type, " + 
			"   a_sgh.credits, a_sgh.grade, a_sgh.course_option from  " + 
			"   (select * from examinations.student_grade_history x " + 
			"   where programme_specialization_id=?2 and  " + 
			"   stdntslgndtls_register_number=?1  and exam_month=?3 " + 
			"   and not credits is null and active<>'N'  and course_option='NIL' " + 
			"   and x.grade<>'---' and course_type not in ('ETH','ELA','EPJ')) " + 
			"   a_sgh order by  a_sgh.course_catalog_code",nativeQuery=true)
	List<Object[]> findStudentHistoryForGpaCalc(String regNo, Short pgmSpecId, Date examMonth);
	
	@Query(value="select a_sgh.stdntslgndtls_register_number,a_sgh.course_catalog_code,a_sgh.course_type,a_sgh.credits, a_sgh.grade, " + 
			"  a_sgh.course_option from  " + 
			"  (select * from examinations.student_grade_history x " + 
			"  where programme_specialization_id=?2 and stdntslgndtls_register_number=?1 and exam_month<=?3  and not credits is null and  " + 
			"  active<>'N' and course_option='NIL' and course_type not in ('ETH','ELA','EPJ')                           " + 
			"  and not exists ( select * from examinations.student_course_change_history where stdntslgndtls_register_number= " + 
			"  x.stdntslgndtls_register_number and pre_crscatalog_course_code=x.course_catalog_code and exam_month<=?3 " + 
			"  and not course_change_option in ('CECC','CVCC','CENC') )) " + 
			"  a_sgh, (select * from examinations.student_grade_history x " + 
			"  where programme_specialization_id=?2 and stdntslgndtls_register_number=?1  and exam_month<=?3 and not credits is null and  " + 
			"  active<>'N' and course_option='NIL' and grade<>'---' and course_type not in ('ETH','ELA','EPJ')) b_sgh " + 
			"  where a_sgh.stdntslgndtls_register_number=b_sgh.stdntslgndtls_register_number " + 
			"  and a_sgh.course_catalog_code=b_sgh.course_catalog_code " + 
			"  and a_sgh.course_type=b_sgh.course_type  " + 
			"  group by a_sgh.stdntslgndtls_register_number, a_sgh.course_catalog_code, a_sgh.course_type, a_sgh.credits, " + 
			"  a_sgh.grade,a_sgh.course_option " + 
			"  having  examinations.GRADE_LEVEL(a_sgh.grade)>=max(examinations.GRADE_LEVEL(b_sgh.grade)) order by  a_sgh.course_catalog_code",nativeQuery=true)
	List<Object[]> findStudentHistoryForCgpaCalc(String regNo, Short pgmSpecId, Date examMonth);
	
	@Query(value="select a.regno, a.subcode,a.papertype,a.credits,a.grade,a.courseopt  from " + 
			"   (select * from examinations.finalresult x  " + 
			"   where grade<> '---' and  grade<> 'W'  And Not Exists  (select * from examinations.student_course_change_history Where x.regno=stdntslgndtls_register_number " + 
			"   and x.subcode=pre_crscatalog_course_code and not course_change_option in ( 'CECN', 'CENN', 'CVCC', 'CVNN', 'CENC') ) " + 
			"   and papertype<> 'ETH' and  papertype<> 'ELA'  and papertype<> 'EPJ' and  " + 
			"   papertype<> 'ETheory' and  papertype<> 'ELab'  and papertype<> 'EProject') a, (select * from examinations.finalresult where regno= ?1 and  " + 
			"   grade<> '---' and grade<> 'W' and cid=?2 and credits is not null and courseopt= 'NIL') b " + 
			"   where a.regno=b.regno and a.subcode=b.subcode and a.cid=b.cid  " + 
			"   group by a.regno,a.subcode,a.credits,a.grade,a.papertype,a.courseopt  " + 
			"   having  examinations.GRADE_LEVEL(a.grade)>=max(examinations.GRADE_LEVEL(b.grade))  " + 
			"   order by a.regno,a.subcode", nativeQuery=true)
	List<Object[]> findStudentHistoryForCgpaNonCalCalc(String regNo, Short pgmSpecId);
	
	@Query(value="select regno, subcode,papertype,credits,grade,courseopt  from " + 
			"   examinations.finalresult where  regno= ?1 and cid=?2 and  exammonth= ?3 and   " + 
			"   grade<> '---' and grade<> 'W' and  papertype<> 'ETH' and  papertype<> 'ELA'  and papertype<> 'EPJ' and  " + 
			"   papertype<> 'ETheory' and  papertype<> 'ELab'  and papertype<> 'EProject' and credits is not null and courseopt= 'NIL' " + 
			"   order by regno,subcode",nativeQuery=true)
	List<Object[]> findStudentHistoryForGpaNonCalCalc(String regNo, Short pgmSpecId, Date examMonth);
		
	@Query(value="select a.regno, a.subcode,a.papertype,a.credits,a.grade,a.courseopt  from " + 
			"  (select * from examinations.finalresult x  where regno= ?1 and cid=?2  and exammonth<= ?3  " + 
			"  and   grade<> '---' and  grade<> 'W'  And Not Exists  (select * from examinations.student_course_change_history  Where x.regno=stdntslgndtls_register_number " + 
			"  and x.subcode=pre_crscatalog_course_code and not course_change_option in ( 'CECN', 'CENN', 'CVCC', 'CVNN', 'CENC')  and exam_month<= ?3) " + 
			"  and papertype<> 'ETH' and  papertype<> 'ELA'  and papertype<> 'EPJ' and  papertype<> 'ETheory' and  papertype<> 'ELab' " + 
			"  and papertype<> 'EProject') a, (select * from examinations.finalresult where regno= ?1 and  cid=?2 and  " + 
			"  exammonth<= ?3 and grade<> '---' and grade<> 'W' and  " + 
			"  credits is not null and courseopt= 'NIL') b where a.regno=b.regno and a.subcode=b.subcode and a.cid=b.cid  " + 
			"  group by a.regno,a.subcode,a.credits,a.grade,a.papertype,a.courseopt  " + 
			"  having  examinations.GRADE_LEVEL(a.grade)>=max(examinations.GRADE_LEVEL(b.grade))  order by a.regno,a.subcode", 
			nativeQuery=true)
	List<Object[]> findStudentHistoryForCgpaNonCalCalc(String regNo, Short pgmSpecId, Date examMonth);
		
	
	/*@Query("select a from StudentHistoryModel a order by a.studentHistoryPKId.registerNumber, "+
				"a.studentHistoryPKId.courseId, a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findAll();

	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) "+
		"order by a.studentHistoryPKId.courseId, a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findByRegisterNumber(List<String> registerNumber);
	
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
		"a.studentHistoryPKId.courseId=?2 order by a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findByRegisterNumberCourseId(List<String> registerNumber, String courseId);
	
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
		"a.courseCode=?2 and a.courseTypeComponentModel.component in (1,3) "+
		"order by a.studentHistoryPKId.courseType desc")
	StudentHistoryModel findStudentHistoryGrade(List<String> registerNumber, String courseCode);
	
	@Query("select distinct a.grade from StudentHistoryModel a where "+
		"a.studentHistoryPKId.registerNumber in (?1) and a.courseCode=?2 and "+
		"a.courseTypeComponentModel.component in (1,3)")
	String findStudentHistoryDistinctGrade(List<String> registerNumber, String courseCode);
	
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
		"a.courseTypeComponentModel.component in (1,3) and a.courseCode in "+
		"(select b.equivalentCourseCode from CourseEquivalancesModel b where "+
		"b.courseEquivalancesPkId.courseId=?2) order by a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findStudentHistoryCEGrade(List<String> registerNumber, String courseId);
	
	@Query(value="select a.grade, a.COURSE_CATALOG_COURSE_ID, decode(a.grade,'S',1,'U',2,'P',3,'Pass',"+
			"4,'A',5,'B',6,'C',7,'D',8,'E',9,'R',10,'F',11,'Fail',12,'N',13,'N1',13,'N2',13,'N3',13,'N4',13,"+
			"'W',14,'WWW',15,'AAA',16,17) as grade_order from academics.student_history a, "+
			"academics.COURSE_TYPE_COMPONENT_MASTER b where "+
			"a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+
			"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and (a.COURSE_CODE in "+
			"(select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES "+
			"where COURSE_CODE=?2) or a.COURSE_CODE in (select COURSE_CODE from "+ 
			"academics.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
			"order by grade_order", nativeQuery=true)
	List<Object[]> findStudentHistoryCEGrade2(List<String> registerNumber, String courseCode);*/
	
	/*@Query("select distinct a.studentHistoryPKId.courseType from StudentHistoryModel a where "+
			"a.studentHistoryPKId.registerNumber in (?1) and a.courseCode=?2 and "+
			"a.grade in ('Y','N1','N2','N3','N4') and a.courseTypeComponentModel.component=2 "+
			"order by a.studentHistoryPKId.courseType desc")
	List<String> findStudentHistoryCourseType(List<String> registerNumber, String courseCode);
	
	@Query("select distinct a.studentHistoryPKId.courseType from StudentHistoryModel a where "+
			"a.studentHistoryPKId.registerNumber in (?1) and a.courseCode=?2 "+
			"and a.courseTypeComponentModel.component not in (2)")
	String findStudentHistoryGenericCourseType(List<String> registerNumber, String courseCode);
	

	//For Course Substitution
	@Query("select a from StudentHistoryModel a where a.studentHistoryPKId.registerNumber in (?1) and "+
			"a.courseCode=?2 and a.grade in ('Fail','F','N','N1','N2','N3','N4') and "+
			"a.courseTypeComponentModel.component in (1,3) order by a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findStudentHistoryCS(List<String> registerNumber, String courseCode);*/
	
	/*@Query("select a from StudentHistoryModel a where (a.studentHistoryPKId.registerNumber in ?1) "+
			"and a.grade in ('Fail','F') and a.courseTypeComponentModel.component in (1,3) "+
			"order by a.studentHistoryPKId.courseType desc")
	List<StudentHistoryModel> findStudentHistoryFailCourse(List<String> registerNumber);
	
	@Query("select nvl(sum(a.credit),0) as FCredits from StudentHistoryModel a where "+
			"(a.studentHistoryPKId.registerNumber in ?1) and a.grade in ('Fail','F') "+
			"and a.courseTypeComponentModel.component in (1,3)")
	Integer findStudentHistoryFailCourseCredits(List<String> registerNumber);*/
	
	/*@Query(value="select GRADE, COURSE_CATALOG_COURSE_ID, COURSE_CODE, GEN_COURSE_TYPE, decode(GRADE,'S',1,'U',2,'P',3,"+
				"'Pass',4,'A',5,'B',6,'C',7,'D',8,'E',9,'R',10,'F',11,'Fail',12,'N',13,'N1',13,'N2',13,'N3',13,'N4',13,"+
				"'W',14,'WWW',15,'AAA',16,17) as grade_order, hist_type from ("+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 1 as hist_type from academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b "+
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+ 
				"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE) "+
				"union all "+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.CODE as COURSE_CODE, a.CRS_CTALOG_GENERIC_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 2 as hist_type from examinations.MIGRATION_STUDENT_HISTORY_ACAD a, academics.COURSE_TYPE_COMPONENT_MASTER b "+ 
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+
				"a.GRADE is not null and a.CRS_CTALOG_GENERIC_COURSE_TYPE=b.COURSE_TYPE)"+
				") where COURSE_CODE=?2 order by grade_order", nativeQuery=true)
	List<Object[]> findStudentHistoryGrade2(List<String> registerNumber, String courseCode);*/
	
	/*@Query(value="select GRADE, COURSE_CATALOG_COURSE_ID, COURSE_CODE, GEN_COURSE_TYPE, decode(GRADE,'S',1,'U',2,'P',3,"+
				"'Pass',4,'A',5,'B',6,'C',7,'D',8,'E',9,'R',10,'F',11,'Fail',12,'N',13,'N1',13,'N2',13,'N3',13,'N4',13,"+
				"'W',14,'WWW',15,'AAA',16,17) as grade_order, hist_type from ("+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 1 as hist_type from academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b "+
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+ 
				"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE) "+
				"union all "+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.CODE as COURSE_CODE, a.CRS_CTALOG_GENERIC_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 2 as hist_type from examinations.MIGRATION_STUDENT_HISTORY_ACAD a, academics.COURSE_TYPE_COMPONENT_MASTER b "+ 
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and b.COMPONENT in (1,3) and "+
				"a.GRADE is not null and a.CRS_CTALOG_GENERIC_COURSE_TYPE=b.COURSE_TYPE)"+
				") where (COURSE_CODE in (select EQUIVALENT_COURSE_CODE from academics.COURSE_EQUIVALANCES "+
				"where COURSE_CODE=?2) or COURSE_CODE in (select COURSE_CODE from academics.COURSE_EQUIVALANCES "+
				"where EQUIVALENT_COURSE_CODE=?2)) order by grade_order", nativeQuery=true)
	List<Object[]> findStudentHistoryCEGrade3(List<String> registerNumber, String courseCode);*/
	
	/*@Query(value="select GRADE, COURSE_CATALOG_COURSE_ID, COURSE_CODE, GEN_COURSE_TYPE, hist_type from ("+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 1 as hist_type from academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b "+
				"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE) "+
				"union all "+
				"(select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.CODE as COURSE_CODE, a.CRS_CTALOG_GENERIC_COURSE_TYPE "+
				"as GEN_COURSE_TYPE, 2 as hist_type from examinations.MIGRATION_STUDENT_HISTORY_ACAD a, "+
				"academics.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and "+
				"a.GRADE is not null and a.CRS_CTALOG_GENERIC_COURSE_TYPE=b.COURSE_TYPE)"+
				") where GRADE in ('Fail','F','Y','N1','N2','N3','N4') order by COURSE_CODE", nativeQuery=true)
	List<Object[]> findStudentHistoryFailCourse2(List<String> registerNumber);*/
	
	/*@Query(value="select a.GRADE, a.COURSE_CATALOG_COURSE_ID, a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE "+
			"as GEN_COURSE_TYPE from academics.STUDENT_HISTORY a, academics.COURSE_TYPE_COMPONENT_MASTER b "+
			"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_CATALOG_COURSE_ID=?2 and "+
			"trunc(EXAM_MONTH)=to_date(?3,'DD-MON-YYYY') and a.GRADE in ('N2') and "+
			"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE order by a.CRSTYPCMPNTMASTER_COURSE_TYPE desc", 
			nativeQuery=true)
	List<Object[]> findStudentHistoryNotAllowedGrade(List<String> registerNumber, String courseId, String examMonth);
	
	//For Arrears Registration
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, decode(REGISTRATION_TYPE,"+
					"'FAR','FAT as Arrear','FAR_RFAT','FAT as Arrear','RAR','Regular Arrear','RAR_RFAT',"+
					"'Regular Arrear') as reg_type_desc, decode(REGISTRATION_TYPE,'FAR',1,'FAR_RFAT',2,"+
					"'RAR',3,'RAR_RFAT',4,5) as reg_type_no from ACADEMICS.ARREAR_COURSE_REG_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER=?1 and code=?2 "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearRegistrationByRegisterNumberAndCourseCode(String registerNumber, String courseCode);*/
	
	/*@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, decode(REGISTRATION_TYPE,"+
					"'FAR','FAT as Arrear','FAR_RFAT','FAT as Arrear','RAR','Regular Arrear','RAR_RFAT',"+
					"'Regular Arrear') as reg_type_desc, decode(REGISTRATION_TYPE,'FAR',1,'FAR_RFAT',2,"+
					"'RAR',3,'RAR_RFAT',4,5) as reg_type_no from ACADEMICS.ARREAR_COURSE_REG_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and REGISTRATION_TYPE not in ('FAR_RFAT','RAR_RFAT') "+
					"and code=?2 order by SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearRegistrationByRegisterNumberAndCourseCode3(List<String> registerNumber, 
						String courseCode);

		
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, decode(REGISTRATION_TYPE,"+
					"'FAR','FAT as Arrear','FAR_RFAT','FAT as Arrear','RAR','Regular Arrear','RAR_RFAT',"+
					"'Regular Arrear') as reg_type_desc, decode(REGISTRATION_TYPE,'FAR',1,'FAR_RFAT',2,"+
					"'RAR',3,'RAR_RFAT',4,5) as reg_type_no, CODE as course_code from ACADEMICS.ARREAR_COURSE_REG_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and (CODE in (select EQUIVALENT_COURSE_CODE from "+
					"ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2) or CODE in "+
					"(select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearCERegistrationByRegisterNumberAndCourseCode(String registerNumber, String courseCode);*/
	
	/*@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, decode(REGISTRATION_TYPE,"+
					"'FAR','FAT as Arrear','FAR_RFAT','FAT as Arrear','RAR','Regular Arrear','RAR_RFAT',"+
					"'Regular Arrear') as reg_type_desc, decode(REGISTRATION_TYPE,'FAR',1,'FAR_RFAT',2,"+
					"'RAR',3,'RAR_RFAT',4,5) as reg_type_no, CODE as course_code from ACADEMICS.ARREAR_COURSE_REG_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and REGISTRATION_TYPE not in ('FAR_RFAT','RAR_RFAT') "+
					"and (code in (select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2) "+
					"or CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID, reg_type_no", nativeQuery=true)
	List<Object[]> findArrearCERegistrationByRegisterNumberAndCourseCode3(List<String> registerNumber, 
						String courseCode);*/
	
	/*//For Course Change or Course Substitution history
	@Query(value="select PRE_CRSCATALOG_COURSE_ID, PRE_CRSCATALOG_COURSE_CODE, COURSE_CHANGE_OPTION from "+
					"EXAMINATIONS.STUDENT_COURSE_CHANGE_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER=?1 and "+
					"PRE_CRSCATALOG_COURSE_CODE=?2", nativeQuery=true)
	List<Object[]> findCourseChangeHistoryByRegisterNumberAndCourseCode(String registerNumber, String courseCode);
	
	@Query(value="select PRE_CRSCATALOG_COURSE_ID, PRE_CRSCATALOG_COURSE_CODE, COURSE_CHANGE_OPTION from "+
					"EXAMINATIONS.STUDENT_COURSE_CHANGE_HISTORY where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"and PRE_CRSCATALOG_COURSE_CODE=?2", nativeQuery=true)
	List<Object[]> findCourseChangeHistoryByRegisterNumberAndCourseCode2(List<String> registerNumber, 
						String courseCode);*/
	
	/*//Previous semester arrear result published course list
	@Query(value="select * from("
			+ " select  gne.semstr_details_semester_sub_id,gne.stdntslgndtls_register_number,cc.course_id, cc.code course_code,"
			+ " cc.generic_course_type,gne.grade from examinations.arrear_grade_non_embedded gne "
			+ " inner join academics.course_catalog cc on gne.course_catalog_course_id=cc.course_id"
			+ " inner join examinations.arrear_course_allocation_rar ca on gne.arr_course_allocation_class_id=ca.class_id"
			+ " inner join (select * from admissions.students_login_details sld "
			+ " inner join vtopmaster.cost_centre vcc2 on sld.cost_centre=vcc2.centre_id "
			+ " inner join admissions.student_base sb on sld.application_no=sb.application_number) sd "
			+ " on gne.stdntslgndtls_register_number=sd.reg_no  "
			+ " inner join vtopmaster.cost_centre vcc on sd.cost_centre=vcc.centre_id "
			+ " inner join (select  pg.programme_mode,pg.programme_name,pss.description, "
			+ " pss.programme_specialization_id from vtopmaster.programme_group pg "
			+ " inner join vtopmaster.programme_specialization pss on "
			+ " pg.programme_group_id=pss.prgrm_group_programme_group_id) ps "
			+ " on sd.prgspl_prgrm_specialization_id=ps.programme_specialization_id "
			+ " where gne.result_declared_date is not null and gne.semstr_details_semester_sub_id=?1"
			+ " and gne.stdntslgndtls_register_number in (?2) union all "
			+ " select gne.semstr_details_semester_sub_id,gne.stdntslgndtls_register_number,"
			+ " cc.course_id,cc.code course_code,cc.generic_course_type,gne.grade"
			+ " from examinations.arrear_grade_embedded gne"
			+ " inner join academics.course_catalog cc on gne.course_catalog_course_id=cc.course_id"
			+ " inner join examinations.arrear_course_allocation_rar ca on gne.arr_course_allocation_class_id=ca.class_id"
			+ " inner join (select * from admissions.students_login_details sld"
			+ " inner join vtopmaster.cost_centre vcc2 on sld.cost_centre=vcc2.centre_id "
			+ " inner join admissions.student_base sb on sld.application_no=sb.application_number) sd"
			+ " on gne.stdntslgndtls_register_number=sd.reg_no "
			+ " inner join vtopmaster.cost_centre vcc on sd.cost_centre=vcc.centre_id"
			+ " inner join (select  pg.programme_mode,pg.programme_name,pss.description,"
			+ " pss.programme_specialization_id from vtopmaster.programme_group pg"
			+ " inner join vtopmaster.programme_specialization pss on"
			+ " pg.programme_group_id=pss.prgrm_group_programme_group_id) ps"
			+ " on sd.prgspl_prgrm_specialization_id=ps.programme_specialization_id"
			+ " where gne.result_declared_date is not null and"
			+ " gne.semstr_details_semester_sub_id=?1 and gne.stdntslgndtls_register_number in (?2))"
			+ " order by stdntslgndtls_register_number,course_code", nativeQuery=true)
	List<Object[]> findResultPublishedCourseDataForRARBySemAndRegNo(String semesterSubId, List<String> regNoList);*/
}
