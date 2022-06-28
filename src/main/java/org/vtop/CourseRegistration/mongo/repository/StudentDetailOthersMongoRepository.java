package org.vtop.CourseRegistration.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.StudentDetailOthers;


public interface StudentDetailOthersMongoRepository extends MongoRepository<StudentDetailOthers, String>
{
	@Query(value="{'registerNumber':?0}")
	StudentDetailOthers findByRegisterNumber(String registerNumber);
}
