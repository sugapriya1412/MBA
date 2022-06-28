package org.vtop.CourseRegistration.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.ProgramSpecializationCurriculumCredit;


public interface ProgramSpecializationCurriculumCreditMongoRepository extends MongoRepository<ProgramSpecializationCurriculumCredit, String>
{
	@Query(value="{'progSpecializationId':?0, 'admissionYear':?1}")
	ProgramSpecializationCurriculumCredit findBySpecIdAndAdmissionYear(int specializationId, int admissionYear);
}
