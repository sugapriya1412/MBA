package org.vtop.CourseRegistration.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.vtop.CourseRegistration.mongo.model.ProgramSpecializationCurriculumDetail;


public interface ProgramSpecializationCurriculumDetailMongoRepository extends 
						MongoRepository<ProgramSpecializationCurriculumDetail, String>
{
	@Query(value="{'progSpecializationId' : ?0, 'admissionYear' : ?1, 'courseCode' : ?2}")
	ProgramSpecializationCurriculumDetail findBySpecIdAdmissionYearAndCourseCode(int specializationId, int admissionYear, 
												String courseCode);
	
	@Query(value="{'progSpecializationId' : ?0, 'admissionYear' : ?1}")
	List<ProgramSpecializationCurriculumDetail> findBySpecIdAndAdmissionYear(int specializationId, int admissionYear);
	
	@Query(value="{'progSpecializationId' : ?0, 'admissionYear' : ?1, 'courseCategory' : ?2}")
	List<ProgramSpecializationCurriculumDetail> findBySpecIdAdmissionYearAndCourseCategory(int specializationId, 
													int admissionYear, String courseCategory);
	
	@Query(value="{'progSpecializationId' : ?0, 'admissionYear' : ?1}", fields="{ 'courseCode' : 1}")
	List<ProgramSpecializationCurriculumDetail> findCourseCodeBySpecIdAndAdmissionYear(int specializationId, int admissionYear);
	
	@Query(value="{'progSpecializationId' : ?0, 'admissionYear' : ?1, 'courseCategory' : ?2}", fields="{ 'courseCode' : 1}")
	List<ProgramSpecializationCurriculumDetail> findCourseCodeBySpecIdAdmissionYearAndCourseCategory(int specializationId, 
													int admissionYear, String courseCategory);
}
