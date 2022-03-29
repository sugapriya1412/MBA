package org.vtop.CourseRegistration.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.WishlistRegistrationModel;
import org.vtop.CourseRegistration.model.WishlistRegistrationPKModel;


@Repository
@Transactional
public interface WishlistRegistrationRepository extends JpaRepository<WishlistRegistrationModel, WishlistRegistrationPKModel>
{	
	@Query("select count(distinct a.wlRegPKId.courseId) as regcnt from WishlistRegistrationModel a "+
			"where a.wlRegPKId.semesterSubId=?1 and a.wlRegPKId.classGroupId in (?2) and a.wlRegPKId.registerNumber=?3")
	Integer findRegisterNumberTCCount2(String semesterSubId, String[] classGroupId, String registerNumber);
	
	/*@Query("select a from WishlistRegistrationModel a where a.wlRegPKId.semesterSubId=?1 and "+
			"a.wlRegPKId.classGroupId in (?2) order by a.wlRegPKId.registerNumber, "+
			"a.wlRegPKId.courseId, a.wlRegPKId.courseType desc")
	List<WishlistRegistrationModel> findBySemesterSubId(String semesterSubId, String[] classGroupId);
	
	@Query("select a from WishlistRegistrationModel a where a.wlRegPKId.semesterSubId=?1 and "+
			"a.wlRegPKId.classGroupId in (?2) and a.wlRegPKId.registerNumber=?3 order by "+
			"a.wlRegPKId.registerNumber, a.wlRegPKId.courseId, a.wlRegPKId.courseType desc")
	List<WishlistRegistrationModel> findByRegisterNumber(String semesterSubId, String[] classGroupId, 
			String registerNumber);
		
	@Query("select a from WishlistRegistrationModel a where a.wlRegPKId.semesterSubId=?1 and "+
			"a.wlRegPKId.classGroupId in (?2) and a.wlRegPKId.registerNumber=?3 and a.wlRegPKId.courseId=?4 "+
			"order by a.wlRegPKId.registerNumber, a.wlRegPKId.courseId, a.wlRegPKId.courseType desc")
	List<WishlistRegistrationModel> findByRegisterNumberCourseId(String semesterSubId, String[] classGroupId, 
			String registerNumber, String courseId);
		
	@Query("select a from WishlistRegistrationModel a where a.wlRegPKId.semesterSubId=?1 and "+
			"a.wlRegPKId.classGroupId in (?2) and a.wlRegPKId.registerNumber=?3 and "+
			"a.courseCatalogModel.code=?4 order by a.wlRegPKId.registerNumber, a.wlRegPKId.courseId, "+
			"a.wlRegPKId.courseType desc")
	List<WishlistRegistrationModel> findByRegisterNumberCourseCode(String semesterSubId, String[] classGroupId, 
										String registerNumber, String courseCode);
			
	@Query("select nvl(count(distinct a.wlRegPKId.courseId),0) as regcnt from WishlistRegistrationModel a "+
			"where a.wlRegPKId.semesterSubId=?1 and a.wlRegPKId.classGroupId in (?2) and a.wlRegPKId.registerNumber=?3")
	Integer findRegisterNumberTCCount(String semesterSubId, String[] classGroupId, String registerNumber);
	
	@Query("select nvl(count(distinct a.wlRegPKId.courseId),0) as rgrcnt from WishlistRegistrationModel a "+
			"where a.wlRegPKId.semesterSubId=?1 and a.wlRegPKId.classGroupId in (?2) and "+
			"a.wlRegPKId.registerNumber=?3 and (a.courseOptionCode in ('RGR','RGA','AUD','RGP','DM') or "+
			"(a.courseOptionCode like 'HON%') or (a.courseOptionCode like 'MIN%'))")
	Integer findRegisterNumberRCCount(String semesterSubId, String[] classGroupId, String registerNumber);
	
	@Query("select nvl(count(distinct a.wlRegPKId.courseId),0) as invcnt from WishlistRegistrationModel a "+
			"where a.wlRegPKId.semesterSubId=?1 and a.wlRegPKId.classGroupId in (?2) and a.wlRegPKId.registerNumber=?3 "+
			"and (a.wlRegPKId.courseId like 'INV%')")
	Integer findRegisterNumbeICCCount(String semesterSubId, String[] classGroupId, String registerNumber);
	
	@Query(value="select nvl(sum(credit),0) as totalCredit from ("+
			"(select b.LECTURE_CREDITS as credit from academics.WISHLIST_REGISTRATION a, "+ 
			"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
			"a.CLSSGRP_MASTER_CLASS_GROUP_ID in (?2) and a.STDNTSLGNDTLS_REGISTER_NUMBER=?3 and "+
			"a.CRSTYPCMPNTMASTER_COURSE_TYPE='ETH' and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) "+
			"union all "+
			"(select b.PRACTICAL_CREDITS as credit from academics.WISHLIST_REGISTRATION a, "+ 
			"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
			"a.CLSSGRP_MASTER_CLASS_GROUP_ID in (?2) and a.STDNTSLGNDTLS_REGISTER_NUMBER=?3 and "+
			"a.CRSTYPCMPNTMASTER_COURSE_TYPE='ELA' and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) "+
			"union all "+
			"(select b.PROJECT_CREDITS as credit from academics.WISHLIST_REGISTRATION a, "+ 
			"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
			"a.CLSSGRP_MASTER_CLASS_GROUP_ID in (?2) and a.STDNTSLGNDTLS_REGISTER_NUMBER=?3 and "+
			"a.CRSTYPCMPNTMASTER_COURSE_TYPE='EPJ' and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID) "+
			"union all "+
			"(select b.CREDITS as credit from academics.WISHLIST_REGISTRATION a, "+ 
			"academics.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+ 
			"a.CLSSGRP_MASTER_CLASS_GROUP_ID in (?2) and a.STDNTSLGNDTLS_REGISTER_NUMBER=?3 and "+
			"a.CRSTYPCMPNTMASTER_COURSE_TYPE not in ('ETH','ELA','EPJ') and "+
			"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID))", nativeQuery=true)
	Integer findRegisterNumberTotalCredits(String semesterSubId, String[] classGroupId, String registerNumber);
	
	@Query("select distinct a.wlRegPKId.courseId from WishlistRegistrationModel a where "+
			"a.wlRegPKId.semesterSubId=?1 and a.wlRegPKId.classGroupId in (?2) and "+
			"a.wlRegPKId.registerNumber=?3")
	List<String> findRegisteredCourse(String semesterSubId, String[] classGroupId, String registerNumber);
	
	
	@Modifying
	@Query(value="insert into academics.WISHLIST_REGISTRATION_DELETE (select a.SEMSTR_DETAILS_SEMESTER_SUB_ID, "+
			"a.CLSSGRP_MASTER_CLASS_GROUP_ID, a.STDNTSLGNDTLS_REGISTER_NUMBER, a.COURSE_CATALOG_COURSE_ID, "+
			"a.CRSTYPCMPNTMASTER_COURSE_TYPE, a.COURSE_OPTION_MASTER_CODE, a.REGISTRATION_COMPONENT_TYPE, "+
			"a.LOG_USERID, a.LOG_TIMESTAMP, a.LOG_IPADDRESS, ?3, SYSTIMESTAMP, ?5 from "+
			"academics.WISHLIST_REGISTRATION a where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
			"a.CLSSGRP_MASTER_CLASS_GROUP_ID in (?2) and a.STDNTSLGNDTLS_REGISTER_NUMBER=?3 and "+
			"a.COURSE_CATALOG_COURSE_ID=?4)", nativeQuery=true)
	void insertWishlistToDelete(String semesterSubId, String[] classGroupId, String registerNumber, 
			String courseId, String logIpAddress);
	
	@Modifying
	@Query("delete from WishlistRegistrationModel a where a.wlRegPKId.semesterSubId=?1 and "+
			"a.wlRegPKId.classGroupId in (?2) and a.wlRegPKId.registerNumber=?3 and a.wlRegPKId.courseId=?4")
	void deleteByRegisterNumberCourseId(String semesterSubId, String[] classGroupId, 
			String registerNumber, String courseId);
	
	
	@Query(value="select distinct b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.WISHLIST_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.CLSSGRP_MASTER_CLASS_GROUP_ID in (?2) and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?3 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"b.CODE=?4", nativeQuery=true)
	List<Object[]> findWLRegistrationByClassGroupAndCourseCode(String semesterSubId, String[] classGroupId, 
						String registerNumber, String courseCode);
	
	@Query(value="select distinct b.CODE as course_code, b.TITLE as course_title, b.GENERIC_COURSE_TYPE from "+
					"ACADEMICS.WISHLIST_REGISTRATION a, ACADEMICS.COURSE_CATALOG b where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.CLSSGRP_MASTER_CLASS_GROUP_ID in (?2) and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?3 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"(b.CODE in (select EQUIVALENT_COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where "+
					"COURSE_CODE=?4) or b.CODE in (select COURSE_CODE from ACADEMICS.COURSE_EQUIVALANCES where "+
					"EQUIVALENT_COURSE_CODE=?4))", nativeQuery=true)
	List<Object[]> findWLCERegistrationByClassGroupAndCourseCode(String semesterSubId, String[] classGroupId, 
						String registerNumber, String courseCode);
	
	@Query("select nvl(count(distinct a.wlRegPKId.courseId),0) as rgrcnt from WishlistRegistrationModel a "+
			"where a.wlRegPKId.semesterSubId=?1 and a.wlRegPKId.classGroupId in (?2) and "+
			"a.wlRegPKId.registerNumber=?3 and (a.courseOptionCode in ('RGR','RGA','AUD','RGP','DM','HON',"+
			"'MIN','RGCE','RPCE','RGW','RWCE','RPEUE') or (a.courseOptionCode like 'HON%') or "+
			"(a.courseOptionCode like 'MIN%'))")
	Integer findRegisterNumberRCCount2(String semesterSubId, String[] classGroupId, String registerNumber);*/
		
