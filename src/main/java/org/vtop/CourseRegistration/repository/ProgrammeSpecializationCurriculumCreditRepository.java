package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationCurriculumCategoryCredit;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationCurriculumCreditModel;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationCurriculumCreditPKModel;


@Repository
public interface ProgrammeSpecializationCurriculumCreditRepository extends 
					JpaRepository<ProgrammeSpecializationCurriculumCreditModel, ProgrammeSpecializationCurriculumCreditPKModel>
{
	@Query(value="select CURRICULUM_VERSION, PROGRAMME_CORE_CREDITS, PROGRAMME_ELECTIVE_CREDITS, UNIVERSITY_CORE_CREDITS, "+
					"UNIVERSITY_ELECTIVE_CREDITS, TOTAL_CREDITS, DISPLAY_STATUS, TOTAL_ALLOTTED_CREDIT, COURSE_SYSTEM "+
					"from ACADEMICS.PRG_SPLZTN_CURRICULUM_CREDITS where PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and ADMISSION_YEAR=?2 "+
					"and LOCK_STATUS=0 and CURRICULUM_VERSION="+
					"(select MAX(CURRICULUM_VERSION) from ACADEMICS.PRG_SPLZTN_CURRICULUM_CREDITS where "+
					"PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and ADMISSION_YEAR=?2 and LOCK_STATUS=0)", nativeQuery=true)
	List<Object[]> findMaxVerDetailBySpecIdAndAdmYear2(Integer specId, Integer admissionYear);
		
	//Basket Course Registered Credit
	@Query(value="select sum(case when(a.credit is null) then 0 else a.credit end) as total_credit from ("+
					"(select a.CREDIT from ACADEMICS.STUDENT_HISTORY a, ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b where "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and a.GRADE not in (?3) and a.COURSE_CODE not in (?5) and "+
					"a.COURSE_CODE in (?6) and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and b.COMPONENT in (1,3) and "+
					"(a.COURSE_CODE, a.EXAM_MONTH) in (select a.COURSE_CODE, max(a.EXAM_MONTH) from ACADEMICS.STUDENT_HISTORY a, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and a.GRADE not in (?3) "+
					"and a.COURSE_CODE not in (?5) and a.COURSE_CODE in (?6) and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and "+
					"b.COMPONENT in (1,3) group by a.COURSE_CODE)) "+
					"union all "+
					"(select (case when (CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') then LECTURE_CREDITS "+
					"when (CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') then PRACTICAL_CREDITS "+
					"when (CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then PROJECT_CREDITS else CREDITS end) as credit "+
					"from ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW where STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and "+
					"COURSE_OPTION_MASTER_CODE in (?4) and CODE not in (?5) and CODE in (?6) and CODE not in (?7)) "+
					"union all "+
					"(select (case when (CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') then LECTURE_CREDITS "+ 
					"when (CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') then PRACTICAL_CREDITS "+ 
					"when (CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then PROJECT_CREDITS else CREDITS end) as credit "+
					"from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and "+
					"a.COURSE_OPTION_MASTER_CODE in (?4) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"b.CODE not in (?5) and b.CODE in (?6)) "+
					"union all "+
					"(select (case when (CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') then LECTURE_CREDITS "+ 
					"when (CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') then PRACTICAL_CREDITS "+ 
					"when (CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then PROJECT_CREDITS else CREDITS end) as credit "+
					"from ACADEMICS.COURSE_REGISTRATION_WAITING a, ACADEMICS.COURSE_CATALOG b where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and "+
					"a.COURSE_OPTION_MASTER_CODE in (?4) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"b.CODE not in (?5) and b.CODE in (?6))) a", nativeQuery=true)
	Integer findStudentBasketCreditDetailByRegisterNo(String semSubId, List<String> registerNumber, List<String> histGrade, 
				List<String> courseOption, List<String> peCourseCode, List<String> basketCourseCode, List<String> tempPSCourseCode);
		
	//Curriculum Category Credit
	@Query("select a from ProgrammeSpecializationCurriculumCategoryCredit a where a.id.specializationId=?1 and "+ 
			"a.id.admissionYear=?2 and a.id.curriculumVersion=?3 order by a.curriculumCategoryMaster.orderNo, "+
			"a.id.courseCategory")
	List<ProgrammeSpecializationCurriculumCategoryCredit> findBySpecializationIdAdmissionYearAndVersion(Integer specializationId, 
																Integer admissionYear, Float curriculumVersion);
	
	
	/*@Query("select a from ProgrammeSpecializationCurriculumCreditModel a order by "+
			"a.pscccPkId.specializationId, a.pscccPkId.admissionYear desc, a.pscccPkId.curriculumVersion")
	List<ProgrammeSpecializationCurriculumCreditModel> findAll();
	
	@Query("select a from ProgrammeSpecializationCurriculumCreditModel a where a.pscccPkId.specializationId=?1 "+
			"order by a.pscccPkId.admissionYear desc, a.pscccPkId.curriculumVersion")
	List<ProgrammeSpecializationCurriculumCreditModel> findBySpecId(Integer specId);
	
	@Query("select a from ProgrammeSpecializationCurriculumCreditModel a where a.pscccPkId.specializationId=?1 "+
			"and a.pscccPkId.admissionYear=?2 order by a.pscccPkId.curriculumVersion")
	List<ProgrammeSpecializationCurriculumCreditModel> findBySpecIdAdmYear(Integer specId, Integer admissionYear);
	
	@Query("select a from ProgrammeSpecializationCurriculumCreditModel a where a.pscccPkId.specializationId=?1 "+
			"and a.pscccPkId.admissionYear=?2 and a.pscccPkId.curriculumVersion="+
			"(select max(a.pscccPkId.curriculumVersion) from ProgrammeSpecializationCurriculumCreditModel a "+
			"where a.pscccPkId.specializationId=?1 and a.pscccPkId.admissionYear=?2)")
	ProgrammeSpecializationCurriculumCreditModel findMaxVerDetailBySpecIdAndAdmYear(Integer specId, Integer admissionYear);*/
	
	//For registration purpose
	/*@Query(value="select courseCategory, sum(CREDIT) as total_credit from "+
					"(select decode(cur.COURSE_CATEGORY,null,'UE',cur.COURSE_CATEGORY) as courseCategory, "+
					"hst.CREDIT from "+
					"(select a.COURSE_CODE, a.CREDIT from ACADEMICS.STUDENT_HISTORY a, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) "+
					"and a.GRADE not in (?5) and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and "+
					"b.COMPONENT in (1,3) and (a.COURSE_CODE, a.EXAM_MONTH) in "+
					"(select a.COURSE_CODE, max(a.EXAM_MONTH) from ACADEMICS.STUDENT_HISTORY a, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) "+
					"and a.GRADE not in (?5) and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and "+
					"b.COMPONENT in (1,3) group by a.COURSE_CODE)) hst left outer join "+
					"(select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE from "+
					"((select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE from "+
					"ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+
					"a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and b.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID))) cur "+
					"on (cur.CODE=hst.COURSE_CODE)) group by courseCategory order by courseCategory", nativeQuery=true)
	List<Object[]> findStudentHistoryCurCtgCreditByRegisterNo(Integer specId, Integer admissionYear, Float ccVersion, 
						List<String> registerNumber, List<String> histGrade);
	
	@Query(value="select courseCategory, sum(CREDIT) as total_credit from "+
					"(select decode(cur.COURSE_CATEGORY,null,'UE',cur.COURSE_CATEGORY) as courseCategory, pvs.CREDIT from "+
					"(select CODE, decode(CRSTYPCMPNTMASTER_COURSE_TYPE, 'ETH', LECTURE_CREDITS, 'ELA', PRACTICAL_CREDITS, "+
					"'EPJ', PROJECT_CREDITS, CREDITS) as credit from ACADEMICS.COURSE_REG_PREVIOUS_SEM_VIEW "+
					"where STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and COURSE_OPTION_MASTER_CODE in (?5) and "+
					"CODE not in (?6)) pvs left outer join "+
					"(select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE from "+
					"((select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE from "+
					"ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+
					"a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and b.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID))) cur "+
					"on (cur.CODE=pvs.CODE)) group by courseCategory order by courseCategory", nativeQuery=true)
	List<Object[]> findPreviousSemRegCurCtgCreditByRegisterNo(Integer specId, Integer admissionYear, Float ccVersion, 
						List<String> registerNumber, List<String> courseOption, List<String> psCourseCode);
	
	@Query(value="select courseCategory, sum(CREDIT) as total_credit from "+
					"(select cus.CODE, cus.CREDIT, decode(cur.COURSE_CATEGORY,null,'UE',cur.COURSE_CATEGORY) "+
					"as courseCategory, cur.CATALOG_TYPE from "+
					"(select b.CODE, decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE, 'ETH', LECTURE_CREDITS, 'ELA', "+
					"PRACTICAL_CREDITS, 'EPJ', PROJECT_CREDITS, CREDITS) as credit from ACADEMICS.COURSE_REGISTRATION a, "+
					"ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?4 and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER in (?5) and COURSE_OPTION_MASTER_CODE in (?6) and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) cus left outer join "+
					"(select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE from "+
					"((select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE from "+
					"ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+
					"a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and b.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID))) cur "+
					"on (cur.CODE=cus.CODE)) group by courseCategory order by courseCategory", nativeQuery=true)
	List<Object[]> findCurrentSemRegCurCtgCreditByRegisterNo(Integer specId, Integer admissionYear, Float ccVersion, 
						String semSubId, List<String> registerNumber, List<String> courseOption);
	
	
	//Curriculum category credit distribution	
	//Method-6 (New): Curriculum category credit distribution with Student History maximum of exam date,  
	//Result Unpublished & Registered Semesters.  Along with RPEUE/RUCUE courses under UE category based & 
	//RUEPE course under PE category.
	@Query(value="select course_category, category_credit, hist_credit, pv_credit, cu_reg_credit, cu_wl_credit, "+
					"(hist_credit + pv_credit + cu_reg_credit + cu_wl_credit) as obtain_credit, "+ 
					"(category_credit - (hist_credit + pv_credit + cu_reg_credit + cu_wl_credit)) as remain_credit, "+
					"category_no, course_category_desc from "+
					"(select a.course_category, a.category_credit, decode(b.history_credit, null, 0, "+
					"b.history_credit) as hist_credit, decode(c.pvs_credit, null, 0, c.pvs_credit) as "+
					"pv_credit, decode(d.cur_reg_credit, null, 0, d.cur_reg_credit) as cu_reg_credit, "+
					"decode(e.cur_wl_credit, null, 0, e.cur_wl_credit) as cu_wl_credit, a.category_no, "+
					"a.course_category_desc from "+
					"(select a1.CODE as course_category, decode(a1.CODE, 'PC', a2.PROGRAMME_CORE_CREDITS, "+
					"'PE', a2.PROGRAMME_ELECTIVE_CREDITS, 'UC', a2.UNIVERSITY_CORE_CREDITS, "+
					"'UE', a2.UNIVERSITY_ELECTIVE_CREDITS, 0) as category_credit, a1.ORDER_POSITION as category_no, "+
					"a1.DESCRIPTION as course_category_desc from "+
					"(select CODE, DESCRIPTION, ORDER_POSITION from ACADEMICS.LOOKUP_MASTER where "+
					"TABLE_NAME='PRG_SPLZTN_CURRICULUM_DETAILS' and COLUMN_NAME='COURSE_CATEGORY' and "+
					"LOCK_STATUS in (0,1)) a1, "+
					"(select PROGRAMME_CORE_CREDITS, PROGRAMME_ELECTIVE_CREDITS, UNIVERSITY_CORE_CREDITS, "+
					"UNIVERSITY_ELECTIVE_CREDITS from ACADEMICS.PRG_SPLZTN_CURRICULUM_CREDITS where "+
					"PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and ADMISSION_YEAR=?2 and CURRICULUM_VERSION=?3 and "+
					"LOCK_STATUS=0) a2) a "+
					"left outer join "+
					"(select courseCategory, sum(case when ((catalogType = 'BC') and (history_credit < basketCredit)) "+
					"then history_credit when ((catalogType = 'BC') and (history_credit >= basketCredit)) "+
					"then basketCredit else history_credit end) as history_credit from "+
					"(select courseCategory, catalogType, BASKET_ID, basketCredit, sum(CREDIT) as history_credit from "+
					"(select decode(cur.COURSE_CATEGORY,null,'UE',cur.COURSE_CATEGORY) as courseCategory, "+
					"decode(cur.CATALOG_TYPE,null,'CC',cur.CATALOG_TYPE) as catalogType, "+
					"(case when (cur.CATALOG_TYPE = 'BC') then cur.COURSE_BASKET_ID else 'NONE' end) as BASKET_ID, "+
					"decode(cur.basket_credit,null,0,cur.basket_credit) as basketCredit, hst.CREDIT from "+
					"(select a.COURSE_CODE, a.CREDIT from ACADEMICS.STUDENT_HISTORY a, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) "+
					"and a.GRADE not in (?5) and a.COURSE_OPTION_MASTER_CODE not in ('HON','MIN') and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and b.COMPONENT in (1,3) and (a.COURSE_CODE, a.EXAM_MONTH) "+
					"in (select a.COURSE_CODE, max(a.EXAM_MONTH) from ACADEMICS.STUDENT_HISTORY a, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) "+
					"and a.GRADE not in (?5) and a.COURSE_OPTION_MASTER_CODE not in ('HON','MIN') and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE and b.COMPONENT in (1,3) "+
					"group by a.COURSE_CODE)) hst left outer join "+
					"(select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE, basket_credit from "+
					"((select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE, "+
					"0 as basket_credit from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID and b.CODE not in (?8)) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE, d.CREDITS as basket_credit from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.BASKET_COURSE_CATALOG b, ACADEMICS.COURSE_CATALOG c, ACADEMICS.BASKET_DETAILS d where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 and "+
					"a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and b.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID "+
					"and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and b.BASKET_DETAILS_BASKET_ID=d.BASKET_ID and c.CODE not in (?8)) "+
					"union all "+ 
					"(select distinct 'PE' as COURSE_CATEGORY, 'CC' as CATALOG_TYPE, a.COURSE_CATALOG_COURSE_ID as COURSE_BASKET_ID, "+ 
					"b.COURSE_ID, b.CODE, 0 as basket_credit from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b "+ 
					"where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and a.COURSE_OPTION_MASTER_CODE in ('RUEPE','CSUPE') and "+ 
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID))) cur on (cur.CODE=hst.COURSE_CODE)) "+
					"group by courseCategory, catalogType, BASKET_ID, basketCredit) "+
					"group by courseCategory) b on (b.courseCategory=a.course_category) "+
					"left outer join "+
					"(select courseCategory, sum(case when ((catalogType = 'BC') and (pvs_credit < basketCredit)) "+
					"then pvs_credit when ((catalogType = 'BC') and (pvs_credit >= basketCredit)) "+
					"then basketCredit else pvs_credit end) as pvs_credit from "+
					"(select courseCategory, catalogType, BASKET_ID, basketCredit, pvs_credit from "+
					"(select (case when ((cur.COURSE_CATEGORY is not null) and ((pvs.COURSE_OPTION_MASTER_CODE = 'RPEUE') "+
					"or (pvs.COURSE_OPTION_MASTER_CODE = 'RUCUE'))) then 'UE' when ((cur.COURSE_CATEGORY is null) and "+
					"(pvs.COURSE_OPTION_MASTER_CODE = 'RUEPE')) then 'PE' when (cur.COURSE_CATEGORY is not null) then "+
					"cur.COURSE_CATEGORY else 'UE' end) as courseCategory, decode(cur.CATALOG_TYPE,null,'CC',cur.CATALOG_TYPE) as catalogType, "+
					"(case when (cur.CATALOG_TYPE = 'BC') then cur.COURSE_BASKET_ID else 'NONE' end) as BASKET_ID, "+
					"decode(cur.basket_credit,null,0,cur.basket_credit) as basketCredit, pvs.pvs_credit from "+
					"(select b.CODE, a.COURSE_OPTION_MASTER_CODE, sum(case when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') "+
					"then b.LECTURE_CREDITS when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') then b.PRACTICAL_CREDITS "+ 
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then b.PROJECT_CREDITS "+ 
					"when ((a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'OC') and (a.COURSE_OPTION_MASTER_CODE = 'RGR')) then 0 "+ 
					"else b.CREDITS end) as pvs_credit from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID in (select SEMESTER_SUB_ID from ACADEMICS.RESULT_UNPUB_SEM_DETAIL_VIEW) "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and a.COURSE_OPTION_MASTER_CODE in (?6) and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and b.CODE not in (?7) group by b.CODE, a.COURSE_OPTION_MASTER_CODE) pvs "+
					"left outer join "+
					"(select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE, basket_credit from "+
					"((select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE, "+
					"0 as basket_credit from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID and b.CODE not in (?8)) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE, d.CREDITS as basket_credit from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.BASKET_COURSE_CATALOG b, ACADEMICS.COURSE_CATALOG c, ACADEMICS.BASKET_DETAILS d where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 and "+
					"a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and b.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID "+
					"and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and b.BASKET_DETAILS_BASKET_ID=d.BASKET_ID and "+
					"c.CODE not in (?8)))) cur on (cur.CODE=pvs.CODE))) group by courseCategory) c on (c.courseCategory=a.course_category) "+
					"left outer join "+
					"(select courseCategory, sum(case when ((catalogType = 'BC') and (cur_reg_credit < basketCredit)) "+
					"then cur_reg_credit when ((catalogType = 'BC') and (cur_reg_credit >= basketCredit)) "+
					"then basketCredit else cur_reg_credit end) as cur_reg_credit from "+
					"(select courseCategory, catalogType, BASKET_ID, basketCredit, cur_reg_credit from "+
					"(select (case when ((cur.COURSE_CATEGORY is not null) and ((cus.COURSE_OPTION_MASTER_CODE = 'RPEUE') "+
					"or (cus.COURSE_OPTION_MASTER_CODE = 'RUCUE'))) then 'UE' when ((cur.COURSE_CATEGORY is null) and "+
					"(cus.COURSE_OPTION_MASTER_CODE = 'RUEPE')) then 'PE' when (cur.COURSE_CATEGORY is not null) then "+
					"cur.COURSE_CATEGORY else 'UE' end) as courseCategory, decode(cur.CATALOG_TYPE,null,'CC',cur.CATALOG_TYPE) "+
					"as catalogType, (case when (cur.CATALOG_TYPE = 'BC') then cur.COURSE_BASKET_ID else 'NONE' end) as BASKET_ID, "+
					"decode(cur.basket_credit,null,0,cur.basket_credit) as basketCredit, cus.cur_reg_credit from "+
					"(select b.CODE, a.COURSE_OPTION_MASTER_CODE, sum(case when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ETH') "+
					"then b.LECTURE_CREDITS when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'ELA') then b.PRACTICAL_CREDITS "+ 
					"when (a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'EPJ') then b.PROJECT_CREDITS "+ 
					"when ((a.CRSTYPCMPNTMASTER_COURSE_TYPE = 'OC') and (a.COURSE_OPTION_MASTER_CODE = 'RGR')) then 0 "+ 
					"else b.CREDITS end) as cur_reg_credit from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID in (select SEMESTER_SUB_ID from ACADEMICS.REGISTERED_SEM_DETAIL_VIEW) and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and a.COURSE_OPTION_MASTER_CODE in (?6) and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID group by b.CODE, a.COURSE_OPTION_MASTER_CODE) cus "+
					"left outer join "+
					"(select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE, basket_credit from "+
					"((select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE, "+
					"0 as basket_credit from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID and b.CODE not in (?8)) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE, d.CREDITS as basket_credit from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.BASKET_COURSE_CATALOG b, ACADEMICS.COURSE_CATALOG c, ACADEMICS.BASKET_DETAILS d where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 and "+
					"a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and b.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID "+
					"and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and b.BASKET_DETAILS_BASKET_ID=d.BASKET_ID and "+
					"c.CODE not in (?8)))) cur on (cur.CODE=cus.CODE))) group by courseCategory) d on (d.courseCategory=a.course_category) "+
					"left outer join "+
					"(select courseCategory, sum(case when ((catalogType = 'BC') and (cur_wl_credit < basketCredit)) "+
					"then cur_wl_credit when ((catalogType = 'BC') and (cur_wl_credit >= basketCredit)) "+
					"then basketCredit else cur_wl_credit end) as cur_wl_credit from "+
					"(select courseCategory, catalogType, BASKET_ID, basketCredit, cur_wl_credit from "+
					"(select (case when ((cur.COURSE_CATEGORY is not null) and ((cws.COURSE_OPTION_MASTER_CODE = 'RPEUE') "+
					"or (cws.COURSE_OPTION_MASTER_CODE = 'RUCUE'))) then 'UE' when ((cur.COURSE_CATEGORY is null) and "+
					"(cws.COURSE_OPTION_MASTER_CODE = 'RUEPE')) then 'PE' when (cur.COURSE_CATEGORY is not null) then "+
					"cur.COURSE_CATEGORY else 'UE' end) as courseCategory, decode(cur.CATALOG_TYPE,null,'CC',cur.CATALOG_TYPE) "+
					"as catalogType, (case when (cur.CATALOG_TYPE = 'BC') then cur.COURSE_BASKET_ID else 'NONE' end) as BASKET_ID, "+
					"decode(cur.basket_credit,null,0,cur.basket_credit) as basketCredit, cws.cur_wl_credit from "+
					"(select b.CODE, a.COURSE_OPTION_MASTER_CODE, sum(decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE, 'ETH', b.LECTURE_CREDITS, "+
					"'ELA', b.PRACTICAL_CREDITS, 'EPJ', b.PROJECT_CREDITS, b.CREDITS)) as cur_wl_credit from "+
					"ACADEMICS.COURSE_REGISTRATION_WAITING a, ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID in "+
					"(select SEMESTER_SUB_ID from ACADEMICS.REGISTERED_SEM_DETAIL_VIEW) and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and "+
					"a.COURSE_OPTION_MASTER_CODE in (?6) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID group by b.CODE, a.COURSE_OPTION_MASTER_CODE) cws "+
					"left outer join "+
					"(select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE, basket_credit from "+
					"((select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE, "+
					"0 as basket_credit from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID and b.CODE not in (?8)) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE, d.CREDITS as basket_credit from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.BASKET_COURSE_CATALOG b, ACADEMICS.COURSE_CATALOG c, ACADEMICS.BASKET_DETAILS d where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 and "+
					"a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and b.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID "+
					"and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and b.BASKET_DETAILS_BASKET_ID=d.BASKET_ID and "+
					"c.CODE not in (?8)))) cur on (cur.CODE=cws.CODE))) group by courseCategory) e on (e.courseCategory=a.course_category)) "+
					"order by category_no", nativeQuery=true)
	List<Object[]> findStudentCurriculumCreditDetailByRegisterNo3(Integer specId, Integer admissionYear, Float ccVersion, 
						List<String> registerNumber, List<String> histGrade, List<String> courseOption, List<String> psCourseCode, 
						List<String> peCourseCode);	
		
		
	@Query(value="select nvl(sum(credit),0) as earn_credit, nvl(sum(component_val),0) as component_count from "+
					"(select hst.COURSE_CODE, hst.CRSTYPCMPNTMASTER_COURSE_TYPE, decode(hst.CREDIT,null,0,hst.CREDIT) "+
					"as credit, decode(hst.CRSTYPCMPNTMASTER_COURSE_TYPE,'LO',1,'PJT',1,0) as component_val from "+
					"(select a.COURSE_CODE, a.CRSTYPCMPNTMASTER_COURSE_TYPE, a.CREDIT from ACADEMICS.STUDENT_HISTORY a, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and "+
					"a.GRADE in ('S','A','B','C','D','E','P','Pass') and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE "+
					"and b.COMPONENT in (1,3) and (a.COURSE_CODE, a.EXAM_MONTH) in "+
					"(select a.COURSE_CODE, max(a.EXAM_MONTH) from ACADEMICS.STUDENT_HISTORY a, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) "+
					"and a.GRADE in ('S','A','B','C','D','E','P','Pass') and a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.COURSE_TYPE "+
					"and b.COMPONENT in (1,3) group by a.COURSE_CODE)) hst, "+
					"(select COURSE_CATEGORY, CATALOG_TYPE, COURSE_BASKET_ID, COURSE_ID, CODE from "+
					"((select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE from "+
					"ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.COURSE_CATEGORY='PE' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_CATALOG c where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+
					"a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='BC' and a.COURSE_CATEGORY='PE' and "+
					"a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and b.LOCK_STATUS=0 and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID))) cur where cur.CODE=hst.COURSE_CODE)", 
					nativeQuery=true)
	List<Object[]> findStudentHonourEligibleDetailByRegisterNo(Integer specId, Integer admissionYear, Float ccVersion, 
						List<String> registerNumber);*/
}
