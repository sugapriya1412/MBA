package org.vtop.CourseRegistration.mongo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vtop.CourseRegistration.model.StudentHistoryModel;
import org.vtop.CourseRegistration.mongo.model.CourseEligible;
import org.vtop.CourseRegistration.mongo.model.ProgramSpecializationCurriculumCategoryCredit;
import org.vtop.CourseRegistration.mongo.model.ProgramSpecializationCurriculumCredit;
import org.vtop.CourseRegistration.mongo.model.ProgramSpecializationCurriculumDetail;
import org.vtop.CourseRegistration.mongo.model.RegistrationSchedule;
import org.vtop.CourseRegistration.mongo.model.SemesterDetail;
import org.vtop.CourseRegistration.mongo.model.StudentDetail;
import org.vtop.CourseRegistration.mongo.model.StudentDetailOthers;
import org.vtop.CourseRegistration.mongo.model.StudentHistory;
import org.vtop.CourseRegistration.mongo.repository.CourseEligibleMongoRepository;
import org.vtop.CourseRegistration.mongo.repository.ProgramSpecializationCurriculumCategoryCreditMongoRepository;
import org.vtop.CourseRegistration.mongo.repository.ProgramSpecializationCurriculumCreditMongoRepository;
import org.vtop.CourseRegistration.mongo.repository.ProgramSpecializationCurriculumDetailMongoRepository;
import org.vtop.CourseRegistration.mongo.repository.RegistrationScheduleMongoRepository;
import org.vtop.CourseRegistration.mongo.repository.SemesterDetailMongoRepository;
import org.vtop.CourseRegistration.mongo.repository.StudentDetailMongoRepository;
import org.vtop.CourseRegistration.mongo.repository.StudentDetailOthersMongoRepository;
import org.vtop.CourseRegistration.mongo.repository.StudentHistoryMongoRepository;


@Service
public class CourseRegistrationCommonMongoService
{
	@Autowired private StudentDetailMongoRepository studentDetailMongoRepository;
	@Autowired private SemesterDetailMongoRepository semesterDetailMongoRepository;
	@Autowired private StudentDetailOthersMongoRepository studentDetailOthersMongoRepository;
	@Autowired private CourseEligibleMongoRepository courseEligibleMongoRepository;
	@Autowired private ProgramSpecializationCurriculumCreditMongoRepository programSpecializationCurriculumCreditMongoRepository;
	@Autowired private ProgramSpecializationCurriculumDetailMongoRepository programSpecializationCurriculumDetailMongoRepository;
	@Autowired private StudentHistoryMongoRepository studentHistoryMongoRepository;
	@Autowired private ProgramSpecializationCurriculumCategoryCreditMongoRepository programSpecializationCurriculumCategoryCreditMongoRepository;
	@Autowired private RegistrationScheduleMongoRepository registrationScheduleMongoRepository;
	
	private static final Logger logger = LogManager.getLogger(CourseRegistrationCommonMongoService.class);
		
	
	//Student Detail
	public StudentDetail getStudentDetailByRegisterNumber(String registerNumber)
	{
		return studentDetailMongoRepository.findByRegisterNumber(registerNumber); 
	}
	
	public StudentDetail getStudentDetailByNickName(String nickName)
	{
		return studentDetailMongoRepository.findByNickName(nickName); 
	}
	
	
	//Semester Detail
	public SemesterDetail getSemesterDetailBySemesterSubId(String semesterSubId)
	{
		return semesterDetailMongoRepository.findBySemesterSubId(semesterSubId); 
	}
	
	
	//Student Detail Others
	public StudentDetailOthers getStudentDetailOthersByRegisterNumber(String registerNumber)
	{
		return studentDetailOthersMongoRepository.findByRegisterNumber(registerNumber); 
	}
	
