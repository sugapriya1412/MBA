package org.vtop.CourseRegistration.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.CourseEligible;

public interface CourseEligibleMongoRepository extends MongoRepository<CourseEligible, String>
{
	@Query(value="{'progGroupId':?0}")
	CourseEligible findByProgGroupId(int progGroupId);
}
