package org.vtop.CourseRegistration.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseEligibleModel;
import org.vtop.CourseRegistration.model.CourseEquivalancesModel;
import org.vtop.CourseRegistration.model.CourseOptionModel;
import org.vtop.CourseRegistration.model.CourseTypeComponentModel;
import org.vtop.CourseRegistration.model.EmployeeProfile;
import org.vtop.CourseRegistration.model.SemesterDetailsModel;
import org.vtop.CourseRegistration.model.SemesterMasterModel;
import org.vtop.CourseRegistration.model.SlotTimeMasterModel;
import org.vtop.CourseRegistration.model.StudentsLoginDetailsModel;


@Repository
@Transactional
public interface SemesterMasterRepository extends JpaRepository<SemesterMasterModel, Integer>
{
	//Semester Detail
	@Query("select a from SemesterDetailsModel a where a.semesterSubId=?1")
	SemesterDetailsModel findSemesterDetailBySemesterSubId(String semesterSubId);
	
	@Query(value="select SEMESTER_MASTER_SEMESTER_ID, DESCRIPTION, DESCRIPTION_SHORT, TMTBLPATTERN_MASTER_PATTERN_ID, "+
					"FEEDBACK_PATTERN_NUMBER, SEMESTER_YEAR, GRADUATE_YEAR from ACADEMICS.SEMESTER_DETAILS where "+
					"SEMESTER_SUB_ID=?1", nativeQuery=true)
	List<Object[]> findSemesterDetailBySemesterSubId2(String semesterSubId);
		
	
	//Student Login Details
	@Query("select a from StudentsLoginDetailsModel a where a.regNo=?1")
	StudentsLoginDetailsModel findStudentDetailByRegisterNumber(String registerNumber);
	
	@Query(value="select a.REG_NO, a.APPLICATION_NO, b.STUDENT_NAME, b.GENDER, a.PRGSPLPRGRM_SPECIALIZATION_ID "+
					"as prog_spec_id, d.CODE as prog_spec_code, d.DESCRIPTION as prog_spec_desc, "+
					"d.PRGRM_GROUP_PROGRAMME_GROUP_ID as prog_group_id, e.CODE as prog_group_code, e.DESCRIPTION "+
					"as prog_group_desc, e.PROGRAMME_MODE as prog_group_mode, e.PROGRAMME_DURATION as "+
					"prog_group_duration, e.PROGRAMME_LEVEL as prog_group_level, a.COST_CENTRE as centre_id, "+
					"f.CODE as centre_code, f.DESCRIPTION as centre_desc, to_char(a.STUDY_START_DATE,'YYYY') "+
					"as stud_year, (case when (a.STUDY_SYSTEM is null) then 'FFCS' else a.STUDY_SYSTEM end) "+
					"as study_system, c.PASSWD, a.EDU_STATUS, a.LOCK_STATUS, b.COLLEGE_FEES_FEE_ID, g.EDU_EXPN, "+
					"c.EMAIL, c.MOBILE from ADMISSIONS.STUDENTS_LOGIN_DETAILS a, ADMISSIONS.STUDENT_BASE b, "+
					"VTOPMASTER.USER_DETAILS c, VTOPMASTER.PROGRAMME_SPECIALIZATION d, VTOPMASTER.PROGRAMME_GROUP e, "+
					"VTOPMASTER.COST_CENTRE f, VTOPMASTER.EDUCATION_STATUS g where a.REG_NO=?1 and "+
					"a.STUDY_START_DATE is not null and a.APPLICATION_NO=b.APPLICATION_NUMBER and a.REG_NO=c.USERID and "+
					"a.PRGSPLPRGRM_SPECIALIZATION_ID=d.PROGRAMME_SPECIALIZATION_ID and "+
					"a.COST_CENTRE=f.CENTRE_ID and d.PRGRM_GROUP_PROGRAMME_GROUP_ID=e.PROGRAMME_GROUP_ID and "+
					"a.EDU_STATUS=g.EDU_STATUS order by a.REG_NO", nativeQuery=true)
	List<Object[]> findStudentDetailByRegisterNumber2(String registerNumber);
	
