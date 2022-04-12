package org.vtop.CourseRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.WishlistRegistrationRepository;


@Service
@Transactional(readOnly=true)
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
}