	public void updateCompulsoryCourseByRegisterNumber(String registerNumber, List<String> courseList)
	{
		if ((registerNumber != null) && (!registerNumber.equals("")) && (!courseList.isEmpty()))
		{
			String compulsoryCourse = "";		
			StudentDetailOthers updateStudentDetailOthers = null;	
			
			updateStudentDetailOthers = studentDetailOthersMongoRepository.findByRegisterNumber(registerNumber);
			if (updateStudentDetailOthers != null)
			{
				for (String courseCode : courseList)
				{
					if (compulsoryCourse.equals(""))
					{
						compulsoryCourse = courseCode;
					}
					else
					{
						compulsoryCourse = compulsoryCourse +"|"+ courseCode;
					}
				}
				
				updateStudentDetailOthers.setCompulsoryCourse(compulsoryCourse);
				
				studentDetailOthersMongoRepository.save(updateStudentDetailOthers);
			}
		}
	}
	
	
	//Registration Schedule
	public RegistrationSchedule getRegistrationScheduleByRegisterNumber(String registerNumber)
	{
		return registrationScheduleMongoRepository.findByRegisterNumber(registerNumber); 
	}
	
	
	//Course Eligible
	public CourseEligible getCourseEligibleByProgGroupId(int progGroupId)
	{
		return courseEligibleMongoRepository.findByProgGroupId(progGroupId); 
	}
	
	
	//Program Specialization Curriculum Credit
	public ProgramSpecializationCurriculumCredit getPrgSpecCurriculumCreditBySpecIdAndAdmissionYear(int specializationId, 
														int	admissionYear)
	{
		return programSpecializationCurriculumCreditMongoRepository.findBySpecIdAndAdmissionYear(specializationId, admissionYear); 
	}
	
	
	//Program Specialization Curriculum Detail
	public ProgramSpecializationCurriculumDetail getBySpecIdAdmissionYearAndCourseCode(int specializationId, int admissionYear, 
													String courseCode)
	{
		return programSpecializationCurriculumDetailMongoRepository.findBySpecIdAdmissionYearAndCourseCode(specializationId, 
					admissionYear, courseCode); 
	}
	
	public List<ProgramSpecializationCurriculumDetail> getBySpecIdAndAdmissionYear(int specializationId, int admissionYear)
	{
		return programSpecializationCurriculumDetailMongoRepository.findBySpecIdAndAdmissionYear(specializationId, admissionYear); 
	}
	
