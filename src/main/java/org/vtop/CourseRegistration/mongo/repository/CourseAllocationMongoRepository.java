package org.vtop.CourseRegistration.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.CourseAllocation;


public interface CourseAllocationMongoRepository extends MongoRepository<CourseAllocation, String>
{
	@Query(value="{'semesterSubId' : ?0, 'classGroupId' : {$in : ?1}, 'classType' : {$in : ?2}, 'status' : 0}", sort="{'courseId' : 1, "
					+"'courseType' : 1, 'slot' : 1, 'associateClassId' : 1, 'classId' : 1}")
	List<CourseAllocation> findBySemesterSubIdClassGroupAndClassType(String semesterSubId, List<String> classGroupId, List<String> classType);
	
	@Query(value="{'semesterSubId' : ?0, 'classGroupId' : {$in : ?1}, 'classType' : {$in : ?2}, $or : [ {$and: [{'classOption' : 1}]}, "
					+"{$and: [{'classOption' : 2}, {'specializationBatch' : ?3}]}, {$and: [{'classOption' : 3}, {'specializationBatch' : ?4}]}, "
					+"{$and: [{'classOption' : 4}, {'specializationBatch' : ?5}]} ], 'status' : 0}", sort="{'courseId' : 1, 'courseType' : 1, "
					+"'slot' : 1, 'associateClassId' : 1, 'classId' : 1}")
	List<CourseAllocation> findBySemesterSubIdClassGroupClassTypeAndClassOption(String semesterSubId, List<String> classGroupId, 
								List<String> classType, String progGroupCode, String progSpecCode, String costCentreCode);
		
	
	@Query(value="{'semesterSubId' : ?0, 'classGroupId' : {$in : ?1}, 'classType' : {$in : ?2}, 'status' : 0}", fields="{'courseId' : 1}")
	List<CourseAllocation> findCourseIdBySemesterSubIdClassGroupAndClassType(String semesterSubId, List<String> classGroupId, List<String> classType);

	@Query(value="{'semesterSubId' : ?0, 'classGroupId' : {$in : ?1}, 'classType' : {$in : ?2}, $or : [ {$and: [{'classOption' : 1}]}, "
					+"{$and: [{'classOption' : 2}, {'specializationBatch' : ?3}]}, {$and: [{'classOption' : 3}, {'specializationBatch' : ?4}]}, "
					+"{$and: [{'classOption' : 4}, {'specializationBatch' : ?5}]} ], 'status' : 0}", fields="{'courseId' : 1}")
	List<CourseAllocation> findCourseIdBySemesterSubIdClassGroupClassTypeAndClassOption(String semesterSubId, List<String> classGroupId, 
								List<String> classType, String progGroupCode, String progSpecCode, String costCentreCode);
	
	
	@Query(value="{'semesterSubId' : ?0, 'classGroupId' : {$in : ?1}, 'classType' : {$in : ?2}, 'courseCode' : {$in : ?3}, 'status' : 0}", 
			fields="{'courseId' : 1}")
	List<CourseAllocation> findCourseIdBySemesterSubIdClassGroupClassTypeAndCourseCode(String semesterSubId, List<String> classGroupId, 
								List<String> classType, List<String> courseCode);

	@Query(value="{'semesterSubId' : ?0, 'classGroupId' : {$in : ?1}, 'classType' : {$in : ?2}, 'courseCode' : {$in : ?6}, "
					+"$or : [ {$and: [{'classOption' : 1}]}, {$and: [{'classOption' : 2}, {'specializationBatch' : ?3}]}, {$and: [{'classOption' : 3}, "
					+"{'specializationBatch' : ?4}]}, {$and: [{'classOption' : 4}, {'specializationBatch' : ?5}]} ], 'status' : 0}", 
			fields="{'courseId' : 1}")
	List<CourseAllocation> findCourseIdBySemesterSubIdClassGroupClassTypeClassOptionAndCourseCode(String semesterSubId, List<String> classGroupId, 
								List<String> classType, String progGroupCode, String progSpecCode, String costCentreCode, List<String> courseCode);
}
