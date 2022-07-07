package org.vtop.CourseRegistration.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vtop.CourseRegistration.mongo.model.StudentDetail;
import org.vtop.CourseRegistration.mongo.repository.StudentDetailMongoRepository;


@Service
public class StudentDetailMongoService 
{
	@Autowired private StudentDetailMongoRepository studentDetailMongoRepository;
	
	public StudentDetail getByRegisterNumber(String registerNumber)
	{
		return studentDetailMongoRepository.findByRegisterNumber(registerNumber); 
	}
	
	public StudentDetail getByNickName(String nickName)
	{
		return studentDetailMongoRepository.findByNickName(nickName); 
	}
	
	public void updatePasswordByRegisterNumber(String registerNumber, String password)
	{
		if ((registerNumber != null) && (!registerNumber.equals("")))
		{
			StudentDetail studentDetail = getByRegisterNumber(registerNumber);
			if (studentDetail != null)
			{
				studentDetail.setPassword(password);
				
				studentDetailMongoRepository.save(studentDetail);
			}
		}
	}
}
