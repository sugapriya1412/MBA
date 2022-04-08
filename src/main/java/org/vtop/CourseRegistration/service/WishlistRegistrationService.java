package org.vtop.CourseRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.WishlistRegistrationRepository;


@Service
@Transactional("transactionManager")
public class WishlistRegistrationService
{	
	@Autowired private WishlistRegistrationRepository wishlistRegistrationRepository;
		
	public Integer getRegisterNumberTCCount2(String semesterSubId, String[] classGroupId, String registerNumber)
	{
		Integer tempCount = 0;
			
		tempCount = wishlistRegistrationRepository.findRegisterNumberTCCount2(semesterSubId, classGroupId, registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
	
	
	/*public List<Object[]> getWLRegistrationByClassGroupAndCourseCode(String semesterSubId, String[] classGroupId, 
								String registerNumber, String courseCode)
	{
		return wishlistRegistrationRepository.findWLRegistrationByClassGroupAndCourseCode(semesterSubId, classGroupId, 
					registerNumber, courseCode);
	}
	
	public List<Object[]> getWLCERegistrationByClassGroupAndCourseCode(String semesterSubId, String[] classGroupId, 
								String registerNumber, String courseCode)
	{
		return wishlistRegistrationRepository.findWLCERegistrationByClassGroupAndCourseCode(semesterSubId, classGroupId, 
					registerNumber, courseCode);
	}
	
	public Integer getRegisterNumberTotalCredits(String semesterSubId, String[] classGroupId, String registerNumber)
	{
		Integer tempCount = 0;
			
		tempCount = wishlistRegistrationRepository.findRegisterNumberTotalCredits(semesterSubId, classGroupId, registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}*/
	
	/*public Integer getCourseCountByRegisterNumber(String semesterSubId, String[] classGroupId, List<String> registerNumber)
	{
		Integer tempCount = 0;
			
		tempCount = wishlistRegistrationRepository.findCourseCountByRegisterNumber(semesterSubId, classGroupId, registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
	
	public Integer getRegisterNumberRCCount2(String semesterSubId, String[] classGroupId, String registerNumber)
	{
		Integer tempCount = 0;
			
		tempCount = wishlistRegistrationRepository.findRegisterNumberRCCount2(semesterSubId, classGroupId, registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
	
	public Integer getRegisterNumberGICount(String semesterSubId, String[] classGroupId, String registerNumber)
	{
		Integer tempCount = 0;
			
		tempCount = wishlistRegistrationRepository.findRegisterNumberGICount(semesterSubId, classGroupId, registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
	
	public Integer getTCCountByRegisterNumberAndCourseCode(String semesterSubId, String[] classGroupId, String registerNumber,
						List<String> courseCode)
	{
		Integer tempCount = 0;
			
		tempCount = wishlistRegistrationRepository.findTCCountByRegisterNumberAndCourseCode(semesterSubId, classGroupId, 
						registerNumber, courseCode);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
	
	public Integer getAuditCountByRegisterNumber(String semesterSubId, String[] classGroupId, String registerNumber)
	{
		Integer tempCount = 0;
			
		tempCount = wishlistRegistrationRepository.findAuditCountByRegisterNumber(semesterSubId, classGroupId, 
						registerNumber);
		if (tempCount == null)
		{
			tempCount = 0;
		}
		
		return tempCount;
	}
	
	public List<String> getBlockedCourseIdByRegisterNumberForDelete(String semesterSubId, String[] classGroupId, 
							String registerNumber)
	{
		List<String> tempCourseIdList = new ArrayList<String>();
		
		tempCourseIdList = wishlistRegistrationRepository.findBlockedCourseIdByRegisterNumberForDelete(semesterSubId, 
								classGroupId, registerNumber);
		if (tempCourseIdList.isEmpty())
		{
			tempCourseIdList.add("NONE");
		}
				
		return tempCourseIdList;
	}*/
}
