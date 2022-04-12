package org.vtop.CourseRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.ProjectRegistrationModel;
import org.vtop.CourseRegistration.repository.ProjectRegistrationRepository;


@Service
@Transactional(readOnly=true)
public class ProjectRegistrationService
{		
	@Autowired private ProjectRegistrationRepository projectRegistrationRepository;
	
	
	public void saveOne(ProjectRegistrationModel projectRegistrationModel)
	{
		 projectRegistrationRepository.save(projectRegistrationModel);
	}
}
