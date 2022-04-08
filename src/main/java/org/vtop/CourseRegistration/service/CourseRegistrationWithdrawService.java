package org.vtop.CourseRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vtop.CourseRegistration.repository.CourseRegistrationWithdrawRepository;


@Service
@Transactional("transactionManager")
public class CourseRegistrationWithdrawService
{	
	@Autowired private CourseRegistrationWithdrawRepository courseRegistrationWithdrawRepository;

		
	public List<Object[]> getByRegisterNumberAndCourseCode2(List<String> registerNumber, String courseCode)
	{
		return courseRegistrationWithdrawRepository.findByRegisterNumberAndCourseCode2(registerNumber, courseCode);
	}
	
	
	//Course Withdraw/ Modify/ Delete OTP
	public List<Object[]> getCourseWithdrawOTP(String semesterSubId, String registerNumber, String courseId, 
								Integer otpReasonType)
	{
		return courseRegistrationWithdrawRepository.findCourseWithdrawOTP(semesterSubId, registerNumber, courseId, 
					otpReasonType);
	}
	
	public void addCourseWithdrawOTP(String semesterSubId, String registerNumber, String courseId, 
						Integer otpReasonType, String mailOTP, String mobileOTP, String userId, String ipAddress)
	{
		courseRegistrationWithdrawRepository.insertCourseWithdrawOTP(semesterSubId, registerNumber, courseId, 
				otpReasonType, mailOTP, mobileOTP, userId, ipAddress);
	}
	
	public void modifyCourseWithdrawOTP(String semesterSubId, String registerNumber, String courseId, 
					Integer otpReasonType, String mailOTP, String mobileOTP, String userId, String ipAddress)
	{
		courseRegistrationWithdrawRepository.insertCRWOTPBackup(semesterSubId, registerNumber, courseId, 
				otpReasonType, userId, ipAddress);
		
		courseRegistrationWithdrawRepository.updateCourseWithdrawOTP(semesterSubId, registerNumber, courseId, 
				otpReasonType, mailOTP, mobileOTP, userId, ipAddress);
	}
	
	public void modifyCourseWithdrawOTPResponse(String semesterSubId, String registerNumber, String courseId, 
					Integer otpReasonType, String mobileOTPResponse)
	{
		courseRegistrationWithdrawRepository.updateCourseWithdrawOTPResponse(semesterSubId, registerNumber, 
				courseId, otpReasonType, mobileOTPResponse);
	}	
	
	public void modifyWithdrawOTPConfirmationStatus(String semesterSubId, String registerNumber, String courseId, 
					Integer otpReasonType, int mailOTPStatus, int mobileOTPStatus, String userId, String ipAddress)
	{
		courseRegistrationWithdrawRepository.updateWithdrawOTPConfirmationStatus(semesterSubId, registerNumber, 
				courseId, otpReasonType, mailOTPStatus, mobileOTPStatus, userId, ipAddress);
	}
	
	/*public List<CourseRegistrationWithdrawModel> getByRegisterNumberAndClassGroup(String semesterSubId, String registerNumber, 
															String[] classGroupId)
	{
		return courseRegistrationWithdrawRepository.findByRegisterNumberAndClassGroup(semesterSubId, registerNumber, 
					classGroupId);
	}
	
	public String getStudentPrvSemRegCourseWdCheck(String[] semesterSubId, String registerNumber, String courseCode)
	{
		return courseRegistrationWithdrawRepository.findStudentPrvSemRegCourseWdCheck(semesterSubId, registerNumber, 
					courseCode);
	}
	
	public String getCourseWithdrawByRegisterNumberAndCourseCode(List<String> registerNumber, String courseCode)
	{
		return courseRegistrationWithdrawRepository.findCourseWithdrawByRegisterNumberAndCourseCode(registerNumber, 
					courseCode);
	}
	
	public List<Object[]> getWithdrawCourseByRegisterNumberAndCourseCode(List<String> registerNumber, String courseCode)
	{
		return courseRegistrationWithdrawRepository.findWithdrawCourseByRegisterNumberAndCourseCode(registerNumber, 
					courseCode);
	}*/
}
