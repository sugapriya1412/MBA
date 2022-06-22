package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.RegistrationLogRepository;


@Service
@Transactional(readOnly=true)
public class RegistrationLogService
{
	@Autowired private RegistrationLogRepository registrationLogRepository;
	
	
	public boolean isExist(String registerNumber)
	{
		return registrationLogRepository.existsById(registerNumber);
	}
	
	public Integer getRegistrationExemptionReasonTypeBySemesterSubIdAndRegisterNumber(String semesterSubId, 
						String registerNumber)
	{
		Integer tempReasonType = 0;
		
		tempReasonType = registrationLogRepository.findRegistrationExemptionReasonTypeBySemesterSubIdAndRegisterNumber(
								semesterSubId, registerNumber);
		if (tempReasonType == null)
		{
			tempReasonType = 0;
		}
		
		return tempReasonType;
	}
	
	public List<Object[]> getRegistrationLogTimeDifference(String registerNumber)
	{
		return registrationLogRepository.findRegistrationLogTimeDifference(registerNumber);
	}
	
	public List<Object[]> getRegistrationLogByRegisterNumber(String registerNumber)
	{
		return registrationLogRepository.findRegistrationLogByRegisterNumber(registerNumber);
	}
}
