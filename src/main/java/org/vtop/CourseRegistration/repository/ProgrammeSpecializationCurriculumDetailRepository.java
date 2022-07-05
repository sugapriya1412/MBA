package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationCurriculumDetailModel;
import org.vtop.CourseRegistration.model.ProgrammeSpecializationCurriculumDetailPKModel;

@Repository
public interface ProgrammeSpecializationCurriculumDetailRepository extends 
					JpaRepository<ProgrammeSpecializationCurriculumDetailModel, ProgrammeSpecializationCurriculumDetailPKModel>
{		
	@Query(value="select a.COURSE_CATEGORY, b.DESCRIPTION as COURSE_CATEGORY_DESC, a.CATALOG_TYPE, a.COURSE_BASKET_ID, "+
					"a.COURSE_ID, a.CODE, b.ORDER_NO, a.BASKET_CATEGORY from ("+
					"select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, a.COURSE_ID, a.CODE, "+
					"a.BASKET_CATEGORY from ("+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE, "+
					"'NONE' as BASKET_CATEGORY from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.COURSE_CATALOG b "+
					"where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as "+
					"COURSE_ID, c.CODE, d.BASKET_CATEGORY from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.BASKET_COURSE_CATALOG b, ACADEMICS.COURSE_CATALOG c, ACADEMICS.BASKET_DETAILS d where "+
					"a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 and "+
					"a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and b.LOCK_STATUS=0 and d.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID and a.COURSE_BASKET_ID=d.BASKET_ID and "+
					"b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID)) a) a, ACADEMICS.CURRICULUM_CATEGORY_MASTER b where "+
					"a.COURSE_CATEGORY=b.COURSE_CATEGORY order by b.ORDER_NO, a.CATALOG_TYPE, a.COURSE_ID", nativeQuery=true)
	List<Object[]> findCurriculumByAdmsnYearAndCCVersion2(Integer specId, Integer admissionYear, Float ccVersion);
	
	//Program Specialization, Year & Course based Curriculum Category detail	
	@Query(value="select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, a.COURSE_ID, a.CODE, a.BASKET_CATEGORY, "+
					"a.CREDITS from ("+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE, "+
					"'NONE' as BASKET_CATEGORY, b.CREDITS from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.COURSE_CATALOG b where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 "+
					"and a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.COURSE_ID and b.CODE=?4) "+
					"union all "+
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, c.COURSE_CATALOG_COURSE_ID "+
					"as COURSE_ID, d.CODE, b.BASKET_CATEGORY, b.CREDITS from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.BASKET_DETAILS b, ACADEMICS.BASKET_COURSE_CATALOG c, ACADEMICS.COURSE_CATALOG d "+
					"where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 "+
					"and a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_ID and "+
					"a.COURSE_BASKET_ID=c.BASKET_DETAILS_BASKET_ID and b.BASKET_ID=c.BASKET_DETAILS_BASKET_ID and "+
					"c.COURSE_CATALOG_COURSE_ID=d.COURSE_ID and d.CODE=?4)) a "+
					"order by a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_ID", nativeQuery=true)
	List<Object[]> findCurriculumByAdmsnYearCCVersionAndCourseCode(Integer specId, Integer admissionYear, Float ccVersion, 
						String courseCode);
	
	@Query(value="select a.COURSE_CODE from ("+
					"(select b.CODE as COURSE_CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.COURSE_CATALOG b where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+
					"a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='CC' and (a.COURSE_CATEGORY in (?4) or a.COURSE_CATEGORY in "+ 
					"(select course_category from academics.prgspl_curr_category_credit where PRGSPLZN_PRG_SPECIALIZATION_ID=?1 "+ 
					"and ADMISSION_YEAR=?2 and CURRICULUM_VERSION=?3 and total_credit_calc_status=2)) and a.LOCK_STATUS=0 and "+
					"a.COURSE_BASKET_ID=b.COURSE_ID) "+
					"union all "+
					"(select d.CODE as COURSE_CODE from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_DETAILS b, "+
					"ACADEMICS.BASKET_COURSE_CATALOG c, ACADEMICS.COURSE_CATALOG d where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 "+
					"and a.ADMISSION_YEAR=?2 and a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='BC' and (a.COURSE_CATEGORY in (?4) or "+
					"a.COURSE_CATEGORY in (select course_category from academics.prgspl_curr_category_credit where "+
					"PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and ADMISSION_YEAR=?2 and CURRICULUM_VERSION=?3 and total_credit_calc_status=2)) "+
					"and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_ID and a.COURSE_BASKET_ID=c.BASKET_DETAILS_BASKET_ID and "+
					"b.BASKET_ID=c.BASKET_DETAILS_BASKET_ID and c.COURSE_CATALOG_COURSE_ID=d.COURSE_ID)) a order by a.COURSE_CODE", 
					nativeQuery=true)
	List<String> findNCCourseByYearAndCCVersion(Integer specId, Integer admissionYear, Float ccVersion, List<String> courseCategory);
	
	@Query(value="select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, a.COURSE_ID, a.course_code, a.basket_code, "+
					"a.basket_credit, a.basket_category, b.DESCRIPTION as course_category_desc from ( "+ 
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE as course_code, "+
					"'NONE' as basket_code, 0 as basket_credit, 'NONE' as basket_category from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+
					"ACADEMICS.COURSE_CATALOG b where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+
					"a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID and "+
					"b.CODE not in (?5)) "+ 
					"union all "+ 
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as course_id, "+ 
					"c.CODE as course_code, d.CODE as basket_code, d.CREDITS as basket_credit, d.BASKET_CATEGORY from "+
					"ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, ACADEMICS.COURSE_CATALOG c, "+
					"ACADEMICS.BASKET_DETAILS d where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+
					"a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID "+ 
					"and a.COURSE_BASKET_ID=d.BASKET_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and b.BASKET_DETAILS_BASKET_ID=d.BASKET_ID "+
					"and c.CODE not in (?5)) "+ 
					"union all  "+ 
					"(select distinct 'PE' as course_category, 'CC' as catalog_type, a.COURSE_CATALOG_COURSE_ID as course_basket_id, "+ 
					"b.COURSE_ID, b.CODE as course_code, 'NONE' as basket_code, 0 as basket_credit, 'NONE' as basket_category from "+
					"ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and "+
					"a.COURSE_OPTION_MASTER_CODE in (?6) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) "+
					"union all  "+ 
					"(select distinct 'PE' as course_category, 'CC' as catalog_type, a.COURSE_CATALOG_COURSE_ID as course_basket_id, "+ 
					"b.COURSE_ID, b.CODE as course_code, 'NONE' as basket_code, 0 as basket_credit, 'NONE' as basket_category from "+
					"ACADEMICS.COURSE_REGISTRATION_WAITING a, ACADEMICS.COURSE_CATALOG b where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and "+
					"a.COURSE_OPTION_MASTER_CODE in (?6) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) "+
					") a, ACADEMICS.CURRICULUM_CATEGORY_MASTER b where a.COURSE_CATEGORY=b.COURSE_CATEGORY "+
					"order by a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, a.COURSE_ID", nativeQuery=true)
	List<Object[]> findStudentCurriculumByRegisterNumberUECourseAndPECourseOption(Integer specializationId, Integer admissionYear, 
						Float curriculumVersion, List<String> registerNumber, List<String> ueCourseCode, List<String> peCourseOptionCode);
	
	
	@Query(value="select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, a.COURSE_ID, a.course_code, a.basket_code, "+ 
					"a.basket_credit, a.basket_category, b.DESCRIPTION as course_category_desc from (  "+ 
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_ID, b.CODE as course_code, "+ 
					"'NONE' as basket_code, 0 as basket_credit, 'NONE' as basket_category from ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, "+ 
					"ACADEMICS.COURSE_CATALOG b where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+ 
					"a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='CC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.COURSE_ID and "+ 
					"b.CODE not in (select b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b, "+ 
					"ACADEMICS.COURSE_OPTION_MASTER c where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID "+ 
					"and a.COURSE_OPTION_MASTER_CODE=c.CODE and c.CURRICULUM_COURSE_CATEGORY not in ('NONE')))  "+ 
					"union all  "+ 
					"(select a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, b.COURSE_CATALOG_COURSE_ID as course_id,  "+ 
					"c.CODE as course_code, d.CODE as basket_code, d.CREDITS as basket_credit, d.BASKET_CATEGORY from "+ 
					"ACADEMICS.PRG_SPLZTN_CURRICULUM_DETAILS a, ACADEMICS.BASKET_COURSE_CATALOG b, ACADEMICS.COURSE_CATALOG c, "+ 
					"ACADEMICS.BASKET_DETAILS d where a.PRGSPLZN_PRG_SPECIALIZATION_ID=?1 and a.ADMISSION_YEAR=?2 and "+ 
					"a.CURRICULUM_VERSION=?3 and a.CATALOG_TYPE='BC' and a.LOCK_STATUS=0 and a.COURSE_BASKET_ID=b.BASKET_DETAILS_BASKET_ID  "+ 
					"and a.COURSE_BASKET_ID=d.BASKET_ID and b.COURSE_CATALOG_COURSE_ID=c.COURSE_ID and b.BASKET_DETAILS_BASKET_ID=d.BASKET_ID "+ 
					"and c.CODE not in (select b.CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b, "+ 
					"ACADEMICS.COURSE_OPTION_MASTER c where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID "+ 
					"and a.COURSE_OPTION_MASTER_CODE=c.CODE and c.CURRICULUM_COURSE_CATEGORY not in ('NONE')))  "+ 
					"union all   "+ 
					"(select c.CURRICULUM_COURSE_CATEGORY as course_category, 'CC' as catalog_type, a.COURSE_CATALOG_COURSE_ID "+ 
					"as course_basket_id, b.COURSE_ID, b.CODE as course_code, 'NONE' as basket_code, 0 as basket_credit, "+ 
					"'NONE' as basket_category from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_CATALOG b, "+ 
					"ACADEMICS.COURSE_OPTION_MASTER c where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID "+ 
					"and a.COURSE_OPTION_MASTER_CODE=c.CODE and c.CURRICULUM_COURSE_CATEGORY not in ('NONE')) "+ 
					"union all   "+ 
					"(select c.CURRICULUM_COURSE_CATEGORY as course_category, 'CC' as catalog_type, a.COURSE_CATALOG_COURSE_ID "+ 
					"as course_basket_id, b.COURSE_ID, b.CODE as course_code, 'NONE' as basket_code, 0 as basket_credit, "+ 
					"'NONE' as basket_category from ACADEMICS.COURSE_REGISTRATION_WAITING a, ACADEMICS.COURSE_CATALOG b, "+ 
					"ACADEMICS.COURSE_OPTION_MASTER c where a.STDNTSLGNDTLS_REGISTER_NUMBER in (?4) and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID "+ 
					"and a.COURSE_OPTION_MASTER_CODE=c.CODE and c.CURRICULUM_COURSE_CATEGORY not in ('NONE')) "+ 
					") a, ACADEMICS.CURRICULUM_CATEGORY_MASTER b where a.COURSE_CATEGORY=b.COURSE_CATEGORY "+ 
					"order by a.COURSE_CATEGORY, a.CATALOG_TYPE, a.COURSE_BASKET_ID, a.COURSE_ID", nativeQuery=true)
	List<Object[]> findCurriculumBySpecIdYearCCVersionAndRegisterNumber(Integer specializationId, Integer admissionYear, Float curriculumVersion, 
						List<String> registerNumber);
}
