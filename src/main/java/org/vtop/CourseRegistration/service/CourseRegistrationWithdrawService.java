package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.CourseRegistrationWithdrawRepository;


@Service
@Transactional(readOnly=true)
public class CourseRegistrationWithdrawService
{	
	@Autowired private CourseRegistrationWithdrawRepository courseRegistrationWithdrawRepository;

		
	public List<Object[]> getByRegisterNumberAndCourseCode2(List<String> registerNumber, String courseCode)
	{
		return courseRegistrationWithdrawRepository.findByRegisterNumberAndCourseCode2(registerNumber, courseCode);
	}
}