	/*@Query("select nvl(count(distinct a.wlRegPKId.courseId),0) as regcnt from WishlistRegistrationModel a "+
			"where a.wlRegPKId.semesterSubId=?1 and a.wlRegPKId.classGroupId in (?2) and "+
			"a.wlRegPKId.registerNumber in (?3)")
	Integer findCourseCountByRegisterNumber(String semesterSubId, String[] classGroupId, List<String> registerNumber);
	
	@Query("select nvl(count(distinct a.wlRegPKId.courseId),0) as rgrcnt from WishlistRegistrationModel a "+
			"where a.wlRegPKId.semesterSubId=?1 and a.wlRegPKId.classGroupId in (?2) and a.wlRegPKId.registerNumber=?3 "+
			"and a.courseOptionCode in ('GI','GICE')")
	Integer findRegisterNumberGICount(String semesterSubId, String[] classGroupId, String registerNumber);
	
	@Query("select nvl(count(distinct a.wlRegPKId.courseId),0) as rgrcnt from WishlistRegistrationModel a "+
			"where a.wlRegPKId.semesterSubId=?1 and a.wlRegPKId.classGroupId in (?2) and "+
			"a.wlRegPKId.registerNumber=?3 and a.courseCatalogModel.code in (?4)")
	Integer findTCCountByRegisterNumberAndCourseCode(String semesterSubId, String[] classGroupId, 
				String registerNumber, List<String> courseCode);
	
	@Query("select nvl(count(distinct a.wlRegPKId.courseId),0) as rgrcnt from WishlistRegistrationModel a "+
			"where a.wlRegPKId.semesterSubId=?1 and a.wlRegPKId.classGroupId in (?2) and a.wlRegPKId.registerNumber=?3 "+
			"and a.courseOptionCode in ('AUD')")
	Integer findAuditCountByRegisterNumber(String semesterSubId, String[] classGroupId, String registerNumber);
	
	@Query(value="SELECT a.COURSE_CATALOG_COURSE_ID, b.CODE, b.TITLE, b.GENERIC_COURSE_TYPE, "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE, c.DESCRIPTION as COURSE_TYPE_DESCRIPTION, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',b.LECTURE_HOURS,'TH',b.LECTURE_HOURS,0) "+
					"as LECTURE_HOUR, decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',b.TUTORIAL_HOURS, "+
					"'TH',b.TUTORIAL_HOURS,0) as TUTORIAL_HOUR, decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE, "+
					"'ELA',b.PRACTICAL_HOURS,'LO',b.PRACTICAL_HOURS,0) as PRACTICAL_HOUR, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'EPJ',b.PROJECT_HOURS,0) as PROJECT_HOUR, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',b.LECTURE_CREDITS,'ELA',b.PRACTICAL_CREDITS, "+
					"'EPJ',b.PROJECT_CREDITS,b.CREDITS) as CREDIT, a.COURSE_OPTION_MASTER_CODE, "+
					"d.DESCRIPTION as COURSE_OPTION_DESCRIPTION, b.EVALUATION_TYPE, "+
					"decode(a.CRSTYPCMPNTMASTER_COURSE_TYPE,'ETH',2,'ELA',3,'EPJ',4,1) as COURSE_TYPE_ORDER "+
					"FROM ACADEMICS.WISHLIST_REGISTRATION a, ACADEMICS.COURSE_CATALOG b, "+
					"ACADEMICS.COURSE_TYPE_COMPONENT_MASTER c, ACADEMICS.COURSE_OPTION_MASTER d where "+
					"a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and a.CLSSGRP_MASTER_CLASS_GROUP_ID in (?2) and "+
					"a.STDNTSLGNDTLS_REGISTER_NUMBER=?3 and a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and "+
					"a.CRSTYPCMPNTMASTER_COURSE_TYPE=c.COURSE_TYPE and a.COURSE_OPTION_MASTER_CODE=d.CODE "+
					"order by a.COURSE_CATALOG_COURSE_ID, COURSE_TYPE_ORDER", nativeQuery=true)
	List<Object[]> findByRegisterNumberAsObject(String semesterSubId, String[] classGroupId, String registerNumber);
	
	@Query(value="select distinct a.COURSE_CATALOG_COURSE_ID from ACADEMICS.WISHLIST_REGISTRATION a, "+
					"ACADEMICS.COURSE_CATALOG b where a.SEMSTR_DETAILS_SEMESTER_SUB_ID=?1 and "+
					"a.CLSSGRP_MASTER_CLASS_GROUP_ID in (?2) and a.STDNTSLGNDTLS_REGISTER_NUMBER=?3 and "+
					"a.COURSE_CATALOG_COURSE_ID=b.COURSE_ID and (substr(b.CODE,1,3) in ('SET') or "+
					"(a.COURSE_OPTION_MASTER_CODE in ('RGR') and b.GENERIC_COURSE_TYPE in ('SS'))) "+
					"order by a.COURSE_CATALOG_COURSE_ID", nativeQuery=true)
	List<String> findBlockedCourseIdByRegisterNumberForDelete(String semesterSubId, String[] classGroupId, 
					String registerNumber);*/
}
