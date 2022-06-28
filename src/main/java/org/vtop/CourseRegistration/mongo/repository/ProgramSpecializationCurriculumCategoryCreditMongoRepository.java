package org.vtop.CourseRegistration.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.ProgramSpecializationCurriculumCategoryCredit;


public interface ProgramSpecializationCurriculumCategoryCreditMongoRepository extends 
					MongoRepository<ProgramSpecializationCurriculumCategoryCredit, String>
{
	@Query(value="{'progSpecializationId':?0, 'admissionYear':?1}", sort="{'courseCategoryOrderNo' : 1}")
	List<ProgramSpecializationCurriculumCategoryCredit> findBySpecIdAndAdmissionYear(int specializationId, int admissionYear);
}
