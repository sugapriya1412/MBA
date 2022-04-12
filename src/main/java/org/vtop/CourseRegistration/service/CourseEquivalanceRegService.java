package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.CourseEquivalanceRegRepository;


@Service
@Transactional(readOnly=true)
public class CourseEquivalanceRegService
{		
	@Autowired private CourseEquivalanceRegRepository courseEquivalanceRegRepository;
		
	public String getEquivCourseByRegisterNumberAndCourseId(String semesterSubId, String registerNumber, String courseId)
	{
		return courseEquivalanceRegRepository.findEquivCourseByRegisterNumberAndCourseId(semesterSubId, registerNumber, courseId);
	}
	
	public List<String> getEquivCourseByRegisterNumber(String semesterSubId, List<String> registerNumber)
	{
		return courseEquivalanceRegRepository.findEquivCourseByRegisterNumber(semesterSubId, registerNumber);
	}
	
	public List<Object[]> getByRegisterNumberAndCourseCode(String semesterSubId, List<String> registerNumber, List<String> courseOptionCode, 
								String courseCode)
	{
		return courseEquivalanceRegRepository.findByRegisterNumberAndCourseCode(semesterSubId, registerNumber, courseOptionCode, courseCode);
	}
}
