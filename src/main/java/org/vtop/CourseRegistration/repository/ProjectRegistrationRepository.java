package org.vtop.CourseRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.ProjectRegistrationModel;
import org.vtop.CourseRegistration.model.ProjectRegistrationPKModel;


@Repository
public interface ProjectRegistrationRepository extends JpaRepository<ProjectRegistrationModel, ProjectRegistrationPKModel>
{			
	@Modifying
	@Query("delete from ProjectRegistrationModel a where a.semesterSubId=?1 "+
			"and a.projectRegistrationPKId.registerNumber=?2 and a.courseId=?3")
	void deleteByRegisterNumberCourseId(String semesterSubId, String registerNumber, String courseId);
	
	/*@Query("select a from ProjectRegistrationModel a where a.semesterSubId=?1 "+
				"order by a.projectRegistrationPKId.registerNumber, a.courseId")
	List<ProjectRegistrationModel> findBySemesterSubId(String semesterSubId);

	@Query("select a from ProjectRegistrationModel a where a.semesterSubId=?1 "+
			"and a.projectRegistrationPKId.registerNumber=?2 order by a.courseId")
	List<ProjectRegistrationModel> findByRegisterNumber(String semesterSubId, String registerNumber);
	
	@Query("select a from ProjectRegistrationModel a where a.semesterSubId=?1 "+
			"and a.projectRegistrationPKId.registerNumber=?2 and a.courseId=?3")
	List<ProjectRegistrationModel> findByRegisterNumberCourseId(String semesterSubId, String registerNumber, 
										String courseId);*/
}