	@Query(value="select a.REG_NO, a.APPLICATION_NO, b.STUDENT_NAME, b.GENDER, a.PRGSPLPRGRM_SPECIALIZATION_ID "+
					"as prog_spec_id, d.CODE as prog_spec_code, d.DESCRIPTION as prog_spec_desc, "+
					"d.PRGRM_GROUP_PROGRAMME_GROUP_ID as prog_group_id, e.CODE as prog_group_code, e.DESCRIPTION "+
					"as prog_group_desc, e.PROGRAMME_MODE as prog_group_mode, e.PROGRAMME_DURATION as "+
					"prog_group_duration, e.PROGRAMME_LEVEL as prog_group_level, a.COST_CENTRE as centre_id, "+
					"f.CODE as centre_code, f.DESCRIPTION as centre_desc, to_char(a.STUDY_START_DATE,'YYYY') as "+
					"stud_year, (case when (a.STUDY_SYSTEM is null) then 'FFCS' else a.STUDY_SYSTEM end) "+
					"as study_system, c.PASSWD, a.EDU_STATUS, a.LOCK_STATUS, b.COLLEGE_FEES_FEE_ID, g.EDU_EXPN, "+
					"c.EMAIL, c.MOBILE from VTOPMASTER.USER_DETAILS c, ADMISSIONS.STUDENTS_LOGIN_DETAILS a, "+
					"ADMISSIONS.STUDENT_BASE b, VTOPMASTER.PROGRAMME_SPECIALIZATION d, VTOPMASTER.PROGRAMME_GROUP e, "+
					"VTOPMASTER.COST_CENTRE f, VTOPMASTER.EDUCATION_STATUS g where c.NICK_NAME=?1 and "+
					"c.USERID=a.REG_NO and a.STUDY_START_DATE is not null and a.APPLICATION_NO=b.APPLICATION_NUMBER and "+
					"a.PRGSPLPRGRM_SPECIALIZATION_ID=d.PROGRAMME_SPECIALIZATION_ID and "+
					"a.COST_CENTRE=f.CENTRE_ID and d.PRGRM_GROUP_PROGRAMME_GROUP_ID=e.PROGRAMME_GROUP_ID and "+
					"a.EDU_STATUS=g.EDU_STATUS order by a.REG_NO", nativeQuery=true)
	List<Object[]> findStudentDetailByUserName(String userName);
	
	@Query(value="SELECT a.FEE_CATEGORY_ID, b.DESCRIPTION as Fee_Category, b.CATEGORY_STUDENT_CATEGORY_ID, "+
					"c.DESCRIPTION as Student_Category FROM FINANCE.COLLEGE_FEES a, FINANCE.FEES_CATEGORY b, "+
					"FINANCE.CATEGORY_STUDENT c where a.FEE_ID=?1 and a.PRGSPL_PRGRM_SPECIALIZATION_ID=?2 and "+
					"a.FEE_CATEGORY_ID=b.FEE_CATEGORY_ID AND b.CATEGORY_STUDENT_CATEGORY_ID=c.CATEGORY_ID", 
					nativeQuery=true)
	List<Object[]> findStudentFeeCategoryByFeeIdAndSpecId(Integer feeId, Integer specId);
	
	
	//Student Credit Transfer
	@Query(value="select OLD_REGISTER_NUMBER from ACADEMICS.STUDENT_CREDIT_TRANSFER where STDNTSLGNDTLS_REGISTER_NUMBER=?1", nativeQuery=true)
	String findStudentCreditTransferOldRegisterNumberByRegisterNumber(String registerNumber);


