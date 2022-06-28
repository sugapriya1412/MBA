package org.vtop.CourseRegistration.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.CourseEquivalanceCatalog;


public interface CourseEquivalanceCatalogMongoRepository extends MongoRepository<CourseEquivalanceCatalog, String>
{	
	@Query(value="{$or : [{'courseCode' : {$in : ?0}}, {'equivalentCourseCode' : {$in : ?0}}]}")
	List<CourseEquivalanceCatalog> findByCourseCodeAndEquivalentCourseCode(List<String> courseCode);
}
