package org.vtop.CourseRegistration.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.ResearchStudentCourseDetail;


public interface ResearchStudentCourseDetailMongoRepository extends MongoRepository<ResearchStudentCourseDetail, String>
{
	@Query(value="{'registerNumber' : ?0}")
	List<ResearchStudentCourseDetail> findByRegisterNumber(String registerNumber);
}