	//Course Eligible
	@Query("select a from CourseEligibleModel a where a.groupId=?1")
	CourseEligibleModel findCourseEligibleByProgramGroupId(Integer progGroupId);
	
	
	//Course Equivalence
	@Query("select a from CourseEquivalancesModel a where a.courseEquivalancesPkId.courseId=?1 "+
			"order by a.courseEquivalancesPkId.equivalentCourseId")
	List<CourseEquivalancesModel> findCourseEquivalancesByCourseId(String courseId);
	
	@Query(value="select a.equv_course_id, a.equv_course_code, b.course_id, b.code, b.GENERIC_COURSE_TYPE, "+
					"to_char(CURRENT_DATE,'DD-MON-YYYY') as hist_date, b.COURSE_SYSTEM from ("+
					"select distinct a.equv_course_id, a.equv_course_code from ("+
					"(select EQUIVALENT_COURSE_ID as equv_course_id, EQUIVALENT_COURSE_CODE as "+
					"equv_course_code from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?1 and "+
					"COURSE_CODE<>EQUIVALENT_COURSE_CODE) "+
					"union all "+
					"(select COURSE_CATALOG_COURSE_ID as equv_course_id, COURSE_CODE as equv_course_code "+
					"from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?1 and "+
					"COURSE_CODE<>EQUIVALENT_COURSE_CODE)) a) a "+
					"left outer join ACADEMICS.COURSE_CATALOG b on (b.CODE=a.equv_course_code) "+
					"order by a.equv_course_id, b.course_id", nativeQuery=true)
	List<Object[]> findCourseEquivalanceListByCourseCode(String courseCode);
	
	
	//Course Type Master
	@Query("select a from CourseTypeComponentModel a where a.courseType=?1")
	CourseTypeComponentModel findCourseTypeMasterByCourseType(String courseType);
	
	
	//Course Type Component
	@Query("select a.ctmpk.cCCourseType from CourseTypeMasterModel a where a.ctmpk.ccGenericType=?1 "+
			"order by (case when (a.ctmpk.cCCourseType = 'ETH') then 2 when (a.ctmpk.cCCourseType = 'ELA') "+
			"then 3 when (a.ctmpk.cCCourseType = 'EPJ') then 4 else 1 end)")
	List<String> findCourseTypeComponentByGenericType(String courseGenericType);
		
	
	//Bridge Course Condition Detail
	@Query(value="select (case when (result is null) then 'NONE' else result end) as ept_result from "+
					"ACADEMICS.STUDENT_EPT_DETAILS where STDNTSLGNDTLS_REGISTER_NUMBER in (?1)", nativeQuery=true)
	String findEPTResultByRegisterNumber(List<String> registerNumber);
	
	@Query(value="select (case when (HSC_GROUP is null) then 'NONE' else HSC_GROUP end) as hsc_grp from "+
					"ACADEMICS.STUDENT_BRIDGE_COURSE_DETAIL where REGNO in (?1)", nativeQuery=true)
	String findPCMBStatusByRegisterNumber(List<String> registerNumber);

	@Query(value="select (case when (b.ELIGIBILITY_CODE is null) then 'NONE' else b.ELIGIBILITY_CODE end) as egb_code "+
					"from ADMISSIONS.STUDENTS_LOGIN_DETAILS a, ADMISSIONS.STUDENT_BASE b where a.REG_NO=?1 and "+
					"a.APPLICATION_NO=b.APPLICATION_NUMBER", nativeQuery=true)
	String findPCMBStatusFromAdmissionsByRegisterNumber(String registerNumber);
	
	@Query(value="select MARK, STUDENT_SEMESTER from ACADEMICS.STUDENT_SPT_DETAILS where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and STUDENT_SEMESTER=?2", nativeQuery=true)
	List<Object[]> findSPTResultByRegisterNumberAndSemester(List<String> registerNumber, Integer studentSemester);
		
