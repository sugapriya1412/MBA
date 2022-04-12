package org.vtop.CourseRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.WishlistRegistrationModel;
import org.vtop.CourseRegistration.model.WishlistRegistrationPKModel;


@Repository
public interface WishlistRegistrationRepository extends JpaRepository<WishlistRegistrationModel, WishlistRegistrationPKModel>
{	
	@Query("select count(distinct a.wlRegPKId.courseId) as regcnt from WishlistRegistrationModel a "+
			"where a.wlRegPKId.semesterSubId=?1 and a.wlRegPKId.classGroupId in (?2) and a.wlRegPKId.registerNumber=?3")
	Integer findRegisterNumberTCCount2(String semesterSubId, String[] classGroupId, String registerNumber);
}
