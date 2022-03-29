package org.vtop.CourseRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.ProjectRegistrationModel;
import org.vtop.CourseRegistration.repository.ProjectRegistrationRepository;


@Service
@Transactional("academicsTransactionManager")
public class ProjectRegistrationService
{		
	@Autowired private ProjectRegistrationRepository projectRegistrationRepository;
	
	
	public void saveOne(ProjectRegistrationModel projectRegistrationModel)
	{
		 projectRegistrationRepository.save(projectRegistrationModel);
	}
		
	public void deleteByRegisterNumberCourseId(String semesterSubId, String registerNumber, String courseId)
	{		
		projectRegistrationRepository.deleteByRegisterNumberCourseId(semesterSubId, registerNumber, courseId);
	}
	
	/*public ProjectRegistrationModel getOne(ProjectRegistrationPKModel projectRegistrationPKModel)
	{
		return projectRegistrationRepository.findOne(projectRegistrationPKModel);
	}*/
	
	/*public List<ProjectRegistrationModel> getAll(String semesterSubId)
	{
		return projectRegistrationRepository.findBySemesterSubId(semesterSubId);
	}
		
	public List<ProjectRegistrationModel> getByRegisterNumber(String semesterSubId, String registerNumber)
	{
		return projectRegistrationRepository.findByRegisterNumber(semesterSubId, registerNumber);
	}
	
	public List<ProjectRegistrationModel> getByRegisterNumberCourseId(String semesterSubId, 
			String registerNumber, String courseId)
	{
		return projectRegistrationRepository.findByRegisterNumberCourseId(semesterSubId, 
				registerNumber, courseId);
	}*/
}