	@Query(value="select MARK, RESULT from ACADEMICS.STUDENT_EPT_DETAILS where STDNTSLGNDTLS_REGISTER_NUMBER in (?1)", 
					nativeQuery=true)
	List<Object[]> findEPTDetailByRegisterNumber(List<String> registerNumber);
	
	
	//CapStone Project Condition Detail
	@Query(value="select TOTAL_CREDITS, PROJECT_PERCENTAGE from ACADEMICS.CAP_STONE_PROJ_COND_DET "+
					"where PROGRAM_GROUP_ID=?1 and STUDENT_BATCH=?2 and STATUS=0", nativeQuery=true)
	List<Object[]> findProjectEligibilityByProgramGroupIdAndStudYear(Integer programGroupId, Integer studYear);
		
	@Query(value="select STUDENT_BATCH, TOTAL_CREDITS, PROJECT_PERCENTAGE from ACADEMICS.CAP_STONE_PROJ_COND_DET "+
					"where PROGRAM_GROUP_ID=?1 and STATUS=0 and STUDENT_BATCH=(select max(STUDENT_BATCH) from "+
					"ACADEMICS.CAP_STONE_PROJ_COND_DET where PROGRAM_GROUP_ID=?1 and STATUS=0)", nativeQuery=true)
	List<Object[]> findStudentMaxYearProjectEligibilityByProgramGroupId(Integer programGroupId);
	
	
	//Additional Learning Course Detail
	@Query(value="select distinct a.CODE, a.DESCRIPTION from ACADEMICS.ADDITIONAL_LEARNING_DETAILS a, "+
					"ACADEMICS.ADDITIONAL_LEARNING_CRS_CTLG b, ACADEMICS.COURSE_CATALOG c where "+
					"a.LEARNING_TYPE=?1 and a.PRGRM_GROUP_PROGRAMME_GROUP_ID=?2 and "+
					"(a.PROGRAM_SPECIALIZATION=?3 or a.PROGRAM_SPECIALIZATION like ?3||'/%' or "+
					"a.PROGRAM_SPECIALIZATION like '%/'||?3||'/%' or a.PROGRAM_SPECIALIZATION like '%/'||?3) "+
					"and a.LOCK_STATUS=0 and a.CODE=b.ADDTNL_LEARNING_DETAILS_CODE and b.LOCK_STATUS=0 and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and c.CODE=?4 order by a.CODE", nativeQuery=true)
	List<Object[]> findAdditionalLearningTitleByLearnTypeGroupIdSpecIdAndCourseCode(String learningType, 
						Integer groupId, String specId, String courseCode);
	
	@Query(value="select CODE, DESCRIPTION from ACADEMICS.ADDITIONAL_LEARNING_DETAILS where LEARNING_TYPE=?1 and "+
					"PRGRM_GROUP_PROGRAMME_GROUP_ID=?2 and (PROGRAM_SPECIALIZATION=?3 or PROGRAM_SPECIALIZATION "+
					"like ?3||'/%' or PROGRAM_SPECIALIZATION like '%/'||?3||'/%' or PROGRAM_SPECIALIZATION "+
					"like '%/'||?3) and LOCK_STATUS=0 order by CODE", nativeQuery=true)
	List<Object[]> findAdditionalLearningTitleByLearnTypeGroupIdAndSpecId(String learningType, Integer groupId, 
						String specializationId);
	
	
	//Slot Time Master Detail
	@Query("select a from SlotTimeMasterModel a where a.stmPkId.patternId in "+
			"(select distinct b.timeTablePatternId from SemesterClassGroupDetail b where b.id.semesterSubId in (?1)) "+ 
			"and a.numOfUnits=1 and a.stmPkId.slot not in ('-') order by a.stmPkId.patternId, a.stmPkId.slot")
	List<SlotTimeMasterModel> findSlotTimeMasterCommonTimeSlotBySemesterSubId(List<String> semesterSubId);
		
