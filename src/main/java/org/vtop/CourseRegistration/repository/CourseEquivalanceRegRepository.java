package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseEquivalanceRegModel;
import org.vtop.CourseRegistration.model.CourseEquivalanceRegPKModel;


@Repository
public interface CourseEquivalanceRegRepository extends JpaRepository<CourseEquivalanceRegModel, CourseEquivalanceRegPKModel>
{		
	@Query("select distinct a.equivalanceCourseId from CourseEquivalanceRegModel a where "+
			"a.courseEquivalanceRegPKId.semesterSubId=?1 and a.courseEquivalanceRegPKId.registerNumber=?2 "+
			"and a.courseEquivalanceRegPKId.courseId=?3")
	String findEquivCourseByRegisterNumberAndCourseId(String semesterSubId, String registerNumber, String courseId);
	
	@Modifying
	@Query("delete from CourseEquivalanceRegModel a where a.courseEquivalanceRegPKId.semesterSubId=?1 "+
			"and a.courseEquivalanceRegPKId.registerNumber=?2 and a.courseEquivalanceRegPKId.courseId=?3")
	void deleteByRegisterNumberCourseId(String semesterSubId, String registerNumber, 
			String courseId);
	
	//For Course Substitution
	@Query(value="select (case when (b.CODE is null) then a.EQUIVALANCE_COURSE_ID else b.CODE end) as course_code "+
					"from ACADEMICS.COURSE_EQUIVALANCE_REG a left outer join (select COURSE_ID, CODE from "+
					"ACADEMICS.COURSE_CATALOG) b on (b.COURSE_ID=a.EQUIVALANCE_COURSE_ID) where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) "+
					"and a.EQUIVALANCE_COURSE_ID is not null order by course_code", nativeQuery=true)
	List<String> findEquivCourseByRegisterNumber(String semesterSubId, List<String> registerNumber);
	
	
	@Query(value="select a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"a.EQUIVALANCE_COURSE_ID, a.COURSE_CODE, a.COURSE_OPTION_MASTER_CODE, b.DESCRIPTION as course_option_desc from ("+
					"(select a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"b.EQUIVALANCE_COURSE_ID, (case when (c.CODE is null) then b.EQUIVALANCE_COURSE_ID else c.CODE end) "+
					"as course_code, a.COURSE_OPTION_MASTER_CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_EQUIVALANCE_REG b "+
					"left outer join ACADEMICS.COURSE_CATALOG c on (c.COURSE_ID=b.EQUIVALANCE_COURSE_ID) where a.STDNTSLGNDTLS_REGISTER_NUMBER "+
					"in (?2) and a.COURSE_OPTION_MASTER_CODE in (?3) and a.SEMSTR_DETAILS_SEMESTER_SUB_ID not in (?1) and "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=b.STDNTSLGNDTLS_REGISTER_NUMBER and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE) "+
					"union all "+
					"(select a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID, a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"b.EQUIVALANCE_COURSE_ID, (case when (c.CODE is null) then b.EQUIVALANCE_COURSE_ID else c.CODE end) "+
					"as course_code, a.COURSE_OPTION_MASTER_CODE from ACADEMICS.COURSE_REGISTRATION a, ACADEMICS.COURSE_EQUIVALANCE_REG b "+
					"left outer join ACADEMICS.COURSE_CATALOG c on (c.COURSE_ID=b.EQUIVALANCE_COURSE_ID) where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 "+
					"and a.STDNTSLGNDTLS_REGISTER_NUMBER in (?2) and a.SEMSTR_DETAILS_SEMESTER_SUB_ID=b.SEMSTR_DETAILS_SEMESTER_SUB_ID and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=b.STDNTSLGNDTLS_REGISTER_NUMBER and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_CATALOG_COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=b.CRSTYPCMPNTMASTER_COURSE_TYPE) "+
					") a, ACADEMICS.COURSE_OPTION_MASTER b where a.COURSE_CODE=?4 and a.COURSE_OPTION_MASTER_CODE=b.CODE "+
					"order by a.SEMSTR_DETAILS_SEMESTER_SUB_ID, a.COURSE_CATALOG_COURSE_ID, a.CRSTYPCMPNTMASTER_COURSE_TYPE", 
					nativeQuery=true)
	List<Object[]> findByRegisterNumberAndCourseCode(String semesterSubId, List<String> registerNumber, List<String> courseOptionCode, 
						String courseCode);
	
	/*@Query("select a from CourseEquivalanceRegModel a where a.courseEquivalanceRegPKId.semesterSubId=?1 "+
				"order by a.courseEquivalanceRegPKId.registerNumber, a.courseEquivalanceRegPKId.courseId, "+
				"a.courseEquivalanceRegPKId.courseType desc")
	List<CourseEquivalanceRegModel> findBySemesterSubId(String semesterSubId);
	
	@Query("select a from CourseEquivalanceRegModel a where a.courseEquivalanceRegPKId.semesterSubId=?1 "+
			"and a.courseEquivalanceRegPKId.registerNumber=?2 order by a.courseEquivalanceRegPKId.courseId, "+
			"a.courseEquivalanceRegPKId.courseType desc")
	List<CourseEquivalanceRegModel> findByRegisterNumber(String semesterSubId, String registerNumber);
	
	@Query("select a from CourseEquivalanceRegModel a where a.courseEquivalanceRegPKId.semesterSubId=?1 "+
			"and a.courseEquivalanceRegPKId.registerNumber=?2 and a.courseEquivalanceRegPKId.courseId=?3 "+
			"order by a.courseEquivalanceRegPKId.courseType desc")
	List<CourseEquivalanceRegModel> findByRegisterNumberCourseId(String semesterSubId, String registerNumber, 
										String courseId);*/
}
