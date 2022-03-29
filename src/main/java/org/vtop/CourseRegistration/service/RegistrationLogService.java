package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.RegistrationLogRepository;


@Service
@Transactional("academicsTransactionManager")
public class RegistrationLogService
{
	@Autowired private RegistrationLogRepository registrationLogRepository;
	
	
	public boolean isExist(String registerNumber)
	{
		return registrationLogRepository.existsById(registerNumber);
	}
	
	public void UpdateLogoutTimeStamp(String ipAddress, String registerNumber)
	{
		registrationLogRepository.UpdateLogoutTimeStamp(ipAddress, registerNumber);
	}
		
	public void addErrorLog(String exceptionMessage, String packageName, String programName, 
			String userId, String ipAddress)
	{
		if (exceptionMessage.length() >= 1000)
		{
			exceptionMessage = exceptionMessage.substring(1, 999);
		}
	
		if (packageName.length() >= 100)
		{
			packageName = packageName.substring(1, 99);
		}
	
		if (programName.length() >= 250)
		{
			programName = programName.substring(1, 249);
		}
	
		registrationLogRepository.InsertErrorLog(exceptionMessage, "ACADEMICS", packageName, 
			programName, userId, ipAddress);
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
	
	public void AddRegistrationLog(String registerNumber, String ipAddress)
	{
		registrationLogRepository.AddRegistrationLog(registerNumber, ipAddress);
	}
	
	public void UpdateActiveTimeStamp(String registerNumber)
	{
		registrationLogRepository.UpdateActiveTimeStamp(registerNumber);
	}
	
	public void UpdateLoginTimeStamp2(String ipAddress, String registerNumber)
	{
		registrationLogRepository.UpdateLoginTimeStamp2(ipAddress, registerNumber);
	}
	
	public void UpdateLogoutTimeStamp2(String ipAddress, String registerNumber)
	{
		registrationLogRepository.UpdateLogoutTimeStamp2(ipAddress, registerNumber);
	}
	
	public List<Object[]> getRegistrationLogTimeDifference(String registerNumber)
	{
		return registrationLogRepository.findRegistrationLogTimeDifference(registerNumber);
	}
	
	public List<Object[]> getRegistrationLogByRegisterNumber(String registerNumber)
	{
		return registrationLogRepository.findRegistrationLogByRegisterNumber(registerNumber);
	}
	
	/*public void saveOne(RegistrationLogModel registrationLogModel){
		registrationLogRepository.save(registrationLogModel);
	}
	
	public void UpdateLoginTimeStamp(String ipAddress, String registerNumber){
		registrationLogRepository.UpdateLoginTimeStamp(ipAddress, registerNumber);
	}*/
	
	/*public RegistrationLogModel getOne(String registerNumber){
		return registrationLogRepository.findOne(registerNumber);
	}*/
}