	@Query(value="select (case when (a.WEEK_DAY = 'MON') then 1 when (a.WEEK_DAY = 'TUE') then 2 "+
					"when (a.WEEK_DAY = 'WED') then 3 when (a.WEEK_DAY = 'THU') then 4 when (a.WEEK_DAY = 'FRI') "+
					"then 5 when (a.WEEK_DAY = 'SAT') then 6 when (a.WEEK_DAY = 'SUN') then 7 else 8 end) as "+
					"week_day_order, a.WEEK_DAY from (select distinct WEEK_DAY as WEEK_DAY from "+
					"ACADEMICS.SLOT_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1 and LOCK_STATUS=0) a "+
					"order by week_day_order", nativeQuery=true)
	List<Object[]> findSlotTimeMasterWeekDayList(Integer patternId);
		
	@Query(value="select a.SLOT_TYPE, a.WEEK_DAY, cast(a.TT_SESSION as varchar(10)) as tt_session, "+
					"(case when (b.MAX_SLOT is null) then 0 else b.MAX_SLOT end) as max_slot, a.SLOT_COUNT, "+
					"(case when (a.SLOT_TYPE = 'THEORY') then 1 when (a.SLOT_TYPE = 'LUNCH') then 2 "+
					"when (a.SLOT_TYPE = 'LAB') then 3 else 4 end) as slot_order, "+
					"(case when (a.WEEK_DAY = 'MON') then 1 when (a.WEEK_DAY = 'TUE') then 2 "+
					"when (a.WEEK_DAY = 'WED') then 3 when (a.WEEK_DAY = 'THU') then 4 when (a.WEEK_DAY = 'FRI') "+
					"then 5 when (a.WEEK_DAY = 'SAT') then 6 when (a.WEEK_DAY = 'SUN') then 7 else 8 end) "+
					"as week_day_order, (case when (a.TT_SESSION = 'FN') then 1 when (a.TT_SESSION = 'LN') then 2 "+
					"when (a.TT_SESSION = 'AN') then 3 when (a.TT_SESSION = 'EN') then 4 else 5 end) as "+
					"session_order from ("+
					"(select a.SLOT_TYPE, a.WEEK_DAY, a.TT_SESSION, count(a.SLOT) as slot_count from "+
					"(select (case when (SLOT_TYPE = 'EX_TH') then 'THEORY' else SLOT_TYPE end) as SLOT_TYPE, "+
					"WEEK_DAY, TT_SESSION, SLOT from ACADEMICS.SLOT_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1 "+
					"and LOCK_STATUS=0) a group by a.SLOT_TYPE, a.WEEK_DAY, a.TT_SESSION) "+
					"union all "+
					"(select distinct 'THEORY' as SLOT_TYPE, WEEK_DAY, 'LN' as TT_SESSION, 0 as slot_count from "+
					"ACADEMICS.SLOT_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1 and LOCK_STATUS=0) "+
					"union all "+
					"(select distinct 'LAB' as SLOT_TYPE, WEEK_DAY, 'LN' as TT_SESSION, 0 as slot_count "+
					"from ACADEMICS.SLOT_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1 and LOCK_STATUS=0)) a "+
					"left outer join (select TT_SESSION, greatest(NUM_TH_SLOTS, NUM_LAB_SLOTS) as MAX_SLOT from "+
					"academics.time_table_pattern_detail where PATTERN_ID=?1) b on (b.TT_SESSION=a.TT_SESSION) "+
					"order by week_day_order, slot_order, session_order", nativeQuery=true)
	List<Object[]> findSlotTimeMasterWeekDaySessionList(Integer patternId);
		 
