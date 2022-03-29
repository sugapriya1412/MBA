package org.vtop.CourseRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.CourseAllocationModel;


@Repository
public interface CourseAllocationRepository extends JpaRepository<CourseAllocationModel, String>
{	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType in (?5) and a.lockStatus=0 "+
			"order by a.timeTableModel.slotName, a.assoClassId, a.classId")
	List<CourseAllocationModel> findByCourseIdAndCourseType(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseId, List<String> courseType);
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType in (?5) and (a.classOption=1 or "+
			"(a.classOption=2 and a.specializationBatch=?6) or (a.classOption=3 and a.specializationBatch=?7) or "+
			"(a.classOption=4 and a.specializationBatch=?8)) and a.lockStatus=0 order by a.timeTableModel.slotName, "+
			"a.assoClassId, a.classId")
	List<CourseAllocationModel> findByCourseIdCourseTypeAndClassOption(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseId, List<String> courseType, String progGroupCode, 
									String progSpecCode, String costCentreCode);
	
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType=?5 and a.lockStatus=0 "+
			"order by a.timeTableModel.slotName, a.assoClassId, a.classId")
	List<CourseAllocationModel> findByCourseIdAndCourseType2(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseId, String courseType);
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType=?5 and (a.classOption=1 or "+
			"(a.classOption=2 and a.specializationBatch=?6) or (a.classOption=3 and a.specializationBatch=?7) or "+ 
			"(a.classOption=4 and a.specializationBatch=?8)) and a.lockStatus=0 order by a.timeTableModel.slotName, "+
			"a.assoClassId, a.classId")
	List<CourseAllocationModel> findByCourseIdCourseTypeAndClassOption2(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseId, String courseType, String progGroupCode, 
									String progSpecCode, String costCentreCode);
	
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType=?5 and a.erpId=?6 and a.lockStatus=0 "+
			"order by a.timeTableModel.slotName, a.assoClassId, a.classId")
	List<CourseAllocationModel> findByCourseIdCourseTypeAndEmpId(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseId, String courseType, String erpId);

	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType=?5 and a.erpId=?6 and (a.classOption=1 or "+ 
			"(a.classOption=2 and a.specializationBatch=?7) or (a.classOption=3 and a.specializationBatch=?8) or "+ 
			"(a.classOption=4 and a.specializationBatch=?9)) and a.lockStatus=0 order by a.timeTableModel.slotName, "+
			"a.assoClassId, a.classId")
	List<CourseAllocationModel> findByCourseIdCourseTypeEmpIdAndClassOption(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseId, String courseType, String erpId, 
									String progGroupCode, String progSpecCode, String costCentreCode);
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType=?5 and a.erpId=?6 and a.slotId=?7 and "+
			"a.assoClassId=?8 and a.lockStatus=0 order by a.classId")
	CourseAllocationModel findByCourseIdCourseTypeEmpIdSlotIdAndAssoClassId(String semesterSubId, String[] classGroupId, 
								String[] classType, String courseId, String courseType, String erpId, Long slotId, 
								String assoClassId);
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType=?5 and a.erpId=?6 and a.slotId=?7 and "+
			"a.assoClassId=?8 and (a.classOption=1 or (a.classOption=2 and a.specializationBatch=?9) or "+
			"(a.classOption=3 and a.specializationBatch=?10) or (a.classOption=4 and a.specializationBatch=?11)) "+
			"and a.lockStatus=0 order by a.classId")
	CourseAllocationModel findByCourseIdCourseTypeEmpIdSlotIdAssoClassIdAndClassOption(String semesterSubId, 
								String[] classGroupId, String[] classType, String courseId, String courseType, 
								String erpId, Long slotId, String assoClassId, String progGroupCode, 
								String progSpecCode, String costCentreCode);
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseCatalogModel.code=?4 and a.courseCatalogModel.courseSystem in (?5) "+
			"and a.registeredSeats<a.totalSeats and a.lockStatus=0 order by a.timeTableModel.slotName, "+
			"a.assoClassId, a.classId")
	List<CourseAllocationModel> findAvailableClassByCourseCode(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseCode, String[] courseSystem);
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseCatalogModel.code=?4 and a.courseCatalogModel.courseSystem in (?5) "+
			"and (a.classOption=1 or (a.classOption=2 and a.specializationBatch=?6) or (a.classOption=3 and "+
			"a.specializationBatch=?7) or (a.classOption=4 and a.specializationBatch=?8)) and a.registeredSeats<a.totalSeats "+
			"and a.lockStatus=0 order by a.timeTableModel.slotName, a.assoClassId, a.classId")
	List<CourseAllocationModel> findAvailableClassByCourseCodeAndClassOption(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseCode, String[] courseSystem, String progGroupCode, 
									String progSpecCode, String costCentreCode);
	
	
	@Query("select (a.totalSeats-a.registeredSeats) as avbseats from CourseAllocationModel a where a.classId=?1")
	Integer findAvailableRegisteredSeats(String classId);
	
	@Query("select (10-a.waitingSeats) as avbseats from CourseAllocationModel a where a.classId=?1")
	Integer findAvailableWaitingSeats(String classId);
	
	
	/*@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.lockStatus=0 order by a.courseId, a.timeTableModel.slotName, "+
			"a.assoClassId, a.classId")
	List<CourseAllocationModel> findByClassGroupAndClassType(String semesterSubId, String[] classGroupId, 
									String[] classType);
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and ((a.classOption=1 and a.specializationBatch is null) or "+
			"(a.classOption=2 and a.specializationBatch=?4) or (a.classOption=3 and a.specializationBatch=?5) "+ 
			"or (a.classOption=4 and a.specializationBatch=?6)) and a.lockStatus=0 "+
			"order by a.courseId, a.timeTableModel.slotName, a.assoClassId, a.classId")
	List<CourseAllocationModel> findByClassGroupClassTypeAndClassOption(String semesterSubId, String[] classGroupId, 
									String[] classType, String progGroupCode, String progSpecCode, 
									String costCentreCode);
	
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.lockStatus=0 order by a.timeTableModel.slotName, "+
			"a.assoClassId, a.classId")
	List<CourseAllocationModel> findByCourseId(String semesterSubId, String[] classGroupId, String[] classType, 
									String courseId);
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and ((a.classOption=1 and a.specializationBatch is null) "+
			"or (a.classOption=2 and a.specializationBatch=?5) or (a.classOption=3 and a.specializationBatch=?6) "+ 
			"or (a.classOption=4 and a.specializationBatch=?7)) and a.lockStatus=0 "+
			"order by a.timeTableModel.slotName, a.assoClassId, a.classId")
	List<CourseAllocationModel> findByCourseIdAndClassOption(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseId, String progGroupCode, String progSpecCode, 
									String costCentreCode);*/
		
	/*@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType=?5 and a.lockStatus=0 "+
			"and a.assoClassId in (select b.classId from CourseAllocationModel b where b.semesterSubId=?1 and "+
			"b.clsGrpMasterGroupId in (?2) and b.classType in (?3) and b.courseId=?4 and b.courseType=?5 and "+
			"b.erpId=?6 and b.lockStatus=0) order by a.classId")
	List<CourseAllocationModel> findAssociateClassByCourseIdCourseTypeAndEmpId(String semesterSubId, 
									String[] classGroupId, String[] classType, String courseId, String courseType, 
									String erpId);
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType=?5 and ((a.classOption=1 and "+
			"a.specializationBatch is null) or (a.classOption=2 and a.specializationBatch=?7) or (a.classOption=3 "+
			"and a.specializationBatch=?8) or (a.classOption=4 and a.specializationBatch=?9)) and a.lockStatus=0 "+
			"and a.assoClassId in (select b.classId from CourseAllocationModel b where b.semesterSubId=?1 and "+
			"b.clsGrpMasterGroupId in (?2) and b.classType in (?3) and b.courseId=?4 and b.courseType=?5 and "+
			"b.erpId=?6 and ((b.classOption=1 and b.specializationBatch is null) or (b.classOption=2 and "+
			"b.specializationBatch=?7) or (b.classOption=3 and b.specializationBatch=?8) or (b.classOption=4 and "+
			"b.specializationBatch=?9)) and b.lockStatus=0) order by a.timeTableModel.slotName, a.assoClassId, a.classId")
	List<CourseAllocationModel> findAssociateClassByCourseIdCourseTypeEmpIdAndClassOption(String semesterSubId, 
									String[] classGroupId, String[] classType, String courseId, String courseType, 
									String erpId, String progGroupCode, String progSpecCode, String costCentreCode);
	
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType=?5 and a.erpId=?6 and a.slotId=?7 and "+
			"a.lockStatus=0 order by a.classId")
	CourseAllocationModel findByCourseIdCourseTypeEmpIdAndSlotId(String semesterSubId, String[] classGroupId, 
								String[] classType, String courseId, String courseType, String erpId, Long slotId);

	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.courseType=?5 and a.erpId=?6 and a.slotId=?7 and "+
			"((a.classOption=1 and a.specializationBatch is null) or (a.classOption=2 and a.specializationBatch=?8) "+
			"or (a.classOption=3 and a.specializationBatch=?9) or (a.classOption=4 and a.specializationBatch=?10)) "+
			"and a.lockStatus=0 order by a.classId")
	CourseAllocationModel findByCourseIdCourseTypeEmpIdSlotIdAndClassOption(String semesterSubId, String[] classGroupId, 
								String[] classType, String courseId, String courseType, String erpId, Long slotId, 
								String progGroupCode, String progSpecCode, String costCentreCode);*/
		
	/*@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and a.registeredSeats<a.totalSeats and a.lockStatus=0 "+
			"order by a.timeTableModel.slotName, a.assoClassId, a.classId")
	List<CourseAllocationModel> findAvailableClassByCourseId(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseId);
	
	@Query("select a from CourseAllocationModel a where a.semesterSubId=?1 and a.clsGrpMasterGroupId in (?2) "+
			"and a.classType in (?3) and a.courseId=?4 and ((a.classOption=1 and a.specializationBatch is null) "+
			"or (a.classOption=2 and a.specializationBatch=?5) or (a.classOption=3 and a.specializationBatch=?6) "+
			"or (a.classOption=4 and a.specializationBatch=?7)) and a.registeredSeats<a.totalSeats and "+
			"a.lockStatus=0 order by a.timeTableModel.slotName, a.assoClassId, a.classId")
	List<CourseAllocationModel> findAvailableClassByCourseIdAndClassOption(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseId, String progGroupCode, String progSpecCode, 
									String costCentreCode);*/
		
	/*@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select (a.totalSeats-a.registeredSeats) as avbseats from CourseAllocationModel a where a.classId=?1")
	Integer findAvailableRegisteredSeatsWithLock(String classId);
		
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select (10-a.waitingSeats) as avbseats from CourseAllocationModel a where a.classId=?1")
	Integer findAvailableWaitingSeatsWithLock(String classId);
	
	@Modifying
	@Query("update CourseAllocationModel a set a.registeredSeats=registeredSeats+1 where a.classId=?1")
	void increaseRegisteredSeats(String classId);
	
	@Modifying
	@Query("update CourseAllocationModel a set a.registeredSeats=registeredSeats-1 where a.classId=?1")
	void decreaseRegisteredSeats(String classId);*/
}