	public List<ProgramSpecializationCurriculumDetail> getBySpecIdAdmissionYearAndCourseCategory(int specializationId, 
																int admissionYear, String courseCategory)
	{
		return programSpecializationCurriculumDetailMongoRepository.findBySpecIdAdmissionYearAndCourseCategory(specializationId, 
					admissionYear, courseCategory); 
	}
	
	
	//Student History
	public void processStudentHistoryByRegisterNumber(String registerNumber, List<StudentHistoryModel> StudentHistoryList)
	{
		try
		{	
			if ((registerNumber != null) && (!registerNumber.equals("")) && (!StudentHistoryList.isEmpty()))
			{
				StudentHistory studentHistory = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");	
				
				studentHistoryMongoRepository.deleteByRegisterNumber(registerNumber);
								
				for (StudentHistoryModel e : StudentHistoryList)
				{
					studentHistory = new StudentHistory();
					
					studentHistory.setRegisterNumber(e.getStudentHistoryPKId().getRegisterNumber());
					studentHistory.setCourseId(e.getStudentHistoryPKId().getCourseId());
					studentHistory.setCourseCode(e.getCourseCode());
					studentHistory.setCourseTitle(e.getCourseTitle());
					studentHistory.setCourseType(e.getStudentHistoryPKId().getCourseType());
					studentHistory.setCourseOptionCode(e.getCourseOptionCode());
					studentHistory.setGrade(e.getGrade());
					studentHistory.setCredit(e.getCredit());
					studentHistory.setResultDate(sdf.format(e.getResultDate()));
					studentHistory.setExamMonth(sdf.format(e.getExamMonth()));
					
					studentHistoryMongoRepository.save(studentHistory);
				}
			}
		}
		catch (Exception exception)
		{
			logger.trace("Exception: "+ exception);
		}
	}
	
	
	//Registration Course Option
	public List<Object[]> getRegistrationOption(String programGroupCode, String courseSystem, int rgrCourseAllowStatus, 
								int reRegCourseAllowStatus, int peueAllowStatus, Integer specializationId, Integer admissionYear, 
								Float curriculumVersion, int compulsoryCourseStatus)
	{
		List<Object[]> returnObjectList = new ArrayList<Object[]>();
		List<ProgramSpecializationCurriculumCategoryCredit> pscccList = new ArrayList<>();
		logger.trace("\n programGroupCode: "+ programGroupCode +" | courseSystem: "+ courseSystem
				 +" | rgrCourseAllowStatus: "+ rgrCourseAllowStatus +" | reRegCourseAllowStatus: "+ reRegCourseAllowStatus 
				 +" | peueAllowStatus: "+ peueAllowStatus +" | specializationId: "+ specializationId 
				 +" | admissionYear: "+ admissionYear +" | curriculumVersion: "+ curriculumVersion);
		
		if ((programGroupCode == null) || programGroupCode.equals(""))
		{
			programGroupCode = "NONE";
		}
		
		if ((courseSystem == null) || courseSystem.equals(""))
		{
			courseSystem = "NONE";
		}
		
		if (specializationId == null)
		{
			specializationId = 0;
		}
		
		if (admissionYear == null)
		{
			admissionYear = 0;
		}
		
		if (curriculumVersion == null)
		{
			curriculumVersion = 0F;
		}
		
		
		if ((!programGroupCode.equals("NONE")) && (programGroupCode.equals("RP") || programGroupCode.equals("IEP")))
		{
			returnObjectList.add(new Object[] {"RGR", "Regular"});
			if (reRegCourseAllowStatus == 1)
			{
				returnObjectList.add(new Object[] {"RR", "Re - Registration"});
			}
		}
		else if ((!programGroupCode.equals("NONE")) && (!courseSystem.equals("NONE")) 
						&& (courseSystem.equals("NONFFCS") || courseSystem.equals("FFCS")))
		{
			if (rgrCourseAllowStatus == 1)
			{
				returnObjectList.add(new Object[] {"RGR", "Regular"});
			}
			returnObjectList.add(new Object[] {"FFCSCAL", "FFCS to CAL Course Equivalence"});
			if (reRegCourseAllowStatus == 1)
			{
				returnObjectList.add(new Object[] {"RR", "Re - Registration"});
			}
		}
		else if ((!programGroupCode.equals("NONE")) && (!courseSystem.equals("NONE")) 
						&& (!courseSystem.equals("NONFFCS")) && (!courseSystem.equals("FFCS")))
		{
			if (compulsoryCourseStatus == 1)
			{
				returnObjectList.add(new Object[] {"COMP", "Compulsory Course"});
			}
			
			if (rgrCourseAllowStatus == 1)
			{
				pscccList = programSpecializationCurriculumCategoryCreditMongoRepository.findBySpecIdAndAdmissionYear(specializationId, 
								admissionYear);
				if (!pscccList.isEmpty())
				{
					for (ProgramSpecializationCurriculumCategoryCredit e : pscccList)
					{
						if (peueAllowStatus == 1)
						{
							returnObjectList.add(new Object[] {e.getCourseCategory(), e.getCourseCategoryDescription()});
						}
						else if ((peueAllowStatus == 2) && (e.getCourseCategory().equals("PC") || e.getCourseCategory().equals("UC")))
						{
							returnObjectList.add(new Object[] {e.getCourseCategory(), e.getCourseCategoryDescription()});
						}
					}
				}
			}
			
			if (reRegCourseAllowStatus == 1)
			{
				returnObjectList.add(new Object[] {"RR", "Re - Registration"});
			}
		}
		logger.trace("\n returnObjectList size: "+ returnObjectList.size());
		
		return returnObjectList;
	}
	
	//Registration Option Description
	public String getCourseOptionDescription(int specializationId, int admissionYear, String registrationOption)
	{
		String returnDescription = "";
		
		switch (registrationOption)
		{
			case "COMP":  returnDescription = "Compulsory Course(s)"; break;
			case "RGR":  returnDescription = "Regular Course(s)"; break;
			case "RR":  returnDescription = "Re-registration Course(s)"; break;
			case "FFCSCAL":  returnDescription = "FFCS to CAL Course Equivalence"; break;
			case "ExtraCur":  returnDescription = "Co-Extra Curricular Activity Courses(s)"; break;
			
			default:
				ProgramSpecializationCurriculumCategoryCredit programSpecializationCurriculumCategoryCredit 
						= programSpecializationCurriculumCategoryCreditMongoRepository.findBySpecIdAdmissionYearAndCategory
								(specializationId, admissionYear, registrationOption);
				if (programSpecializationCurriculumCategoryCredit != null)
				{
					returnDescription = programSpecializationCurriculumCategoryCredit.getCourseCategoryDescription();
				}
				break;
		}
				
		return returnDescription;
	}
}