	@Query(value="select a.SLOT_TYPE, a.WEEK_DAY, a.TT_SESSION, a.SLOT, a.WEEK_DAY_ORDER, a.SLOT_STARTING_TIME, "+
					"(case when (a.SLOT_TYPE = 'THEORY') then 1 when (a.SLOT_TYPE = 'LAB') then 2 else 3 end) "+
					"as slot_order from "+
					"(select (case when (SLOT_TYPE = 'EX_TH') then 'THEORY' else SLOT_TYPE end) as SLOT_TYPE, "+
					"WEEK_DAY, TT_SESSION, SLOT, (case when (WEEK_DAY = 'MON') then 1 when (WEEK_DAY = 'TUE') then 2 "+ 
					"when (WEEK_DAY = 'WED') then 3 when (WEEK_DAY = 'THU') then 4 when (WEEK_DAY = 'FRI') "+ 
					"then 5 when (WEEK_DAY = 'SAT') then 6 when (WEEK_DAY = 'SUN') then 7 else 8 end) as week_day_order, "+
					"SLOT_STARTING_TIME from ACADEMICS.SLOT_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1 and "+
					"LOCK_STATUS=0) a order by slot_order, a.WEEK_DAY_ORDER, a.SLOT_STARTING_TIME", nativeQuery=true)
	List<Object[]> findSlotTimeMasterByPatternId(Integer patternId);
	
	
	//Basket Course Catalog Detail
	@Query("select a.courseCatalogModel.code from BasketCourseCatalogModel a where a.bccPkId.basketId=?1 "+
			"and a.status=0 order by a.bccPkId.courseId")
	List<String> findBasketCourseCatalogCourseCodeByBasketId(String basketId);
	
	
	//Employee Profile
	@Query ("select distinct a.centreId, a.costCentre.code, a.costCentre.description from EmployeeProfile a "+
			"where a.campusCode=?1 and a.employmentType='FACULTY' and a.categoryId in (1) and "+
			"a.employmentStatus in (0) and a.leaveStatus in (0) and a.employeeId not in ('ACAD','COE01','PAT',"+
			"'ETHNUS','FACE','SMART','CHANCEL') and (a.employeeId not like 'T%') order by a.costCentre.code")
	List<Object[]> findEmployeeProfileByCampusCode(String campusCode);
	
	@Query ("select a from EmployeeProfile a where a.employmentType='FACULTY' and a.centreId=?1 and "+
			"a.campusCode='VLR' and a.categoryId in (1) and a.employmentStatus in (0) and a.leaveStatus in (0) "+
			"and a.employeeId not in ('ACAD','COE01','PAT','ETHNUS','FACE','SMART','CHANCEL') and "+
			"(a.employeeId not like 'T%') order by a.firstName, a.employeeId")
	List<EmployeeProfile> findEmployeeProfileByCentreId(Integer costCentreId);
	
	
	//Student Registration Method
	@Query(value="select min(YEAR) as min_year from ACADEMICS.CURRICULUM_PROGRAM where PROGRAM_SPECIALIZATION_ID=?1 "+
					"and COURSE_SYSTEM='CAL'", nativeQuery=true)
	Integer findStudentCurriculumProgramStartYearBySpecializationId(Integer specializationId);
	
	
	//Registration Schedule
	/*@Query(value="select to_char(reg_date,'DD-MON-YYYY') as regdate, FROM_TIME, TO_TIME, "+
					"to_char((to_date(FROM_TIME,'HH24:MI:SS') - (5/1440)), 'HH24:MI:SS') as ALLOW_FROM_TIME, "+
					"STATUS from ACADEMICS.REGISTRATION_SCHEDULE where REGNO=?1", nativeQuery=true)
	List<Object[]> findRegistrationScheduleByRegisterNumber(String registerNumber);*/
	@Query(value="select to_char(reg_date,'DD-MON-YYYY') as regdate, FROM_TIME, TO_TIME, "+
					"to_char((to_timestamp(FROM_TIME,'HH24:MI:SS') - interval '300 second'), 'HH24:MI:SS'), "+
					"STATUS from ACADEMICS.REGISTRATION_SCHEDULE where REGNO=?1", nativeQuery=true)
	List<Object[]> findRegistrationScheduleByRegisterNumber(String registerNumber);
	
	
	//Course Eligible Program Group Criteria
	@Query(value="select PROGRAM_ELIGIBLE, PROGRAM_CGPA from ACADEMICS.COURSE_ELIGIBLE where "+
					"PRGRM_GROUP_PROGRAMME_GROUP_ID=?1", nativeQuery=true)
	List<Object[]> findCourseEligibleProgramByProgGroupId(Integer progGroupId);
	
	
	//Pattern Time Master
	@Query("select distinct a.ptmPkId.timeTablePatternModel, a.ptmPkId.slotType, (case when (a.ptmPkId.slotType = 'THEORY') then 1 "+
			"else 2 end) as slotTypeOrder from PatternTimeMasterModel a where a.ptmPkId.timeTablePatternModel in (?1) "+
			"order by a.ptmPkId.timeTablePatternModel, slotTypeOrder")
	List<Object[]> findPatternTimeMasterSlotTypeByPatternId(List<Integer> patternId);
		
