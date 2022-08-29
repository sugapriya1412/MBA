package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CompulsoryCourseConditionDetailModel;
import org.vtop.CourseRegistration.model.CompulsoryCourseConditionDetailModelPK;


@Repository
public interface CompulsoryCourseConditionDetailRepository extends JpaRepository<CompulsoryCourseConditionDetailModel,
					CompulsoryCourseConditionDetailModelPK>
{		
	@Query("select a.cccdmPkId.courseId from CompulsoryCourseConditionDetailModel a where "+
			"a.cccdmPkId.semesterSubId in (?1) and a.cccdmPkId.programGroupId=?2 and "+
			"a.cccdmPkId.studentBatch=?3 and "+
			"a.status=0 order by a.cccdmPkId.courseId")
	List<String> findSoftSkillCourseList(String semesterSubId, Integer progGroupId, Integer studentBatch);
	
	@Query("select a.cccdmPkId.courseId, a.prerequisiteType, a.prerequisiteParam, a.priorityNo from "+
			"CompulsoryCourseConditionDetailModel a where a.cccdmPkId.semesterSubId=?1 and a.cccdmPkId.programGroupId=?2 "+
			"and a.cccdmPkId.studentBatch=?3 and (a.offerType=1 or (a.offerType=2 and (a.programSpecialization=?4 or "+
			"a.programSpecialization like ?4||'/%' or a.programSpecialization like '%/'||?4||'/%' or "+
			"a.programSpecialization like '%/'||?4)) or (a.offerType=3 and (a.programSpecialization=?5 or "+
			"a.programSpecialization like ?5||'/%' or a.programSpecialization like '%/'||?5||'/%' or "+
			"a.programSpecialization like '%/'||?5))) and a.status=0 order by a.priorityNo, a.cccdmPkId.courseId")
	List<Object[]> findCompulsoryCourseListByProgSpecId2(String semesterSubId, Integer progGroupId, Integer studentBatch, 
						String progSpecId, String costCenterId);
	
	@Query("select a.cccdmPkId.courseId, a.prerequisiteType, a.prerequisiteParam from CompulsoryCourseConditionDetailModel a "+
			"where a.cccdmPkId.semesterSubId=?1 and a.cccdmPkId.programGroupId=?2 and a.cccdmPkId.studentBatch=?3 and "+
			"a.cccdmPkId.courseId=?4 and a.status=0")
	List<Object[]> findByCourseId(String semesterSubId, Integer progGroupId, Integer studentBatch, String courseId);
}
