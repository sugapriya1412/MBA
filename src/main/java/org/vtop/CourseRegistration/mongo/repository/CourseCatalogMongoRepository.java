package org.vtop.CourseRegistration.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.CourseCatalog;


public interface CourseCatalogMongoRepository extends MongoRepository<CourseCatalog, String>
{		
	@Query(value="{'courseId' : ?0}")
	CourseCatalog findByCourseId(String courseId);
	
	@Query(value="{'courseSystem' : {$in : ?0}, $or : [{'programGroupId' : {$in : ?1}}, {'alternateProgramGroup' : ?2}, "
					+"{'alternateProgramGroup' : {$regex : ?4}}, {'alternateProgramGroup' : {$regex : ?5}}, {'alternateProgramGroup' : {$regex : ?6}}], "
					+"'genericCourseType' : {$nin : ?7}, 'courseCode' : {$in : ?3}, 'courseId' : {$in : ?8}}", sort="{'courseId' : 1}")
	List<CourseCatalog> findByCourseSystemGroupIdAndCourseCodeExceptCourseTypeAndEvalType(List<String> courseSystem, List<Integer> eligibleGroupId, 
							String alternateProgramGroup, List<String> courseCode, String altProgGroupStart, String altProgGroupMid, 
							String altProgGroupEnd, List<String> notGenericCourseType, List<String> courseId);
	
	@Query(value="{'courseSystem' : {$in : ?0}, $or : [{'programGroupId' : {$in : ?1}}, {'alternateProgramGroup' : ?2}, "
					+"{'alternateProgramGroup' : {$regex : ?4}}, {'alternateProgramGroup' : {$regex : ?5}}, {'alternateProgramGroup' : {$regex : ?6}}], "
					+ "'genericCourseType' : {$nin : ?7}, 'evaluationType' : {$nin : ?8}, 'courseCode' : {$nin : ?3}, 'courseId' : {$in : ?9}}", 
					sort="{'courseId' : 1}")
	List<CourseCatalog> findByCourseSystemGroupIdAndNINCourseCodeExceptCourseTypeAndEvalType(List<String> courseSystem, List<Integer> eligibleGroupId, 
							String alternateProgramGroup, List<String> courseCode, String altProgGroupStart, String altProgGroupMid, String altProgGroupEnd, 
							List<String> notGenericCourseType, List<String> notEvaluationType, List<String> courseId);
		
	@Query(value="{'courseSystem' : {$in : ?0}, $or : [{'programGroupId' : {$in : ?1}}, {'alternateProgramGroup' : ?2}, "
					+"{'alternateProgramGroup' : {$regex : ?4}}, {'alternateProgramGroup' : {$regex : ?5}}, {'alternateProgramGroup' : {$regex : ?6}}], "
					+"'genericCourseType' : {$nin : ?7}, 'courseCode' : {$in : ?3}, 'courseId' : {$in : ?8}}", sort="{'courseId' : 1}")
	List<CourseCatalog> findByCourseSystemGroupIdAndCourseCodeExceptCourseTypeForRR(List<String> courseSystem, List<Integer> eligibleGroupId, 
							String alternateProgramGroup, List<String> courseCode, String altProgGroupStart, String altProgGroupMid, String altProgGroupEnd, 
							List<String> notGenericCourseType, List<String> courseId);
	
	@Query(value="{'courseSystem' : {$in : ?0}, $or : [{'programGroupId' : {$in : ?1}}, {'alternateProgramGroup' : ?2}, "
					+"{'alternateProgramGroup' : {$regex : ?4}}, {'alternateProgramGroup' : {$regex : ?5}}, {'alternateProgramGroup' : {$regex : ?6}}], "
					+"'genericCourseType' : {$nin : ?7}, 'courseCode' : {$in : ?3}, 'courseId' : {$in : ?8}}", sort="{'courseId' : 1}")
	List<CourseCatalog> findByCourseSystemGroupIdAndCourseCodeExceptCourseTypeForFFCSToCAL(List<String> courseSystem, List<Integer> eligibleGroupId, 
							String alternateProgramGroup, List<String> courseCode, String altProgGroupStart, String altProgGroupMid, String altProgGroupEnd, 
							List<String> notGenericCourseType, List<String> courseId);
	
	@Query(value="{'courseSystem' : {$in : ?0}, $or : [{'programGroupId' : {$in : ?1}}, {'alternateProgramGroup' : ?2}, "
					+"{'alternateProgramGroup' : {$regex : ?4}}, {'alternateProgramGroup' : {$regex : ?5}}, {'alternateProgramGroup' : {$regex : ?6}}],"
					+" 'genericCourseType' : {$nin : ?7}, 'courseCode' : {$nin : ?3}, 'courseId' : {$in : ?8}}", sort="{'courseId' : 1}")
	List<CourseCatalog> findByCourseSystemGroupIdAndNINCourseCodeExceptCourseType(List<String> courseSystem, List<Integer> eligibleGroupId, 
							String alternateProgramGroup, List<String> courseCode, String altProgGroupStart, String altProgGroupMid, String altProgGroupEnd, 
							List<String> notGenericCourseType, List<String> courseId);
	
	@Query(value="{'courseSystem' : {$in : ?0}, $or : [{'programGroupId' : {$in : ?1}}, {'alternateProgramGroup' : ?2}, "
					+"{'alternateProgramGroup' : {$regex : ?5}}, {'alternateProgramGroup' : {$regex : ?6}}, {'alternateProgramGroup' : {$regex : ?7}}], "
					+"'genericCourseType' : {$nin : ?8}, 'courseCode' : {$in : ?3}, 'courseCode' : {$nin : ?4}, 'courseId' : {$in : ?9}}", 
					sort="{'courseId' : 1}")
	List<CourseCatalog> findByCourseSystemGroupIdAndINWNINCourseCodeExceptCourseType(List<String> courseSystem, List<Integer> eligibleGroupId, 
							String alternateProgramGroup, List<String> courseCode, List<String> notCourseCode, String altProgGroupStart, String altProgGroupMid, 
							String altProgGroupEnd, List<String> notGenericCourseType, List<String> courseId);
}