	@Query(value="select a.SLOT_TYPE, a.SLOT_NAME, a.SLOT_SESSION, a.SLOT_SL_NO, a.start_time, a.end_time, a.slot_order, "+
					"a.STARTING_TIME from (select SLOT_TYPE, SLOT_NAME, substr(SLOT_NAME,1,2) as SLOT_SESSION, "+
					"substr(SLOT_NAME,6,1) as SLOT_SL_NO, to_char(STARTING_TIME,'HH24:MI') as start_time, "+
					"to_char(ENDING_TIME,'HH24:MI') as end_time, (case when (SLOT_TYPE = 'THEORY') then 1 "+
					"when (SLOT_TYPE = 'LAB') then 2 else 3 end) as slot_order, STARTING_TIME from "+
					"ACADEMICS.TT_PATTERN_TIME_MASTER where TMTBLPATTERN_MASTER_PATTERN_ID=?1) a "+
					"order by a.slot_order, a.STARTING_TIME", nativeQuery= true)
	List<Object[]> findPatternTimeMasterSlotDetailByPatternId(Integer patternId);
	

	//Time Table Pattern Detail
	/*@Query(value="select decode(TT_SESSION,'FN',1,'LN',2,'AN',3,'EN',4,5) as tt_session_order, TT_SESSION, "+
					"NUM_TH_SLOTS, NUM_LAB_SLOTS, MAX_SLOT from "+
					"((select TT_SESSION, NUM_TH_SLOTS, NUM_LAB_SLOTS, greatest(NUM_TH_SLOTS, NUM_LAB_SLOTS) "+
					"as max_slot from academics.time_table_pattern_detail where PATTERN_ID=?1) "+
					"union all "+
					"(select 'LN' as TT_SESSION, 0 as NUM_TH_SLOTS, 0 as NUM_LAB_SLOTS, 0 as max_slot from dual)) "+
					"order by tt_session_order", nativeQuery= true)
	List<Object[]> findTTPatternDetailSessionSlotByPatternId(Integer patternId);*/
	@Query(value="select TT_SESSION, NUM_TH_SLOTS, NUM_LAB_SLOTS, greatest(NUM_TH_SLOTS, NUM_LAB_SLOTS) as max_slot "+
					"from academics.time_table_pattern_detail where PATTERN_ID=?1 order by TT_SESSION", nativeQuery=true)
	List<Object[]> findTTPatternDetailSessionSlotByPatternId(Integer patternId);
	
	
	@Query(value="select greatest(NUM_TH_SLOTS,NUM_LAB_SLOTS) as num, TT_SESSION from academics.time_table_pattern_detail "+
					"where PATTERN_ID=?1", nativeQuery= true)
	List<Object[]> findTTPatternDetailMaxSlots(Integer patternId);
	
	
	//Course Option
	@Query("select a from CourseOptionModel a where a.code in (?1) order by a.code")
	List<CourseOptionModel> findByOptionCode(List<String> code);
}
