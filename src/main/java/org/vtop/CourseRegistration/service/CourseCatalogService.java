package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseCatalogModel;
import org.vtop.CourseRegistration.repository.CourseCatalogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vtop.CourseRegistration.repository.CompulsoryCourseConditionDetailRepository;



@Service
@Transactional(readOnly=true)
public class CourseCatalogService
{
	@Autowired private CourseCatalogRepository courseCatalogRepository;
	@Autowired private StudentHistoryService studentHistoryService;
	@Autowired private CompulsoryCourseConditionDetailRepository compulsoryCourseConditionDetailRepository;
	private static final Logger logger = LogManager.getLogger(CourseCatalogService.class);
		
		
	public CourseCatalogModel getOne(String courseId)
	{
		return courseCatalogRepository.findById(courseId).orElse(null);
	}
	
	public CourseCatalogModel getOfferedCourseDetailByCourseCode(String semesterSubId, String[] classGroupId, 
									String[] classType, String courseCode)
	{
		return courseCatalogRepository.findOfferedCourseDetailByCourseCode(semesterSubId, classGroupId, 
					classType, courseCode);
	}	
			
	//To get Course Owner's List
	public List<Object[]> getCourseCostCentre (String campus)
	{
		return courseCatalogRepository.findCourseCostCentre(campus);
	}
	
	
	//Compulsory Course Pagination
	public Page<CourseCatalogModel> getCompulsoryCoursePagination(String campusCode, String[] courseSystem, 
										List<Integer> egbGroupId, String groupCode, String semesterSubId, 
										String[] classGroupId, String[] classType, List<String> courseCode, 
										String progGroupCode, String progSpecCode, String costCentreCode, 
										Pageable pageable)
	{
		if (progGroupCode.equals("RP"))
		{
			return courseCatalogRepository.findCompulsoryCourseAsPage(campusCode, courseSystem, egbGroupId, 
						groupCode, semesterSubId, classGroupId, classType, courseCode, pageable);
		}
		else
		{
			return courseCatalogRepository.findCompulsoryCourseByClassOptionAsPage(campusCode, courseSystem, 
						egbGroupId, groupCode, semesterSubId, classGroupId, classType, courseCode, progGroupCode, 
						progSpecCode, costCentreCode, pageable);
		}
	}
	
	
	public List<CourseCatalogModel> getCourseListForRegistration(String registrationOption, String campusCode, 
										String[] courseSystem, List<Integer> egbGroupId, Integer programGroupId, 
										String semesterSubId, Integer programSpecId, String[] classGroupId, 
										String[] classType, Integer admissionYear, Float curriculumVersion, 
										String registerNumber, int searchType, String searchValue, 
										Integer studentGraduateYear, String programGroupCode, 
										String programSpecCode, String registrationMethod, String[] registerNumber2, 
										int PEUEAllowStatus, int evalPage, int evalPageSize, String costCentreCode, 
										List<String> compulsoryCourseList)
	{
		List<CourseCatalogModel> tempList = new ArrayList<CourseCatalogModel>();
		
		
		int dataListFlag = 2;
		String programGroup = "";
		List<String> courseCode = new ArrayList<String>();
		List<String> naGenericCourseType = new ArrayList<String>();
		
		//Not allowed Generic Course Type except Compulsory Course
		naGenericCourseType = Arrays.asList("SS", "ECA");
		
		if ((registrationOption != null) && (!registrationOption.equals("")))
		{
			if (registrationOption.equals("PE") || registrationOption.equals("UE") 
					|| registrationOption.equals("BC") || registrationOption.equals("NC"))
			{
				if (PEUEAllowStatus == 1)
				{
					dataListFlag = 1;
				}
			}
			else
			{
				dataListFlag = 1;
			}
		}
		
		if ((searchType == 2) || (searchType == 3))
		{
			if ((searchValue == null) || (searchValue.equals("")))
			{
				searchValue = "NONE";
			}
			else
			{
				searchValue = searchValue.toUpperCase();
			}
		}
		
		if (programGroupId == null)
		{
			programGroup = "NONE";
		}
		else
		{
			programGroup = programGroupId.toString();
		}
				
		logger.trace("\n registrationOption: "+ registrationOption +" | srhType: "+ searchType 
				+" | dataListFlag: "+ dataListFlag +" | PEUEAllowStatus: "+ PEUEAllowStatus);	
		logger.trace("\n campusCode: "+ campusCode +" | programGroup: "+ programGroup 
			+" | semesterSubId: "+ semesterSubId +" | registerNumber: "+ registerNumber
			+" | searchValue: "+ searchValue +" | evalPage: "+ evalPage 
			+" | evalPageSize: "+ evalPageSize +" | programSpecId: "+ programSpecId 
			+" | admissionYear: "+ admissionYear +" | curriculumVersion: "+ curriculumVersion 
			+" | registrationMethod: "+ registrationMethod +" | costCentreCode: "+ costCentreCode 
			+" | programGroupCode: "+ programGroupCode +" | programSpecCode: "+ programSpecCode);
		
		logger.trace("\n courseSystem: "+ courseSystem);
		logger.trace("\n egbGroupId: "+ egbGroupId);
		logger.trace("\n classGroupId: "+ classGroupId);
		logger.trace("\n classType: "+ classType);
		logger.trace("\n registerNumber2: "+ registerNumber2);
		
		if (dataListFlag == 1)
		{
			//System.out.println("**********registrationOption**********::::" + registrationOption);
			switch(registrationOption)
			{
				case "COMP":
					//System.out.println("programGroupId"+programGroupId);
					//System.out.println("admissionYear"+admissionYear);
					//System.out.println("studentGraduateYear"+studentGraduateYear);
					//System.out.println("registerNumber"+registerNumber);
					//System.out.println("programSpecCode"+programSpecCode);
					//System.out.println("programGroupCode"+programGroupCode);
					//System.out.println("semesterSubId"+semesterSubId);
					
					//courseCode =compulsoryCourseConditionDetailRepository.findSoftSkillCourseList(semesterSubId, programGroupId, admissionYear); 
				
					if (programGroupCode.equals("RP"))
					{
						tempList = courseCatalogRepository.findCompulsoryCourse(campusCode, courseSystem, egbGroupId, 
											programGroup, semesterSubId, classGroupId, classType, compulsoryCourseList);
					}
					else
					{
						
						tempList = courseCatalogRepository.findCompulsoryCourseByClassOption(campusCode, courseSystem, 
										egbGroupId, programGroup, semesterSubId, classGroupId, classType, compulsoryCourseList, 
										programGroupCode, programSpecCode, costCentreCode);
					}
					break;
					
				case "UE":
					switch(searchType)
					{
						case 1:
							if (programGroupCode.equals("RP"))
							{
								tempList = courseCatalogRepository.findCurriculumUEByCourseCode(campusCode, courseSystem, 
												egbGroupId, programGroup, semesterSubId, classGroupId, classType, 
												programSpecId, admissionYear, curriculumVersion, searchValue);
							}
							else
							{
								tempList = courseCatalogRepository.findCurriculumUEByCourseCodeAndClassOption(campusCode, 
												courseSystem, egbGroupId, programGroup, semesterSubId, classGroupId, 
												classType, programSpecId, admissionYear, curriculumVersion, searchValue, 
												programGroupCode, programSpecCode, costCentreCode);
							}
							break;
						default:
							if (programGroupCode.equals("RP"))
							{
								tempList = courseCatalogRepository.findCurriculumUE(campusCode, courseSystem, egbGroupId, 
												programGroup, semesterSubId, classGroupId, classType, programSpecId, 
												admissionYear, curriculumVersion);
							}
							else
							{
								tempList = courseCatalogRepository.findCurriculumUEByClassOption(campusCode, courseSystem, 
												egbGroupId, programGroup, semesterSubId, classGroupId, classType, 
												programSpecId, admissionYear, curriculumVersion, programGroupCode, 
												programSpecCode, costCentreCode);
							}
							break;
					}
					break;
					
				case "RGR":
					if ((programGroupCode.equals("RP")) && (admissionYear >= 2018))
					{
						courseCode = studentHistoryService.getRPCourseWorkByRegisterNumber(registerNumber);
						tempList = courseCatalogRepository.findRegularCourseByCourseCodeForResearch(campusCode, 
										courseSystem, egbGroupId, programGroup, semesterSubId, classGroupId, classType, 
										registerNumber2, courseCode, naGenericCourseType);
					}
					else
					{
						switch(searchType)
						{
							case 1:
								if (programGroupCode.equals("RP"))
								{
									tempList = courseCatalogRepository.findRegularCourseByCourseCode(campusCode, 
													courseSystem, egbGroupId, programGroup, semesterSubId, classGroupId, 
													classType, registerNumber2, searchValue, naGenericCourseType);
								}
								else
								{
									tempList = courseCatalogRepository.findRegularCourseByCourseCodeAndClassOption(
													campusCode, courseSystem, egbGroupId, programGroup, semesterSubId, 
													classGroupId, classType, registerNumber2, searchValue, 
													programGroupCode, programSpecCode, costCentreCode, naGenericCourseType);
								}
								break;
							default:
								if (programGroupCode.equals("RP"))
								{
									tempList = courseCatalogRepository.findRegularCourse(campusCode, courseSystem,
													egbGroupId, programGroup, semesterSubId, classGroupId, classType, 
													registerNumber2, naGenericCourseType);
								}
								else
								{
									tempList = courseCatalogRepository.findRegularCourseByClassOption(campusCode, 
													courseSystem, egbGroupId, programGroup, semesterSubId, classGroupId, 
													classType, registerNumber2, programGroupCode, programSpecCode, 
													costCentreCode, naGenericCourseType);
								}
								break;
						}
					}
					break;
					
				case "RR":
					switch(searchType)
					{
						case 1:
							if (programGroupCode.equals("RP"))
							{
								tempList = courseCatalogRepository.findRRCourseByCourseCode(campusCode, courseSystem, 
												egbGroupId, programGroup, semesterSubId, classGroupId, classType, 
												registerNumber2, searchValue);
							}
							else
							{
								tempList = courseCatalogRepository.findRRCourseByCourseCodeAndClassOption(campusCode, 
												courseSystem, egbGroupId, programGroup, semesterSubId, classGroupId, 
												classType, registerNumber2, searchValue, programGroupCode, programSpecCode, 
												costCentreCode);
							}
							break;
						default:
							if (programGroupCode.equals("RP"))
							{
								tempList = courseCatalogRepository.findRRCourse(campusCode, courseSystem, egbGroupId, 
												programGroup, semesterSubId, classGroupId, classType, registerNumber2);
							}
							else
							{
								tempList = courseCatalogRepository.findRRCourseByClassOption(campusCode, courseSystem, 
												egbGroupId, programGroup, semesterSubId, classGroupId, classType, 
												registerNumber2, programGroupCode, programSpecCode, costCentreCode);
							}
							break;
					}
					break;
									
				case "FFCSCAL":
					switch(searchType)
					{
						case 1:
							if (programGroupCode.equals("RP"))
							{
								tempList = courseCatalogRepository.findCALToFFCSCEByCourseCode(campusCode, egbGroupId, 
												programGroup, semesterSubId, classGroupId, classType, searchValue);
							}
							else
							{
								tempList = courseCatalogRepository.findCALToFFCSCEByCourseCodeAndClassOption(campusCode, 
												egbGroupId, programGroup, semesterSubId, classGroupId, classType, 
												searchValue, programGroupCode, programSpecCode, costCentreCode);
							}
							break;
						default:
							if (programGroupCode.equals("RP"))
							{
								tempList = courseCatalogRepository.findCALToFFCSCE(campusCode, egbGroupId, programGroup,
												semesterSubId, classGroupId, classType);
							}
							else
							{
								tempList = courseCatalogRepository.findCALToFFCSCEByClassOption(campusCode, egbGroupId, 
												programGroup, semesterSubId, classGroupId, classType, programGroupCode, 
												programSpecCode, costCentreCode);
							}
							break;
					}
					break;
					
				default:
					switch(searchType)
					{
						case 1:
							if (programGroupCode.equals("RP"))
							{
								tempList = courseCatalogRepository.findCurriculumPCPEUCByCourseCode(
												campusCode, courseSystem, egbGroupId, programGroup, semesterSubId, 
												classGroupId, classType, programSpecId, admissionYear, registrationOption, 
												curriculumVersion, searchValue, naGenericCourseType);
							}
							else
							{
								tempList = courseCatalogRepository.findCurriculumPCPEUCByCourseCodeAndClassOption(
												campusCode, courseSystem, egbGroupId, programGroup, semesterSubId, 
												classGroupId, classType, programSpecId, admissionYear, registrationOption, 
												curriculumVersion, searchValue, programGroupCode, programSpecCode, 
												costCentreCode, naGenericCourseType);
							}
							break;
						default:
							if (programGroupCode.equals("RP"))
							{
								tempList = courseCatalogRepository.findCurriculumPCPEUC(campusCode, courseSystem, 
												egbGroupId, programGroup, semesterSubId, classGroupId, classType, 
												programSpecId, admissionYear, registrationOption, curriculumVersion, 
												naGenericCourseType);
							}
							else
							{
								tempList = courseCatalogRepository.findCurriculumPCPEUCByClassOption(campusCode, 
												courseSystem, egbGroupId, programGroup, semesterSubId, classGroupId, 
												classType, programSpecId, admissionYear, registrationOption, 
												curriculumVersion, programGroupCode, programSpecCode, costCentreCode, 
												naGenericCourseType);
							}
							break;
					}
					break;
			}
		}
		
		return tempList;
	}
	
	public String getTotalPageAndIndex(int dataSize, int pageSize, int pageNumber)
	{
		int totalPage = 0, fromIndex = 0, toIndex = 0;
		double calcTotalPage = 0;
		
		if (pageSize > 0)
		{
			calcTotalPage = (double)dataSize / (double)pageSize;
			totalPage = (int) Math.ceil(calcTotalPage);
		}
		
		if (pageNumber <= 0)
		{
			pageNumber = 0;
		}
		else if (pageNumber >= totalPage)
		{
			pageNumber = totalPage - 1;
		}
				
		if (totalPage > 0)
		{
			fromIndex = pageNumber * pageSize;
			toIndex = fromIndex + pageSize;
			if (toIndex > dataSize)
			{
				toIndex = dataSize;
			}
		}
						
		return totalPage +"|"+ fromIndex +"|"+ toIndex;
	}
}
