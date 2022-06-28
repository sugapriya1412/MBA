package org.vtop.CourseRegistration.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.SemesterDetail;


public interface SemesterDetailMongoRepository extends MongoRepository<SemesterDetail, String>
{
	@Query(value="{'semesterSubId':?0}")
	SemesterDetail findBySemesterSubId(String semesterSubId);
}
