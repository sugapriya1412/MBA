package org.vtop.CourseRegistration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.model.CourseCatalogModel;
import org.vtop.CourseRegistration.repository.CourseCatalogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



@Service
@Transactional("transactionManager")
public class CourseCatalogService
{
	@Autowired private CourseCatalogRepository courseCatalogRepository;
	@Autowired private CourseRegistrationCommonFunction courseRegistrationCommonFunction;
	@Autowired private StudentHistoryService studentHistoryService;
		
		
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
										int PEUEAllowStatus, int evalPage, int evalPageSize, String costCentreCode)
	{
		List<CourseCatalogModel> tempList = new ArrayList<CourseCatalogModel>();
				
		int dataListFlag = 2;
		String programGroup = "";
		List<String> courseCode = new ArrayList<String>();
		List<String> naGenericCourseType = new ArrayList<String>();
		
		//Not allowed Generic Course Type except Compulsory Course
		//naGenericCourseType = Arrays.asList("SS", "ECA", "OC");
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
				//searchValue = searchValue.toUpperCase() + "%";
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
		
		
		//System.out.println("registrationOption: "+ registrationOption +" | srhType: "+ searchType 
		//		+" | dataListFlag: "+ dataListFlag +" | PEUEAllowStatus: "+ PEUEAllowStatus);	
		
		//System.out.println("campusCode: "+ campusCode +" | programGroup: "+ programGroup 
		//	+" | semesterSubId: "+ semesterSubId +" | registerNumber: "+ registerNumber
		//	+" | searchValue: "+ searchValue +" | evalPage: "+ evalPage 
		//	+" | evalPageSize: "+ evalPageSize +" | programSpecId: "+ programSpecId 
		//	+" | admissionYear: "+ admissionYear +" | curriculumVersion: "+ curriculumVersion 
		//	+" | registrationMethod: "+ registrationMethod +" | costCentreCode: "+ costCentreCode 
		//	+" | programGroupCode: "+ programGroupCode +" | programSpecCode: "+ programSpecCode);
		
		//Course System
		//System.out.println("courseSystem: ");
		//for(int i=0; i<courseSystem.length; i++)
		//{
		//	System.out.println("Course System: "+ courseSystem[i]);
		//}
		
		//Eligible Program Group Id
		//System.out.println("egbGroupId: ");
		//for(Integer e: egbGroupId)
		//{
		//	System.out.println("Eligible Program Group Id: "+ e);
		//}
		
		//Class Group Id
		//System.out.println("classGroupId: ");
		//for(int i=0; i<classGroupId.length; i++)
		//{
		//	System.out.println("Class Group Id: "+ classGroupId[i]);
		//}
		
		//Class Type
		//System.out.println("classType: ");
		//for(int i=0; i<classType.length; i++)
		//{
		//	System.out.println("Class Type: "+ classType[i]);
		//}
		
		//Register Number List 2
		//System.out.println("registerNumber2: ");
		//for(int i=0; i<registerNumber2.length; i++)
		//{
		//	System.out.println("Register No.: "+ registerNumber2[i]);
		//}
		
		
		if (dataListFlag == 1)
		{
			switch(registrationOption)
			{						
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

				case "SS":
					//courseCode = courseRegistrationCommonFunction.SoftSkillCourseCheck(programGroupId, admissionYear, 
					//				studentGraduateYear, registerNumber, programSpecCode, programGroupCode);
					courseCode = courseRegistrationCommonFunction.SoftSkillCourseCheck(programGroupId, admissionYear, 
									studentGraduateYear, registerNumber, programSpecCode, programGroupCode, semesterSubId);
						
					if (registrationMethod.equals("CAL"))
					{
						if (programGroupCode.equals("RP"))
						{
							tempList = courseCatalogRepository.findCALSoftSkillCourse(campusCode, courseSystem, 
											egbGroupId, programGroup, semesterSubId, classGroupId, classType, courseCode);
						}
						else
						{
							tempList = courseCatalogRepository.findCALSoftSkillCourseByClassOption(campusCode, 
											courseSystem, egbGroupId, programGroup, semesterSubId, classGroupId, 
											classType, courseCode, programGroupCode, programSpecCode, costCentreCode);
						}
					}
					else if (!registrationMethod.equals("CAL"))
					{
						if (programGroupCode.equals("RP"))
						{
							tempList = courseCatalogRepository.findFFCSSoftSkillCourse(campusCode, egbGroupId,
											programGroup, semesterSubId, classGroupId, classType, courseCode);
						}
						else
						{
							tempList = courseCatalogRepository.findFFCSSoftSkillCourseByClassOption(campusCode, 
											egbGroupId, programGroup, semesterSubId, classGroupId, classType, 
											courseCode, programGroupCode, programSpecCode, costCentreCode);
						}
					}
					break;
					
				//case "PC":
				//case "PE":
				//case "UC":
				//case "BC":
				//case "NC":
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
	
	
	/*public List<CourseCatalogModel> getByCourseCode(String searchVal)
	{
		return courseCatalogRepository.findByCourseCode(searchVal);
	}
		
	public CourseCatalogModel getByCourseCodeAndVersion(String code, float courseVersion)
	{
		return courseCatalogRepository.findByCourseCodeAndVersion(code, courseVersion);
	}
		
	public List<CourseCatalogModel> getRegistrationCourseList(String campusCode, String[] courseSystem, 
			List<Integer> egbGroupId, String groupCode)
	{
		return courseCatalogRepository.findRegistrationCourseList(campusCode, courseSystem, 
				egbGroupId, groupCode);
	}*/
		
	/*public boolean isExist(String courseId)
	{
		return courseCatalogRepository.exists(courseId);
	}*/
}
