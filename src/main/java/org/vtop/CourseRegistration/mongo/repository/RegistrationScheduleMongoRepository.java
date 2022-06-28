package org.vtop.CourseRegistration.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.RegistrationSchedule;


public interface RegistrationScheduleMongoRepository extends MongoRepository<RegistrationSchedule, String>
{
	@Query(value="{'registerNumber':?0}")
	RegistrationSchedule findByRegisterNumber(String registerNumber);
}
