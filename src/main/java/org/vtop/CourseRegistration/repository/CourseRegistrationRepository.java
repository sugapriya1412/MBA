package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseRegistrationModel;
import org.vtop.CourseRegistration.model.CourseRegistrationPKModel;


@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistrationModel, CourseRegistrationPKModel>
{	
	@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 and "+
			"a.courseRegistrationPKId.registerNumber=?2 and a.courseRegistrationPKId.courseId=?3 and "+
			"a.courseAllocationModel.clsGrpMasterGroupId in (?4) order by a.courseRegistrationPKId.courseType desc")
	List<CourseRegistrationModel> findByRegisterNumberCourseIdByClassGroupId(String semesterSubId, 
										String registerNumber, String courseId, String[] classGroupId);
		
	@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2 and a.courseCatalogModel.code=?3 "+
			"order by a.courseRegistrationPKId.courseType desc")
	List<CourseRegistrationModel> findByRegisterNumberCourseCode(String semesterSubId, String registerNumber, 
										String courseCode);
	
	@Query("select a from CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 "+
			"and a.courseRegistrationPKId.registerNumber=?2 and a.courseRegistrationPKId.courseId=?3 "+
			"and a.courseRegistrationPKId.courseType=?4")
	CourseRegistrationModel findByRegisterNumberCourseIdAndType(String semesterSubId, String registerNumber, 
								String courseId, String courseType);
		
	@Query("select count(distinct a.courseRegistrationPKId.courseId) as regcnt from "+
			"CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 and "+
			"a.courseRegistrationPKId.registerNumber=?2 and a.courseAllocationModel.clsGrpMasterGroupId in (?3)")
	Integer findRegisterNumberTCCountByClassGroupId(String semesterSubId, String registerNumber, 
				String[] classGroupId);
	
	@Query("select count(distinct a.courseRegistrationPKId.courseId) as courseCount from CourseRegistrationModel a"+
			" where a.courseRegistrationPKId.semesterSubId=?1 and a.courseRegistrationPKId.registerNumber=?2 and "+
			"a.courseRegistrationPKId.courseType='PJT' and a.courseOptionCode in ('RGR') and "+
			"a.courseCatalogModel.evaluationType in (?3)")
	Integer findProjectCourseCountByRegisterNumber(String semesterSubId, String registerNumber, List<String> evaluationType);
	
	
	@Query(value="select count(distinct a.COURSE_ID) as COURSE_COUNT from ("+ 
					"(select COURSE_CATALOG_COURSE_ID as COURSE_ID from ACADEMICS.COURSE_REGISTRATION "+ 
					"where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+ 
					"COURSE_OPTION_MASTER_CODE in (?3)) "+ 
					"union all "+ 
					"(select COURSE_CATALOG_COURSE_ID as COURSE_ID from ACADEMICS.COURSE_REGISTRATION_WAITING "+ 
					"where SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+ 
					"COURSE_OPTION_MASTER_CODE in (?3))) a", nativeQuery=true)
	Integer findCourseCountByRegisterNumberAndCourseOption(String semesterSubId, String registerNumber, 
				List<String> courseOption);
	
	@Query(value="select count(distinct a.COURSE_ID) as COURSE_COUNT from ("+ 
					"(select a.COURSE_CATALOG_COURSE_ID as COURSE_ID from ACADEMICS.COURSE_REGISTRATION a, "+ 
					"ACADEMICS.COURSE_ALLOCATION b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_OPTION_MASTER_CODE in (?3) and "+ 
					"a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID and "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID "+ 
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"b.CLSSGRP_MASTER_CLASS_GROUP_ID in (?4)) "+ 
					"union all " + 
					"(select a.COURSE_CATALOG_COURSE_ID as COURSE_ID from ACADEMICS.COURSE_REGISTRATION_WAITING a, "+ 
					"ACADEMICS.COURSE_ALLOCATION b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_OPTION_MASTER_CODE in (?3) and "+ 
					"a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID and "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+ 
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE and "+ 
					"b.CLSSGRP_MASTER_CLASS_GROUP_ID in (?4))) a", nativeQuery=true)
	Integer findCourseCountByRegisterNumberCourseOptionAndClassGroup(String semesterSubId, String registerNumber, 
				List<String> courseOption, List<String> classGroup);
			
	@Query(value="select sum(a.credit) as totalCredit from ("+ 
					"select (case when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') then b.LECTURE_CREDITS "+ 
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') then b.PRACTICAL_CREDITS "+ 
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then b.PROJECT_CREDITS else b.CREDITS end) "+ 
					"as credit from academics.COURSE_REGISTRATION a, academics.COURSE_CATALOG b where "+ 
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+ 
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ECA') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+ 
					"b.CODE not in (?3) and b.EVALUATION_TYPE not in ('STUDYTOUR')) a", nativeQuery=true)
	Float findRegCreditByRegisterNumberAndNCCourseCode(String semesterSubId, String registerNumber, 
					List<String> ncCourseCode);
		
	@Query("select a.courseAllocationModel.timeTableModel.patternId, a.courseAllocationModel.timeTableModel.slotName from "+
			"CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 and a.courseRegistrationPKId.registerNumber=?2 "+
			"and a.courseRegistrationPKId.courseType not in ('EPJ') and a.courseAllocationModel.slotId not in (0) "+
			"order by a.courseAllocationModel.timeTableModel.patternId, a.courseAllocationModel.timeTableModel.slotName")
	List<Object[]> findRegisteredSlots2(String semesterSubId, String registerNumber);
		
	@Query("select a.courseAllocationModel.timeTableModel.patternId, a.courseAllocationModel.timeTableModel.slotName from "+
			"CourseRegistrationModel a where a.courseRegistrationPKId.semesterSubId=?1 and a.courseRegistrationPKId.registerNumber=?2 "+
			"and a.classId not in (?3) and a.courseRegistrationPKId.courseType not in ('EPJ') and a.courseAllocationModel.slotId not in (0) "+ 
			"order by a.courseAllocationModel.timeTableModel.patternId, a.courseAllocationModel.timeTableModel.slotName")
	List<Object[]> findRegisteredSlotsforUpdate2(String semesterSubId, String registerNumber, String oldClassId);
	
	@Query(value="select a.PATTERN_ID, a.SLOT_NAME, a.TIME_TABLE_SLOT_ID, a.COURSE_ALLOCATION_CLASS_ID, a.BUILDING_MASTER_BUILDING_ID, "+ 
					"a.building_code from ("+ 
					"(select c.PATTERN_ID, c.SLOT_NAME, b.TIME_TABLE_SLOT_ID, a.COURSE_ALLOCATION_CLASS_ID, b.BUILDING_MASTER_BUILDING_ID, "+ 
					"d.CODE as building_code from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_ALLOCATION b, ACADEMICS.TIME_TABLE c, "+ 
					"estates.BUILDING_MASTER d where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+ 
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('EPJ') and a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID and "+ 
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+ 
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+ 
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE and b.TIME_TABLE_SLOT_ID not in (0) and "+ 
					"b.TIME_TABLE_SLOT_ID=c.SLOT_ID and b.BUILDING_MASTER_BUILDING_ID=d.BUILDING_ID) "+ 
					"union all "+ 
					"(select c.PATTERN_ID, c.SLOT_NAME, b.TIME_TABLE_SLOT_ID, a.COURSE_ALLOCATION_CLASS_ID, b.BUILDING_MASTER_BUILDING_ID, "+ 
					"d.CODE as building_code from ACADEMICS.COURSE_REGISTRATION_WAITING a, ACADEMICS.COURSE_ALLOCATION b, "+ 
					"ACADEMICS.TIME_TABLE c, estates.BUILDING_MASTER d where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('EPJ') and a.WAITING_STATUS=0 "+ 
					"and a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID and a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+ 
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE "+ 
					"and b.TIME_TABLE_SLOT_ID not in (0) and b.TIME_TABLE_SLOT_ID=c.SLOT_ID and b.BUILDING_MASTER_BUILDING_ID=d.BUILDING_ID)"+ 
					") a order by a.PATTERN_ID, a.SLOT_NAME", nativeQuery=true)
	List<Object[]> findRegistrationAndWaitingSlotDetail(String semesterSubId, String registerNumber);
		
	@Query("select distinct a.courseRegistrationPKId.courseId from CourseRegistrationModel a where "+
			"a.courseRegistrationPKId.semesterSubId=?1 and a.courseRegistrationPKId.registerNumber=?2 "+
			"and a.courseAllocationModel.clsGrpMasterGroupId in (?3)")
	List<String> findRegisteredCourseByClassGroup(String semesterSubId, String registerNumber, 
						String[] classGroupId);
	
	@Query(value="select distinct CODE from ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW where STDNTSLGNDTLS_REGISTER_NUMBER "+
				"in (?1) and (CODE in (?2) or CODE in (select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES "+
				"where COURSE_CODE in (?2)) or CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where "+
				"EQUIVALENT_COURSE_CODE in (?2)))", nativeQuery=true)
	List<String> findPrevSemCourseRegistrationPARequisiteByRegisterNumber(List<String> registerNumber, List<String> courseCode);
	
	
	//Procedure
	//For Insert
	@Query(value="call academics.registration_insert_prc (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, "+
					"?13, ?14, ?15, ?16, ?17)", nativeQuery=true)
	String registration_insert_prc(String psemsubid, String pclassid, String pregno, String pcourseid, 
				String pcomponent_type, String pcourse_option, Integer pregstatus, Integer pregcomponent_type, 
				String ploguserid, String plogipaddress, String pregtype, String pold_course_code, 
				String pcalltype, String pold_course_type, String pold_exam_month, String gradecategory, 
				String returnValue);
	
	
	//For Update
	@Query(value="call academics.registration_update_prc (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, "+
					"?13, ?14, ?15, ?16, ?17)", nativeQuery=true)
	String registration_update_prc(String psemsubid, String pregno, String pcourseid, String pcomponent_type,
				String pcourse_option, String poldclassid, String pnewclassid, String ploguserid, String plogipaddress,
				Integer pregstatus, Integer pregcomponent_type, String pregtype, String pold_course_code, 
				String pold_course_type, String pold_exam_month, String gradecategory, String returnValue);
	
	
	//For Delete
	@Query(value="call academics.registration_delete_prc (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery=true)
	String registration_delete_prc(String psemsubid, String pregno, String pcourseid, String pcalltype, 
				String ploguserid, String plogipaddress, String pregtype, String poldcoursecode, String returnValue);
	
	
	@Query(value="select a.SLOT, a.week_day, a.CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, a.ROOM_MASTER_ROOM_NUMBER, "+ 
					"a.TIME_TABLE_SLOT_ID, a.SLOT_ID, a.SLOT_NAME, a.SLOT_TYPE from ("+ 
					"(select e.SLOT, e.week_day, b.CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.ROOM_MASTER_ROOM_NUMBER, "+ 
					"c.TIME_TABLE_SLOT_ID, d.SLOT_ID, d.SLOT_NAME, d.SLOT_TYPE from academics.course_registration a, "+ 
					"academics.course_catalog b, academics.course_allocation c, academics.time_table d, "+ 
					"academics.slot_time_master e where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE in "+ 
					"('ETH','TH','ELA','LO','SS') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+ 
					"a.COURSE_ALLOCATION_CLASS_ID=c.CLASS_ID and "+ 
					"a.semstr_details_semester_sub_id=c.semstr_details_semester_sub_id and "+ 
					"a.course_catalog_course_id=c.course_catalog_course_id and "+ 
					"a.crstypcmpntmaster_course_type=c.crstypcmpntmaster_course_type and "+ 
					"b.COURSE_ID=c.COURSE_CATALOG_COURSE_ID and c.TIME_TABLE_SLOT_ID=d.SLOT_ID and "+ 
					"d.pattern_id=e.TMTBLPATTERN_MASTER_PATTERN_ID and e.SLOT IN (select regexp_split_to_table(d.SLOT_NAME, '\\+')))"+ 
					"union all "+ 
					"(select e.SLOT, e.week_day, b.CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.ROOM_MASTER_ROOM_NUMBER, "+ 
					"c.TIME_TABLE_SLOT_ID, d.SLOT_ID, d.SLOT_NAME, d.SLOT_TYPE from academics.COURSE_REGISTRATION_WAITING a, "+ 
					"academics.course_catalog b, academics.course_allocation c, academics.time_table d, "+ 
					"academics.slot_time_master e where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE in "+ 
					"('ETH','TH','ELA','LO','SS') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+ 
					"a.COURSE_ALLOCATION_CLASS_ID=c.CLASS_ID and "+ 
					"a.semstr_details_semester_sub_id=c.semstr_details_semester_sub_id and "+ 
					"a.course_catalog_course_id=c.course_catalog_course_id and "+ 
					"a.crstypcmpntmaster_course_type=c.crstypcmpntmaster_course_type and "+ 
					"b.COURSE_ID=c.COURSE_CATALOG_COURSE_ID and c.TIME_TABLE_SLOT_ID=d.SLOT_ID and "+ 
					"d.pattern_id=e.TMTBLPATTERN_MASTER_PATTERN_ID and e.SLOT IN (select regexp_split_to_table(d.SLOT_NAME, '\\+')))"+ 
					") a", nativeQuery=true)
	List<Object[]> findCourseRegWlSlotByStudent(String semesterSubId, String registerNumber);
	
	@Query(value="select a.SLOT_TYPE, a.WEEK_DAY, a.SLOT, a.CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, a.ROOM_MASTER_ROOM_NUMBER, "+ 
					"(case when (a.SLOT_TYPE = 'THEORY') then 1 when (a.SLOT_TYPE = 'LAB') then 2 else 3 end) as slot_order, "+ 
					"(case when (a.WEEK_DAY = 'MON') then 1 when (a.WEEK_DAY = 'TUE') then 2 when (a.WEEK_DAY = 'WED') then 3 "+ 
					"when (a.WEEK_DAY = 'THU') then 4 when (a.WEEK_DAY = 'FRI') then 5 when (a.WEEK_DAY = 'SAT') then 6 "+ 
					"when (a.WEEK_DAY = 'SUN') then 7 else 8 end) as week_day_order, a.CLSSGRP_MASTER_CLASS_GROUP_ID from ("+ 
					"select e.SLOT, e.week_day, b.CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.ROOM_MASTER_ROOM_NUMBER, "+ 
					"c.TIME_TABLE_SLOT_ID, d.SLOT_ID, d.SLOT_NAME, d.SLOT_TYPE, c.CLSSGRP_MASTER_CLASS_GROUP_ID from "+ 
					"academics.course_registration a, academics.course_catalog b, academics.course_allocation c, "+ 
					"academics.time_table d, academics.slot_time_master e where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.CRSTYPCMPNTMASTER_COURSE_TYPE in ('ETH','TH','ELA','LO','SS') "+ 
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and a.COURSE_ALLOCATION_CLASS_ID=c.CLASS_ID and "+ 
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=c.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+ 
					"a.COURSE_CATALOG_COURSE_ID=c.COURSE_CATALOG_COURSE_ID and "+ 
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=c.CRSTYPCMPNTMASTER_COURSE_TYPE and b.COURSE_ID=c.COURSE_CATALOG_COURSE_ID "+ 
					"and c.TIME_TABLE_SLOT_ID=d.SLOT_ID and d.PATTERN_ID=e.TMTBLPATTERN_MASTER_PATTERN_ID and "+ 
					"e.SLOT IN (select regexp_split_to_table(d.SLOT_NAME, '\\+'))) a "+ 
					"order by slot_order, week_day_order, SLOT", nativeQuery=true)
	List<Object[]> findCourseRegWlSlotByStudent2(String semesterSubId, String registerNumber);
		
	@Query(value="select a.hist_type, a.COURSE_CATALOG_COURSE_ID, a.CODE, a.GENERIC_COURSE_TYPE, a.hist_date from ("+
					"(select 1 as hist_type, a.COURSE_CATALOG_COURSE_ID, b.CODE, b.GENERIC_COURSE_TYPE, "+
					"to_char(a.LOG_TIMESTAMP,'DD-MON-YYYY') as hist_date from ACADEMICS.COURSE_REGISTRATION_CANCEL a, "+
					"ACADEMICS.COURSE_CATALOG b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and "+
					"(upper(a.REMARKS) like '%SUSPEND%' or upper(a.REMARKS) like '%DISCONTINUE%' or "+
					"upper(a.REMARKS) like '%BREAK%') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.code=?2) "+
					"union all "+
					"(select 2 as hist_type, a.COURSE_CATALOG_COURSE_ID, b.CODE, b.GENERIC_COURSE_TYPE, "+
					"to_char(a.LOG_TIMESTAMP,'DD-MON-YYYY') as hist_date from ACADEMICS.COURSE_REGISTRATION_CANCEL a, "+
					"ACADEMICS.COURSE_CATALOG b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and "+
					"(upper(a.REMARKS) like '%SUSPEND%' or upper(a.REMARKS) like '%DISCONTINUE%' or "+
					"upper(a.REMARKS) like '%BREAK%') and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and (b.CODE in "+
					"(select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2 and "+
					"COURSE_CODE<>EQUIVALENT_COURSE_CODE) or b.CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES "+
					"where EQUIVALENT_COURSE_CODE=?2 and COURSE_CODE<>EQUIVALENT_COURSE_CODE)))) a "+
					"order by a.hist_type, a.COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<Object[]> findCancelCourseByRegisterNumberAndCourseCode(List<String> registerNumber, String courseCode);

	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID from ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) order by SEMSTR_DETAILS_SEMESTER_SUB_ID", 
					nativeQuery=true)
	List<String> findPrevSemesterSubIdByRegisterNumber(List<String> registerNumber);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, STDNTSLGNDTLS_REGISTER_NUMBER from "+
					"ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and code=?2 "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID desc", nativeQuery=true)
	List<Object[]> findPrevSemCourseRegistrationByRegisterNumber2(List<String> registerNumber, String courseCode);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, CODE from "+
					"ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<Object[]> findPrevSemCourseDetailByRegisterNumber(List<String> registerNumber);
	
	@Query(value="select distinct SEMSTR_DETAILS_SEMESTER_SUB_ID, DESCRIPTION, CODE from ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and (code in (select EQUIVALENT_COURSE_CODE "+
					"from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?2) or code in (select COURSE_CODE from "+
					"ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?2)) "+
					"order by SEMSTR_DETAILS_SEMESTER_SUB_ID desc, CODE", nativeQuery=true)
	List<Object[]> findPrevSemCourseRegistrationCEByRegisterNumber2(List<String> registerNumber, String courseCode);
	
	
	//For registration purpose
	@Query(value="select distinct a.reg_type, a.course_code, a.course_title, a.GENERIC_COURSE_TYPE from ("+
					"(select 'REG' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.CODE=?3) "+
					"union all "+
					"(select 'WL' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION_WAITING a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.CODE=?3)) a "+
					"order by a.reg_type, a.course_code", nativeQuery=true)
	List<Object[]> findRegistrationAndWLByRegisterNumberAndCourseCode(String semesterSubId, String registerNumber, 
						String courseCode);
	
	@Query(value="select distinct a.reg_type, a.course_code, a.course_title, a.GENERIC_COURSE_TYPE from ("+
					"(select 'REG' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"(b.CODE in (select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?3) "+
					"or b.CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?3))) "+
					"union all "+
					"(select 'WL' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION_WAITING a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and (b.CODE in "+
					"(select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE=?3) or b.CODE in "+
					"(select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE=?3)))) a "+
					"order by reg_type, course_code", nativeQuery=true)
	List<Object[]> findCERegistrationAndWLByRegisterNumberAndCourseCode(String semesterSubId, String registerNumber, 
					String courseCode);
	
	@Query(value="select a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID, sum(a.credit) as "+
					"course_credit from ( "+
					"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, LECTURE_CREDITS as credit, "+
					"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and CRSTYPCMPNTMASTER_COURSE_TYPE='ETH' and "+
					"COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE')) "+
					"union all "+
					"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, PRACTICAL_CREDITS as credit, "+
					"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and CRSTYPCMPNTMASTER_COURSE_TYPE='ELA' and "+
					"COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE')) "+
					"union all "+
					"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, PROJECT_CREDITS as credit, "+
					"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and CRSTYPCMPNTMASTER_COURSE_TYPE='EPJ' and "+
					"COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE')) "+
					"union all "+
					"(select SEMSTR_DETAILS_SEMESTER_SUB_ID, COURSE_CATALOG_COURSE_ID, CREDITS as credit, "+
					"COURSE_OPTION_MASTER_CODE from academics.COURSE_REG_PREVIOUS_SEM_VIEW where "+
					"STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and CRSTYPCMPNTMASTER_COURSE_TYPE not in "+
					"('ETH','ELA','EPJ','ECA') and COURSE_OPTION_MASTER_CODE not in ('AUD','GI','GICE'))) a "+
					"group by a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID "+
					"order by a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<Object[]> findPSRegisteredCourseCreditsByRegisterNumber2(List<String> registerNumber);
	
	
	//For course substitution purpose
	@Query(value="select distinct a.course_code from ("+
					"(select 'REG' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) "+
					"union all "+
					"(select 'WL' as reg_type, b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.COURSE_REGISTRATION_WAITING a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID)) a "+
					"order by course_code", nativeQuery=true)
	List<String> findRegistrationAndWLCourseByRegisterNumber(String semesterSubId, List<String> registerNumber);
	
	@Query(value="SELECT f.CLSSGRP_MASTER_CLASS_GROUP_ID, j.DESCRIPTION as CLASS_GROUP_DESCRIPTION, "+
					"a.COURSE_CATALOG_COURSE_ID, b.CODE, b.TITLE, b.GENERIC_COURSE_TYPE, "+
					"a.COURSE_OPTION_MASTER_CODE, d.DESCRIPTION as COURSE_OPTION_DESCRIPTION, "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.DESCRIPTION as COURSE_TYPE_DESCRIPTION, "+
					"(case when ((a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') or (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'TH') or "+
					"(a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'SS')) then b.LECTURE_HOURS else 0 end) as LECTURE_HOUR, "+
					"(case when ((a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') or (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'TH')) "+
					"then b.TUTORIAL_HOURS else 0 end) as TUTORIAL_HOUR, "+
					"(case when ((a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') or (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'LO') or "+
					"(a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'SS')) then b.PRACTICAL_HOURS else 0 end) as PRACTICAL_HOUR, "+
					"(case when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then b.PRACTICAL_HOURS else 0 end) "+
					"as PROJECT_HOUR, (case when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') then b.LECTURE_CREDITS "+
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') then b.PRACTICAL_CREDITS "+
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then b.PROJECT_CREDITS else b.CREDITS end) "+
					"as CREDIT, b.EVALUATION_TYPE, a.COURSE_ALLOCATION_CLASS_ID, i.SLOT_NAME, f.ROOM_MASTER_ROOM_NUMBER, "+
					"f.ERP_ID, g.FIRST_NAME, h.CODE as SCHOOL_CODE, a.RGSTRTNSTSMSTR_STATUS_NUMBER, e.DESCRIPTION "+
					"as STATUS_DESCRIPTION, e.FONT_COLOUR, a.INVOICES_INVOICE_NUMBER, to_char(a.LOG_TIMESTAMP,'DD-Mon-YYYY') "+
					"as reg_date, (case when (f.CLSSGRP_MASTER_CLASS_GROUP_ID = 'ALL') then 1 "+
					"when (f.CLSSGRP_MASTER_CLASS_GROUP_ID = 'WEI') then 2 else 3 end) as CLASS_GROUP_ORDER, "+
					"(case when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') then 2 when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') "+
					"then 3 when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then 4 else 1 end) as COURSE_TYPE_ORDER, "+
					"i.PATTERN_ID, f.TIME_TABLE_SLOT_ID FROM ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER c, ACADEMICS.COURSE_OPTION_MASTER d, "+
					"ACADEMICS.REGISTRATION_STATUS_MASTER e, ACADEMICS.COURSE_ALLOCATION f, "+
					"HRMS.EMPLOYEE_PROFILE g, VTOPMASTER.COST_CENTRE h, ACADEMICS.TIME_TABLE i, "+
					"ACADEMICS.CLASS_GROUP_MASTER j where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and b.GENERIC_COURSE_TYPE not in ('ECA') and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and a.CRSTYPCMPNTMASTER_COURSE_TYPE=c.COURSE_TYPE and "+
					"a.COURSE_OPTION_MASTER_CODE=d.CODE and a.RGSTRTNSTSMSTR_STATUS_NUMBER=e.STATUS_NUMBER and "+
					"a.COURSE_ALLOCATION_CLASS_ID=f.CLASS_ID and a.SEMSTR_DETAILS_SEMESTER_SUB_ID=f.SEMSTR_DETAILS_SEMESTER_SUB_ID "+
					"and a.COURSE_CATALOG_COURSE_ID=f.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=f.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"b.COURSE_ID=f.COURSE_CATALOG_COURSE_ID and f.ERP_ID=g.EMPLOYEE_ID and "+
					"f.TIME_TABLE_SLOT_ID=i.SLOT_ID and f.CLSSGRP_MASTER_CLASS_GROUP_ID=j.CLASS_GROUP_ID and "+
					"g.COST_CENTRE_CENTRE_ID=h.CENTRE_ID order by CLASS_GROUP_ORDER, f.CLSSGRP_MASTER_CLASS_GROUP_ID, "+
					"a.COURSE_CATALOG_COURSE_ID, COURSE_TYPE_ORDER", nativeQuery=true)
	List<Object[]> findByRegisterNumber3(String semesterSubId, String registerNumber);
	
	@Query(value="SELECT a.COURSE_CATALOG_COURSE_ID, b.CODE, b.TITLE, b.GENERIC_COURSE_TYPE, "+
					"a.COURSE_OPTION_MASTER_CODE, d.DESCRIPTION as COURSE_OPTION_DESCRIPTION, "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.DESCRIPTION as COURSE_TYPE_DESCRIPTION, "+
					"(case when ((a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') or (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'TH') or "+
					"(a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'SS')) then b.LECTURE_HOURS else 0 end) as LECTURE_HOUR, "+
					"(case when ((a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') or (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'TH')) "+
					"then b.TUTORIAL_HOURS else 0 end) as TUTORIAL_HOUR, "+
					"(case when ((a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') or (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'LO') or "+
					"(a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'SS')) then b.PRACTICAL_HOURS else 0 end) as PRACTICAL_HOUR, "+
					"(case when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then b.PRACTICAL_HOURS else 0 end) "+
					"as PROJECT_HOUR, (case when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') then b.LECTURE_CREDITS "+
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') then b.PRACTICAL_CREDITS "+
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then b.PROJECT_CREDITS else b.CREDITS end) "+
					"as CREDIT, b.EVALUATION_TYPE, a.COURSE_ALLOCATION_CLASS_ID, "+
					"i.SLOT_NAME, f.ROOM_MASTER_ROOM_NUMBER, f.ERP_ID, g.FIRST_NAME, h.CODE as SCHOOL_CODE, "+
					"a.RGSTRTNSTSMSTR_STATUS_NUMBER, e.DESCRIPTION as STATUS_DESCRIPTION, e.FONT_COLOUR, "+
					"a.INVOICES_INVOICE_NUMBER, to_char(a.LOG_TIMESTAMP,'DD-Mon-YYYY') as reg_date, "+
					"(case when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') then 2 when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') "+
					"then 3 when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then 4 else 1 end) as COURSE_TYPE_ORDER "+
					"FROM ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER c, ACADEMICS.COURSE_OPTION_MASTER d, "+
					"ACADEMICS.REGISTRATION_STATUS_MASTER e, ACADEMICS.COURSE_ALLOCATION f, "+
					"HRMS.EMPLOYEE_PROFILE g, VTOPMASTER.COST_CENTRE h, ACADEMICS.TIME_TABLE i where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+
					"f.CLSSGRP_MASTER_CLASS_GROUP_ID in (?3) and b.GENERIC_COURSE_TYPE not in ('ECA') and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and a.CRSTYPCMPNTMASTER_COURSE_TYPE=c.COURSE_TYPE and "+
					"a.COURSE_OPTION_MASTER_CODE=d.CODE and a.RGSTRTNSTSMSTR_STATUS_NUMBER=e.STATUS_NUMBER and "+
					"a.COURSE_ALLOCATION_CLASS_ID=f.CLASS_ID and a.SEMSTR_DETAILS_SEMESTER_SUB_ID=f.SEMSTR_DETAILS_SEMESTER_SUB_ID "+
					"and a.COURSE_CATALOG_COURSE_ID=f.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=f.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"b.COURSE_ID=f.COURSE_CATALOG_COURSE_ID and f.ERP_ID=g.EMPLOYEE_ID and "+
					"f.TIME_TABLE_SLOT_ID=i.SLOT_ID and g.COST_CENTRE_CENTRE_ID=h.CENTRE_ID "+
					"order by a.COURSE_CATALOG_COURSE_ID, COURSE_TYPE_ORDER", nativeQuery=true)
	List<Object[]> findByRegisterNumberAndClassGroup(String semesterSubId, String registerNumber, String[] classGroupId);
	
	@Query(value="select distinct a.COURSE_CATALOG_COURSE_ID from ACADEMICS.COURSE_REGISTRATION a, "+
					"ACADEMICS.COURSE_ALLOCATION b, ACADEMICS.COURSE_CATALOG c where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 "+
					"and a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID and "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"a.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID "+
					"and (a.CRSTYPCMPNTMASTER_COURSE_TYPE in ('PJT') or a.RGSTRTNSTSMSTR_STATUS_NUMBER in (2,3,4,5,6,13) "+
					"or b.LOCK_STATUS in (1,2)) order by a.COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<String> findBlockedCourseIdByRegisterNumberForUpdate2(String semesterSubId, String registerNumber);
		
	@Query(value="select distinct a.COURSE_CATALOG_COURSE_ID from ACADEMICS.COURSE_REGISTRATION a, "+
					"ACADEMICS.COURSE_ALLOCATION b, ACADEMICS.COURSE_CATALOG c where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 "+
					"and a.COURSE_ALLOCATION_CLASS_ID=b.CLASS_ID and "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE and "+
					"a.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID "+
					"and (a.RGSTRTNSTSMSTR_STATUS_NUMBER in (2,3,4,5,6,13) or b.LOCK_STATUS in (1) or "+
					"(a.CRSTYPCMPNTMASTER_COURSE_TYPE='PJT' and a.COURSE_OPTION_MASTER_CODE in ('RGR') "+
					"and c.EVALUATION_TYPE in ('CAPSTONE'))) order by a.COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<String> findBlockedCourseIdByRegisterNumberForDelete(String semesterSubId, String registerNumber);
		
	@Query(value="select distinct a.course_code from ("+
					"(select a.COURSE_CODE from ACADEMICS.STUDENT_HISTORY a, ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE in ('RPEUE','RUCUE') "+
					"and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and b.COMPONENT in (1,3)) "+
					"union all "+
					"(select b.CODE as course_code from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE in ('RPEUE','RUCUE') "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) "+
					"union all "+
					"(select EQUIVALENT_COURSE_CODE as course_code from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE "+
					"in (select distinct b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE in ('RPEUE','RUCUE') "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID)) "+
					"union all "+
					"(select COURSE_CODE as course_code from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE "+
					"in (select distinct b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?1) and a.COURSE_OPTION_MASTER_CODE in ('RPEUE','RUCUE') "+
					"and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID))) a order by a.course_code", nativeQuery=true)
	List<String> findUECourseByRegisterNumber(List<String> registerNumber);
	
	
	@Query(value="select distinct b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE, "+
					"a.COURSE_OPTION_MASTER_CODE, a.RGSTRTNSTSMSTR_STATUS_NUMBER from ACADEMICS.COURSE_REGISTRATION a, "+
					"ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"(b.CODE=?3 or b.CODE in (select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES "+
					"where COURSE_CODE=?3) or b.CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES "+
					"where EQUIVALENT_COURSE_CODE=?3)) order by course_code", nativeQuery=true)
	List<Object[]> findCourseOptionByRegisterNumberAndCourseCode(String semesterSubId, String registerNumber, 
						String courseCode);
	
	@Query(value="select distinct a.course_code from ("+
					"(select b.CODE as course_code from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+
					"a.COURSE_OPTION_MASTER_CODE in (?3) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) "+
					"union all "+
					"(select EQUIVALENT_COURSE_CODE as course_code from ACADEMICS.COURSE_EQUIVALANCES where COURSE_CODE "+
					"in (select distinct b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+
					"a.COURSE_OPTION_MASTER_CODE in (?3) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID)) "+
					"union all "+
					"(select COURSE_CODE as course_code from ACADEMICS.COURSE_EQUIVALANCES where EQUIVALENT_COURSE_CODE "+
					"in (select distinct b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+
					"where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER=?2 and "+
					"a.COURSE_OPTION_MASTER_CODE in (?3) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID))) a "+
					"order by a.course_code", nativeQuery=true)
	List<String> findUECourseByRegisterNumberAndCourseOption(String semesterSubId, String registerNumber, 
					List<String> courseOption);
	
	@Query("select a.courseRegistrationPKId.courseId, a.courseRegistrationPKId.courseType, a.classId, "+
			"a.courseOptionCode, a.statusNumber, a.componentType from CourseRegistrationModel a where "+
			"a.courseRegistrationPKId.semesterSubId=?1 and a.courseRegistrationPKId.registerNumber in (?2) "+
			"and a.courseCatalogModel.code=?3 order by a.courseRegistrationPKId.courseType desc")
	List<Object[]> findByRegisterNumberCourseCode3(String semesterSubId, List<String> registerNumber, 
						String courseCode);
	
	@Query("select a.courseRegistrationPKId.courseId, a.courseRegistrationPKId.courseType, a.classId, "+
			"a.courseOptionCode, a.statusNumber, a.componentType from CourseRegistrationModel a where "+
			"a.courseRegistrationPKId.registerNumber in (?1) and a.courseCatalogModel.code=?2 and "+
			"a.courseRegistrationPKId.semesterSubId not in (?3) order by a.courseRegistrationPKId.courseType desc")
	List<Object[]> findByRegisterNumberCourseCodeAndExcludeSemesterSubId(List<String> registerNumber, String courseCode, 
							String semesterSubId);
	
	@Query(value="select SEMESTER_SUB_ID from ACADEMICS.RESULT_UNPUB_SEM_DETAIL_VIEW order by SEMESTER_SUB_ID", 
					nativeQuery=true)
	List<String> findResultUnpublishedSemesterSubId();
	
	@Query(value="select SEMESTER_SUB_ID from ACADEMICS.REGISTERED_SEM_DETAIL_VIEW order by SEMESTER_SUB_ID", 
					nativeQuery=true)
	List<String> findRegisteredSemesterSubId();
	
	@Query(value="select a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID, a.course_code, a.course_title, "+ 
					"a.GENERIC_COURSE_TYPE, a.generic_course_type_desc, a.COURSE_OPTION_MASTER_CODE, "+ 
					"sum(a.course_type_credit) as credit from "+ 
					"(select a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID, b.CODE as course_code, "+ 
					"b.TITLE as course_title, b.GENERIC_COURSE_TYPE, c.DESCRIPTION as generic_course_type_desc, "+ 
					"a.COURSE_OPTION_MASTER_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+ 
					"(case when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') then b.LECTURE_CREDITS "+ 
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') then b.PRACTICAL_CREDITS  "+ 
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then b.PROJECT_CREDITS  "+ 
					"when ((a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'OC') and (a.COURSE_OPTION_MASTER_CODE = 'RGR')) then 0 "+ 
					"else b.CREDITS end) as course_type_credit from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b, "+ 
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER c where a.SEMSTR_DETAILS_SEMESTER_SUB_ID in (?1) and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and a.COURSE_OPTION_MASTER_CODE in (?3) and "+ 
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.GENERIC_COURSE_TYPE=c.COURSE_TYPE and b.CODE not in (?4)) a "+ 
					"group by a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID, a.course_code, a.course_title, "+ 
					"a.GENERIC_COURSE_TYPE, a.generic_course_type_desc, a.COURSE_OPTION_MASTER_CODE "+ 
					"order by a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.course_code", nativeQuery=true)
	List<Object[]> findByRegisterNumberCourseOptionAndExceptCourse(List<String> semesterSubId, List<String> registerNumber, 
						List<String> courseOptionCode, List<String> exceptCourseCode);
	
	@Query(value="select a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID, a.course_code, a.course_title, "+ 
					"a.GENERIC_COURSE_TYPE, a.generic_course_type_desc, a.COURSE_OPTION_MASTER_CODE, "+ 
					"sum(a.course_type_credit) as credit from "+ 
					"(select a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID, b.CODE as course_code, "+ 
					"b.TITLE as course_title, b.GENERIC_COURSE_TYPE, c.DESCRIPTION as generic_course_type_desc, "+ 
					"a.COURSE_OPTION_MASTER_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+ 
					"(case when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') then b.LECTURE_CREDITS "+ 
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') then b.PRACTICAL_CREDITS  "+ 
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then b.PROJECT_CREDITS  "+ 
					"when ((a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'OC') and (a.COURSE_OPTION_MASTER_CODE = 'RGR')) then 0 "+ 
					"else b.CREDITS end) as course_type_credit from ACADEMICS.COURSE_REGISTRATION_WAITING a, "+
					"ACADEMICS.COURSE_CATALOG b, ACADEMICS.COURSE_TYPE_COMPONENT_MASTER c where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID in (?1) and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and "+
					"a.COURSE_OPTION_MASTER_CODE in (?3) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"b.GENERIC_COURSE_TYPE=c.COURSE_TYPE ) a group by a.SEMSTR_DETAILS_SEMESTER_SUB_ID, "+
					"a.COURSE_CATALOG_COURSE_ID, a.course_code, a.course_title, a.GENERIC_COURSE_TYPE, "+ 
					"a.generic_course_type_desc, a.COURSE_OPTION_MASTER_CODE "+ 
					"order by a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.course_code", nativeQuery=true)
	List<Object[]> findWaitingByRegisterNumberAndCourseOption(List<String> semesterSubId, List<String> registerNumber, 
						List<String> courseOptionCode);
}
