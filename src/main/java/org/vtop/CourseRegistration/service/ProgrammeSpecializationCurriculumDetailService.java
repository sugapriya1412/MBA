package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.ProgrammeSpecializationCurriculumDetailRepository;


@Service
@Transactional(readOnly=true)
public class ProgrammeSpecializationCurriculumDetailService
{
	@Autowired private ProgrammeSpecializationCurriculumDetailRepository programmeSpecializationCurriculumDetailRepository;
	@Autowired private CourseRegistrationService courseRegistrationService;
	
	private static final Logger logger = LogManager.getLogger(ProgrammeSpecializationCurriculumDetailService.class);
	
	
	public List<Object[]> getCurriculumByAdmsnYearCCVersionAndCourseCode(Integer specId, Integer admissionYear, 
								Float ccVersion, String courseCode)
	{
		return programmeSpecializationCurriculumDetailRepository.findCurriculumByAdmsnYearCCVersionAndCourseCode(
					specId, admissionYear, ccVersion, courseCode);
	}
	
	public List<String> getNCCourseByYearAndCCVersion(Integer specId, Integer admissionYear, Float ccVersion)
	{
		List<String> tempStringList = new ArrayList<String>();
		
		tempStringList = programmeSpecializationCurriculumDetailRepository.findNCCourseByYearAndCCVersion(specId, 
							admissionYear, ccVersion, Arrays.asList("BC","NC"));
		if (tempStringList.isEmpty())
		{
			tempStringList.add("NONE");
		}
		
		return tempStringList;
	}
	
	
	public Map<String, List<Object[]>> getCurriculumBySpecIdYearAndCCVersionAsMap(Integer specId, Integer admissionYear, 
											Float ccVersion)
	{
		Map<String, List<Object[]>> tempMapList = new HashMap<String, List<Object[]>>();
		
		String tempHashKey = "";
		List<Object[]> tempObjectList = new ArrayList<Object[]>();
		List<Object[]> tempObjectMapList = new ArrayList<Object[]>();
		
		tempObjectList = programmeSpecializationCurriculumDetailRepository.findCurriculumByAdmsnYearAndCCVersion2(specId, 
							admissionYear, ccVersion);
		if (!tempObjectList.isEmpty())
		{
			for (Object[] parameters : tempObjectList)
			{
				tempHashKey = parameters[5].toString();
				if(tempMapList.containsKey(tempHashKey))
				{
					tempObjectMapList = tempMapList.get(tempHashKey);
					tempObjectMapList.add(parameters);
					tempMapList.put(tempHashKey, tempObjectMapList);
				}
				else
				{
					tempObjectMapList = new ArrayList<Object[]>();
					tempObjectMapList.add(parameters);
					tempMapList.put(tempHashKey, tempObjectMapList);
				}
			}
		}
		
		return tempMapList;
	}
	
	public Map<String, Object[]> getStudentCurriculumByRegisterNumber(Integer specializationId, Integer admissionYear, 
										Float curriculumVersion, List<String> registerNumber)
	{
		Map<String, Object[]> returnMapList = new HashMap<String, Object[]>();
		
		String courseCode = "";
		List<String> ueCourseCode = new ArrayList<String>();
		List<Object[]> objectList = new ArrayList<Object[]>();
		
		if ((specializationId != null) && (admissionYear != null) && (curriculumVersion != null) && (!registerNumber.isEmpty()))
		{	
			ueCourseCode = courseRegistrationService.getUECourseByRegisterNumber(registerNumber);
			if (ueCourseCode.isEmpty())
			{
				ueCourseCode.add("NONE");
			}
			logger.trace("\n ueCourseCode: "+ ueCourseCode.toString());
			
			objectList = programmeSpecializationCurriculumDetailRepository.findStudentCurriculumByRegisterNumberUECourseAndPECourseOption(
								specializationId, admissionYear, curriculumVersion, registerNumber, ueCourseCode, Arrays.asList("RUEPE","CSUPE"));
			logger.trace("\n Curriculum Detail Data: "+ objectList.size());
			if (!objectList.isEmpty())
			{
				for (Object[] e : objectList)
				{
					logger.trace("\n "+ Arrays.deepToString(e));
					courseCode = e[4].toString();
					if(!returnMapList.containsKey(courseCode))
					{
						returnMapList.put(courseCode, e);
					}
				}
			}
		}
		
		return returnMapList;
	}
}
