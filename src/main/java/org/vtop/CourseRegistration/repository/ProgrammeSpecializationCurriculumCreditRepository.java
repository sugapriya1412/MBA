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
}
