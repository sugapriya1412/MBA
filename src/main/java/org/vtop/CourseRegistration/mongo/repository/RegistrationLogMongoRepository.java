package org.vtop.CourseRegistration.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.RegistrationLog;


public interface RegistrationLogMongoRepository extends MongoRepository<RegistrationLog, String>
{
	@Query(value="{'registerNumber':?0}")
	RegistrationLog findByRegisterNumber(String registerNumber);
}
