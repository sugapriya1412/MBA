package org.vtop.CourseRegistration.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.StudentDetail;

public interface StudentDetailMongoRepository extends MongoRepository<StudentDetail, String>
{
	@Query(value="{'registerNumber':?0}")
	StudentDetail findByRegisterNumber(String registerNumber); 
	
	@Query(value="{'nickName':?0}")
	StudentDetail findByNickName(String nickName);
}
